package com.example.graphqlserver;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private DataService dataService;

    @QueryMapping
    public Film filmById(@Argument long id) {
        var res = filmRepository.findById(id);
        return res.orElse(null);
    }

    @QueryMapping
    public List<Film> filmsByTitle(@Argument String title) {
        return filmRepository.findByFuzzyTitleSearch(title);
    }

    @QueryMapping
    public List<Film> filmsByGenreAndDirector(@Argument String genre, @Argument String director) {
        return filmRepository.findFilmsByGenreAndDirector(genre, director);
    }


    @QueryMapping
    public List<Film> findByCastNameIn(@Argument List<String> actors) {
        return filmRepository.findByCastNameIn(actors);
    }

    @QueryMapping
    public List<Film> allFilms() {
        List<Film> films = new ArrayList<>();
        filmRepository.findAll().forEach(films::add);
         return films;
    }

    @SchemaMapping
    public List<Actor> cast(Film film) {
        return dataService.getFilmCast(film.getId());
    }

    @SchemaMapping
    public String id(Film film) {
        return film.getId().toString();
    }

    @SchemaMapping
    public String title(Film film) {
        return film.getTitle();
    }

    @SchemaMapping
    public String productionCompany(Film film) {
        return film.getProductionCompany();
    }

    @SchemaMapping
    public String director(Film film) {
        return film.getDirector();
    }

    @SchemaMapping
    public String genre(Film film) {
        return film.getGenre();
    }
}
