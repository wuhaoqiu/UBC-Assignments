function transformedImage=my_histeq(inputImage)
[r,c]=size(inputImage);
%create empty box waiting for result
transformedImage=uint8(zeros(r,c));
%total pixels
n=r*c;
%matlab matrx index from 1 indtead of 0, so for 
%graylevel image whode intensity level is from 
%0 to 255, we need to construct a matrix of size 256*1 
freqOfEachIntensity=zeros(256,1);
pdf=zeros(256,1);
cdf=zeros(256,1);
transformedResultForEachIntensity=zeros(256,1);
%traverse whole image,used to calculate the number of 
%each intensity in given image
for i=1:r
    for j=1:c
        value=inputImage(i,j);
        freqOfEachIntensity(value+1)=freqOfEachIntensity(value+1)+1;
        pdf(value+1)=freqOfEachIntensity(value+1)/n;
    end
end
%calculate the corresponding cdf and 
%transformed intentisy result
cdf(1)=pdf(1);
for i=2:size(pdf)
    cdf(i)=pdf(i)+cdf(i-1);
    transformedResultForEachIntensity(i)=round(cdf(i)*254);
end
%beucae 0 intensity corresponds to index 1 of matrix
%which means that the true intensity need plus 1 to find correct
%index of matrix, so add 1 to inputImage(r,c) to get the 
%correct index of transformedResultForEachIntensity matrix
for i=1:r
    for j=1:c
        transformedImage(i,j)=transformedResultForEachIntensity(inputImage(i,j)+1);
    end
end
