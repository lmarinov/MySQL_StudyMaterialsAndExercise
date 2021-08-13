package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.CarSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {


    public final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private static final String CARS_FILE_PATH = "src/main/resources/files/json/cars.json";
    private final CarRepository carRepository;

    public CarServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() >0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();


        CarSeedDto[] carSeedDtos = gson.fromJson(
                readCarsFileContent(),
                CarSeedDto[].class
        );

        Arrays.stream(carSeedDtos)
                .filter(carSeedDto -> {
                      boolean isValid = validationUtil.isValid(carSeedDto);
                      if (isValid) stringBuilder.append(String.format("Successfully imported car - %s - %s", carSeedDto.getMake(), carSeedDto.getModel()));
                      else stringBuilder.append("Invalid car");

                      stringBuilder.append(System.lineSeparator());

                      return isValid;
                })
                .map(carSeedDto -> modelMapper.map(carSeedDto, Car.class))
                .forEach(this.carRepository::save);

        return stringBuilder.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        StringBuilder sb = new StringBuilder();

        this.carRepository.findAllCarsOrderByPicturesCountThenByMake()
                .forEach(car -> {
                    sb.append(String.format("Car make - %s, model - %s\n" +
                            "\tKilometers - %d\n" +
                            "\tRegistered on - %s\n" +
                            "\tNumber of pictures - %d\n",
                            car.getMake(), car.getModel(),
                            car.getKilometers(), car.getRegisteredOn(),
                            car.getPictures().size()))
                            .append(System.lineSeparator());

                });

        return sb.toString();
    }

    @Override
    public Car findById(Long car) {
        return this.carRepository
                .findById(car)
                .orElse(null);
    }
}
