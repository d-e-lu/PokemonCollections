package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;


public interface MainWindowDelegate {
    public void mainWindowFinished();
    public void insert(PokemonModel p);
    public void delete(int pokemonId);
    public void update(PokemonModel p);
    public void selectTable(String attribute_to_show, String table, String attribute_to_filter, int threshold);
    public void countPokemonOnWeight(double threshold);
}

