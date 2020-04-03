package com.example.jpacommonweb.post;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository repository;

    private void beforeSetting() {
        Post post = new Post();
        post.setTitle("jpa title");
        repository.save(post);
    }

    @Test
    void findByTitleStartsWith() {
        beforeSetting();
        List <Post> findedPost = repository.findByTitleStartsWith("jpa ti");
        assertThat(findedPost.size()).isEqualTo(1);
    }

    @Test
    void findByTitle() {
        beforeSetting();
        List <Post> findedPost = repository.findByTitle("jpa title");
        assertThat(findedPost.size()).isEqualTo(1);
    }
}