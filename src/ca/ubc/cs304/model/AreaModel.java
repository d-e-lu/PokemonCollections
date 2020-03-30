package ca.ubc.cs304.model;

public class AreaModel {
    private final String region;
    private final String location;

    public AreaModel(String region, String location) {
        this.region = region;
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public String getLocation() {
        return location;
    }
}
