function result=q4(input_image)
%input_image='particles.png'
i=im2bw(imread(input_image));
%separte into independent blobs
[l,num]=bwlabel(i,8);
disp(['num of blobs is:',num2str(num)]);
[row_num_of_input column_num_of_input]=size(i);
%find connected components that are at edges
boundary_pickles=zeros(row_num_of_input,column_num_of_input);
for i=1:num
    [r c]=find(l==i);
    if ismember(row_num_of_input,r)==1 || ismember(column_num_of_input,c)==1 || ismember(1,r)==1 || ismember(1,c)==1
        boundary_pickles=boundary_pickles+(l==i);
    end
end
subplot(1,4,1),imshow(boundary_pickles),title('pixels merged with boundaries')
%find nonoverlapping conponents
nonoverlapping_pickles=zeros(row_num_of_input,column_num_of_input);
for i=1:num
    [r c]=find(l==i);
    if length(r)>230 && length(r)<=275
        nonoverlapping_pickles=nonoverlapping_pickles+(l==i);
    end
end
subplot(1,4,2),imshow(nonoverlapping_pickles),title('nonoverlapping')
%find overlapping components
overlapping_pickles=zeros(row_num_of_input,column_num_of_input);
for i=1:num
    [r c]=find(l==i);
    if length(r)>275
        overlapping_pickles=overlapping_pickles+(l==i);
    end
end
subplot(1,4,3),imshow(overlapping_pickles),title('overlapping')

%set overlapping pixels in overlapping and nonoverlapping to 0, otherwise
%if three layers have one pixels at the same position, color at that
%position will not be red anymore
for i=1:row_num_of_input
    for j=1:column_num_of_input
        if nonoverlapping_pickles(i,j)==boundary_pickles(i,j)
            nonoverlapping_pickles(i,j)=0;
        end
        if overlapping_pickles(i,j)==boundary_pickles(i,j)
            overlapping_pickles(i,j)=0;
        end
    end
end
final_colorful_image=cat(3,boundary_pickles,nonoverlapping_pickles,overlapping_pickles);
subplot(1,4,4),imshow(final_colorful_image),title('final colourful')

