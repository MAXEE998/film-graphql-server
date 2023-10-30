package com.example.graphqlserver;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "cast")
    private Set<Film> filmography;

    public Actor() {

    }

    public String getName() {
        return name;
    }

    public Set<Film> getFilmography() {
        return filmography;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Actor(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
