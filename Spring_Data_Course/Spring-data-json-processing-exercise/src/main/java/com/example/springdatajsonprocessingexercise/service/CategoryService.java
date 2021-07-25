package com.example.springdatajsonprocessingexercise.service;

import com.example.springdatajsonprocessingexercise.model.entity.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService extends BaseService {
    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();

}
