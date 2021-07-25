package com.example.springdatajsonprocessingexercise.service;

import com.example.springdatajsonprocessingexercise.model.entity.User;

import java.io.IOException;

public interface UserService extends BaseService{
    void seedUsers() throws IOException;

    User findRandomUser();
}
