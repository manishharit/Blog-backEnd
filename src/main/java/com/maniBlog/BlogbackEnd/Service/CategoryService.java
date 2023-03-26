package com.maniBlog.BlogbackEnd.Service;

import com.maniBlog.BlogbackEnd.PayLoad.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long id);

    List<CategoryDto> getAllcategory();

    CategoryDto updateCategory(CategoryDto categoryDto,Long id);

    void deleteCategory(Long id);
}
