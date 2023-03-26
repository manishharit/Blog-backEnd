package com.maniBlog.BlogbackEnd.Repository;

import com.maniBlog.BlogbackEnd.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
