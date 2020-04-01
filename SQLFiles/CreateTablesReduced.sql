CREATE TABLE ability (
    ability_name VARCHAR(20),
    description VARCHAR(100),
    PRIMARY KEY (ability_name)
);

INSERT INTO ability VALUES ('Fire', 'Shoot a fire ball');
INSERT INTO ability VALUES ('Ice', 'Shoot an ice ball');

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

INSERT INTO pokemon VALUES (1, 'Pikachu',12.2, 100,2,3,4,5,6,'Fire');
INSERT INTO pokemon VALUES (2, 'Pikachu2',20.2, 200,2,3,4,5,6,'Ice');

CREATE TABLE area (
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (region, location)
);

CREATE TABLE found_in (
    pokemon_id INTEGER,
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (pokemon_id, region, location),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon,
    FOREIGN KEY (region, location) REFERENCES area
);