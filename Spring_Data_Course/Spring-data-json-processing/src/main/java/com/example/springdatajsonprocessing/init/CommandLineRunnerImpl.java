package com.example.springdatajsonprocessing.init;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final Gson gson;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(Gson gson, BufferedReader bufferedReader) {
        this.gson = gson;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        this.gson.toJson("as");

        System.out.println("Hello");
    }
}
