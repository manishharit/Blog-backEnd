package com.maniBlog.BlogbackEnd.Service.Impl;

import com.maniBlog.BlogbackEnd.Entity.Category;
import com.maniBlog.BlogbackEnd.Execption.ResourceNotFoundExecption;
import com.maniBlog.BlogbackEnd.PayLoad.CategoryDto;
import com.maniBlog.BlogbackEnd.Repository.CategoryRepository;
import com.maniBlog.BlogbackEnd.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        Category savedCatogery =  categoryRepository.save(category);
        return modelMapper.map(savedCatogery,CategoryDto.class);

    }

    @Override
    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundExecption("Category","id",id));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllcategory() {
        List<Category> categories = categoryRepository.findAll();
      return   categories.stream().map(
              (category)-> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
        Category category= categoryRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundExecption("Category","id",id));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(id);

       Category updatedCatogery = categoryRepository.save(category);
       return modelMapper.map(updatedCatogery,CategoryDto.class);

    }

    @Override
    public void deleteCategory(Long id) {
        Category category= categoryRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundExecption("Category","id",id));
        categoryRepository.deleteById(id);
    }
}
