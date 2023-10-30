package com.example.graphqlserver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class GraphqlserverApplication {
    private static final Logger log = LoggerFactory.getLogger(GraphqlserverApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GraphqlserverApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(DataService ds) {
        return (args) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            File json = new File("./films.json");
            try {
                List<Film> films = objectMapper.readValue(json, new TypeReference<>() {
                });
                ds.saveFilms(films);
                log.info("Films data saved to the database.");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        };
    }
}
