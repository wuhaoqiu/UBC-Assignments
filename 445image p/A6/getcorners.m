function [ r,c ] = getcorners( i,method,sigma,thresh,alpha )
%GETCORNERS Summary of this function goes here
%   Detailed explanation goes here
    switch nargin
        case 2
            sigma=1;
            method='h'
            thresh=0.5;
            alpha=0.004;
            I =im2double(i); % pixel values between 0,1
            % Compute Ix and Iy
            dx = [-1 -1 -1; 0 0 0; 1 1 1]; dy = dx'; % derivative masks
            Ix = imfilter(I,dx,'same'); %same: output array is same size as input array
            Iy = imfilter(I,dy,'same');
            % Compute Sxx, Syy, and Sxy
            w = fspecial('gaussian', round(6*sigma), sigma); %Gaussian filter
            Sxx = conv2(Ix.^2, w, 'same');
            Syy = conv2(Iy.^2, w, 'same');
            Sxy = conv2(Ix.*Iy, w, 'same');
            % Compute R
            if method=='h'
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            elseif method=='s'
                R=(Sxx.*Syy-Sxy.^2)./(Sxx+Syy);%szelski method
            else %default use h
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            end
           % Threshold R and find local maxima
            N = 4 * sigma + 1; % Size of mask.
            Rdilated = imdilate(R, strel('disk',N)); % Grey-scale dilate.
            corners = (R > thresh) & (R == Rdilated); % Find local maxima.
            % overlaying corners on original image
            [r,c] = find(corners); % Find row,col coords.
            figure, imshow(I), hold on
            plot(c,r,'rs');
        case 3
            thresh=0.5;
            alpha=0.004;
            I =im2double(i); % pixel values between 0,1
            % Compute Ix and Iy
            dx = [-1 -1 -1; 0 0 0; 1 1 1]; dy = dx'; % derivative masks
            Ix = imfilter(I,dx,'same'); %same: output array is same size as input array
            Iy = imfilter(I,dy,'same');
            % Compute Sxx, Syy, and Sxy
            w = fspecial('gaussian', round(6*sigma), sigma); %Gaussian filter
            Sxx = conv2(Ix.^2, w, 'same');
            Syy = conv2(Iy.^2, w, 'same');
            Sxy = conv2(Ix.*Iy, w, 'same');
            % Compute R
            if method=='h'
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            elseif method=='s'
                R=(Sxx.*Syy-Sxy.^2)./(Sxx+Syy);%szelski method
            else %default use h
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            end
           % Threshold R and find local maxima
            N = 4 * sigma + 1; % Size of mask.
            Rdilated = imdilate(R, strel('disk',N)); % Grey-scale dilate.
            corners = (R > thresh) & (R == Rdilated); % Find local maxima.
            % overlaying corners on original image
            [r,c] = find(corners); % Find row,col coords.
            figure, imshow(I), hold on
            plot(c,r,'rs');        
        case 4
            alpha=0.004;
            I =im2double(i); % pixel values between 0,1
            % Compute Ix and Iy
            dx = [-1 -1 -1; 0 0 0; 1 1 1]; dy = dx'; % derivative masks
            Ix = imfilter(I,dx,'same'); %same: output array is same size as input array
            Iy = imfilter(I,dy,'same');
            % Compute Sxx, Syy, and Sxy
            w = fspecial('gaussian', round(6*sigma), sigma); %Gaussian filter
            Sxx = conv2(Ix.^2, w, 'same');
            Syy = conv2(Iy.^2, w, 'same');
            Sxy = conv2(Ix.*Iy, w, 'same');
            % Compute R
            if method=='h'
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            elseif method=='s'
                R=(Sxx.*Syy-Sxy.^2)./(Sxx+Syy);%szelski method
            else %default use h
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            end
           % Threshold R and find local maxima
            N = 4 * sigma + 1; % Size of mask.
            Rdilated = imdilate(R, strel('disk',N)); % Grey-scale dilate.
            corners = (R > thresh) & (R == Rdilated); % Find local maxima.
            % overlaying corners on original image
            [r,c] = find(corners); % Find row,col coords.
            figure, imshow(I), hold on
            plot(c,r,'rs');
        case 5
            I =im2double(i); % pixel values between 0,1
            % Compute Ix and Iy
            dx = [-1 -1 -1; 0 0 0; 1 1 1]; dy = dx'; % derivative masks
            Ix = imfilter(I,dx,'same'); %same: output array is same size as input array
            Iy = imfilter(I,dy,'same');
            % Compute Sxx, Syy, and Sxy
            w = fspecial('gaussian', round(6*sigma), sigma); %Gaussian filter
            Sxx = conv2(Ix.^2, w, 'same');
            Syy = conv2(Iy.^2, w, 'same');
            Sxy = conv2(Ix.*Iy, w, 'same');
            % Compute R
            if method=='h'
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            elseif method=='s'
                R=(Sxx.*Syy-Sxy.^2)./(Sxx+Syy);%szelski method
            else %default use h
                R = (Sxx.*Syy - Sxy.^2)-alpha*((Sxx + Syy).^2); % Harris measure
            end
            % Threshold R and find local maxima
            N = 4 * sigma + 1; % Size of mask.
            Rdilated = imdilate(R, strel('disk',N)); % Grey-scale dilate.
            corners = (R > thresh) & (R == Rdilated); % Find local maxima.
            % overlaying corners on original image
            [r,c] = find(corners); % Find row,col coords.
            figure, imshow(I), hold on
            plot(c,r,'rs');          
    end
end

