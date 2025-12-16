package com.example.bingo.config;

import com.example.bingo.model.BingoCard;
import java.util.List;
import com.example.bingo.repository.BingoCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFixer implements CommandLineRunner {

    private final BingoCardRepository cardRepo;

    public DatabaseFixer(BingoCardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        List<BingoCard> cards = cardRepo.findAll();
        for (BingoCard card : cards) {
            card.initializeCrossed();
            cardRepo.save(card);
        }        
    }
}
