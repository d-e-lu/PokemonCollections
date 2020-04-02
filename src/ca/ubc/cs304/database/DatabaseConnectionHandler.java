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
            String[] exception = {EXCEPTION_TAG + " " + e.getMessage()};
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return exception;
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Count # of pokemon >= threshold weight, if none exist, function
     * returns 0
     *
     */
    public int countPokemonOnWeight(double threshold) {
        try {
            String query = "SELECT count(*) AS \"number\" FROM pokemon WHERE weight >= " + Double.toString(threshold);
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            if (rs.next()) {
               return Integer.parseInt(rs.getString("number"));
            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return 0;
    }

    /**
     * Find average number of pokemon per region
     *
     */
    public double avgPokemonPerRegion() {
        try {
            String query = "Select avg(counting) average_num " +
                    "    From (Select count(*) counting " +
                    "        From (Select * " +
                    "            From Pokemon p " +
                    "            Left Join Found_In f " +
                    "            On p.pokemon_id = f.pokemon_id) pf " +
                    "        Group by pf.region)";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            if (rs.next()) {
                return Double.parseDouble(rs.getString("average_num"));
            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return 0.0;
    }

    /**
     * Division Operation
     *
     * Find all the pokemon that occurs in all region
     *
     */
    public String[] division() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("name"); // add the attribute name as first element

        try {
            String query = "Select p.name " +
                    "From pokemon p " +
                    "Where Not Exists ( " +
                    "    (Select region From area) " +
                    "Minus " +
                    "    (Select f.region " +
                    "    From Found_In f " +
                    "    Where p.pokemon_id = f.pokemon_id))";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            // iterate through all the resulting tuples
            while(rs.next()) {
                String current_value = rs.getString("name");
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
    public PokemonModel[] delete(int pokemonId) {
        ArrayList<PokemonModel> result = new ArrayList<PokemonModel>();

        try {
            String query = "DELETE FROM pokemon WHERE pokemon_id = " + Integer.toString(pokemonId);
            PreparedStatement ps = connection.prepareStatement(query);

            int changes = ps.executeUpdate(); // check how many rows were deleted
            if (changes == 0) {
                System.out.println(WARNING_TAG + "Pokemon ID: " + pokemonId + " does not exist. Please try again.");
            }
            connection.commit();

            // get the information for updated pokemon table
            String getPokemonTable = "SELECT * FROM POKEMON";
            ps = connection.prepareStatement(getPokemonTable);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            while (rs.next()){
                PokemonModel model = new PokemonModel(rs.getInt("pokemon_Id"),
                        rs.getString("name"),
                        rs.getDouble("weight"),
                        rs.getInt("attack"),
                        rs.getInt("special_defense"),
                        rs.getInt("speed"),
                        rs.getInt("hp"),
                        rs.getInt("defense"),
                        rs.getInt("special_attack"),
                        rs.getString("ability_name")
                        );
                result.add(model);
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println((EXCEPTION_TAG + " " + e.getMessage()));
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(EXCEPTION_TAG + " " + e1.getMessage());
            }
        }
        return result.toArray(new PokemonModel[result.size()]);
    }

    /**
     * Insert Operation on Pokemon Table
     *
     */
    public PokemonModel[] insertTable(PokemonModel p) {
        ArrayList<PokemonModel> result = new ArrayList<PokemonModel>();

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

            // get the information for updated pokemon table
            String getPokemonTable = "SELECT * FROM POKEMON";
            ps = connection.prepareStatement(getPokemonTable);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            while (rs.next()){
                PokemonModel model = new PokemonModel(rs.getInt("pokemon_Id"),
                        rs.getString("name"),
                        rs.getDouble("weight"),
                        rs.getInt("attack"),
                        rs.getInt("special_defense"),
                        rs.getInt("speed"),
                        rs.getInt("hp"),
                        rs.getInt("defense"),
                        rs.getInt("special_attack"),
                        rs.getString("ability_name")
                );
                result.add(model);
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new PokemonModel[result.size()]);
    }

    /**
     * Update operation for Pokemon table, can not change pokemon ID or name
     * @param p
     */
    public PokemonModel[] update(PokemonModel p) {
        ArrayList<PokemonModel> result = new ArrayList<PokemonModel>();
        try {
            String q = "SELECT * FROM ability where ability_name = '" + p.getAbility_name() + "'";
            PreparedStatement s = connection.prepareStatement(q);
            ResultSet r = s.executeQuery();

            if(r == null) {
                System.out.println(WARNING_TAG + "Ability does not exist. Please try again");
            } else {
                try {
                    String query = "UPDATE pokemon SET " +
                            "weight = " + Double.toString(p.getWeight()) + ", " +
                            "attack = " + Integer.toString(p.getAttack()) + ", " +
                            "special_defense = " + Integer.toString(p.getSpecial_defense()) + ", " +
                            "speed = " + Integer.toString(p.getSpeed()) + ", " +
                            "hp = " + Integer.toString(p.getHp()) + ", " +
                            "defense = " + Integer.toString(p.getDefense()) + ", " +
                            "special_attack = " + Integer.toString(p.getSpecial_attack()) + ", " +
                            "ability_name = '" + p.getAbility_name() + "' " +
                            "WHERE pokemon_id = " + Integer.toString(p.getPokemon_id()) +
                            " AND name = '" + p.getName() + "'";

                    PreparedStatement ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    connection.commit();

                    while (rs.next()) {
                        PokemonModel model = new PokemonModel(rs.getInt("pokemon_Id"),
                                rs.getString("name"),
                                rs.getDouble("weight"),
                                rs.getInt("attack"),
                                rs.getInt("special_defense"),
                                rs.getInt("speed"),
                                rs.getInt("hp"),
                                rs.getInt("defense"),
                                rs.getInt("special_attack"),
                                rs.getString("ability_name"));
                        result.add(model);
                    }
                    ps.close();
                } catch (SQLException e) {
                    System.out.println(EXCEPTION_TAG + " " + e.getMessage());
                }
            }
        } catch (SQLException x) {
            System.out.println(EXCEPTION_TAG + " " + x.getMessage());
        }
        return result.toArray(new PokemonModel[result.size()]);
    }

    /**
     * Project operation on Any Table
     *
     * @param attribute
     * @param table
     */
    public String[] projectTable(String attribute, String table) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            String query = "SELECT " + attribute + " FROM " + table;
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            while (rs.next()) { // retrieve tuples
                String curr = rs.getString(attribute);
                result.add(curr);
            }

        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
    }

    /**
     * Join operation for pokemon and region table
     * @param region
     */
    public String[] joinTable(String region) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            String query = "SELECT p.name, f.region " +
                    "FROM pokemon p, found_in f " +
                    "WHERE p.pokemon_id = f.pokemon_id " +
                    "AND region = '" + region + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            connection.commit();

            while (rs.next()) {
                String curr = rs.getString("name");
                result.add(curr);
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result.toArray(new String[result.size()]);
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
