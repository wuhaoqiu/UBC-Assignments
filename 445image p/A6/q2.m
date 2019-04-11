i=im2bw(imread('hands.png'));
[L,N]=bwlabel(~i,4);
props=regionprops(L,'all');
imshow(i);
for i=1:N
%     props(i).MinorAxisLength
%     props(i).Orientation
    if abs(props(i).Orientation)>=0 & abs(props(i).Orientation)<=10;
        rectangle('Position',props(i).BoundingBox,'EdgeColor','b');
        text('Position',props(i).Centroid,'string','circle','Color','b');
    end
    if abs(props(i).Orientation)>=30 & abs(props(i).Orientation)<=60;
        rectangle('Position',props(i).BoundingBox,'EdgeColor','r');
        text('Position',props(i).Centroid,'string','highfive','Color','r');
    end
    if abs(props(i).Orientation)>=65 & abs(props(i).Orientation)<=100;
        rectangle('Position',props(i).BoundingBox,'EdgeColor','g');
        text('Position',props(i).Centroid,'string','point','Color','g');
    end
end