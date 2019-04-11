function result=q5(input_image)
%input_image=('445_descr.png')
i=imread(input_image);
subplot(1,6,1),imshow(i),title('raw input');
se=strel('disk',3);
background_without_white_words=imopen(i,se);
subplot(1,6,2),imshow(background_without_white_words);
se=strel('disk',10);
background_without_white_black_words=imclose(background_without_white_words,se);
subplot(1,6,3),imshow(background_without_white_black_words),title('background');

top_words=i-background_without_white_black_words;
top_words(220:715,:)=0;
%highlight all words, because after overserving, the intensity of words are
%usually larger than 1,so intense them to 255
top_words(top_words>1)=255;
subplot(1,6,4),imshow(top_words),title('top words');

%same idea as above,plus, use 255-i to conter black words to white wrods
bottom_words=(255-i)-(255-background_without_white_black_words);
bottom_words(1:250,:)=0;
bottom_words(bottom_words>4)=255;
se=strel('disk',1);
bottom_words=imdilate(bottom_words,se);
subplot(1,6,5),imshow(bottom_words),title('bottom words');

all_words=top_words+bottom_words;
subplot(1,6,6),imshow(255-all_words),title('all words');
imtool(255-all_words);



