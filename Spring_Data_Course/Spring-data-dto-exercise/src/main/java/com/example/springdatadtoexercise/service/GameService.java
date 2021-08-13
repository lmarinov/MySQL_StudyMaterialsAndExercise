package com.example.springdatadtoexercise.service;

import com.example.springdatadtoexercise.model.dto.GameAddDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(long id, GameAddDto gameAddDto);

    void deleteGame(long id);

    void showAllGames();

    void showGameDetails(String title);
}
