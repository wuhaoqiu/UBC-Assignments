function precessedImage=myCanny(inputImage,hthreshold,sigma)
%backup image
% inputImage=imread('gateway_arch.jpg');
% sigma=5;
% hthreshold=0.2;
inputImage=im2double(inputImage);
backup=inputImage;
%generate and apply gauss filter
gaussfilter=fspecial('gaussian',5*sigma,sigma);
smoothedImage=imfilter(inputImage,gaussfilter);
subplot(2,3,1),imshow(smoothedImage),title('smoothed image');
%build sobel operators
wx=[-1 -2 -1;0 0 0;1 2 1];
wy=[-1 0 1;-2 0 2;-1 0 1];
%calculate magnitude of image gradient
gx=imfilter(smoothedImage,wx);
gy=imfilter(smoothedImage,wy);
magnitude=(gx.^2+gy.^2).^0.5;
direction=rad2deg(atan2(gx,gy));
subplot(2,3,2),imshow(magnitude,[]),title('magnitude of gradient');
subplot(2,3,3),imshow(direction,[]),title('direction of gradient');
%figure,imshow(smoothedImage);
%assign angel of each pixel to its closest direction
angle_list=[0 45 90 135 180 -45 -90 -135];
[num_row num_col]=size(inputImage);
for i=1:num_row
    for j=1:num_col
        min=9999;
        for k=1:8
            distance=(direction(i,j)-angle_list(k))^2;
            if distance<min
                min=distance;
                closest_angle=angle_list(k);
                %disp([num2str(closest_angle),'and',num2str(distance)])
            end    
        end
         direction(i,j)=closest_angle;
    end
end
subplot(2,3,4),imshow(direction,[]),title('only 4 directions');
%non-maxima suppression
for i=2:num_row-1
    for j=2:num_col-1
        if direction(i,j)==45 ||direction(i,j)==-135
            if ~(magnitude(i,j)>magnitude(i+1,j-1)&&magnitude(i,j)>magnitude(i-1,j+1))
                magnitude(i,j)=0;
            end
        elseif direction(i,j)==90 ||direction(i,j)==-90
            if ~(magnitude(i,j)>magnitude(i+1,j)&&magnitude(i,j)>magnitude(i-1,j))
                magnitude(i,j)=0;
            end
        elseif direction(i,j)==135 ||direction(i,j)==-45
            if ~(magnitude(i,j)>magnitude(i-1,j-1)&&magnitude(i,j)>magnitude(i+1,j+1))
                magnitude(i,j)=0;
            end
        elseif direction(i,j)==180 ||direction(i,j)==0
            if ~(magnitude(i,j)>magnitude(i,j-1)&&magnitude(i,j)>magnitude(i,j+1))
                magnitude(i,j)=0;
            end
        end % end of if  
    end%end of inner for
end%end of outer for
subplot(2,3,5),imshow(magnitude,[]),title('non-maxmima supression');

magnitude(1,:)=0;
magnitude(:,1)=0;
magnitude(num_row,:)=0;
magnitude(:,num_col)=0;
%label strong edge, weak edge, and based on connectivity to determine
%whether weak edge is a real edge
maxIntensiy=max(magnitude(:));
high=maxIntensiy*hthreshold;
low=high*0.4;
maskHigh= magnitude>=high;
maskLow= magnitude<high&magnitude>=low;
label=zeros(num_row,num_col);
label(maskHigh)=2;
label(maskLow)=1;
for i=2:num_row-1
    for j=2:num_col-1
        if label(i,j)==2
%             magnitude(i,j)=255;
        elseif label(i,j)==1
            if label(i-1,j-1)==2 || label(i-1,j)==2 || label(i-1,j+1)==2 || label(i,j-1)==2 || label(i,j+1)==2 || label(i+1,j-1)==2 || label(i+1,j)==2 || label(i+1,j+1)==2 
%                magnitude(i,j)=255;
            else
                magnitude(i,j)=0;
            end
        else
            magnitude(i,j)=0;
        end
    end
end
magnitude(1:10,:)=0;
magnitude(:,1:10)=0;
magnitude(num_row-10:num_row,:)=0;
magnitude(:,num_col-10:num_col)=0;
subplot(2,3,6),imshow(magnitude,[]),title('final edge');
% figure,imtool(magnitude);

