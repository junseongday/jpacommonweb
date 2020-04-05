package com.example.jpacommonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.example.jpacommonweb.post.CommentSpecs.isBest;
import static com.example.jpacommonweb.post.CommentSpecs.isGood;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public  void getComment() {
       commentRepository.getById(1l);
        System.out.println("=======================");
        commentRepository.findById(1l);
    }

    @Test
    public void findByPost_Id() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savePost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setPost(savePost);
        comment.setComment("comment Jpa");
        comment.setUp(10);
        comment.setDown(1);
        commentRepository.save(comment);

        commentRepository.findByPost_Id(savePost.getId(), CommentOnly.class).forEach(c ->{
            System.out.println("=====================");
//            System.out.println(c.getVotes());
            System.out.println(c.getComment());
        });
    }

    @Test
    public void specs() {
        Page<Comment> page = commentRepository.findAll(isBest().or(isGood()), PageRequest.of(0,10));
    }
}