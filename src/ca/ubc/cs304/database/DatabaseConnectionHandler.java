package ca.ubc.cs304.database;

import java.sql.*;
import java.util.ArrayList;

import ca.ubc.cs304.model.PokemonModel;
import ca.ubc.cs304.model.AbilityModel;
import ca.ubc.cs304.model.AreaModel;
import ca.ubc.cs304.model.FoundInModel;

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
     * Select Operation on Pokemon table
     */
    public String[] selectPokemon(String attribute_to_show, String attribute_to_filter, int threshold) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT ? FROM POKEMON WHERE ? >= ?");
            ps.setString(1, attribute_to_show);
            ps.setString(2, attribute_to_filter);
            ps.setInt(3, threshold);

            System.out.println(attribute_to_show);
            System.out.println(attribute_to_filter);
            System.out.println(Integer.toString(threshold));

            ResultSet rs = ps.executeQuery();
            connection.commit();

            while(rs.next()) {

//                String current_value = "";
//                // Handle different cases
//                switch (attribute_to_show) {
//                    case "pokemon_id":
//                        current_value = rs.getString("pokemon_id");
//                        break;
//                    case "name":
//                        current_value = rs.getName();
//                        break;
//                    case "weight":
//                        current_value = Double.toString(rs.getWeight());
//                        break;
//                    case "attack":
//                        current_value = Integer.toString(rs.getAttack());
//                        break;
//                    case "special_defense":
//                        current_value = Integer.toString(rs.getSpecial_defense());
//                        break;
//                    case "speed":
//                        current_value = Integer.toString(rs.getSpeed());
//                        break;
//                    case "hp":
//                        current_value = Integer.toString(rs.getHp());
//                        break;
//                    case "defense":
//                        current_value = Integer.toString(rs.getDefense());
//                        break;
//                    case "special_attack":
//                        current_value = Integer.toString(rs.getSpecial_attack());
//                        break;
//                    case "ability_name":
//                        current_value = rs.getAbility_name();
//                        break;
//                }
                String current_value = rs.getString(attribute_to_show);
                result.add(current_value);

            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * This is for testing purpose
     */
    public AbilityModel[] getTableInfo() {
        ArrayList<AbilityModel> result = new ArrayList<AbilityModel>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ability");

            while(rs.next()) {
                AbilityModel model = new AbilityModel(rs.getString("ability_name"),
                        rs.getString("description"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new AbilityModel[result.size()]);

    }

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
