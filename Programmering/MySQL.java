import java.sql.Connection;
import java.sql.*; // Import required packages
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * MySQL-klassen har to metoder, som opretter forbindelse til vores database og sender SQL-statements til databasen. Når et SQL-statement sendes til databasen oprettes der et ResultSet objekt, der
 * repræsenterer en tabel af det data, som blev tilgået ved query'en.
 * Klassen anvendes således til kunne at tilgå data i databasen, og anvende dataerne i vores reservationssystem. 
 */
public class MySQL {
    // Database credentials: replace with *YOUR* data here:
    private static final String MYDB = "CinemaDatabase";
    private static Connection connection;
    static final String USER = "juliusf";
    static final String PASS = "kransekage66";
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;

<<<<<<< HEAD
    private MySQL() {}

=======
    /**
     * Default konstruktør
     */
    private MySQL() {

    }
    
    /**
     * Metode til at tilgå og udvælge data i databasen, som returneres i et ResultSet objekt. 
     */
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6
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
            System.out.println("Exception, klasse: MySQL   Metode: query");
        }
        return null;
    }
    
    /**
     * Metode til at tilgå og opdatere data i databasen.
     */
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
            System.out.println("Exception, klasse: MySQL   Metode: queryUpdate");
        }
    }

}