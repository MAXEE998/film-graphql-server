package com.example.graphqlserver;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DataService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ActorRepository actorRepository;

    @Transactional
    public List<Actor> getFilmCast(Long filmId) {
        Film film = filmRepository.findById(filmId).orElse(null);
        if (film != null) {
            // Access the cast association within the @Transactional method
            return film.getCast().stream().toList();
        }
        return null;
    }

    @Transactional
    public List<Film> getActorFilmography(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElse(null);
        if (actor != null) {
            return actor.getFilmography().stream().toList();
        }
        return null;
    }

    @Transactional
    public void saveFilms(List<Film> films) {
        HashMap<String, Actor> actors = new HashMap<>();
        for (var film : films) {
            Set<Actor> cast = new HashSet<>();
            // create actors
            for (var actor : film.getCast()) {
                if (!actors.containsKey(actor.getName())) {
                    actors.put(actor.getName(), actorRepository.save(actor));
                }
                cast.add(actors.get(actor.getName()));
            }
            film.setCast(cast);
        }
        filmRepository.saveAll(films);
    }
}
