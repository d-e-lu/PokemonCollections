package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;


public interface MainWindowDelegate {
    public void mainWindowFinished();
    public PokemonModel[] insert(PokemonModel p);
    public PokemonModel[] delete(int pokemonId);
    public void update(PokemonModel p);
    public String[] select(String attribute_to_show, String table, String attribute_to_filter, int threshold);
    public String[] project(String attribute, String table);
    public String[] join(String region);
    public void countPokemonOnWeight(double threshold);
}

