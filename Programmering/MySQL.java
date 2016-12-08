 import java.sql.Connection;

import java.sql.*; // Import required packages
public class MySQL {
    // Database credentials: replace with *YOUR* data here:
    private static final String MYDB = "CinemaDatabase";
    private static Connection connection;
    static final String USER = "juliusf";
    static final String PASS = "kransekage66";
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;

    private MySQL() {

    }

    public static ResultSet query(String query){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(MySQL.DB_URL, MySQL.USER, MySQL.PASS); // Open connection
            statement = connection.createStatement(); // Create statement
            rs = statement.executeQuery(query);
            return rs;
        } catch(Exception e) { // handle errors:
            e.printStackTrace();
        }
        return null;
    }

    public static void queryUpdate(String query){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(MySQL.DB_URL, MySQL.USER, MySQL.PASS); // Open connection
            statement = connection.createStatement(); // Create statement
            statement.executeUpdate(query);
        } catch(Exception e) { // handle errors:
            e.printStackTrace();
        }
    }

}