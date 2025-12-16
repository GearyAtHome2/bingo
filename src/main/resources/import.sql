-- Ensure the table exists
CREATE TABLE IF NOT EXISTS bingo_phrases (
    id SERIAL PRIMARY KEY,
    phrase TEXT NOT NULL,
    type TEXT NOT NULL,
    UNIQUE(phrase)
);

-- Optional: clear table before inserting
TRUNCATE TABLE bingo_phrases RESTART IDENTITY;

-- Insert phrases
INSERT INTO bingo_phrases (phrase, type) VALUES
('Footy talk', 'NORMAL'),
('Somebody accused of drinking too much', 'NORMAL'),
('Viv mentions Parkrun', 'NORMAL'),
('Nat mentions Mr Warsons', 'NORMAL'),
('Food complaint', 'NORMAL'),
('Service complaint', 'NORMAL'),
('Building temperature complaint', 'NORMAL'),
('3 different people named Sue are mentioned', 'NORMAL'),
('Mild Homophobia', 'NORMAL'),
('Severe Homophobia', 'NORMAL'),
('ChatGPT', 'NORMAL'),
('"Who`s going to have a baby first?"', 'DECLARE'),
('"Ravaged by Wolves"', 'DECLARE'),
('"Eaten by Sharks"', 'DECLARE'),
('"Mugged by Monkeys"', 'DECLARE'),
('"Bombaclaat"', 'DECLARE'),
('"My neck and back"', 'DECLARE'),
('"Arsehole"', 'DECLARE'),
('Woke Nonsense', 'PATRIOT'),
('Nigel Farage', 'PATRIOT'),
('Bloody Labour', 'PATRIOT'),
('Two Tier Kier', 'PATRIOT'),
('Just Stop Oil!?', 'PATRIOT'),
('Swap two people`s drinks', 'CHALLENGE'),
('Get 5 people clapping at the same time', 'CHALLENGE'),
('Convince somebody you`re going on holiday to Minsk', 'CHALLENGE'),
('placeholder 3', 'PATRIOT'),
('placeholder 10', 'CHALLENGE'),
('placeholder 17', 'DECLARE');
