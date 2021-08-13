package com.example.football.util;

import com.example.football.models.enumeration.Position;

import javax.persistence.AttributeConverter;

public interface PositionConverter extends AttributeConverter<Position, Integer> {

    Integer convertToDatabaseColumn(Position attribute);
    Position convertToEntityAttribute(Integer dbData);
}
