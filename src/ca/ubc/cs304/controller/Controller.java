package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.MainWindowDelegate;

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
    public void selectTable(String attribute_to_show, String table, String attribute_to_filter, int threshold) {
        String[] results = dbHandler.selectTable(attribute_to_show, table, attribute_to_filter, threshold);

        // show results array in the terminal
        for (int i = 0; i < results.length; i++) {
            String current_value = results[i];

            System.out.print(current_value);
            System.out.println();
        }
    }

//    TODO
//    public String[] projectTable(String attribute, String table) {
//
//    }

    public void insert(PokemonModel p) {
        // Do something dbHandler
        dbHandler.insertTable(p);
    }

    /**
     * Delete Operation for Pokemon Table, only delete 1 pokemonId at a time
     *
     * @param pokemonId
     */
    public void delete(int pokemonId) {
        dbHandler.delete(pokemonId);
    }

    public void update(PokemonModel p) {
        // Do something dbHandler
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
