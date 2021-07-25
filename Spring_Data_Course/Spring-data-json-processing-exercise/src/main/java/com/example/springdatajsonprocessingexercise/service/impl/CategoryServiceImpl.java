package com.example.springdatajsonprocessingexercise.service.impl;

import com.example.springdatajsonprocessingexercise.model.dto.CategorySeedDto;
import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import com.example.springdatajsonprocessingexercise.model.entity.Category;
import com.example.springdatajsonprocessingexercise.repository.BaseRepository;
import com.example.springdatajsonprocessingexercise.repository.CategoryRepository;
import com.example.springdatajsonprocessingexercise.service.BaseService;
import com.example.springdatajsonprocessingexercise.service.CategoryService;
import com.example.springdatajsonprocessingexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.springdatajsonprocessingexercise.constants.GlobalConstants.RESOURCE_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    public static final String CATEGORIES_FILE_NAME = "categories.json";
    public final Gson gson;
    private final ValidationUtil validationUtil;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(Gson gson, ValidationUtil validationUtil, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories() throws IOException {

        if (categoryRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCE_FILE_PATH + CATEGORIES_FILE_NAME));


        CategorySeedDto[] categorySeedDtos = gson
                .fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }

    @Override
    public Set<Category> findRandomCategories() {
        Set<Category> categorySet = new HashSet<>();
        int catCount = ThreadLocalRandom.current().nextInt(1, 3);

        for (int i = 0; i < catCount; i++) {
            categorySet.add(categoryRepository.findById(findRandomId(categoryRepository)).orElse(null));
        }
        return categorySet;
    }

    @Override
    public <Y extends BaseRepository<? extends BaseEntity>> String findRandomId(Y repository) {
        BaseService baseService = new BaseServiceImpl();
        return baseService.findRandomId(repository);
    }
}
