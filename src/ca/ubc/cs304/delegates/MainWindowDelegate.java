package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;


public interface MainWindowDelegate {
    public void mainWindowFinished();
    public void insert(PokemonModel p);
    public void showTable();
    public void selectPokemon(String attribute_to_show, String attribute_to_filter, int threshold);
}

