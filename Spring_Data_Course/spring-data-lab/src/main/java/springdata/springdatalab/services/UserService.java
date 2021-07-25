package springdata.springdatalab.services;

import java.math.BigDecimal;

public interface UserService {
    void registerUser(String username, int age, BigDecimal initialAmount);
}
