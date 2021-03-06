package ca.ubc.cs304.model;

public class PokemonModel implements Model {
    private final int pokemon_id;
    private final String name;
    private final double weight;
    private final int attack;
    private final int special_defense;
    private final int speed;
    private final int hp;
    private final int defense;
    private final int special_attack;
    private final String ability_name;

    public PokemonModel(int pokemon_id, String name, double weight, int attack, int special_defense, int speed, int hp, int defense, int special_attack, String ability_name) {
        this.pokemon_id = pokemon_id;
        this.name = name;
        this.weight = weight;
        this.attack = attack;
        this.special_defense = special_defense;
        this.speed = speed;
        this.hp = hp;
        this.defense = defense;
        this.special_attack = special_attack;
        this.ability_name = ability_name;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecial_defense() {
        return special_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecial_attack() {
        return special_attack;
    }

    public String getAbility_name() {
        return ability_name;
    }

    public String[] getAttributeNames() {
        String[] columns = {"pokemon_id","name","weight","attack","special_defense", "speed", "hp", "defense","special_attack","ability_name"};
        return columns;
    }
    public String[] getAttributes() {
        String id = Integer.toString(getPokemon_id());
        String weight = Double.toString(getWeight());
        String attack = Integer.toString(getAttack());
        String sp_d = Integer.toString(getSpecial_defense());
        String sp = Integer.toString(getSpeed());
        String hp = Integer.toString(getHp());
        String defense = Integer.toString(getDefense());
        String sp_a = Integer.toString(getSpecial_attack());
        String[] s = {id, name, weight, attack, sp_d, sp, hp, defense, sp_a, ability_name};
        return s;
    }
}

