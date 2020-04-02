CREATE TABLE ability (
    ability_name VARCHAR(20),
    description VARCHAR(200),
    PRIMARY KEY (ability_name)
);

INSERT INTO ability VALUES ('Overgrow', 'Increases the power of Grass-type moves by 50%.');
INSERT INTO ability VALUES ('Blaze', 'Increases the power of Fire-type moves by 50%.');
INSERT INTO ability VALUES ('Torrent', 'Increases the power of Water-type moves by 50%.');
INSERT INTO ability VALUES ('Immunity', 'Prevents the Pokemon from getting poisoned.');
INSERT INTO ability VALUES ('Snow Cloak', 'Raises evasion by 20% in a hail storm. It also gives immunity to damage by hail');
INSERT INTO ability VALUES ('Static', 'If a Pokemon with Static is hit by a move making contact, there is a 30% chance the foe will become paralyzed.');
INSERT INTO ability VALUES ('Clear Body', 'Prevents other Pokemon from lowering its stats.');
INSERT INTO ability VALUES ('Levitate', 'Causes the Pokemon to be raised like Flying-type Pokemon. Raised Pokemon are not affected by various moves.');
INSERT INTO ability VALUES ('Air Lock', 'Suppresses all effects brought on by weather, including move power increases, relevant abilities and so on.');
INSERT INTO ability VALUES ('Run Away', 'Allows the bearer to flee a battle with a wild Pokemon without fail.')
INSERT INTO ability VALUES ('Pressure', 'Makes any attack targeted at the ability bearer - regardless of whether it hits - use 2 PP instead of 1.');

CREATE TABLE pokemon (
    pokemon_id INTEGER,
    name VARCHAR(20) NOT NULL UNIQUE,
    weight FLOAT(25),
    attack INTEGER,
    special_defense INTEGER,
    speed INTEGER,
    hp INTEGER,
    defense INTEGER,
    special_attack INTEGER,
    ability_name VARCHAR(20),
    PRIMARY KEY (pokemon_id),
    FOREIGN KEY (ability_name) REFERENCES ability
);

INSERT INTO pokemon VALUES (1, 'Bulbasaur', 6.9, 49, 65, 45, 45, 49, 65, 'Overgrow');
INSERT INTO pokemon VALUES (2, 'Charzard', 8.5, 52, 50, 65, 39, 43, 60, 'Blaze');
INSERT INTO pokemon VALUES (3, 'Squirtle', 9.0, 48, 64, 43, 44, 65, 50, 'Torrent');
INSERT INTO pokemon VALUES (4, 'Snorlax', 460.0, 110, 30, 160, 65, 65, 65, 'Immunity');
INSERT INTO pokemon VALUES (5, 'Articuno', 55.4, 85, 125, 85, 90, 100, 95, 'Snow Cloak');
INSERT INTO pokemon VALUES (6, 'Pikachu', 6.0, 55, 50, 90, 35, 40, 50, 'Static');
INSERT INTO pokemon VALUES (7, 'Regice', 175.0, 50, 200, 50, 80, 100, 100, 'Clear Body');
INSERT INTO pokemon VALUES (8, 'Latias', 40.0, 80, 130, 110, 80, 90, 110, 'Levitate');
INSERT INTO pokemon VALUES (9, 'Latios', 60.0, 90, 110, 110, 80, 80, 130, 'Levitate');
INSERT INTO pokemon VALUES (10, 'Rayquaza', 206.5, 150, 90, 95, 105, 90, 150, 'Air Lock');
INSERT INTO pokemon VALUES (11, 'Piplup', 5.2, 51, 56, 40, 53, 53, 61, 'Torrent');
INSERT INTO pokemon VALUES (12, 'Buneary', 5.5, 66, 56, 85, 55, 44, 44, 'Run Away');
INSERT INTO pokemon VALUES (13, 'Spiritomb', 108.0, 92, 108, 35, 50, 108, 92, 'Pressure');
INSERT INTO pokemon VALUES (14, 'Mesprit', 0.3, 105, 105, 80, 80, 105, 105, 'Levitate');
INSERT INTO pokemon VALUES (15, 'Dialga', 683.0, 120, 100, 90, 100, 120, 150, 'Pressure');


CREATE TABLE area (
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (region, location)
);

INSERT INTO area VALUES ('Kanto', 'Cerulean City');
INSERT INTO area VALUES ('Kanto', 'Route 24');
INSERT INTO area VALUES ('Kanto', 'Vermilion City');
INSERT INTO area VALUES ('Kanto', 'Route 12, 16');
INSERT INTO area VALUES ('Kanto', 'Seafoam Islands');
INSERT INTO area VALUES ('Hoenn', 'Safari Zone');
INSERT INTO area VALUES ('Hoenn', 'Route 105');
INSERT INTO area VALUES ('Hoenn', 'Southern Island');
INSERT INTO area VALUES ('Hoenn', 'Roaming Hoenn');
INSERT INTO area VALUES ('Hoenn', 'Sky Pillar');
INSERT INTO area VALUES ('Sinnoh', 'Lake Verity');
INSERT INTO area VALUES ('Sinnoh', 'Eterna Forest');
INSERT INTO area VALUES ('Sinnoh', 'Route 209');
INSERT INTO area VALUES ('Sinnoh', 'Roaming Sinnoh');
INSERT INTO area VALUES ('Sinnoh', 'Spear Pillar');

CREATE TABLE found_in (
    pokemon_id INTEGER,
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (pokemon_id, region, location),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon
                      ON DELETE CASCADE,
    FOREIGN KEY (region, location) REFERENCES area
);

INSERT INTO found_in VALUES (1, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (1, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (1, 'Sinnoh', 'Spear Pillar');

INSERT INTO found_in VALUES (2, 'Kanto', 'Route 24');
INSERT INTO found_in VALUES (3, 'Kanto', 'Vermilion City');
INSERT INTO found_in VALUES (4, 'Kanto', 'Route 12, 16');
INSERT INTO found_in VALUES (5, 'Kanto', 'Seafoam Islands');
INSERT INTO found_in VALUES (6, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (7, 'Hoenn', 'Route 105');

INSERT INTO found_in VALUES (8, 'Kanto', 'Route 24');
INSERT INTO found_in VALUES (8, 'Hoenn', 'Southern Island');
INSERT INTO found_in VALUES (8, 'Sinnoh', 'Route 209');

INSERT INTO found_in VALUES (9, 'Hoenn', 'Roaming Hoenn');
INSERT INTO found_in VALUES (10, 'Hoenn', 'Sky Pillar');
INSERT INTO found_in VALUES (11, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (12, 'Sinnoh', 'Eterna Forest');
INSERT INTO found_in VALUES (13, 'Sinnoh', 'Route 209');
INSERT INTO found_in VALUES (14, 'Sinnoh', 'Roaming Sinnoh');
INSERT INTO found_in VALUES (15, 'Sinnoh', 'Spear Pillar');

