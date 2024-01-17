package com.caioprogramador.workshopmongo.services;

import com.caioprogramador.workshopmongo.entity.Post;

import java.util.Date;
import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(String id);
    Post insert(Post post);
    void update(Post post);
    void delete(String id);
    List<Post> findByTitle(String text);
    List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
