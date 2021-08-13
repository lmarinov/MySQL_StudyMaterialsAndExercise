package com.example.football.util;

public interface DataConverter {

    <T> T deserialize(String input, Class<T> type);

    String serialize(Object o);
}
