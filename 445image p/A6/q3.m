i=imread('cameraman.tif');
figure(1),getcorners(i,'h',1,0.3,0.004),title('harris');
figure(2),getcorners(i,'s',1,0.3,0.004),title('szeleski');

% corner points become less as the alpha increases
count=3;
for alpha=0.004:0.005:0.1
    figure(count),getcorners(i,'h',1,0.3,alpha),title(num2str(alpha));
    count=count+1;
end