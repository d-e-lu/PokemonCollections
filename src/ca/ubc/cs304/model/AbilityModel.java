package ca.ubc.cs304.model;

public class AbilityModel {
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
}
