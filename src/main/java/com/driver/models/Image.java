package com.driver.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="Images")
@Data
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String dimensions;
    private int imageId;

    public Image(String description, String dimensions) {
        this.description = description;
        this.dimensions = dimensions;
    }

    @ManyToOne
    @JoinColumn(name="blog")
    private Blog blog;

}