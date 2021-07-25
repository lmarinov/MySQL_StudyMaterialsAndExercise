package springdataintro.exercise.services;

import springdataintro.exercise.models.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    void findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);
}
