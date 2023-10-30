package com.example.graphqlserver;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends CrudRepository<Film, Long> {
    // Add a custom query method to find films by a list of actor names
    List<Film> findByCastNameIn(List<String> actorNames);
    // Add a custom query method to find films by their title (name)
    @Query("SELECT f FROM Film f WHERE LOWER(f.title) LIKE %:title%")
    List<Film> findByFuzzyTitleSearch(@Param("title") String title);

    @Query("SELECT f FROM Film f WHERE f.genre = :genre AND f.director = :director")
    List<Film> findFilmsByGenreAndDirector(
            @Param("genre") String genre,
            @Param("director") String director
    );
}
