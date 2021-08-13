package com.example.springdatadtoexercise.configuration;

import com.example.springdatadtoexercise.model.dto.GameAddDto;
import com.example.springdatadtoexercise.model.entity.Game;
import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };

        Converter<String, LocalDate> localDateConverter = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return source == null
                        ? null
                        : LocalDate.parse(source, format);
            }
        };

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(localDateConverter);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        modelMapper
                .typeMap(GameAddDto.class, Game.class)
                .addMappings(mapper -> mapper.map(GameAddDto::getThumbnailUrl,
                        Game::setImageThumbnail));


        return modelMapper;
    }

    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
