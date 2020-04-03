package com.example.jpacommonweb.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleStartsWith(String title);

//    @Query("select p, p.title as pTitle from Post as p where p.title = ?1")
    @Query("select p, p.title as pTitle from #{#entityName} as p where p.title = :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);

    @Transactional
    @Modifying
    @Query("update Post as p set p.title = ?1 where p.id = ?2")
    int updateTitle(String title, Long id);
}
