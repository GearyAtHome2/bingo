package com.example.bingo.model;

import java.util.List;

public class SyncRequest {
    private String email;
    private List<Boolean> crossed;

    public String getEmail() { return email; }
    public List<Boolean> getCrossed() { return crossed; }
}
