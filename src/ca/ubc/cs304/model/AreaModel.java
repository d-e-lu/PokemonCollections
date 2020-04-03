package ca.ubc.cs304.model;

public class AreaModel implements Model{
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

    public String[] getAttributeNames() {
        String[] columns = {"region", "location"};
        return columns;
    }

    public String[] getAttributes() {
        String[] s = {this.region, this.location};
        return s;
    }
}
