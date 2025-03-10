package com.devjr.apiJWT.services;

import com.devjr.apiJWT.models.Category;
import com.devjr.apiJWT.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Category not found"));

    }

    @Override
    public void save(Category category) {

        categoryRepository.save(category);

    }
}
