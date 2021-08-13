package com.example.mvc_project_lab.service.util;

public interface DataConverter {

    <T> T deserialize(String input, Class<T> type);

    String serialize(Object o);
}
