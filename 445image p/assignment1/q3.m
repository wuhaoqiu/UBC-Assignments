I3=imread('cameraman.tif');
sI3=size(I3);
%add one more pixel
I1((sI1(1)-sI3(1))+1:sI1(1),1:sI3(2))=I3(:,:),title('merge two images');
imshow(I1);