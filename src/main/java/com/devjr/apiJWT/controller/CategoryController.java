package com.devjr.apiJWT.controller;

import com.devjr.apiJWT.models.Category;
import com.devjr.apiJWT.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){

        return new ResponseEntity<>(categoryService.getAll(),HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> saveCategory(@RequestBody Category category){

        categoryService.save(category);

        return ResponseEntity.ok().build();
    }

}
