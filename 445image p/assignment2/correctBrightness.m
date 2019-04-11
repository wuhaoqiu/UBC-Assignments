function correctedImage=correctBrightness(inputImage,percentage,indicator)
%if indicator is 1, brighten
%is indicator is 0, darken
if indicator==1
    correctedImage=inputImage.*(1+percentage/100);
elseif indicator==0
    correctedImage=inputImage.*(1-percentage/100);
end





    
    
   