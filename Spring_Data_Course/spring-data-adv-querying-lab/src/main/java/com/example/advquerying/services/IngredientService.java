package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService{

    List<Ingredient> findAllByNameStartingWith(String name);
    List<Ingredient> findAllByNameIn(List<String> name);
    int deleteAllByName(String name);
    int updatePrice();
    List<Ingredient> findAll();
    int updatePriceByNameAndPercentage(List<String> names, BigDecimal percentage);
}
