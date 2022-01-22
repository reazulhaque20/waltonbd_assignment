package com.waltonbd.assignment.springngblog.repository;

import com.waltonbd.assignment.springngblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.status=:status")
    public List<Post> showAllActivePost(@Param("status") String status);

    @Query("SELECT p FROM Post p WHERE p.username=:username AND p.status=:status")
    public List<Post> showAllOwnPost(@Param("username") String username, @Param("username") String status);
}
