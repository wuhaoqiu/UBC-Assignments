%% read image
I = imread('rice.png');
subplot(1,3,1), imshow(I,[]);
%% remove background
background = imopen(I,strel('disk',25)); % remove local maxima
Ip = imsubtract(I,background); % this is called top-hat transform
subplot(1,3,2), imshow(Ip,[]);
%% remove noise (small white objects)
Ip = imopen(Ip,strel('disk',2)); % or median filter
%% display result
subplot(1,3,3), imshow(Ip,[]);