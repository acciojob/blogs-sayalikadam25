package com.driver.models;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Blog")
@Data
public class Blog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int blogId;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdTime;

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdTime=LocalDateTime.now();
    }
    public Blog(){
    }

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<Image> listOfImages;

}