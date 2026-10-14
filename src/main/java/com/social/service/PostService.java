package com.social.service;

import com.social.model.Post;
import com.social.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void createPost(String authorName, String authorAvatar, String content, String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            imageUrl = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?auto=format&fit=crop&w=1000&q=80";
        }
        Post post = Post.builder()
                .authorName(authorName)
                .authorAvatar(authorAvatar)
                .content(content)
                .imageUrl(imageUrl)
                .build();
        postRepository.save(post);
    }

    public void likePost(Long id) {
        // CORRECTED: passing 'id' into findById
        postRepository.findById(id).ifPresent(post -> {
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);
        });
    }

    @PostConstruct
    public void initSeedData() {
        if (postRepository.count() == 0) {
            postRepository.save(Post.builder()
                    .authorName("Elena Rostova")
                    .authorAvatar("https://images.unsplash.com/photo-1534528741775-53994a69daeb?auto=format&fit=crop&w=200&q=80")
                    .content("Chasing golden hour lighting across the coast today. Architecture meets nature. 🌅")
                    .imageUrl("https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1000&q=80")
                    .likes(24)
                    .build());

            postRepository.save(Post.builder()
                    .authorName("Marcus Chen")
                    .authorAvatar("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?auto=format&fit=crop&w=200&q=80")
                    .content("Refactoring our deployment pipelines. Clean code and immutable infrastructure make for peaceful evenings. 🚀")
                    .imageUrl("https://images.unsplash.com/photo-1555066931-4365d14bab8c?auto=format&fit=crop&w=1000&q=80")
                    .likes(42)
                    .build());
        }
    }
}
