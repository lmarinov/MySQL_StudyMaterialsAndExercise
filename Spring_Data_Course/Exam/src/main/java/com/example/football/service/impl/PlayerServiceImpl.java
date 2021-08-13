package com.example.football.service.impl;

import com.example.football.models.dto.PlayerCreateDto;
import com.example.football.models.entity.Player;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.impl.XMLFormatConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.example.football.constant.GlobalConstants.RESOURCE_FILE_PATH;

//ToDo - Implement all methods
@Service
public class PlayerServiceImpl implements PlayerService {

    public static final String PLAYERS_FILE_NAME = "xml/players.xml";
    private final PlayerRepository playerRepository;
    private final StringBuilder stringBuilder;
    private final XMLFormatConverter xmlFormatConverter;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, StringBuilder stringBuilder, XMLFormatConverter xmlFormatConverter, ValidationUtil validationUtil, ModelMapper mapper) {
        this.playerRepository = playerRepository;
        this.stringBuilder = stringBuilder;
        this.xmlFormatConverter = xmlFormatConverter;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }


    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readPlayersFileContent()  {
        return this.xmlFormatConverter.deserializeFromFile(RESOURCE_FILE_PATH + PLAYERS_FILE_NAME, String.class);
    }

    @Override
    public String importPlayers()  {
        if (this.playerRepository.count() > 0){
            return "Player data has already been saved.";
        }


        PlayerCreateDto[] playerCreateDtos = this.xmlFormatConverter.deserializeFromFile(RESOURCE_FILE_PATH + PLAYERS_FILE_NAME, PlayerCreateDto[].class);

        Arrays.stream(playerCreateDtos)
                .forEach(playerCreateDto -> {
                    if (this.validationUtil.isValid(playerCreateDto)) {
                        this.stringBuilder.append(String.format("Successfully imported Player %s-%s%n", playerCreateDto.getFirstName() + " " + playerCreateDto.getLastName(), playerCreateDto.getPosition().name()));
                    } else {
                        this.stringBuilder.append(String.format("Invalid Player%n"));
                    }
                });

        Arrays.stream(playerCreateDtos)
                .filter(validationUtil::isValid)
                .map(playerCreateDto -> mapper.map(playerCreateDto, Player.class))
                .forEach(playerRepository::save);

        return this.stringBuilder.toString();
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
