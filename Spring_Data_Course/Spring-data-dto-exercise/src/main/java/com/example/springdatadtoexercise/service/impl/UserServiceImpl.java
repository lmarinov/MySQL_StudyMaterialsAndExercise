package com.example.springdatadtoexercise.service.impl;

import com.example.springdatadtoexercise.model.dto.UserLoginDto;
import com.example.springdatadtoexercise.model.dto.UserRegisterDto;
import com.example.springdatadtoexercise.model.entity.User;
import com.example.springdatadtoexercise.repository.UserRepository;
import com.example.springdatadtoexercise.service.UserService;
import com.example.springdatadtoexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = this.validationUtil.getViolations(userLoginDto);

        if (!violations.isEmpty()){
            violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            return;
        }

        User user = userRepository
                .findEmailAndFullName(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if(user == null){
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
    }

    @Override
    public void logout() {
        if(loggedInUser == null){
            System.out.println("Cannot log out. No user was logged in.");
        }else{
            loggedInUser = null;
        }
    }

    @Override
    public boolean hasLoggedInUser() {
        return loggedInUser != null;
    }
}
