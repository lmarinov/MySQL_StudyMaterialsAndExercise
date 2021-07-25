package springdataintro.exercise.services.impl;

import org.springframework.stereotype.Service;
import springdataintro.exercise.models.entities.Category;
import springdataintro.exercise.repositories.CategoryRepository;
import springdataintro.exercise.services.CategoryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_PATH = "src/main/resources/files/categories.txt";

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(categoryName -> {
                    Category category = new Category(categoryName);

                    categoryRepository.save(category);
                });
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randInt = ThreadLocalRandom.current().nextInt(1, 3);
        long cateRepoCount = categoryRepository.count();

        for (int i = 0; i < randInt; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, cateRepoCount + 1);

            categories.add(categoryRepository.findById(randomId).orElse(null));
        }

        return categories;
    }
}
