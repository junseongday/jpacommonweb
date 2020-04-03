package com.example.jpacommonweb.post;

import javax.persistence.*;

@NamedEntityGraph(name="Comment.post", attributeNodes = @NamedAttributeNode("post"))
@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Post post;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
