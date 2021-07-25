package springdata.springdatalab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import springdata.springdatalab.services.UserService;

import java.math.BigDecimal;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.userService.registerUser("pesho", 25, new BigDecimal(1000));
    }
}
