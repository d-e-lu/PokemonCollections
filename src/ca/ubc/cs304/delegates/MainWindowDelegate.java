package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.PokemonModel;

public interface MainWindowDelegate {
    public void mainWindowFinished();
    public void insert(PokemonModel p);
    public void delete(int pokemonId);
    public void update(PokemonModel p);
}
