package ca.ubc.cs304.model;

public class FoundInModel implements Model{
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

    public String[] getAttributeNames() {
        String[] columns = {"pokemon_id", "region", "location"};
        return columns;
    }

    public String[] getAttributes() {
        String id = Integer.toString(getPokemon_id());
        String[] s = {id, region, location};
        return s;
    }
}
