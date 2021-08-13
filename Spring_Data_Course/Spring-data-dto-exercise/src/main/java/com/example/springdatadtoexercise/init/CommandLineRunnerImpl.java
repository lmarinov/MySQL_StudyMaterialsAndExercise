package com.example.springdatadtoexercise.init;

import com.example.springdatadtoexercise.model.dto.GameAddDto;
import com.example.springdatadtoexercise.model.dto.UserLoginDto;
import com.example.springdatadtoexercise.model.dto.UserRegisterDto;
import com.example.springdatadtoexercise.service.GameService;
import com.example.springdatadtoexercise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.math.BigDecimal;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(BufferedReader bufferedReader, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("Please enter your commands:");
            String[] commands = bufferedReader.readLine().split("\\|");

            switch (commands[0]){
                case "RegisterUser" -> {
                    if (commands.length > 5) {
                        userService
                                .registerUser(new UserRegisterDto(commands[1],
                                        commands[2],
                                        commands[3],
                                        commands[4],
                                        Boolean.parseBoolean(commands[5])));
                    } else {
                        userService
                                .registerUser(new UserRegisterDto(commands[1],
                                        commands[2],
                                        commands[3],
                                        commands[4]));
                    }
                }
                case "LoginUser" -> userService
                        .loginUser(new UserLoginDto(
                                commands[1],
                                commands[2]));
                case "Logout" -> userService
                        .logout();
                case "AddGame" -> gameService
                        .addGame(new GameAddDto(
                                commands[1],
                                new BigDecimal(commands[2]),
                                Double.parseDouble(commands[3]),
                                commands[4],
                                commands[5],
                                commands[6],
                                commands[7]));
                case "EditGame" -> {

                    String title = null;
                    BigDecimal price = null;
                    Double size = null;
                    String trailer = null;
                    String thumbnailUrl = null;
                    String description = null;
                    String releaseDate = null;

                    for (String command : commands) {
                        if (command.contains("=")) {
                            String[] arguments = command.split("=");
                            String itemName = arguments[0];
                            String itemValue = arguments[1];

                            switch (itemName){
                                case "title" -> title = itemValue;
                                case "price" -> price = new BigDecimal(itemValue);
                                case "size" -> size = Double.parseDouble(itemValue);
                                case "trailer" -> trailer = itemValue;
                                case "thumbnailUrl" -> thumbnailUrl = itemValue;
                                case "description" -> description = itemValue;
                                case "releaseDate" -> releaseDate = itemValue;
                            }
                        }
                    }

                    this.gameService
                            .editGame(Long.parseLong(commands[1]), new GameAddDto(title, price, size, trailer, thumbnailUrl, description, releaseDate));

                }

                case "DeleteGame" -> this.gameService
                        .deleteGame(Long.parseLong(commands[1]));
                case "AllGames" -> this.gameService.showAllGames();
                case "DetailGame" -> this.gameService.showGameDetails(commands[1]);
                case "OwnedGames" -> this.userService.showOwnedGames();
                case "BuyGame" -> this.userService.gamePurchase(commands[1]);
                case "x" -> { return; }
                default -> System.out.println("Incorrect command syntax.");
            }
        }
    }
}
