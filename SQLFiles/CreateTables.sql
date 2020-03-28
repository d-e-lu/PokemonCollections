CREATE TABLE ability (
    ability_name VARCHAR(20),
    description VARCHAR(100),
    PRIMARY KEY (ability_name)
);

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

CREATE TABLE egg (
    egg_id INTEGER,
    pokemon_id INTEGER,
    PRIMARY KEY (egg_id, pokemon_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon
);

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

CREATE TABLE move (
    name VARCHAR(20),
    PRIMARY KEY(name)
);

CREATE TABLE can_learn (
    pokemon_id INTEGER,
    name VARCHAR(20),
    PRIMARY KEY (pokemon_id, name),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon,
    FOREIGN KEY (name) REFERENCES move
);

CREATE TABLE type (
    name VARCHAR(20),
    PRIMARY KEY (name)
);

CREATE TABLE belongs_to (
    pokemon_id INTEGER,
    name VARCHAR(20),
    PRIMARY KEY (pokemon_id, name),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon,
    FOREIGN KEY (name) REFERENCES type
);

CREATE TABLE has_a (
    move_name VARCHAR(20),
    type_name VARCHAR(20),
    PRIMARY KEY (move_name, type_name),
    FOREIGN KEY (move_name) REFERENCES move,
    FOREIGN KEY (type_name) REFERENCES type
);

CREATE TABLE people (
    sin INTEGER,
    name VARCHAR(20),
    PRIMARY KEY (sin)
);

CREATE TABLE caught_by_r1 (
    sin INTEGER,
    people_name VARCHAR(20),
    PRIMARY KEY (sin),
    FOREIGN KEY (sin) REFERENCES people
);

CREATE TABLE caught_by_r2 (
    ball_caught_with VARCHAR(20),
    ball_rarity INTEGER,
    PRIMARY KEY (ball_caught_with)
);

CREATE TABLE caught_by_r3 (
    pokemon_id INTEGER,
    sin INTEGER,
    name VARCHAR(20),
    pokemon_level INTEGER,
    ball_caught_with VARCHAR(20),
    PRIMARY KEY (pokemon_id, sin),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon,
    FOREIGN KEY (sin) REFERENCES caught_by_r1,
    FOREIGN KEY (ball_caught_with) REFERENCES caught_by_r2
);

CREATE TABLE item (
    order_id INTEGER,
    name VARCHAR(20),
    description VARCHAR(100),
    PRIMARY KEY (order_id)
);

CREATE TABLE building (
    address VARCHAR(40),
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (address),
    FOREIGN KEY (region, location) REFERENCES area
);

CREATE TABLE buys (
    sin INTEGER,
    order_id INTEGER,
    address VARCHAR(20),
    PRIMARY KEY (sin, order_id, address),
    FOREIGN KEY (sin) REFERENCES people,
    FOREIGN KEY (order_id) REFERENCES item,
    FOREIGN KEY (address) REFERENCES building
);

CREATE TABLE lives_in_r1 (
    address VARCHAR(40),
    region VARCHAR(20),
    location VARCHAR(40),
    PRIMARY KEY (address),
    FOREIGN KEY (region, location) REFERENCES area
);

CREATE TABLE lives_in_r2 (
    sin INTEGER,
    address VARCHAR(40),
    PRIMARY KEY (sin),
    FOREIGN KEY (address) REFERENCES lives_in_r1
);

CREATE TABLE strong_against (
    weaker_type_name VARCHAR(20),
    stronger_type_name VARCHAR(20),
    multiplier FLOAT(25),
    PRIMARY KEY (weaker_type_name, stronger_type_name),
    FOREIGN KEY (weaker_type_name) REFERENCES type,
    FOREIGN KEY (stronger_type_name) REFERENCES type
);

CREATE TABLE nurse_works_at (
    sin INTEGER,
    nurse_id INTEGER,
    PRIMARY KEY (sin),
    FOREIGN KEY (sin) REFERENCES people
);

CREATE TABLE award (
    award_id INTEGER,
    title VARCHAR(20),
    PRIMARY KEY (award_id)
);

CREATE TABLE medal (
    award_id INTEGER,
    gym VARCHAR(20),
    PRIMARY KEY (award_id),
    FOREIGN KEY (award_id) REFERENCES award
);

CREATE TABLE ribbon (
    award_id INTEGER,
    contest_name VARCHAR(20),
    PRIMARY KEY (award_id),
    FOREIGN KEY (award_id) REFERENCES award
);

CREATE TABLE trainer (
    sin INTEGER,
    trainer_id INTEGER,
    PRIMARY KEY (sin),
    FOREIGN KEY (sin) REFERENCES people
);

CREATE TABLE shop (
    address VARCHAR(40),
    shop_size INTEGER,
    PRIMARY KEY (address),
    FOREIGN KEY (address) REFERENCES building
);

CREATE TABLE pokemon_center (
    address VARCHAR(40),
    capacity INTEGER,
    PRIMARY KEY (address),
    FOREIGN KEY (address) REFERENCES building
);