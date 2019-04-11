function convertedImage=convertToGrayscaleImage(rawImage)
%this function is used to check whether input image is grayscale,if not,
%convert it to gray scale,otherwise equal to 0;
if size(rawImage,3) == 3
    convertedImage=rgb2gray(rawImage);
elseif size(rawImage,3) == 1
    convertedImage=rawImage;
else
    convertedImage=0;
end