package io.liatr.sampleappapi;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PostController {
    private PostRepository repository;

    public PostController(PostRepository repository) {
        this.repository= repository;
    }

    @GetMapping("/posts")
    public Collection<Post> posts() {
        return repository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/post")
    public Post post(Post post) {
        return repository.save(post);
    }
    @DeleteMapping("/post")
    public void deletePost(Post post) {
        repository.delete(post);
    }
}
