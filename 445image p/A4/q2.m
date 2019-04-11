function result=q2(input_image)
%use imtool() functtion to roughly measure 
%size of items
i=im2bw((imread(input_image)))
%count the number of chickens, 38 is best
subplot(1,6,1),imshow(i),title('origin image')
se=strel('disk',38);
chicken=imopen(i,se);
[blobs n_of_chickens]=bwlabel(chicken);
subplot(1,6,2),imshow(chicken),title([num2mstr(n_of_chickens) ' chickens'])

i_without_chicken=i-chicken;
%count number of eggs, 30 is best
se=strel('disk',30);
eggs=imopen(i_without_chicken,se);
[blobs n_of_eggs]=bwlabel(eggs);
%extract features of eggs
features_of_eggs=regionprops(blobs);
subplot(1,6,3),imshow(eggs),title([num2mstr(n_of_eggs) ' eggs'])

i_without_chicken_egg=i-chicken-eggs
%count number of big circles, 20 is best
se=strel('disk',23)
large_circle=imopen(i_without_chicken_egg,se)
[blobs n_of_large_circle]=bwlabel(large_circle)
subplot(1,6,4),imshow(large_circle),title([num2mstr(n_of_large_circle) ' large circles'])

%remove chicken heads then count the number of small circles
i_without_chicken_egg_large_circle=i-chicken-eggs-large_circle
se=strel('disk',20)
chicken_head=imopen(i_without_chicken_egg_large_circle,se)
i_without_chicken_egg_large_circle_head=i-chicken-eggs-large_circle-chicken_head
se=strel('disk',18)
small_circle=imopen(i_without_chicken_egg_large_circle_head,se);
[blobs n_of_small_circle]=bwlabel(small_circle);
subplot(1,6,5),imshow(small_circle),title([num2mstr(n_of_small_circle) ' small circles']);

subplot(1,6,6),imshow(i),title('there are 11 larges circles, 10 small circles, 3 eggs, 2 chickens')
for i=1:n_of_eggs
    x0=features_of_eggs(i).Centroid(1);
    y0=features_of_eggs(i).Centroid(2);
    line([x0-5 x0+5],[y0 y0], 'Color','r');
    line([x0 x0],[y0-5 y0+5], 'Color','r');
    rectangle('Position',features_of_eggs(i).BoundingBox,'EdgeColor','r');
end