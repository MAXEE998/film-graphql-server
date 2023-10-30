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
@Transactional
public class ActorController {
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DataService dataService;

    @QueryMapping
    public Actor actorById(@Argument long id) {
        var res = actorRepository.findById(id);
        return res.orElse(null);
    }

    @QueryMapping
    public List<Actor> allActors() {
        List<Actor> actors = new ArrayList<>();
        actorRepository.findAll().forEach(actors::add);
        return actors;
    }

    @SchemaMapping
    public List<Film> filmography(Actor actor) {
        return dataService.getActorFilmography(actor.getId());
    }

    @SchemaMapping
    public String id(Actor actor) {
        return actor.getId().toString();
    }

    @SchemaMapping
    public String name(Actor actor) {
        return actor.getName();
    }

}
