package ca.ubc.cs304.model;

public class FoundInModel {
    private final int pokemon_id;
    private final String region;
    private final String location;

    public FoundInModel(int pokemon_id, String region, String location) {
        this.pokemon_id = pokemon_id;
        this.region = region;
        this.location = location;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public String getRegion() {
        return region;
    }

    public String getLocation() {
        return location;
    }
}
