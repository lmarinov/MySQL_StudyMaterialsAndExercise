package com.example.springdatajsonprocessingexercise.repository;

import com.example.springdatajsonprocessingexercise.model.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {
}
