package com.example.bingo.repository;

import com.example.bingo.model.BingoPhrase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BingoPhraseRepository extends JpaRepository<BingoPhrase, Long> {
    List<BingoPhrase> findAll();
}
