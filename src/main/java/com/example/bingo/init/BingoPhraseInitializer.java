import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Column;
import java.util.List;

import com.example.bingo.model.BingoPhrase;

@Component
public class BingoPhraseInitializer implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Long> count = em.createQuery("SELECT COUNT(p) FROM BingoPhrase p", Long.class)
                             .getResultList();

        if (count.isEmpty() || count.get(0) == 0) {
            System.out.println("Initializing Bingo phrases...");

            String[][] phrases = {
                {"Footy talk","NORMAL"},
                {"Somebody accused of drinking too much","NORMAL"},
                {"Viv mentions Parkrun","NORMAL"},
                {"Nat mentions Mr Warsons","NORMAL"},
                {"Food complaint","NORMAL"},
                {"Service complaint","NORMAL"},
                {"Building temperature complaint","NORMAL"},
                {"3 different people named Sue are mentioned","NORMAL"},
                {"Mild Homophobia","NORMAL"},
                {"Severe Homophobia","NORMAL"},
                {"ChatGPT","NORMAL"},
                {"\"Who`s going to have a baby first?\"","DECLARE"},
                {"\"Ravaged by Wolves\"","DECLARE"},
                {"\"Eaten by Sharks\"","DECLARE"},
                {"\"Mugged by Monkeys\"","DECLARE"},
                {"\"Bombaclaat\"","DECLARE"},
                {"\"My neck and back\"","DECLARE"},
                {"\"Arsehole\"","DECLARE"},
                {"Woke Nonsense","PATRIOT"},
                {"Nigel Farage","PATRIOT"},
                {"Bloody Labour","PATRIOT"},
                {"Two Tier Kier","PATRIOT"},
                {"Just Stop Oil!?","PATRIOT"},
                {"Swap two people`s drinks","CHALLENGE"},
                {"Get 5 people clapping at the same time","CHALLENGE"},
                {"Convince somebody you`re going on holiday to Minsk","CHALLENGE"},
                {"placeholder 3","PATRIOT"},
                {"placeholder 10","CHALLENGE"},
                {"placeholder 17","DECLARE"}
            };

            for (String[] p : phrases) {
                em.persist(new BingoPhrase(p[0], p[1]));
                System.out.println("Inserted: " + p[0]);
            }
        } else {
            System.out.println("Bingo phrases already present in DB: " + count.get(0));
        }
    }
}
