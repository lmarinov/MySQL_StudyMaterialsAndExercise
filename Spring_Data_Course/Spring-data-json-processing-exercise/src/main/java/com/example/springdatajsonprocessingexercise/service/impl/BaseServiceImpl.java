package com.example.springdatajsonprocessingexercise.service.impl;

import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import com.example.springdatajsonprocessingexercise.repository.BaseRepository;
import com.example.springdatajsonprocessingexercise.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public <Y extends BaseRepository<? extends BaseEntity>> String findRandomId(Y repository) {
        List<String> allUUIDS = repository
                .findAll()
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        int totalIdCount = allUUIDS.size();
        int randomIndex = ThreadLocalRandom
                .current().nextInt(0,  totalIdCount - 1);

        return allUUIDS.get(randomIndex);
    }
}
