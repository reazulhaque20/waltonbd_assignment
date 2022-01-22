package com.waltonbd.assignment.springngblog.repository;

import com.waltonbd.assignment.springngblog.model.BlogComments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCommentsRepository extends JpaRepository<BlogComments, Long> {
}
