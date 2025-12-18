package com.example.bingo.model;

import java.util.List;

public class CardSyncResponse {
    private List<Boolean> crossed;
    private boolean bingo;

    public CardSyncResponse(List<Boolean> crossed, boolean bingo) {
        this.crossed = crossed;
        this.bingo = bingo;
    }

    public List<Boolean> getCrossed() { return crossed; }
    public boolean isBingo() { return bingo; }
}
