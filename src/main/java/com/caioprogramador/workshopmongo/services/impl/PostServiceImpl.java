package com.caioprogramador.workshopmongo.services.impl;

import com.caioprogramador.workshopmongo.entity.Post;
import com.caioprogramador.workshopmongo.repository.PostRepository;
import com.caioprogramador.workshopmongo.services.PostService;
import com.caioprogramador.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post findById(String id) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        return post;
    }

    @Override
    public Post insert(Post post) {
        return repository.insert(post);
    }

    @Override
    public void update(Post post) {
        Post newPost = repository.findById(post.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        updateData(newPost,post);
    }

    private void updateData(Post newPost, Post post) {
        newPost.setDate(post.getDate());
        newPost.setBody(post.getBody());
        newPost.setAuthor(post.getAuthor());
        newPost.setTitle(post.getTitle());

    }

    @Override
    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
