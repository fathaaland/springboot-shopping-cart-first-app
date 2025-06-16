package com.dailycodework.dreamshops.controller;

import com.dailycodework.dreamshops.exceptions.AlreadyExistsException;
import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final ICategoryService categoryService;


    @GetMapping("/categories/all")
   public ResponseEntity<ApiResponse> getAllCategories() {
       try {
           List<Category> categories = categoryService.getAllCategories();
           return ResponseEntity.ok(new ApiResponse("Categories fetched successfully", categories));
       }catch (Exception e) {
              return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                     .body(new ApiResponse("Failed to fetch categories: " + e.getMessage(), null));
       }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
        try {
            Category thecategory = categoryService.addCategory(name);
            return ResponseEntity.ok(new ApiResponse("Category added successfully", thecategory));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
