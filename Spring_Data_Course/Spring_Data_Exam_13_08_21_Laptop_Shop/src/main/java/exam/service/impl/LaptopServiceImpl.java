package exam.service.impl;

import com.google.gson.Gson;
import exam.model.Laptop;
import exam.model.dto.LaptopSeedDto;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LaptopServiceImpl implements LaptopService {

    public final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private static final String LAPTOPS_FILE_PATH = "src/main/resources/files/json/laptops.json";
    private final LaptopRepository laptopRepository;
    private final ShopService shopService;

    public LaptopServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, LaptopRepository laptopRepository, ShopService shopService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.laptopRepository = laptopRepository;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files
                .readString(Path.of(LAPTOPS_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        LaptopSeedDto[] laptopSeedDtos = gson.fromJson(
                readLaptopsFileContent(),
                LaptopSeedDto[].class
        );

        Arrays.stream(laptopSeedDtos)
                .filter(laptopSeedDto -> {
                    boolean isValid = validationUtil.isValid(laptopSeedDto);

                    stringBuilder.append(isValid
                            ? String.format("Successfully imported Laptop %s - %.2f - %d - %d", laptopSeedDto.getMacAddress(), laptopSeedDto.getCpuSpeed(), laptopSeedDto.getRam(), laptopSeedDto.getStorage())
                            : "Invalid Customer")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(laptopSeedDto -> {
                    Laptop laptop = modelMapper.map(laptopSeedDto, Laptop.class);
                    laptop.setShop(this.shopService.findByName(laptopSeedDto.getShop().getName()));

                    return laptop;
                })
                .forEach(laptop -> {
                    if (this.laptopRepository.findFirstByMacAddress(laptop.getMacAddress()).orElse(null) == null){
                        this.laptopRepository.save(laptop);
                    }
                });

        return stringBuilder.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder stringBuilder = new StringBuilder();

        this.laptopRepository.findAllOrderedByCpuSpeedDescThenByRamDescThenByStorageDescThenByMacAddress()
                .forEach(laptop -> {

                   stringBuilder.append(String.format("Laptop - %s\n" +
                            "*Cpu speed - %.2f\n" +
                            "**Ram - %d\n" +
                            "***Storage - %d\n" +
                            "****Price - %.2f\n" +
                            "#Shop name - %s\n" +
                            "##Town - %s\n",
                           laptop.getMacAddress(),
                           laptop.getCpuSpeed(),
                           laptop.getRam(),
                           laptop.getStorage(),
                           laptop.getPrice(),
                           laptop.getShop().getName(),
                           laptop.getShop().getTown().getName()))
                   .append(System.lineSeparator());
                });

        return stringBuilder.toString();
    }
}
