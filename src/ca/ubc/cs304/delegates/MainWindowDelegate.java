package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.Model;
import ca.ubc.cs304.model.PokemonModel;

public interface MainWindowDelegate {
    public void mainWindowFinished();
    public PokemonModel[] insert(PokemonModel p);
    public PokemonModel[] delete(int pokemonId);
    public PokemonModel[] update(PokemonModel p);
    public String[] select(String attribute_to_show, String table, String attribute_to_filter, int threshold);
    public String[] project(String attribute, String table);
    public String[] join(String region);
    public int countPokemonOnWeight(double threshold);
    public double avgPokemonPerRegion();
    public String[] division();
    public Model[] view(String table);
}

