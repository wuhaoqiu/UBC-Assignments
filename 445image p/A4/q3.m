function result=q3(input_image)
color_image=(imread(input_image));
binary_image=im2bw(color_image);
%clean picture after thresholding
se=strel('disk',15);
c=imclose(binary_image,se);
%separate items
se=strel('disk',70);
c_o=imerode(c,se);
subplot(1,6,1),imshow(c_o),title('cleaning after thresholding');
i=c_o;

%count number of 2-dollar coin
se=strel('disk',60);
dollar2=imopen(i,se);
[blobs n_of_2]=bwlabel(dollar2);
features_of_dollar2=regionprops(blobs);
subplot(1,6,2),imshow(dollar2),title([num2mstr(n_of_2) ' dollar2'])


i_without_dollar2=i-dollar2;
%count number of dollar1
se=strel('disk',30);
dollar1=imopen(i_without_dollar2,se);
[blobs n_of_1]=bwlabel(dollar1);
features_of_dollar1=regionprops(blobs);
subplot(1,6,3),imshow(dollar1),title([num2mstr(n_of_1) ' dollar1'])

i_without_dollar2_dollar1=i-dollar2-dollar1;
%count number of cent25
se=strel('disk',15);
cent25=imopen(i_without_dollar2_dollar1,se);
[blobs n_of_25]=bwlabel(cent25);
features_of_cent25=regionprops(blobs);
subplot(1,6,4),imshow(cent25),title([num2mstr(n_of_25) ' cent25']);

sum=2*n_of_2+n_of_1+(25*n_of_25/100);

subplot(1,6,5),imshow(color_image),title([num2str(sum) ' dollar in image'])