package com.example.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Post {

    @Id private String id;
    private String title = "default title";
    private String text = "default text";
    private String category = "default category";
    private Integer version = 1;

    public Post(){}

    public Post update(Post entity) {
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.category = entity.getCategory();
        return this;
    }

    public Post(String title, String text, String category, Integer version) {
        this.title = title;
        this.text = text;
        this.category = category;
        this.version = version;
    }
}
