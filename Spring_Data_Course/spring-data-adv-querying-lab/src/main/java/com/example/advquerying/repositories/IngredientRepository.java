package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient>{

    List<Ingredient> findAllByNameStartingWith(String name);
    List<Ingredient> findAllByNameIn(List<String> name);
    List<Ingredient> findAll();

    @Query("DELETE FROM Ingredient AS i WHERE i.name = :name")
    @Modifying
    int deleteAllByName(String name);

    @Query("UPDATE Ingredient  AS i SET i.price = i.price * 1.1")
    @Modifying
    int updatePrice();

    @Query("UPDATE Ingredient  AS i SET i.price = i.price * :percentage WHERE i.name in :names")
    @Modifying
    int updatePriceByNameAndPercentage(List<String> names, BigDecimal percentage);
}
