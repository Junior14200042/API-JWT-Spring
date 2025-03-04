package com.devjr.apiJWT.services;

import com.devjr.apiJWT.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    List<Product> getAll();

    void saveProduct(Product product, MultipartFile file, Long idCategory) throws IOException;

    Product findById(Long id);

    Product updateProduct(Long id, Product product, MultipartFile file);

    void deleteById(Long id);

}
