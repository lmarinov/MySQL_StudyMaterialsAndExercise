package com.example.springdatajsonprocessingexercise.service.impl;

import com.example.springdatajsonprocessingexercise.model.dto.ProductSeedDto;
import com.example.springdatajsonprocessingexercise.model.entity.BaseEntity;
import com.example.springdatajsonprocessingexercise.model.entity.Product;
import com.example.springdatajsonprocessingexercise.repository.BaseRepository;
import com.example.springdatajsonprocessingexercise.repository.ProductRepository;
import com.example.springdatajsonprocessingexercise.service.BaseService;
import com.example.springdatajsonprocessingexercise.service.CategoryService;
import com.example.springdatajsonprocessingexercise.service.ProductService;
import com.example.springdatajsonprocessingexercise.service.UserService;
import com.example.springdatajsonprocessingexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.springdatajsonprocessingexercise.constants.GlobalConstants.RESOURCE_FILE_PATH;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_FILE_NAME = "products.json";

    public final Gson gson;
    private final UserService userservice;
    private final ValidationUtil validationUtil;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(Gson gson, UserService userservice, ValidationUtil validationUtil, CategoryService categoryService, ProductRepository productRepository, ModelMapper modelMapper) {
        this.gson = gson;
        this.userservice = userservice;
        this.validationUtil = validationUtil;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0){
            return;
        }

        String fileContents = Files
                .readString(Path.of(RESOURCE_FILE_PATH + PRODUCTS_FILE_NAME));

        ProductSeedDto[] productSeedDtos = gson
                .fromJson(fileContents, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userservice.findRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(500L)) > 0){
                        product.setBuyer(userservice.findRandomUser());
                    }

                    product.setCategories(categoryService.findRandomCategories());

                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public Set<Product> findRandomProducts() {
        Set<Product> products = new HashSet<>();
        int productCount = ThreadLocalRandom.current().nextInt(1, 3);

        for (int i = 0; i < productCount; i++) {
            products.add(productRepository.findById(findRandomId(productRepository)).orElse(null));
        }
        return products;
    }

    @Override
    public <Y extends BaseRepository<? extends BaseEntity>> String findRandomId(Y repository) {
        BaseService baseService = new BaseServiceImpl();
        return baseService.findRandomId(repository);
    }
}
