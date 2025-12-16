package com.example.bingo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bingo_phrases")
public class BingoPhrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String phrase;

    @Enumerated(EnumType.STRING)
    private PhraseType type = PhraseType.NORMAL; // default to NORMAL

    public BingoPhrase(String phrase, String type){
        this.phrase = phrase;
        this.type = PhraseType.valueOf(type);
    }

    public enum PhraseType {
        NORMAL,
        CHALLENGE,
        PATRIOT,
        DECLARE
    }
    
    // getters and setters
    public Long getId() { return id; }
    public String getPhrase() { return phrase; }
    public void setPhrase(String phrase) { this.phrase = phrase; }

    public PhraseType getType() { return type; }
    public void setType(PhraseType type) { this.type = type; }
}
