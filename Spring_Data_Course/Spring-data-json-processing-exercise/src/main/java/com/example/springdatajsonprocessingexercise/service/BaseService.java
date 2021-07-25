package com.example.springdatajsonprocessingexercise.service;

import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import com.example.springdatajsonprocessingexercise.repository.BaseRepository;

public interface BaseService {

    <Y extends BaseRepository<? extends BaseEntity>> String findRandomId(Y repository);
}
