package com.example.mvc_project_lab.service;

import com.example.mvc_project_lab.dto.UserLoginDto;
import com.example.mvc_project_lab.dto.UserRegisterDto;

public interface UserService {

    boolean register(UserRegisterDto userRegisterDto);

    Long validateUserLoginDetails(UserLoginDto user);
}
