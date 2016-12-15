import java.sql.Connection;
import java.sql.*; // Import required packages
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * MySQL-klassen har to metoder, som opretter forbindelse til vores database og sender SQL-statements til databasen. Når et SQL-statement sendes til databasen opdateres eller tilgås databasen,
 * som ud fra det, der blev anmodet ved query'en.
 * Klassen anvendes således til kunne at tilgå data i databasen, og anvende dataerne i vores reservationssystem. 
 */
public class MySQL {
    // Database credentials: replace with *YOUR* data here:
    private static final String MYDB = "CinemaDatabase";
    private static Connection connection;
    private static Statement statement;
    private static MySQL instance = null;
    static final String USER = "juliusf";
    static final String PASS = "kransekage66";
    // JDBC driver name and database URL
    static final String DB_URL = "jdbc:mysql://mydb.itu.dk/" + MYDB;

    /**
     * private konstruktor da det er en singleton
     */
    private MySQL() {}
    
    public static MySQL getInstance(){
        if(instance == null){
            instance = new MySQL();
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = DriverManager.getConnection(MySQL.DB_URL, MySQL.USER, MySQL.PASS); // Open connection
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    /**
     * Metode til at tilgå og udvælge data i databasen, som returneres i et ResultSet objekt. 
     * @param query, strengen som skal hente fra databasen
     * @return et resultsæt med de efterspurgte værdier
     */
    public ResultSet query(String query){
        ResultSet rs = null;
        try {
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
     * @param query, strengen som skal hente fra databasen
     */
    public void queryUpdate(String query) {
        ResultSet rs = null;
        try {
            statement = connection.createStatement(); // Create statement
            statement.executeUpdate(query);
        } catch(Exception e) { // handle errors:
            System.out.println("Exception, klasse: MySQL   Metode: queryUpdate");
        }
    }

}