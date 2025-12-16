package com.example.bingo;

import com.example.bingo.repository.BingoPhraseRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BingoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoApplication.class, args);
	}

	@Bean
    public CommandLineRunner checkPhrases(BingoPhraseRepository repo) {
        return args -> {
            long count = repo.count();
            System.out.println("Bingo phrases loaded in DB: " + count);
            repo.findAll().forEach(p -> System.out.println(p.getPhrase() + " (" + p.getType() + ")"));
        };
    }

}
