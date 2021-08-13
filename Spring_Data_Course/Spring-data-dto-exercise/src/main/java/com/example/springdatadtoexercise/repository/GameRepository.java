package com.example.springdatadtoexercise.repository;

import com.example.springdatadtoexercise.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    /*will have to join tables games and users_games by game_id and search for
    a game id with the wanted title that does not have a user_id associated with it*/

    @Query(value = "SELECT * FROM games AS g LEFT JOIN users_games As ug ON g.id = ug.games_id WHERE g.title = :title AND ug.user_id IS NULL LIMIT 1", nativeQuery = true)
    Optional<Game> findOneByTitleWithoutOwner(String title);

    @Query("select g from Game g where g.title = ?1")
    List<Game> findAllByTitle(String title);
}
