package com.example.objectmapping;

import com.example.objectmapping.models.dto.EmployeeCreateRequest;
import com.example.objectmapping.models.dto.EmployeeCreateResponse;
import com.example.objectmapping.models.dto.ManagerCollectionDto;
import com.example.objectmapping.models.dto.ManagerDto;
import com.example.objectmapping.services.EmployeeService;
import com.example.objectmapping.services.util.FormatConverter;
import com.example.objectmapping.services.util.FormatConverterFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    private final EmployeeService employeeService;

    private final FormatConverterFactory factory;

    public Main(EmployeeService employeeService, FormatConverterFactory factory) {
        this.employeeService = employeeService;
        this.factory = factory;
    }

    @Override
    public void run(String... args) throws Exception {
        var sc = new Scanner(System.in);

        System.out.println("Enter format type: ");
        String formatType = sc.nextLine();

        FormatConverter converter = this.factory.create(formatType);
        converter.setPrettyPrint();

        var line = sc.nextLine();

        while (!line.equals("end")) {
            var cmdParts = line.split(" ", 2);
            switch (cmdParts[0]) {
                case "find":
                    Long id = Long.parseLong(cmdParts[1]);
                    ManagerDto managerById = this.employeeService.findOne(id);

                    System.out.println(converter.serialize(managerById));
                    break;
                case "findAll":
                    List<ManagerDto> allManagers = this.employeeService.findAll();
                    System.out.println(converter.serialize(new ManagerCollectionDto(allManagers)));
                    break;
                case "save":
                    String input = cmdParts[1];

                    EmployeeCreateRequest request
                            = converter.deserialize(input, EmployeeCreateRequest.class);


                    EmployeeCreateResponse response = this.employeeService.save(
                            request
                    );

                    System.out.println(converter.serialize(response));
                    break;
                case "save-from-file":
                    EmployeeCreateRequest fileRequest = converter.deserializeFromFile(
                            cmdParts[1],
                            EmployeeCreateRequest.class
                    );

                    EmployeeCreateResponse fileResponse = this.employeeService.save(
                            fileRequest
                    );

                    System.out.println(converter.serialize(fileResponse));
                    break;
                case "findAll-to":
                    List<ManagerDto> managers = this.employeeService.findAll();
                    converter.serialize(
                            new ManagerCollectionDto(managers),
                            cmdParts[1] + "." + formatType
                    );

                    System.out.println("Written to file " + cmdParts[1]);

                    break;
                case "change-format":
                    converter = this.factory.create(cmdParts[1]);
                    System.out.println("Format changed to " + cmdParts[1]);
                    break;
                case "pretty-print":
                    converter.setPrettyPrint();
                    System.out.println("Success");
                    break;
            }

            line = sc.nextLine();
        }



//
//        List<ManagerDto> managers = this.employeeService.findAll();
//        managers.forEach(managerDto -> {
//            if (managerDto.getSubordinates().isEmpty()) {
//                return;
//            }
//            System.out.println(managerDto.getFirstName() + " " + managerDto.getLastName() + " (" + managerDto.getSubordinates().size() + "):");
//            managerDto.getSubordinates().forEach(employeeDto -> {
//                System.out.println("\t" + employeeDto.getFirstName() + " " + employeeDto.getLastName() + " : " + employeeDto.getIncome());
//            });
//        });
    }
}
