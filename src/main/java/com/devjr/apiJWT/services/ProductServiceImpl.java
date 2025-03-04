package com.devjr.apiJWT.services;

import com.devjr.apiJWT.models.Category;
import com.devjr.apiJWT.models.Product;
import com.devjr.apiJWT.repository.CategoryRepository;
import com.devjr.apiJWT.repository.ProductRepository;
import com.devjr.apiJWT.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductServiceImpl implements IProductService{

    @Autowired
    Utileria utileria;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product, MultipartFile file, Long idCategory) throws IOException {

        String fileImage = utileria.saveImage(file);
        product.setImage(fileImage);

        Category category = categoryRepository.findById(idCategory)
                .orElseThrow(()->new RuntimeException("Category not found"));

        product.setDate(LocalDate.now());

        product.setCategory(category);

        productRepository.save(product);

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product, MultipartFile file) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
