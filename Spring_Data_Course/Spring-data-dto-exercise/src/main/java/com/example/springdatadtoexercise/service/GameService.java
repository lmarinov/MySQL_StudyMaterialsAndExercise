package com.example.springdatadtoexercise.service;

import com.example.springdatadtoexercise.model.dto.GameAddDto;

import java.math.BigDecimal;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(long id, BigDecimal price, double size);
}
