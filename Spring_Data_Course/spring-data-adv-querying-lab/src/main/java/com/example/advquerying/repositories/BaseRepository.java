package com.example.advquerying.repositories;

import com.example.advquerying.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<EntityType extends BaseEntity> extends JpaRepository<EntityType, Long> {
}
