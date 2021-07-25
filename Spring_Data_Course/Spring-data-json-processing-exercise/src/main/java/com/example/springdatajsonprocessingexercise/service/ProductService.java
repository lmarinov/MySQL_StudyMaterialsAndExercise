package com.example.springdatajsonprocessingexercise.service;

import com.example.springdatajsonprocessingexercise.model.entity.Product;

import java.io.IOException;
import java.util.Set;

public interface ProductService extends BaseService {
    void seedProducts() throws IOException;

    Set<Product> findRandomProducts();
}
