package com.example.restfulwebservice.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));

        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{userId}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        }

        return user.get().getPosts();
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public EntityModel<Post> retrievePost(@PathVariable int userId, @PathVariable int postId) {
        Optional<User> opUser = userRepository.findById(userId);

        if (!opUser.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        }

        Post post = postRepository.findById(postId).get();
        EntityModel<Post> entityModel = EntityModel.of(post);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllPostsByUser(userId));
        entityModel.add(linkTo.withRel("all-posts"));

        return entityModel;
    }


    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<User> createPost(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> opUser = userRepository.findById(userId);

        if (!opUser.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        }

        User user = opUser.get();
        user.addPost(post);

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public void deletePost(@PathVariable int userId, @PathVariable int postId) {
        Optional<User> opUser = userRepository.findById(userId);

        if (!opUser.isPresent()) {
            throw new UserNotFoundException(String.format("ID[%s] not found", userId));
        }

        opUser.get().removePost(postId);
        postRepository.deleteById(postId);
    }
}
