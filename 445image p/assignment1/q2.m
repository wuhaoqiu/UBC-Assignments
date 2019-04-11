I1=imread('okanagan.jpg');
imshow(I1);
figure,imshow(I1(:,:,1)),title('red');
figure,imshow(I1(:,:,2)),title('green');
figure,imshow(I1(:,:,3)),title('blue');
I1=rgb2gray(I1);
figure,imshow(I1),title('I1');
sI1=size(I1);
disp(['the image i1 has ',num2str(sI1(1)),'rows and ',num2str(sI1(2)),'column']);
%class of I1 is uint8
disp(['gray level range in I1 is from ',num2str(max(I1(:))),' to ',num2str(min(I1(:))),'column']);
I2=I1.*4;
disp(['gray level range in I2 is from ',num2str(max(I2(:))),' to ',num2str(min(I2(:))),'column']);




