i1=imread('prespectiveCorrect.jpg');
i1=convertToGrayscaleImage(i1);
%bright the image
i2=correctBrightness(i1,100,1);
figure,imshow(i1),title('I1');
figure,imshow(i2),title('I2');

