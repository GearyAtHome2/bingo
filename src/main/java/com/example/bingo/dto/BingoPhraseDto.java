package com.example.bingo.dto;

import com.example.bingo.model.BingoPhrase;

public class BingoPhraseDto {

    private String text;
    private boolean crossed;
    private String type;

    public BingoPhraseDto(String text, boolean crossed, String type) {
        this.text = text;
        this.crossed = crossed;
        this.type = type;
    }

    // Factory method from BingoPhrase entity
    public static BingoPhraseDto from(BingoPhrase phrase, boolean crossed) {
        return new BingoPhraseDto(
            phrase.getPhrase(),
            crossed,
            phrase.getType().name()
        );
    }

    // Getters
    public String getText() { return text; }
    public boolean isCrossed() { return crossed; }
    public String getType() { return type; }
}
