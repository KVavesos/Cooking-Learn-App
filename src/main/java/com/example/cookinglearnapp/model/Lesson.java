package com.example.cookinglearnapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content1;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content2;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content3;

    private String image_url;

    private String image2_url;

    private String image3_url;

    public Lesson() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }

    public String getImage2Url() {
        return image2_url;
    }

    public void setImage2Url(String image2Url) {
        this.image2_url = image2Url;
    }

    public String getImage3Url() {
        return image3_url;
    }

    public void setImage3Url(String image3Url) {
        this.image3_url = image3Url;
    }
}