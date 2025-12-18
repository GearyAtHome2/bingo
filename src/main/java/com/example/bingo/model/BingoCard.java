package com.example.bingo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bingo_cards")
public class BingoCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<BingoPhrase> phrases = new ArrayList<>();

    @ElementCollection
    private List<Boolean> crossed = new ArrayList<>();
    
    @PostLoad
    public void onLoad() {
        if (crossed == null) crossed = new ArrayList<>();
        if (phrases == null) phrases = new ArrayList<>();
        while (crossed.size() < phrases.size()) {
            crossed.add(false);
        }
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and setters
    public Long getId() { return id; }
    public List<BingoPhrase> getPhrases() { return phrases; }
    public void setPhrases(List<BingoPhrase> phrases) { 
        this.phrases = phrases;
        initializeCrossed(); // ensure crossed is same size
    }

    public List<Boolean> getCrossed() { return crossed; }
    public void setCrossed(List<Boolean> crossed) { this.crossed = crossed; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }


    public void initializeCrossed() {
        if (crossed == null) crossed = new ArrayList<>();
        if (phrases == null) phrases = new ArrayList<>();
        
        if (crossed.size() > phrases.size()) {
            crossed = crossed.subList(0, phrases.size());
        }
        
        while (crossed.size() < phrases.size()) {
            crossed.add(false);
        }
    }
    
}
