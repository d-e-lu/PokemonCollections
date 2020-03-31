package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.MainWindowDelegate;
import ca.ubc.cs304.model.PokemonModel;
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
        mainWindow = new MainWindow();
        mainWindow.showFrame(this);
        //loginWindow = new LoginWindow();
        //loginWindow.showFrame(this);
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

    public void insert(PokemonModel p) {
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
