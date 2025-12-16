package com.example.bingo.repository;

import com.example.bingo.model.BingoCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BingoCardRepository extends JpaRepository<BingoCard, Long> {}
