package com.driver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="User")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Username;
    private String Password;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Blog> getBlogList() {
        return listOfBlogs;
    }

    public void setBlogList(List<Blog> listOfBlogs) {
        this.listOfBlogs = listOfBlogs;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Blog> listOfBlogs;

}