package com.example.springdatajsonprocessingexercise.repository;

import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@NoRepositoryBean
public interface BaseRepository<E> extends JpaRepository<E, String> {

}
