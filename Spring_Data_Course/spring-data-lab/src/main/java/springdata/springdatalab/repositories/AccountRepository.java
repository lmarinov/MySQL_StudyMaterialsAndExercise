package springdata.springdatalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.springdatalab.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
