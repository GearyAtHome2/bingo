package com.example.bingo.service;

import com.example.bingo.model.BingoCard;
import com.example.bingo.model.BingoPhrase;
import com.example.bingo.repository.BingoPhraseRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BingoService {
    
    private final BingoPhraseRepository phraseRepo;

    public BingoService(BingoPhraseRepository phraseRepo) {
        this.phraseRepo = phraseRepo;
    }

    public List<BingoPhrase> generateCard() {
        List<BingoPhrase> allPhrases = phraseRepo.findAll();
        Collections.shuffle(allPhrases);
        return allPhrases.stream()
                .limit(25)
                .collect(Collectors.toList());
    }

    public void applyCrossedState(BingoCard card, List<Boolean> newCrossed) {
        if (card == null || newCrossed == null) return;
    
        card.initializeCrossed();
    
        List<Boolean> crossed = card.getCrossed();
    
        // Safety: enforce correct size
        if (newCrossed.size() != crossed.size()) return;
    
        for (int i = 0; i < crossed.size(); i++) {
            crossed.set(i, newCrossed.get(i));
        }
    
        card.setCrossed(crossed);
    }
            

    public void togglePhrase(BingoCard card, int index) {
        if (card == null) {
            return;
        }

        card.getCrossed().size();
        card.getPhrases().size();
    
        card.initializeCrossed();
        List<Boolean> crossed = card.getCrossed();
        if (crossed == null) {
            crossed = new ArrayList<>();
            card.setCrossed(crossed);
        }
    
        if (index < 0 || index >= crossed.size()) {
            return;
        }

        if (index < 0 || index >= crossed.size()) return;
    
        crossed.set(index, !crossed.get(index));
        card.setCrossed(crossed);
    }
    

    public boolean hasBingo(BingoCard card) {
        card.initializeCrossed();

        List<Boolean> c = card.getCrossed();

        // check rows
        for (int row = 0; row < 5; row++) {
            boolean rowComplete = true;
            for (int col = 0; col < 5; col++) {
                if (!c.get(row * 5 + col)) {
                    rowComplete = false;
                    break;
                }
            }
            if (rowComplete) return true;
        }

        // check columns
        for (int col = 0; col < 5; col++) {
            boolean colComplete = true;
            for (int row = 0; row < 5; row++) {
                if (!c.get(row * 5 + col)) {
                    colComplete = false;
                    break;
                }
            }
            if (colComplete) return true;
        }

        // check diagonals
        boolean diag1 = true, diag2 = true;
        for (int i = 0; i < 5; i++) {
            if (!c.get(i * 5 + i)) diag1 = false;
            if (!c.get(i * 5 + (4 - i))) diag2 = false;
        }
        return diag1 || diag2;
    }
}
