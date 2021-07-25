package springdataintro.exercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springdataintro.exercise.models.entities.Author;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a JOIN Book b ON a.id = b.author.id WHERE b.releaseDate < :releasedAfter")
    List<Author> findAllByBooksWithReleaseDateAfter(LocalDate releasedAfter);
}
