package com.example.advquerying.repositories;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends BaseRepository<Shampoo> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long label_id);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    @Query("SELECT DISTINCT(s.brand) FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :names")
    List<String> findAllByIngredientsNames(List<String> names);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i GROUP BY s.id HAVING COUNT(i) < :ingredientCount")
    List<Shampoo> findAllByIngredientsCount(Long ingredientCount);
}
