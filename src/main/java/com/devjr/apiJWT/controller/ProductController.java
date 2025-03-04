package com.devjr.apiJWT.controller;


import com.devjr.apiJWT.models.Category;
import com.devjr.apiJWT.models.Product;
import com.devjr.apiJWT.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> saveProduct(@RequestParam String name,
                                            @RequestParam String description,
                                            @RequestParam MultipartFile image,
                                            @RequestParam String price,
                                            @RequestParam String quantity,
                                            @RequestParam("category") Long categoryId
                                           ) throws IOException {

        Product product= new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(Double.parseDouble(price));
        product.setQuantity(Integer.parseInt(quantity));

        Category category= new Category();
        category.setId(categoryId);

        productService.saveProduct(product,image,categoryId);

        return ResponseEntity.ok().build();
    }

}
