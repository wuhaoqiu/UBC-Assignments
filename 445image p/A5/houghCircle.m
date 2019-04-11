function processedImage=houghCircle(inputImage)
% subplot(1,2,1),imshow(inputImage),title('origin image');
%using close to remove small circles
se=strel('disk',15);
inputImage2=imclose(inputImage,se);
%find circles on closed image
[centers,radii]=imfindcircles(inputImage2,[10 100],'ObjectPolarity','dark');
imshow(inputImage),title('r1=42.55 at (121.7444,211.568); r2=46.1909 at (199.5362,62.4807)');
%show circle on origin image
viscircles(centers,radii,'EdgeColor','r');