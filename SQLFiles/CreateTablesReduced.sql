CREATE TABLE ability (ability_name VARCHAR(20),
    description VARCHAR(250),
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
INSERT INTO ability VALUES ('Run Away', 'Allows the bearer to flee a battle with a wild Pokemon without fail.');
INSERT INTO ability VALUES ('Pressure', 'Makes any attack targeted at the ability bearer - regardless of whether it hits - use 2 PP instead of 1.');
INSERT INTO ability VALUES ('Imposter', 'It transforms itself into the Pokemon it is facing.');
INSERT INTO ability VALUES ('Sturdy',  'Allows the Pokemon to survive with 1 HP if attacked at full HP with an attack that would otherwise knock it out.');
INSERT INTO ability VALUES ('Sniper', 'Increases the power of critical hits by 1.5x. This means a critical hit will deal 2.25x normal damage instead of 1.5x.');
INSERT INTO ability VALUES ('Cute Charm', 'If a Pokemon is hit by a move that makes contact, there is a 30% chance the attacker will become infatuated.');
INSERT INTO ability VALUES ('Hustle', 'Increases the ability-bearers Attack by 50%, however it also reduces the Accuracy of all Physical moves by 20%.');
INSERT INTO ability VALUES ('Adaptability', 'Increases the effectiveness of STAB moves from the usual 1.5x to 2x.');
INSERT INTO ability VALUES ('Cursed Body', 'May disable a move used on the Pokemon');
INSERT INTO ability VALUES ('Ice Body', 'The Pokemon will recover 1/16 of its max HP during hail, after each turn. It also grants immunity to hailstorm damage.');
INSERT INTO ability VALUES ('Chlorophyll', 'Doubles the ability-bearers Speed during bright sunshine.');
INSERT INTO ability VALUES ('Justified', 'Raises the ability-bearers Attack by one stage when hit by a damaging Dark-type move.');
INSERT INTO ability VALUES ('Turboblaze', 'Prevents a targets ability from affecting moves that the Turboblaze Pokemon uses.');
INSERT INTO ability VALUES ('Teravolt', 'Prevents a targets ability from affecting moves that the Teravolt Pokemon uses.');
INSERT INTO ability VALUES ('Keen Eye', 'Prevents the Pokemon from losing accuracy.');
INSERT INTO ability VALUES ('Shed Skin', 'The Pokemon may heal its own status problems.');
INSERT INTO ability VALUES ('Intimidate', 'Lowers the Attack of all opponents by one stage when the ability bearers switches in.');
INSERT INTO ability VALUES ('Honey Gather', 'Honey Gather has no effect in battle, but when a battle ends, the bearer may pick up Honey as a held item.');
INSERT INTO ability VALUES ('Plus', 'Uses Sp. Atk if another Pokemon has Plus or Minus.');
INSERT INTO ability VALUES ('Minus', 'Uses Sp. Atk if another Pokemon has Plus or Minus');
INSERT INTO ability VALUES ('Dark Aura', 'Raises the power of Dark-type moves by 33% for all Pokemon on the field (not just the ability bearer).');
INSERT INTO ability VALUES ('Aura Break', 'Cancels the effects of the abilities Fairy Aura and Dark Aura, instead reduces Dark and Fairy-type moves by 25%.');
INSERT INTO ability VALUES ('Misty Surge', 'Creates a low-lying mist when the bearer enters the battle. It protects all grounded Pokemon from status conditions.');
INSERT INTO ability VALUES ('Psychic Surge', 'Creates a low-lying psychic field when the bearer enters the battle. It prevents high-priority moves being used.');
INSERT INTO ability VALUES ('Electric Surge', 'Creates a low-lying electric field when the bearer enters the battle. It prevents grounded Pokemon from falling asleep.');
INSERT INTO ability VALUES ('Shadow Shield', 'Reduces damage  when HP is full');
INSERT INTO ability VALUES ('Prism Armor', 'Reduces damage taken from super-effective attacks by 25%.');
INSERT INTO ability VALUES ('Water Absorb', 'Heals 1/4 of max HP when hit by a Water-type attack.');


CREATE TABLE pokemon (pokemon_id INTEGER,
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
INSERT INTO pokemon VALUES (16, 'Ditto', 4.0, 48, 48, 48, 48, 48, 48,'Imposter');
INSERT INTO pokemon VALUES (17, 'Sudowoodo', 38.0, 100, 65, 30, 70, 115, 30, 'Sturdy');
INSERT INTO pokemon VALUES (18, 'Beedrill', 29.5, 90, 80, 75, 65, 40, 45, 'Sniper');
INSERT INTO pokemon VALUES (19, 'Clefairy', 7.5, 45, 65, 35, 70, 48, 60, 'Cute Charm');
INSERT INTO pokemon VALUES (20, 'Jigglypuff', 5.5, 45, 25, 20, 115, 20, 45, 'Cute Charm');
INSERT INTO pokemon VALUES (21, 'Togepi', 1.5, 20, 65, 20, 35, 65, 40, 'Hustle');
INSERT INTO pokemon VALUES (22, 'Eevee', 6.5, 55, 65, 55, 55, 50, 45, 'Adaptability');
INSERT INTO pokemon VALUES (23, 'Frillish', 33.0, 40, 85, 40, 55, 50, 65, 'Cursed Body');
INSERT INTO pokemon VALUES (24, 'Vanillite', 5.7, 50, 60, 44, 36, 50, 'Ice Body');
INSERT INTO pokemon VALUES (25, 'Sawsbuck', 92.5, 70, 70, 95, 80, 70, 60, 'Chlorophyll');
INSERT INTO pokemon VALUES (26, 'Beartic', 260.0, 130, 80, 50, 95, 80, 70, 'Snow Cloak');
INSERT INTO pokemon VALUES (27, 'Cryogonal', 148.0, 50, 135, 105, 80, 50, 95, 'Levitate');
INSERT INTO pokemon VALUES (28, 'Colbatron', 250.0, 91, 72, 108, 91, 129, 90, 'Justified');
INSERT INTO pokemon VALUES (29, 'Reshiram', 330.0, 120, 120, 90, 100, 100, 150, 'Turboblaze');
INSERT INTO pokemon VALUES (30, 'Zekrom', 345.0, 150, 100, 90, 100, 120, 120, 'Teravolt');
INSERT INTO pokemon VALUES (31, 'Kyurem', 325.0, 130, 90, 95, 125, 90, 130, 'Pressure');
INSERT INTO pokemon VALUES (32, 'Pidgey', 1.8, 45, 35, 56, 40, 40, 35, 'Keen Eye');
INSERT INTO pokemon VALUES (33, 'Burmy', 3.4, 29, 45, 36, 40, 45, 29, 'Shed Skin');
INSERT INTO pokemon VALUES (34, 'Gyarados', 235.0, 125, 100, 81, 95, 79, 60, 'Intimidate');
INSERT INTO pokemon VALUES (35, 'Farfetchd', 15.0, 90, 62, 60, 52, 55, 58, 'Keen Eye');
INSERT INTO pokemon VALUES (36, 'Combee', 5.5, 30, 42, 70, 30, 42, 30, 'Honey Gather');
INSERT INTO pokemon VALUES (37, 'Yveltal', 203.0, 131, 98, 99, 126, 95, 131, 'Dark Aura');
INSERT INTO pokemon VALUES (38, 'Plusle', 4.2, 50, 75, 95, 60, 40, 85, 'Plus');
INSERT INTO pokemon VALUES (39, 'Minun', 4.2, 40, 85, 95, 60, 50, 75, 'Minus');
INSERT INTO pokemon VALUES (40, 'Mewtwo', 122.0, 110, 90, 130, 106, 90, 154, 'Pressure');
INSERT INTO pokemon VALUES (41, 'Zygarde', 305.0, 100, 95, 95, 108, 121, 81, 'Aura Break');
INSERT INTO pokemon VALUES (42, 'Dratini', 16.5, 84, 70, 70, 61, 65, 70, 'Shed Skin');
INSERT INTO pokemon VALUES (43, 'Tapu Fini', 21.1, 75, 130, 85, 70, 115, 95, 'Misty Surge');
INSERT INTO pokemon VALUES (44, 'Tapu Lele', 18.6, 85, 115, 95, 70, 75, 130, 'Psychic Surge');
INSERT INTO pokemon VALUES (45, 'Tapu Koko', 20.5, 115, 75, 130, 70, 85, 85, 'Electric Surge');
INSERT INTO pokemon VALUES (46, 'Lunala', 120.0, 113, 107, 97, 137, 89, 137, 'Shadow Shield');
INSERT INTO pokemon VALUES (47, 'Necrozma', 230.0, 107, 89, 79, 97,101, 127, 'Prism Armor');
INSERT INTO pokemon VALUES (48, 'Lapras', 220.0, 85, 95, 60, 130, 80, 85, 'Water Absorb');
INSERT INTO pokemon VALUES (49, 'Ho-oh', 199.0, 130, 154, 90, 106, 90, 110, 'Pressure');
INSERT INTO pokemon VALUES (50, 'Giratina', 750.0, 100, 120, 90, 150, 120, 100, 'Pressure');
-- INSERT INTO pokemon VALUES (ID, NAME, WEIGHT, ATTACK, SP DEFENSE, SPEED, HP, DEFENSE, SP ATTACK, ABILITY NAME)


CREATE TABLE area (region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (region, location)
);

INSERT INTO area VALUES ('Kanto', 'Cerulean City');
INSERT INTO area VALUES ('Kanto', 'Route 24');
INSERT INTO area VALUES ('Kanto', 'Vermilion City');
INSERT INTO area VALUES ('Kanto', 'Route 12, 16');
INSERT INTO area VALUES ('Kanto', 'Seafoam Islands');
INSERT INTO area VALUES ('Kanto', 'Celadon City');
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
INSERT INTO area VALUES ('Sinnoh', 'Route 201');
INSERT INTO area VALUES ('Johto', 'Route 34, 35');
INSERT INTO area VALUES ('Johto', 'Route 36');
INSERT INTO area VALUES ('Johto', 'National Park');
INSERT INTO area VALUES ('Johto', 'Mt. Moon');
INSERT INTO area VALUES ('Johto', 'Violet City');
INSERT INTO area VALUES ('Johto', 'Celadon City');
INSERT INTO area VALUES ('Unova', 'Route 4, 17, 18');
INSERT INTO area VALUES ('Unova', 'Route 6, Dragonspiral Tower, Cold Storage');
INSERT INTO area VALUES ('Unova', 'Dragonspiral Tower');
INSERT INTO area VALUES ('Unova', 'Twist Mountain');
INSERT INTO area VALUES ('Unova', 'Mistralton Cave');
INSERT INTO area VALUES ('Unova', 'Dragonspiral Tower, Ns Castle');
INSERT INTO area VALUES ('Unova', 'Giant Chasm');
INSERT INTO area VALUES ('Kalos', 'Route 2,3');
INSERT INTO area VALUES ('Kalos', 'Route 3, 7');
INSERT INTO area VALUES ('Kalos', 'Route 22, Santalune City');
INSERT INTO area VALUES ('Kalos', 'Route 4, 7');
INSERT INTO area VALUES ('Kalos', 'Route 5');
INSERT INTO area VALUES ('Kalos', 'Team Flare HQ');
INSERT INTO area VALUES ('Kalos', 'Unknown Dungeon');
INSERT INTO area VALUES ('Kalos', 'Terminus Cave');
INSERT INTO area VALUES ('Alola', 'Poni Gauntlet, Poni Meadow, Vast Poni Canyon');
INSERT INTO area VALUES ('Alola', 'Ruins of Hope');
INSERT INTO area VALUES ('Alola', 'Ruins of Life');
INSERT INTO area VALUES ('Alola', 'Ruins of Conflict');
INSERT INTO area VALUES ('Alola', 'Altar of the Moone');
INSERT INTO area VALUES ('Alola', 'Ten Carat Hill');
INSERT INTO area VALUES ('Kanto', 'Silph Co.');
INSERT INTO area VALUES ('Johto', 'Tin Tower');
INSERT INTO area VALUES ('Sinnoh', 'Turnback Cave');


CREATE TABLE found_in (
                          pokemon_id INTEGER,
                          region VARCHAR(20),
                          location VARCHAR(60),
                          PRIMARY KEY (pokemon_id, region, location),
                          FOREIGN KEY (pokemon_id) REFERENCES pokemon
                              ON DELETE CASCADE,
                          FOREIGN KEY (region, location) REFERENCES area
);

INSERT INTO found_in VALUES (1, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (2, 'Kanto', 'Route 24');
INSERT INTO found_in VALUES (3, 'Kanto', 'Vermilion City');
INSERT INTO found_in VALUES (4, 'Kanto', 'Route 12, 16');
INSERT INTO found_in VALUES (5, 'Kanto', 'Seafoam Islands');

INSERT INTO found_in VALUES (6, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (6, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (6, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (6, 'Johto', 'Route 34, 35');
INSERT INTO found_in VALUES (6, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (6, 'Kalos', 'Route 2,3');
INSERT INTO found_in VALUES (6, 'Alola', 'Ten Carat Hill');

INSERT INTO found_in VALUES (7, 'Hoenn', 'Route 105');
INSERT INTO found_in VALUES (8, 'Kanto', 'Route 24')
INSERT INTO found_in VALUES (9, 'Hoenn', 'Roaming Hoenn');
INSERT INTO found_in VALUES (10, 'Hoenn', 'Sky Pillar');
INSERT INTO found_in VALUES (11, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (12, 'Sinnoh', 'Eterna Forest');
INSERT INTO found_in VALUES (13, 'Sinnoh', 'Route 209');
INSERT INTO found_in VALUES (14, 'Sinnoh', 'Roaming Sinnoh');
INSERT INTO found_in VALUES (15, 'Sinnoh', 'Spear Pillar');

INSERT INTO found_in VALUES (16, 'Johto', 'Route 34, 35');
INSERT INTO found_in VALUES (16, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (16, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (16, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (16, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (16, 'Kalos', 'Route 2,3');
INSERT INTO found_in VALUES (16, 'Alola', 'Ten Carat Hill');


INSERT INTO found_in VALUES (17, 'Johto', 'Route 36');
INSERT INTO found_in VALUES (18, 'Johto', 'National Park');
INSERT INTO found_in VALUES (19, 'Johto', 'Mt. Moon');
INSERT INTO found_in VALUES (20, 'Johto', 'Mt. Moon');
INSERT INTO found_in VALUES (21, 'Johto', 'Violet City');

INSERT INTO found_in VALUES (22, 'Johto', 'Celadon City');
INSERT INTO found_in VALUES (22, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (22, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (22, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (22, 'Johto', 'Route 34, 35');
INSERT INTO found_in VALUES (22, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (22, 'Kalos', 'Route 2,3');
INSERT INTO found_in VALUES (22, 'Alola', 'Ten Carat Hill');

INSERT INTO found_in VALUES (23, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (24, 'Unova', 'Route 6, Dragonspiral Tower, Cold Storage');
INSERT INTO found_in VALUES (25, 'Unova', 'Dragonspiral Tower');
INSERT INTO found_in VALUES (26, 'Unova', 'Dragonspiral Tower');
INSERT INTO found_in VALUES (27, 'Unova', 'Twist Mountain');
INSERT INTO found_in VALUES (28, 'Unova', 'Mistralton Cave');
INSERT INTO found_in VALUES (29, 'Unova', 'Dragonspiral Tower, Ns Castle');
INSERT INTO found_in VALUES (30, 'Unova', 'Dragonspiral Tower, Ns Castle');
INSERT INTO found_in VALUES (31, 'Unova', 'Giant Chasm');
INSERT INTO found_in VALUES (32, 'Kalos', 'Route 2, 3');
INSERT INTO found_in VALUES (33, 'Kalos', 'Route 3, 7');
INSERT INTO found_in VALUES (34, 'Kalos', 'Route 22, Santalune City');
INSERT INTO found_in VALUES (35, 'Kalos', 'Route 4, 7');
INSERT INTO found_in VALUES (36, 'Kalos', 'Route 5');
INSERT INTO found_in VALUES (37, 'Kalos', 'Team Flare HQ');

INSERT INTO found_in VALUES (38, 'Kalos', 'Route 5');
INSERT INTO found_in VALUES (38, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (38, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (38, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (38, 'Johto', 'Route 34, 35');
INSERT INTO found_in VALUES (38, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (38, 'Alola', 'Ten Carat Hill');

INSERT INTO found_in VALUES (39, 'Kalos', 'Route 5');
INSERT INTO found_in VALUES (39, 'Kanto', 'Cerulean City');
INSERT INTO found_in VALUES (39, 'Hoenn', 'Safari Zone');
INSERT INTO found_in VALUES (39, 'Sinnoh', 'Lake Verity');
INSERT INTO found_in VALUES (39, 'Johto', 'Route 34, 35');
INSERT INTO found_in VALUES (39, 'Unova', 'Route 4, 17, 18');
INSERT INTO found_in VALUES (39, 'Alola', 'Ten Carat Hill');

INSERT INTO found_in VALUES (40, 'Kalos', 'Unknown Dungeon');
INSERT INTO found_in VALUES (41, 'Kalos', 'Terminus Cave');
INSERT INTO found_in VALUES (42, 'Alola', 'Poni Gauntlet, Poni Meadow, Vast Poni Canyon');
INSERT INTO found_in VALUES (43, 'Alola', 'Ruins of Hope');
INSERT INTO found_in VALUES (44, 'Alola', 'Ruins of Life');
INSERT INTO found_in VALUES (45, 'Alola', 'Ruins of Conflict');
INSERT INTO found_in VALUES (46, 'Alola', 'Altar of the Moone');
INSERT INTO found_in VALUES (47, 'Alola', 'Ten Carat Hill');
INSERT INTO found_in VALUES (48, 'Kanto', 'Silph Co.');
INSERT INTO found_in VALUES (49, 'Johto', 'Tin Tower');
INSERT INTO found_in VALUES (50, 'Sinnoh', 'Turnback Cave');