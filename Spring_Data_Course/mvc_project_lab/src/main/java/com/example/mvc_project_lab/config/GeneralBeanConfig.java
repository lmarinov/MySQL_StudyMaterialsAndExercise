package com.example.mvc_project_lab.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralBeanConfig {

    @Bean
    public ModelMapper ModelMapper(){
        return new ModelMapper();
    }
}
