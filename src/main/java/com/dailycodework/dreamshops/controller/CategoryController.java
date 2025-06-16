package com.dailycodework.dreamshops.controller;

import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
