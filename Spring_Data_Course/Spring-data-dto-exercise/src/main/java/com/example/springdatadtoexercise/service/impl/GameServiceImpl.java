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
import java.math.BigDecimal;
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
    public void editGame(long id, BigDecimal price, double size) {
        Game game = gameRepository.findById(id).orElse(null);

        if (game == null){
            System.out.println("Id is not correct");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
    }
}
