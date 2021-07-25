package springdataintro.exercise.initializers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdataintro.exercise.models.entities.Book;
import springdataintro.exercise.services.AuthorService;
import springdataintro.exercise.services.BookService;
import springdataintro.exercise.services.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;

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

        while (true){
            System.out.println("Please enter query number or 'x' to close");

            String input = bufferedReader.readLine();

            switch (input){
                case "1" -> printAllBooksAfterYear(2000);
                case "2" -> printAllAuthorsNamesWithBooksReleaseDateBeforeYear(1990);
                case "3" -> printAllAuthorsFullNamesAndBookCountByBookCountDesc();
                case "4" -> printAllBookTitlesAndReleaseDateAndCopiesByAuthorOrderedByReleaseDateDescThenByBookTitle();
            }
        }

    }

    private void printAllBookTitlesAndReleaseDateAndCopiesByAuthorOrderedByReleaseDateDescThenByBookTitle() {

    }

    private void printAllAuthorsFullNamesAndBookCountByBookCountDesc() {

    }

    private void printAllAuthorsNamesWithBooksReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year);
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
        authorService.seedCategories();
        bookService.seedBooks();
    }
}
