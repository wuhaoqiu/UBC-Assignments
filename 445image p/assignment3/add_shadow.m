function afterAddShadow=add_shadow(inputImage,thredhold,blurAmount,distance)
%donot know how to move dstance
distance=5;
inputImage=imread('coins.png');
thredhold=90;
blurAmount=5;
backup=zeros(size(inputImage));
backup=inputImage;
%filter items and color them black
inputImage(inputImage>90)=0;
%filter background and color them white
inputImage(inputImage>0)=255;
%create blur matrix
smoother=fspecial('gaussian',5*blurAmount,blurAmount);
%store blurred image
smootherImage=imfilter(inputImage,smoother,'replicate');
sizeOfImage=size(smootherImage);

left=255*ones(sizeOfImage(1)+distance,distance);
up=255*ones(distance,sizeOfImage(2));
smootherImageWithUp=[up;smootherImage];
smootherImageComplete=[left,smootherImageWithUp];
smootherImageComplete=smootherImageComplete(1:sizeOfImage(1),1:sizeOfImage(2));
%store items on black background
itemOnBlackBackground=backup.*uint8(backup>90);
%create background
background=backup.*(1-(uint8(backup>90)));
%convert image range to 0~1
smootherImageComplete2=im2double(smootherImageComplete);

combination1=(smootherImageComplete2).*double(background);
combination2=uint8(double(combination1)+double(itemOnBlackBackground));
subplot(1,5,1),imshow(smootherImageComplete2),title('smootherImage');
subplot(1,5,2),imshow(itemOnBlackBackground),title('itemOnBlackBackground');
subplot(1,5,3),imshow(background),title('background');
subplot(1,5,4),imshow(uint8(combination1));
subplot(1,5,5),imshow(combination2),title('final figure');

