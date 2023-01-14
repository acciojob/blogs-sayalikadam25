package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository1;

    @Autowired
    private ImageService imageService1;

    @Autowired
    private UserRepository userRepository1;

    public List<Blog> showBlogs(){
        List<Blog> blogs=blogRepository1.findAll();
        return blogs;
    }
    public int noOfAllBlogs(){
        return showBlogs().size();
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        //updating the blog details
        //Updating the userInformation and changing its blogs
        Blog blog=new Blog(title,content);
        blog.setUser(userRepository1.findById(userId).get());
        User user=userRepository1.findById(userId).get();
        List<Blog> blogsOfUser=user.getBlogList();
        if(blogsOfUser==null)
            blogsOfUser=new ArrayList<>();
        blogsOfUser.add(blog);
        user.setBlogList(blogsOfUser);
        blogRepository1.save(blog);
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){

        return blogRepository1.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        Blog blog=blogRepository1.findById(blogId).get();
        Image image=imageService1.createAndReturn(blog,description,dimensions);
        image.setBlog(blog);
        List<Image> imagesInBlog=blog.getImageList();
        if(imagesInBlog==null)
            imagesInBlog=new ArrayList<>();
        imagesInBlog.add(image);
        blog.setImageList(imagesInBlog);
        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        blogRepository1.deleteById(blogId);
    }
}
