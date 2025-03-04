package com.devjr.apiJWT.services;

import com.devjr.apiJWT.models.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAll();

    Category findById(Long id);

    void save(Category category);

}
