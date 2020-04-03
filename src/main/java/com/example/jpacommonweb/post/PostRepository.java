package com.example.jpacommonweb.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleStartsWith(String title);

    @Query("select p, p.title as pTitle from Post as p where p.title = ?1")
    List<Post> findByTitle(String title, Sort sort);
}
