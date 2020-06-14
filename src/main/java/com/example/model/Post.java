package com.example.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Post {

    @Id
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String text;

    @NonNull
    private String category;

    @Version
    private Integer version;

    public Post update(Post entity) {
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.category = entity.getCategory();
        return this;
    }
}
