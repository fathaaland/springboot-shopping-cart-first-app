package com.dailycodework.dreamshops.service.product;

import com.dailycodework.dreamshops.exceptions.ProductNotFoundException;
import com.dailycodework.dreamshops.model.Category;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.repository.ProductRepository;
import com.dailycodework.dreamshops.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(AddProductRequest product) {
        return null;
    }

    private Product createProduct(AddProductRequest request, Category category) {

        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found!"));
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByCategoryName(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {

        return productRepository.findByCategoryNameAndBrand(category, brand);

    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String category, String name) {
        return productRepository.findByCategoryNameAndBrand(category, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
