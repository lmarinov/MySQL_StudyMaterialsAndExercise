package springdata.springdatalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import springdata.springdatalab.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
