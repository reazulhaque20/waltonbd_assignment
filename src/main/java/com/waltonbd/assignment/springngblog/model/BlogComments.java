package com.waltonbd.assignment.springngblog.model;

import javax.persistence.*;

@Entity
@Table
public class BlogComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogComments_id;
    @Column
    private Long post_id;
    @Column
    private Long user_id;
    @Column
    private String comment;

    public Long getBlogComments_id() {
        return blogComments_id;
    }

    public void setBlogComments_id(Long blogComments_id) {
        this.blogComments_id = blogComments_id;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
