package com.example.mvc_project_lab.service.impl;

import com.example.mvc_project_lab.dto.UserLoginDto;
import com.example.mvc_project_lab.dto.UserRegisterDto;
import com.example.mvc_project_lab.entity.User;
import com.example.mvc_project_lab.repository.UserRepository;
import com.example.mvc_project_lab.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
       if (this.userRepository.existsByUsernameOrEmail(
               userRegisterDto.getUsername(),
               userRegisterDto.getEmail()
       )){
           return false;
       }

       if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
           return false;
       }

       var user = this.modelMapper.map(
               userRegisterDto,
               User.class
       );

       this.userRepository.save(user);

       return true;
    }

    @Override
    public Long validateUserLoginDetails(UserLoginDto userRequest) {
        var user = this.userRepository.findFirstByUsername(userRequest.getUsername());

        if (user == null){
            return null;
        }

        if (!user.getPassword().equals(userRequest.getPassword())){
            return null;
        }

        return user.getId();
    }
}
