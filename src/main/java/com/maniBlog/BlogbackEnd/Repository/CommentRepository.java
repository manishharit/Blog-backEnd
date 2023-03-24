package com.maniBlog.BlogbackEnd.Repository;

import com.maniBlog.BlogbackEnd.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(Long postId);

}
