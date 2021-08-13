package com.example.springdatadtoexercise.service.impl;

import com.example.springdatadtoexercise.model.dto.GameAddDto;
import com.example.springdatadtoexercise.model.entity.Game;
import com.example.springdatadtoexercise.repository.GameRepository;
import com.example.springdatadtoexercise.service.GameService;
import com.example.springdatadtoexercise.service.UserService;
import com.example.springdatadtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ValidationUtil validationUtil, ModelMapper modelMapper, UserService userService) {
        this.gameRepository = gameRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public void addGame(GameAddDto gameAddDto) {
        if (!userService.hasLoggedInUser()){
            System.out.println("User not logged in.");
            return;
        }else if(!this.userService.isAdmin()){
            System.out.println("Only admin users can add games.");
            return;
        }

        Set<ConstraintViolation<GameAddDto>> violations = this.validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);
        gameRepository.save(game);

        System.out.println("Added game " + gameAddDto.getTitle());
    }

    @Override
    public void editGame(long id, GameAddDto gameAddDto) {
        if (!userService.hasLoggedInUser()){
            System.out.println("User not logged in.");
            return;
        }else if(!this.userService.isAdmin()){
            System.out.println("Only admin users can edit games.");
            return;
        }

        Set<ConstraintViolation<GameAddDto>> violations = this.validationUtil.getViolations(gameAddDto);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        Game game = gameRepository.findById(id).orElse(null);

        if (game == null){
            System.out.println("Id is not correct");
            return;
        }

        if(gameAddDto.getPrice() != null) game.setPrice(gameAddDto.getPrice());
        if(gameAddDto.getSize() != null) game.setSize(gameAddDto.getSize());
        if(gameAddDto.getTitle() != null) game.setTitle(gameAddDto.getTitle());
        if(gameAddDto.getThumbnailUrl() != null) game.setImageThumbnail(gameAddDto.getThumbnailUrl());
        if(gameAddDto.getDescription() != null) game.setDescription(gameAddDto.getDescription());
        if(gameAddDto.getReleaseDate() != null) game.setReleaseDate(LocalDate.parse(gameAddDto.getReleaseDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        gameRepository.save(game);
        System.out.println("Edited " + game.getTitle());
    }

    @Override
    public void deleteGame(long id) {
        if (!userService.hasLoggedInUser()){
            System.out.println("User not logged in.");
            return;
        }else if(!this.userService.isAdmin()){
            System.out.println("Only admin users can delete games.");
            return;
        }

        Game game = this.gameRepository.findById(id).orElse(null);

        if (game == null){
            System.out.println("Id is not correct");
            return;
        }

        this.gameRepository.delete(game);
        System.out.println("Deleted " + game.getTitle());
    }

    @Override
    public void showAllGames() {
        this.gameRepository.findAll().forEach(game -> System.out.println(game.getTitle() + " " + game.getPrice()));
    }

    @Override
    public void showGameDetails(String title) {
        this.gameRepository.findAllByTitle(title)
                .forEach(game -> System.out.printf("Title: %s%nPrice: %s%nDescription: %s%nRelease date: %s%n%n",
                game.getTitle(),
                game.getPrice(),
                game.getDescription(),
                game.getReleaseDate()));
    }
}
