package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println("Please select exercise number:");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum){
            case 1 -> booksTitlesByAgeRest();
            case 2 -> goldBookTitlesWithCopiesLessThan5000();
            case 3 -> booksByPrice();
            case 4 -> notReleasedBooks();
            case 5 -> booksReleasedBeforeDate();
            case 6 -> authorsSearch();
            case 7 -> booksSearch();
            case 8 -> bookTitleSearch();
            case 9 -> countBooks();
            case 10 -> totalBookCopies();
            case 12 -> increaseBookCopies();
            case 99 -> test();
        }


    }

    private void increaseBookCopies() throws IOException {
        System.out.println("Please enter date in format dd MMM yyyy:");
        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd MMM yyyy"));

        System.out.println("Please enter copies:");
        int copies = Integer.parseInt(bufferedReader.readLine());

        System.out.println(this.bookService
                .increaseCopiesByDate(localDate, copies));
    }

    private void test() {
        this.bookService.changePrice(1L);
    }

    private void totalBookCopies() {
        this.authorService
                .findAllAuthorsAndTheirTotalCopies()
                .forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println("Please enter length of book title:");
        int titleLength = Integer.parseInt(bufferedReader.readLine());

        System.out.println(this.bookService
                .findCountOfBooksWithTitleLengthLongerThan(titleLength));
    }

    private void bookTitleSearch() throws IOException {
        System.out.println("Please enter string with which the name of an author has to start with:");
        String startStr = bufferedReader.readLine();

        this.bookService
                .findAllTitleWithAuthorWithLastNameStartsWith(startStr)
                .forEach(System.out::println);
    }

    private void booksSearch() throws IOException {
        System.out.println("Please enter containing letters in the book title:");
        String containsString = bufferedReader.readLine();

        this.bookService
                .findAllBookTitlesWhereTitleContainsStr(containsString)
                .forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println("Please enter the end of the first name of an author:");

        String endStr = bufferedReader.readLine();

        this.authorService.findAuthorFirstNameEndsWithStr(endStr)
                .forEach(System.out::println);
    }

    private void booksReleasedBeforeDate() throws IOException {
        System.out.println("Please provide date in format dd-MM-yyyy:");

        LocalDate localDate = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        this.bookService
                .findAllBooksBeforeDate(localDate)
                .forEach(System.out::println);
    }

    private void notReleasedBooks() throws IOException {
        System.out.println("Please enter year to skip:");

        Integer year = Integer.parseInt(bufferedReader.readLine());

        this.bookService
                .findNotReleasedBookTitlesInYear(year)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        this.bookService.findAllBookTitlesWithPriceLessThan5OrMoreThan40()
                .forEach(System.out::println);
    }

    private void goldBookTitlesWithCopiesLessThan5000() {
        this.bookService.findAllGoldBookTitlesWithCopiesLessThan5000()
                .forEach(System.out::println);
    }

    private void booksTitlesByAgeRest() throws IOException {
        System.out.println("Please enter age restriction:");

        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase(Locale.ROOT));

        this.bookService
                .findAllBookTitlesWithAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
