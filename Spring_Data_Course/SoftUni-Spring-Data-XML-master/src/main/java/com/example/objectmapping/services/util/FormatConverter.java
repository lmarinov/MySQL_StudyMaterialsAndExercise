package com.example.objectmapping.services.util;

import com.example.objectmapping.exception.UnableToConvertException;

import javax.xml.bind.JAXBException;

public interface FormatConverter {

    void setPrettyPrint();

    String serialize(Object obj) throws UnableToConvertException;

    void serialize(Object obj, String fileName) throws UnableToConvertException;

    <T> T deserialize(String format, Class<T> toType) throws JAXBException;

    <T> T deserializeFromFile(String fileName, Class<T> toType);


}
