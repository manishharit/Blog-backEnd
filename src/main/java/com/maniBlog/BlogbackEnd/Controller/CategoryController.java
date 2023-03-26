package com.maniBlog.BlogbackEnd.Controller;

import com.maniBlog.BlogbackEnd.PayLoad.CategoryDto;
import com.maniBlog.BlogbackEnd.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto saveCategory =  categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(saveCategory,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(name = "id") Long id){
       CategoryDto category =  categoryService.getCategory(id);
       return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllcategory(){
       List<CategoryDto> categoryList = categoryService.getAllcategory();
       return ResponseEntity.ok(categoryList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable(name = "id") Long id){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,id);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CODEOWNER')")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category has been deleted successfully");
    }

}
