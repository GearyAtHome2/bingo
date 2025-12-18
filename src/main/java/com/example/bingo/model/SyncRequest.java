package com.example.bingo.model;

import java.util.List;

public class SyncRequest {
    private String email;
    private List<Boolean> crossed;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Boolean> getCrossed() { return crossed; }
    public void setCrossed(List<Boolean> crossed) { this.crossed = crossed; }
}
