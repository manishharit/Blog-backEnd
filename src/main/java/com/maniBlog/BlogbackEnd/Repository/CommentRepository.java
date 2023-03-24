package com.maniBlog.BlogbackEnd.Repository;

import com.maniBlog.BlogbackEnd.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment,Long> {

}
