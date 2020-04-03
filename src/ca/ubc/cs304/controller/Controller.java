package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.MainWindowDelegate;

import ca.ubc.cs304.model.Model;
import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;

import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.MainWindow;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Controller implements LoginWindowDelegate, MainWindowDelegate {
    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;
    private MainWindow mainWindow = null;

    public Controller() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    /**
     * LoginWindowDelegate Implementation
     *
     * connects to Oracle database with supplied username and password
     */
    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            // Once connected, remove login window and start Main Window
            loginWindow.dispose();

            mainWindow = new MainWindow();
            mainWindow.showFrame(this);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts");
                System.exit(-1);
            }
        }
    }

    /**
     * Select Operation on Any Table
     *
     * @param attribute_to_show
     * @param table
     * @param attribute_to_filter
     * @param threshold
     */
    public String[] select(String attribute_to_show, String table, String attribute_to_filter, int threshold) {
        String[] results = dbHandler.selectTable(attribute_to_show, table, attribute_to_filter, threshold);
        for (String result : results) {
            System.out.println(result);
        }
        return results;
    }

    /**
     * Count Number of Pokemon that is heavier or equal to threshold
     *
     * @param threshold
     */
    public int countPokemonOnWeight(double threshold) {
        int result = dbHandler.countPokemonOnWeight(threshold);

        // show result number in the terminal
        System.out.print("Total number of Pokemon heavier than " + threshold + " is " + result);
        System.out.println();

        return result;
    }

    /**
     * Find average number of pokemon per region
     *
     */
    public double avgPokemonPerRegion() {
        double result = dbHandler.avgPokemonPerRegion();

        // show result number in the terminal
        System.out.print("Average number of pokemon per region is " + result);
        System.out.println();

        return result;
    }

    /**
     * Division Operation
     * Find all pokemon in all region
     *
     */
    public String[] division() {
        String[] results = dbHandler.division();

        for (String result : results) {
            System.out.println(result);
        }

        return results;
    }


        /**
         * Project Operation on Any Table
         *
         * @param attribute
         * @param table
         */
    public String[] project(String attribute, String table) {
        String[] results = dbHandler.projectTable(attribute, table);

        for (String result : results) {
            System.out.println(result);
        }
        return results;
    }

    /**
     * Join Operation on pokemon and FoundIn, filtering based on region
     *
     * @param region
     */
    public String[] join(String region) {
        String[] results = dbHandler.joinTable(region);

        for (String result : results) {
            System.out.println(result);
        }

        return results;
    }

    /**
     * Insert Operation for Pokemon Table
     *
     * @param p
     */
    public PokemonModel[] insert(PokemonModel p) {
        PokemonModel[] models = dbHandler.insertTable(p);
        return models;
    }

    /**
     * Delete Operation for Pokemon Table, only delete 1 pokemonId at a time
     *
     * @param pokemonId
     */
    public PokemonModel[] delete(int pokemonId) {
        PokemonModel[] models = dbHandler.delete(pokemonId);
        return models;
    }

    public PokemonModel[] update(PokemonModel p) {
        // Do something dbHandler
        PokemonModel[] models = dbHandler.update(p);
        return models;
    }

    public Model[] view(String table) {
        Model[] models = dbHandler.view(table);
        return models;
    }

    /**
     * MainWindowDelegate Implementation
     *
     * The MainWindow instance tells us that it is done with what it's
     * doing so we are cleaning up the connection since it's no longer needed.
     */
    public void mainWindowFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    /**
     * Main method called at launch time
     */
    public static void main(String args[]) {
        Controller controller = new Controller();
        controller.start();
    }
}
