package com.ilya.ivanov.slides;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@Slf4j
public class SlidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlidesApplication.class, args);
    }

    @Bean
    public CommandLineRunner indexer(EntityManagerFactory entityManagerFactory) {
        return args -> {
            val em = entityManagerFactory.createEntityManager();
            val fullTextEntityManager = Search.getFullTextEntityManager(em);
            fullTextEntityManager.createIndexer().startAndWait();
        };
    }
}
