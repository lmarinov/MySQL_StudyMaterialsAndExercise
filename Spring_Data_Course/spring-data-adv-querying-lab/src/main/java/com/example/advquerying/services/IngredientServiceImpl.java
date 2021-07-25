package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;


    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> findAllByNameStartingWith(String name) {
        return this.ingredientRepository.findAllByNameStartingWith(name);
    }

    @Override
    public List<Ingredient> findAllByNameIn(List<String> name) {
        return this.ingredientRepository.findAllByNameIn(name);
    }

    @Override
    @Transactional
    public int deleteAllByName(String name) {
        return this.ingredientRepository.deleteAllByName(name);
    }

    @Override
    @Transactional
    public int updatePrice() {
        return this.ingredientRepository.updatePrice();
    }

    @Override
    public List<Ingredient> findAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    @Transactional
    public int updatePriceByNameAndPercentage(List<String> names, BigDecimal percentage) {
        return this.ingredientRepository.updatePriceByNameAndPercentage(names, percentage);
    }
}
