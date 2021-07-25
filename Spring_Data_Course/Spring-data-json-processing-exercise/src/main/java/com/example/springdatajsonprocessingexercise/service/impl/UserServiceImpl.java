package com.example.springdatajsonprocessingexercise.service.impl;

import com.example.springdatajsonprocessingexercise.constants.GlobalConstants;
import com.example.springdatajsonprocessingexercise.model.dto.UserSeedDto;
import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import com.example.springdatajsonprocessingexercise.model.entity.User;
import com.example.springdatajsonprocessingexercise.repository.BaseRepository;
import com.example.springdatajsonprocessingexercise.repository.UserRepository;
import com.example.springdatajsonprocessingexercise.service.BaseService;
import com.example.springdatajsonprocessingexercise.service.UserService;
import com.example.springdatajsonprocessingexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_NAME = "users.json";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {

        if (userRepository.count() == 0){
        Arrays.stream(gson.fromJson(
                Files
                        .readString(Path.of(GlobalConstants.RESOURCE_FILE_PATH + USERS_FILE_NAME)), UserSeedDto[].class))
                        .filter(validationUtil::isValid)
                        .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                        .forEach(userRepository::save);
        }
    }

    @Override
    public User findRandomUser() {
        return userRepository.findById(findRandomId(userRepository)).orElse(null);
    }

    @Override
    public <Y extends BaseRepository<? extends BaseEntity>> String findRandomId(Y repository) {
        BaseService baseService = new BaseServiceImpl();
        return baseService.findRandomId(repository);
    }
}