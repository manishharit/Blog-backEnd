package com.maniBlog.BlogbackEnd.Repository;

import com.maniBlog.BlogbackEnd.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
