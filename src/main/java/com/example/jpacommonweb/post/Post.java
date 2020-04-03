package com.example.jpacommonweb.post;

import javax.persistence.*;
import java.util.Date;

@Entity
//@NamedQuery(name="Post.findByTitle", query = "select p from Post as p where p.title = ?1")
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
