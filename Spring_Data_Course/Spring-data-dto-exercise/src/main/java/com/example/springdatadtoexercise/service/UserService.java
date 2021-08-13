package com.example.springdatadtoexercise.service;

import com.example.springdatadtoexercise.model.dto.UserLoginDto;
import com.example.springdatadtoexercise.model.dto.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    boolean hasLoggedInUser();

    boolean isAdmin();

    void gamePurchase(String title);

    void showOwnedGames();
}
