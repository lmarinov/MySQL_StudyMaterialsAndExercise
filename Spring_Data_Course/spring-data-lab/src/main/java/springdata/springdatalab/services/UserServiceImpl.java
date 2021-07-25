package springdata.springdatalab.services;

import org.springframework.stereotype.Service;
import springdata.springdatalab.models.Account;
import springdata.springdatalab.models.User;
import springdata.springdatalab.repositories.AccountRepository;
import springdata.springdatalab.repositories.UserRepository;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private  final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void registerUser(String username, int age, BigDecimal initialAmount) {
        var user = new User();
        user.setUsername(username);
        user.setAge(age);

        this.userRepository.save(user);

        var firstAccount = new Account();
        firstAccount.setBalance(initialAmount);
        firstAccount.setUser(user);

        this.accountRepository.save(firstAccount);
    }
}
