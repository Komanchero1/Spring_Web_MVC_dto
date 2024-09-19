package org.example.service;

import org.springframework.stereotype.Service;
import org.example.repository.PostRepository;
import org.example.model.Post;
import org.example.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.all().stream()
                .filter(post -> !post.isRemoved()) // Исключает удаленные посты
                .collect(Collectors.toList());
    }

    public Post findById(Long id) {
        return postRepository.getById(id)
                .filter(post -> !post.isRemoved()) // Проверяем флаг removed
                .orElseThrow(() -> new NotFoundException("Post not found with id " + id));
    }

    public Post save(Post post) {
        if (post.isRemoved()) {
            throw new NotFoundException("Cannot update post with id " + post.getId() + ", post is marked as removed.");
        }
        return postRepository.save(post);
    }
}