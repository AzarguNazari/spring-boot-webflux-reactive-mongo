package com.example.controller;

import com.example.DemoApplication;
import com.example.model.Post;
import com.example.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping(value = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private PostService postService;

    @GetMapping(path = "/{id}")
    public Mono<Post> get(@PathVariable String id) {
        return postService.findOne(id);
    }

    @PostMapping
    public Mono<ResponseEntity<Post>> save(@RequestBody Post post) {
        return postService.save(post).map(savedHotel -> new ResponseEntity<>(savedHotel, HttpStatus.CREATED));
    }

    @PutMapping
    public Mono<ResponseEntity<Post>> update(@RequestBody Post post) {
        return postService.update(post)
                .map(savedPost -> new ResponseEntity<>(savedPost, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
        return postService.delete(id)
                          .filter(delete -> delete)
                          .map(delete -> new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED))
                          .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Flux<Post> findAll() {
        LOGGER.warn("All posts are sent");
        return postService.findAll();
    }

}
