package com.example.advquerying;

import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooService shampooService;
    private final Scanner scanner = new Scanner(System.in);
    private final IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Please enter task number:");

        int taskNumber = Integer.parseInt(scanner.nextLine());

        switch (taskNumber) {
            case 1 -> task1();
            case 2 -> task2();
            case 3 -> task3();
            case 4 -> task4();
            case 5 -> task5();
            case 6 -> task6();
            case 7 -> task7();
            case 8 -> task8();
            case 9 -> task9();
            case 10 -> task10();
            case 11 -> task11();
        }
    }

    private void task11() {
        System.out.println("Task 11");

        System.out.println("Enter percentage to change by (format: 1.n to n.n):");

        BigDecimal percentage = new BigDecimal(scanner.nextLine());

        System.out.println("Enter names not update prices for:");

        List<String> names = Arrays.asList(scanner.nextLine().split("\\s+"));

        int updated = this.ingredientService.updatePriceByNameAndPercentage(names, percentage);

        System.out.println("Updated rows " + updated);
    }

    private void task10() {
        System.out.println("Task 10");

        System.out.println("Prices before update:");

        this.ingredientService.findAll().forEach(System.out::println);

        int updated = this.ingredientService.updatePrice();

        System.out.println("Updated rows " + updated);

        System.out.println("Prices after update:");

        this.ingredientService.findAll().forEach(System.out::println);
    }

    private void task9() {
        System.out.println("Task 9");

        System.out.println("Enter ingredient name to delete by:");

        String ingredientName = scanner.nextLine();

        int updated = this.ingredientService.deleteAllByName(ingredientName);

        System.out.println("Deleted rows " + updated);
    }

    private void task8() {
        System.out.println("Task 8");

        System.out.println("Enter ingredients count:");

        long count = Long.parseLong(scanner.nextLine());

        this.shampooService.findAllByIngredientsCount(count).forEach(System.out::println);
    }

    private void task7() {
        System.out.println("Task 7");

        System.out.println("Enter names:");

        List<String> names = Arrays.asList(scanner.nextLine().split("\\s+"));

        this.shampooService.findAllByIngredientsNames(names).forEach(System.out::println);
    }

    private void task6() {
        System.out.println("Task 6");

        System.out.println("Please enter price:");

        BigDecimal price = new BigDecimal(scanner.nextLine());

        this.shampooService.findAllByPriceLessThan(price).forEach(System.out::println);
    }

    private void task5() {
        System.out.println("Task 5");

        System.out.println("Please enter names:");

        List<String> name = Arrays.asList(scanner.nextLine().split("\\s+"));

        this.ingredientService.findAllByNameIn(name).forEach(System.out::println);

    }

    private void task4() {
        System.out.println("Task 4");

        System.out.println("Please enter ingredient name:");
        String ingredientName = scanner.nextLine();

        this.ingredientService.findAllByNameStartingWith(ingredientName).forEach(System.out::println);
    }

    private void task3() {
        System.out.println("Task 3");

        System.out.println("Please enter price:");
        BigDecimal price = new BigDecimal(scanner.nextLine());

        this.shampooService.findAllByPriceGreaterThanOrderByPriceDesc(price).forEach(System.out::println);
    }

    private void task2() {
        System.out.println("Task 2");

        System.out.println("Please enter size:");

        Size size = Size.valueOf(scanner.nextLine());

        System.out.println("Please enter label id:");

        Long label_id = Long.parseLong(scanner.nextLine());

        shampooService.findAllBySizeOrLabel_IdOrderByPrice(size, label_id).forEach(System.out::println);
    }

    private void task1() {
        System.out.println("Task 1");

        System.out.println("Please enter size:");

        Size size = Size.valueOf(scanner.nextLine());

        shampooService.findAllBySizeOrderById(size).forEach(shampoo -> {

            System.out.println(shampoo.getBrand() + " " + shampoo.getSize() + " " + shampoo.getPrice());
        });
    }
}
