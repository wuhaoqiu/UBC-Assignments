%this is a kind of geometric transformation of image, like projective
%transformation by using four control points

i1=imread('prespectiveCorrect.jpg');
i1=convertToGrayscaleImage(i1);
figure,imshow(i1),title('input image');
c1=ginput(1);
i2=insertMarker(i1,c1,'s','color','red');
c2=ginput(1);
i2=insertMarker(i2,c2,'s','color','red');
c3=ginput(1);
i2=insertMarker(i2,c3,'s','color','red');
c4=ginput(1);
i2=insertMarker(i2,c4,'s','color','red');
figure,imshow(i2);
%initial points
movingPoints=[c1;c2; c3; c4];
%let c1 and c2 at same horizontal line with same height
c2(2)=c1(2);
%let c1 and c3 at the same vertical line
c3(1)=c1(1);
%let c4 and c3 at same horizontal line
c4(2)=c3(2);
%let c4 and c2 at the same vertical line
c4(1)=c2(1);
%expected result of points
fixedPoints=[c1;c2;c3;c4];
%define required geometirc transfrom by providing four corresponding points
tform=fitgeotrans(movingPoints,fixedPoints,'projective');
%apply defined geometirc transform-tform to this image
fixedImage=imwarp(i1,tform);

figure,imshow(fixedImage),title('fixedImage');




