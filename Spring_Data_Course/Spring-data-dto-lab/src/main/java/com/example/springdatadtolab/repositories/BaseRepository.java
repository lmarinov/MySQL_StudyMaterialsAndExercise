package com.example.springdatadtolab.repositories;

import com.example.springdatadtolab.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<EntityType extends BaseEntity> extends JpaRepository<EntityType, Long> {
}
