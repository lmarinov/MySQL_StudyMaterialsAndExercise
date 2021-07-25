package springdataintro.exercise.services.impl;

import org.springframework.stereotype.Service;
import springdataintro.exercise.models.entities.Author;
import springdataintro.exercise.models.entities.Category;
import springdataintro.exercise.repositories.AuthorRepository;
import springdataintro.exercise.services.AuthorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(authorName -> {
                    String[] fullName = authorName.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
        ;
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1, authorRepository.count() + 1);

        return authorRepository.findById(randomId).orElse(null);
    }
}
