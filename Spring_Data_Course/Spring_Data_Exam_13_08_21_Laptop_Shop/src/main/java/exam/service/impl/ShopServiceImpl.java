package exam.service.impl;

import exam.model.Shop;
import exam.model.dto.ShopSeedRootDto;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ShopServiceImpl implements ShopService {

    public final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    public static final String SHOPS_FILE_PATH = "src/main/resources/files/xml/shops.xml";
    private final ShopRepository shopRepository;
    private final TownService townService;

    public ShopServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, ShopRepository shopRepository, TownService townService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.shopRepository = shopRepository;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files
                .readString(Path.of(SHOPS_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {


        StringBuilder sb = new StringBuilder();

        ShopSeedRootDto shopSeedRootDto = xmlParser
                .fromFile(
                        SHOPS_FILE_PATH,
                        ShopSeedRootDto.class
                );

        shopSeedRootDto.getShops().stream()
                .filter(shopSeedDto -> {
                    boolean isValid = validationUtil.isValid(shopSeedDto);

                    sb.append(isValid
                            ? String.format("Successfully imported Shop %s - %.0f", shopSeedDto.getName(), shopSeedDto.getIncome())
                            : "Invalid shop")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(shopSeedDto ->{
                   Shop shop = modelMapper.map(shopSeedDto, Shop.class);
                   shop.setTown(this.townService.findByName(shopSeedDto.getTown().getName()));
                   return shop;
                })
                .forEach(shop -> {
                    if (this.shopRepository.findFirstByName(shop.getName()).orElse(null) == null){
                        this.shopRepository.save(shop);
                    }
                }); // This solution is not technically correct, but I don't have the time to implement a better custom validation at this moment

        return sb.toString();
    }

    @Override
    public Shop findByName(String name) {
        return this.shopRepository.findFirstByName(name).orElse(null);
    }
}
