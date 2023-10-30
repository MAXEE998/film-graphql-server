package com.example.graphqlserver;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {
    Actor findByName(String name);

    default Actor findOrCreateByName(String name) {
        Actor actor = findByName(name);
        if (actor == null) {
            actor = new Actor(name);
            actor = save(actor);
        }
        return actor;
    }
}