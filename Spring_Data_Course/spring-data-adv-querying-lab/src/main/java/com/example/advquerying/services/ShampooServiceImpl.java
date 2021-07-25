package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findAllBySizeOrderById(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long label_id) {
        return this.shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(size, label_id);
    }

    @Override
    public List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> findAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceLessThan(price);
    }

    @Override
    public List<String> findAllByIngredientsNames(List<String> names) {
        return this.shampooRepository.findAllByIngredientsNames(names);
    }

    @Override
    public List<Shampoo> findAllByIngredientsCount(Long ingredientCount) {
        return this.shampooRepository.findAllByIngredientsCount(ingredientCount);
    }


}
