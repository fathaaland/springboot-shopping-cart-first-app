package com.dailycodework.dreamshops.controller;


import com.dailycodework.dreamshops.dto.ProductDto;
import com.dailycodework.dreamshops.exceptions.ProductNotFoundException;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.request.AddProductRequest;
import com.dailycodework.dreamshops.request.ProductUpdateRequest;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.tomcat.websocket.Constants.FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}")

public class ProductController {

    private final IProductService productService;

    @GetMapping("/products/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<ProductDto> products = productService.getAllProductsDto();
            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch products: " + e.getMessage(), null));
        }
    }


    @GetMapping("/products/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product fetched successfully", product));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Internal server error: " + e.getMessage(), null));
        }
    }



    @PostMapping("/products/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Product added successfully", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to add product: " + e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest request) {
        try {
            Product updatedProduct = productService.updateProduct(productId, request);
            return ResponseEntity.ok(new ApiResponse("Product updated successfully", updatedProduct));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found: " + e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse("Failed to update product: " + e.getMessage(), null));
        }
    }



    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductBy(@PathVariable Long productId) {
        try {

            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse("Failed to delete product: " + e.getMessage(), null));
        }

    }


    @GetMapping("/products/by/name-and-brand")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);

            if (products.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse("No products found for the given brand and name", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch products: " + e.getMessage(), null));
        }

    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String categoryName, @RequestParam String brandName) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(brandName, categoryName);

            if (products.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse("No products found for the given brand and name", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch products: " + e.getMessage(), null));
        }

    }

    @GetMapping("/products/name/{productName}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String productName) {
        try {
            List<Product> products = productService.getProductsByName(productName);

            if (products.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse("No products found for the given brand and name", null));
            }

            return ResponseEntity.ok(new ApiResponse("Product fetched successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse("Product not found: " + e.getMessage(), null));
        }
    }

    @GetMapping("/products/by-brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);

            if (products.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse("No products found for the given brand and name", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch products: " + e.getMessage(), null));
        }

    }


    @GetMapping("/products/by/category/{categoryName}")
    public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable String categoryName) {
        try {
            List<Product> products = productService.getProductsByCategory(categoryName);

            if (products.isEmpty()) {
                return ResponseEntity.status(404).body(new ApiResponse("No products found for the given brand and name", null));
            }

            return ResponseEntity.ok(new ApiResponse("Products fetched successfully", products));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("Failed to fetch products: " + e.getMessage(), null));
        }

    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse( "Product count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }




}
