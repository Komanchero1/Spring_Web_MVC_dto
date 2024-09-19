package org.example.controller;

import org.example.PostDTO;
import org.example.exception.NotFoundException;
import org.example.model.Post;
import org.springframework.web.bind.annotation.*;
import org.example.service.PostService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public List<PostDTO> all() {
        List<Post> allPosts = postService.findAll();
        return allPosts.stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return new PostDTO(post.getId(), post.getTitle(), post.getContent());
    }

    @PostMapping("/")
    public PostDTO save(@RequestBody PostDTO postDTO) {
        Post post = new Post(postDTO.getId(), postDTO.getTitle(), postDTO.getContent());
        post = postService.save(post);
        return new PostDTO(post.getId(), post.getTitle(), post.getContent());
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable Long id) {
        Post post = postService.findById(id);
        if (post == null) {
            throw new NoSuchElementException("Пост с идентификатором " + id + " не найден.");
        }
        post.setRemoved(true);
        postService.save(post); // Перезаписываем с обновленным флагом
    }
}