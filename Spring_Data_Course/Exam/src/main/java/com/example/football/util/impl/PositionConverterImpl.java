package com.example.football.util.impl;

import com.example.football.models.enumeration.Position;
import com.example.football.util.PositionConverter;

import javax.persistence.Converter;

@Converter
public class PositionConverterImpl implements PositionConverter {

    @Override
    public Integer convertToDatabaseColumn(Position attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case ATT:
                return 1;

            case MID:
                return 2;

            case DEF:
                return 3;

            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public Position convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return Position.ATT;

            case 2:
                return Position.MID;

            case 3:
                return Position.DEF;

            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}
