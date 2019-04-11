function transformedImage=intensityStretch(inputImage,r1,r2,s1,s2)
%extract min and max intensity value of input image
minOfInput=min(inputImage(:));
maxOfInput=max(inputImage(:));
%validate input
if (r1>=minOfInput && r2<=maxOfInput &&s1>=0&&s2<=255)
    %transformation function, calculate the slope of the linear function
    transformedImage=(inputImage-r1).*((s2-s1)/(r2-r1))+s1;
else
    %remind user when inputting wrong values
    disp('wrong input');
    transformedImage=0;
end