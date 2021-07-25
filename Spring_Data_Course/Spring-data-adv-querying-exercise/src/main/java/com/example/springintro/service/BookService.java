package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBookTitlesWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllGoldBookTitlesWithCopiesLessThan5000();

    List<String> findAllBookTitlesWithPriceLessThan5OrMoreThan40();

    List<String> findNotReleasedBookTitlesInYear(Integer year);

    List<String> findAllBooksBeforeDate(LocalDate localDate);

    List<String> findAllBookTitlesWhereTitleContainsStr(String containsString);

    List<String> findAllTitleWithAuthorWithLastNameStartsWith(String startStr);

    int findCountOfBooksWithTitleLengthLongerThan(int titleLength);

    void changePrice(long bookId);

    int increaseCopiesByDate(LocalDate localDate, int copies);
}
