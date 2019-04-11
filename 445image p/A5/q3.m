I = imread('gateway_arch.jpg');
subplot(1,3,1),imshow(I,[]);
E = edge(I, 'canny',0.3,'both',0.9);
subplot(1,3,2), imshow(E,[]);
% choose parabola sizes to try
C = 0.01:0.001:0.015;
c_length = numel(C);
[M,N] = size(I);
% Define accumulator array H(N,M,C) and initialize with zeros
H = zeros(M,N,c_length);
% vote to fill H
[y_edge, x_edge] = find(E); % get edge points
for i = 1:length(x_edge) % for all edge points
for c_idx=1:c_length % for all c
for a = 1:N
b = round(y_edge(i)-C(c_idx)*(x_edge(i)-a)^2);
if(b < M && b >= 1) H(b,a,c_idx)=H(b,a,c_idx)+1; end
end
end
end
% show only third slice of H
%figure, imshow(H(:,:,3),[]);
%title(sprintf('Slice of H for r = %f', C(3)));
% we need code here to find the peaks and draw the parabolas
%traverse all positions to find max
[x,y,z]=size(H);
max=0;
for i=1:x
    for j=1:y
        for k=1:z
            if H(i,j,k)>max
                max=H(i,j,k)
                b=i
                a=j
                c=k
            end
        end
    end
end

%record positions of parabola
for x=1:N
    y=round(C(c)*(x-a)^2+b);
    position=[x,y];
    if x==1
        positions=position;
    else
        positions=cat(1,positions,position);
    end
end  
%draw parabola
i2=insertMarker(I,positions,'s','color','red');
subplot(1,3,3),imshow(i2);