package com.example.springdatadtoexercise.service.impl;

import com.example.springdatadtoexercise.model.dto.UserLoginDto;
import com.example.springdatadtoexercise.model.dto.UserRegisterDto;
import com.example.springdatadtoexercise.model.entity.Game;
import com.example.springdatadtoexercise.model.entity.User;
import com.example.springdatadtoexercise.repository.GameRepository;
import com.example.springdatadtoexercise.repository.UserRepository;
import com.example.springdatadtoexercise.service.UserService;
import com.example.springdatadtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;
    private final GameRepository gameRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gameRepository = gameRepository;
    }


    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
            return;
        }

        Set<ConstraintViolation<UserRegisterDto>> violations = this.validationUtil.getViolations(userRegisterDto);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        userRepository.save(user);

        System.out.printf("%s was registered%n", user.getFullName());
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = userRepository
                .findByPasswordAndEmail(userLoginDto.getPassword(), userLoginDto.getEmail())
                .orElse(null);

        if(user == null){
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
        System.out.printf("Successfully logged in %s%n", user.getFullName());
    }

    @Override
    public void logout() {
        if(loggedInUser == null){
            System.out.println("Cannot log out. No user was logged in.");
        }else{
            System.out.printf("User %s successfully logged out%n", this.loggedInUser.getFullName());
            loggedInUser = null;
        }
    }

    @Override
    public boolean hasLoggedInUser() {
        return loggedInUser != null;
    }

    @Override
    public boolean isAdmin() {
        return this.loggedInUser.getAdmin();
    }

    @Override
    public void gamePurchase(String title) {
        if (!userLoggedInCheck()) return;

        Game game = this.gameRepository.findOneByTitleWithoutOwner(title).orElse(null);

        if (game == null){
            System.out.println("Sorry, no available game with the given title.");
            return;
        }


        this.loggedInUser.getGames().add(game);
        System.out.println("User " + this.loggedInUser.getFullName() + " successfully bought game: " + game.getTitle());
    }

    @Override
    public void showOwnedGames() {
        if (!userLoggedInCheck()) return;

        if (this.loggedInUser.getGames().isEmpty()){
            System.out.println("No items to show.");
        }else{
            this.loggedInUser.getGames().forEach(game -> System.out.println(game.getTitle()));
        }
    }

    private boolean userLoggedInCheck() {
        if (this.loggedInUser == null) {
            System.out.println("No user currently logged in.");
            return false;
        }

        return true;
    }
}
