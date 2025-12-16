package com.example.bingo.dto;

import com.example.bingo.model.BingoCard;

import java.util.List;
import java.util.stream.Collectors;

public record BingoCardDto(
        List<BingoPhraseDto> phrases,  // <-- changed from List<String>
        List<Boolean> crossed,
        boolean bingo
) {
    public static BingoCardDto from(BingoCard card, boolean bingo) {
        List<BingoPhraseDto> phraseDtos = card.getPhrases().stream()
                .map(p -> BingoPhraseDto.from(p, card.getCrossed().get(card.getPhrases().indexOf(p))))
                .collect(Collectors.toList());

        return new BingoCardDto(
                phraseDtos,
                card.getCrossed(),
                bingo
        );
    }
}
