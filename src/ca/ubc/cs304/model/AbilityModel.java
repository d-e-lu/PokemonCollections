package ca.ubc.cs304.model;

public class AbilityModel implements Model{
    private final String ability_name;
    private final String description;

    public AbilityModel(String ability_name, String description) {
        this.ability_name = ability_name;
        this.description = description;
    }

    public String getName() {
        return ability_name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAttributeNames() {
        String[] columns = {"ability_name", "description"};
        return columns;
    }

    public String[] getAttributes() {
        String[] s = {ability_name, description};
        return s;
    }
}
