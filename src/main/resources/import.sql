INSERT INTO bingo_phrases (phrase, type) VALUES ('Footy talk', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Somebody accused of drinking too much', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Viv mentions Parkrun', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Nat mentions Mr Warsons', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Food complaint', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Service complaint', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Building temperature complaint', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('3 different people named Sue are mentioned', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Mild Homophobia', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Severe Homophobia', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('ChatGPT', 'NORMAL') ON CONFLICT (phrase) DO NOTHING;

INSERT INTO bingo_phrases (phrase, type) VALUES ('"Who`s going to have a baby first?"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"Ravaged by Wolves"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"Eaten by Sharks"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"Mugged by Monkeys"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"Bombaclaat"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"My neck and back"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('"Arsehole"', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;

INSERT INTO bingo_phrases (phrase, type) VALUES ('Woke Nonsense', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Nigel Farage', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Bloody Labour', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Two Tier Kier', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Just Stop Oil!?', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;

INSERT INTO bingo_phrases (phrase, type) VALUES ('Swap two people`s drinks', 'CHALLENGE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Get 5 people clapping at the same time', 'CHALLENGE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('Convince somebody you`re going on holiday to Minsk', 'CHALLENGE') ON CONFLICT (phrase) DO NOTHING;

INSERT INTO bingo_phrases (phrase, type) VALUES ('placeholder 3', 'PATRIOT') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('placeholder 10', 'CHALLENGE') ON CONFLICT (phrase) DO NOTHING;
INSERT INTO bingo_phrases (phrase, type) VALUES ('placeholder 17', 'DECLARE') ON CONFLICT (phrase) DO NOTHING;
