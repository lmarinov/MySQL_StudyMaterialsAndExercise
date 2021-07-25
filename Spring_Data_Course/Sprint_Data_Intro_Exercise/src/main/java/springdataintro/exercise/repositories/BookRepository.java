package springdataintro.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import springdataintro.exercise.models.entities.Book;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);
}
