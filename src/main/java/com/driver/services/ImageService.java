package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository2;
    @Autowired
    private BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image=new Image(description,dimensions);
        image.setBlog(blog);
        List<Image> images=blog.getImageList();
        if(images==null)
            images=new ArrayList<>();
        images.add(image);
        blog.setImageList(images);
        blogRepository.save(blog);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Image image){

        imageRepository2.delete(image);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
//        int count=0;
//        String imgD=image.getDimensions();
//        String [] imgDArray=imgD.split("x");
//        String [] screenDArray=screenDimensions.split("x");
//        int d1=Integer.valueOf(imgDArray[0]);
//        int D1=Integer.valueOf(screenDArray[0]);
//        int d2=Integer.valueOf(imgDArray[1]);
//        int D2=Integer.valueOf(screenDArray[1]);
//        count=(D1/d1)*(D2/d2);
        if(screenDimensions.split("X").length==2 || Objects.nonNull(image)){
            int maxLength=Integer.parseInt(screenDimensions.split("X")[0])/Integer.parseInt(image.getDimensions().split("X")[0]);
            int maxBreadth=Integer.parseInt(screenDimensions.split("X")[1])/Integer.parseInt(image.getDimensions().split("X")[1]);
            return maxBreadth*maxLength;
        }
        return 0;
    }
}
