package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;
import ca.ubc.cs304.model.AreaModel;
import ca.ubc.cs304.model.FoundInModel;
import oracle.jdbc.proxy.annotation.Pre;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    /**
     * Select Operation on Any Table
     *
     * Expects users to know the attribute names of table of selection.
     * The first element of returned array is attribute_to_show, followed by
     * the tuples (all converted to string)
     *
     */
    public String[] selectTable(String attribute_to_show, String table, String attribute_to_filter, int threshold) {
        ArrayList<String> result = new ArrayList<String>();
        result.add(attribute_to_show); // add the attribute name as first element

        try {
            String query = "SELECT "+ attribute_to_show + " FROM "+ table +" WHERE "
                    + attribute_to_filter + " >= " + Integer.toString(threshold);
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            // iterate through all the resulting tuples
            while(rs.next()) {
                String current_value = rs.getString(attribute_to_show);
                result.add(current_value);
            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Delete Operation on Pokemon Table
     *
     * If user input pokemonId that does not exist or has already been deleted,
     * do nothing, and print warning
     *
     */
    public void delete(int pokemonId) {
        try {
            String query = "DELETE FROM pokemon WHERE pokemon_id = " + Integer.toString(pokemonId);
            PreparedStatement ps = connection.prepareStatement(query);

            int changes = ps.executeUpdate(); // check how many rows were deleted
            if (changes == 0) {
                System.out.println(WARNING_TAG + "Pokemon ID: " + pokemonId + " does not exist. Please try again.");
            }
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println((EXCEPTION_TAG + " " + e.getMessage()));
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(EXCEPTION_TAG + " " + e1.getMessage());
            }
        }
    }

    /**
     * Insert Operation on Pokemon Table
     *
     */
    public void insertTable(PokemonModel p) {
        try {
            String query = "INSERT INTO pokemon VALUES ("
                    + Integer.toString(p.getPokemon_id()) + ",'"
                    + p.getName() + "',"
                    + Double.toString(p.getWeight()) + ","
                    + Integer.toString(p.getAttack()) + ","
                    + Integer.toString(p.getSpecial_defense()) + ","
                    + Integer.toString(p.getSpeed()) + ","
                    + Integer.toString(p.getHp()) + ","
                    + Integer.toString(p.getDefense()) + ","
                    + Integer.toString(p.getSpecial_attack()) + ",'"
                    + p.getAbility_name() + "')";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

//    TODO
//    public String[] projectTable(String attribute, String table) {
//
//    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }
}
