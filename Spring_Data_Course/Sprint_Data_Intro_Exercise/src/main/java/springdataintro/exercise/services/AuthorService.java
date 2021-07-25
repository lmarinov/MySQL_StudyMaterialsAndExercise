package springdataintro.exercise.services;

import springdataintro.exercise.models.entities.Author;

import java.io.IOException;

public interface AuthorService {
    void seedCategories() throws IOException;

    Author getRandomAuthor();
}
