I1=imread('okanagan.jpg');
I1=rgb2gray(I1);
I1=I1.*(1-uint8(I1>175));
%I1(I1>175)=0;
imshow(I1),title('background sky removed');