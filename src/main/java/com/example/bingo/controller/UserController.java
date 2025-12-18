package com.example.bingo.controller;

import com.example.bingo.model.User;
import com.example.bingo.model.BingoCard;
import com.example.bingo.model.CardSyncResponse;
import com.example.bingo.model.SyncRequest;
import com.example.bingo.dto.BingoCardDto;
import com.example.bingo.repository.UserRepository;
import com.example.bingo.repository.BingoCardRepository;
import com.example.bingo.service.BingoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BingoCardRepository cardRepo;

    @Autowired
    private BingoService bingoService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok("{\"status\":\"ok\"}");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (req.getEmail() == null || req.getPassword() == null) {
            return ResponseEntity.badRequest().body("{\"error\":\"Missing email or password\"}");
        }

        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("{\"error\":\"User already exists\"}");
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setPasswordHash(encoder.encode(req.getPassword()));

        BingoCard card = new BingoCard();
        card.setPhrases(bingoService.generateCard());
        card.setUser(user);
        user.setBingoCard(card);

        System.out.println("generated user with card: "+card);

        userRepo.save(user);

        return ResponseEntity.status(201).body("{\"message\":\"User created\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterRequest req) {
        if (req.getEmail() == null || req.getPassword() == null) {
            return ResponseEntity.badRequest().body("{\"error\":\"Missing email or password\"}");
        }

        User user = userRepo.findByEmail(req.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).body("{\"error\":\"User not found\"}");
        }

        if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(401).body("{\"error\":\"Invalid password\"}");
        }

        return ResponseEntity.ok("{\"message\":\"Login successful\"}");
    }


    @PostMapping("/toggle")
    public ResponseEntity<?> togglePhrase(@RequestBody ToggleRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
                            .orElse(null);
        if (user == null) return ResponseEntity.badRequest().body("{\"error\":\"User not found\"}");

        BingoCard card = user.getBingoCard();
        bingoService.togglePhrase(card, req.getIndex());
        cardRepo.save(card);

        boolean bingo = bingoService.hasBingo(card);

        return ResponseEntity.ok("{\"crossed\":" + card.getCrossed() + ", \"bingo\":" + bingo + "}");
    }

    @PostMapping("/sync")
    public ResponseEntity<?> syncCard(@RequestBody SyncRequest req) {

        User user = userRepo.findByEmail(req.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(403).build();
        }

        BingoCard card = user.getBingoCard();
        if (card == null) {
            return ResponseEntity.badRequest().body("No card");
        }

        bingoService.applyCrossedState(card, req.getCrossed());
        cardRepo.save(card);

        boolean bingo = bingoService.hasBingo(card);

        return ResponseEntity.ok(
            new CardSyncResponse(card.getCrossed(), bingo)
        );
    }

    
    @GetMapping("/card")
    public ResponseEntity<BingoCardDto> getCard(@RequestParam String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(401).body(null);
        }
    
        BingoCard card = user.getBingoCard();
        return ResponseEntity.ok(BingoCardDto.from(card, bingoService.hasBingo(card)));
    }

static class ToggleRequest {
    private String email;
    private int index; // 0-24 for the cell
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }
}


    static class RegisterRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
