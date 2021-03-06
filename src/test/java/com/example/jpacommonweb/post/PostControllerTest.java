package com.example.jpacommonweb.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Test
    public void crud() {
        Post post = new Post();
        post.setTitle("jpa");
        post.setId(1L);
        postRepository.save(post);
        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void save () {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);
        List<Post> all = postRepository.findAll();

        Post updatePost = new Post();
        updatePost.setTitle("jpa");
        updatePost.setId(1L);
        postRepository.save(post);
        List<Post> all2 = postRepository.findAll();

        assertThat(all2.size()).isEqualTo(2);
    }

    @Test
    public void getPost() throws Exception {
        Post post = new Post();
        post.setTitle("jpa");
        postRepository.save(post);
        mockMvc.perform(get("/posts/"+post.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("jpa"));
    }


    @Test
    public void getPosts() throws Exception{
        createPosts();

        mockMvc.perform(get("/posts/")
                .param("page","0")
                .param("size","10")
                .param("sort", "created,desc")
                .param("sort","title")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", is("jpa")));

//                .andExpect(content().string("jpa"));
    }

    private void createPosts() {
        int postCount = 100;
        while(postCount > 0) {
            Post post =  new Post();
            post.setTitle("jpa");
            postRepository.save(post);
            postCount--;
        }
    }
}