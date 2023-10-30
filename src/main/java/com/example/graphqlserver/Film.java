package com.example.graphqlserver;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    private String productionCompany;
    private String director;
    private String genre;
    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    Set<Actor> cast = new HashSet<>();

    public Film(String title, String productionCompany, String director, String genre) {
        this.title = title;
        this.productionCompany = productionCompany;
        this.director = director;
        this.genre = genre;
    }

    public Film() {

    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productionCompany='" + productionCompany + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", cast=" + cast +
                '}';
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public Set<Actor> getCast() {
        return cast;
    }

    public void setCast(Set<Actor> cast) {
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
