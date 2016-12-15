import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;

/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * DataController-klassen er vores controller i henhold til MVC-designet. Klassens metoder er SQL-queries, der anvendes ved at bruge MYSQL-klassens metoder til at forbinde til databasen.
 * Klassen står for al logikken til at specificere hvad og hvor der skal opdateres i databasen, samt specificere hvilke informationer der skal hentes ud fra databasen, og i hvilke modeller
 * informationen skal lagres.
 * Controlleren tager imod inputs fra viewet, når viewet skal opdateres.
 */
public class DataFactory
{
    /**
     * Skriv her Jonathan
     */
    public static DataFactory dataFactory = null;
    private DataFactory(){}
    
    public static DataFactory getInstance(){
        if(dataFactory == null){
            dataFactory = new DataFactory();
        }
        return dataFactory;
    }
    
    /**
     * SQL-query til at tilføje film til databasen
     */
    public void addMovie(String title)
    {
        MySQL.queryUpdate("INSERT INTO movies (title) VALUES ('"+ title +"');");
    }
    
    /**
     * SQL-query til at tilføje kunder til databasen
     */
     public void addCustomer(int telephoneNumber, String name)
    {
        if(getCustomer(telephoneNumber) == null){ 
            MySQL.queryUpdate("INSERT INTO customers (telephone_number, name) VALUES ('"+ telephoneNumber + "', '" + name +"');");
        }
    }
    
    /**
     * SQL-query til at tilføje reservationer til databasen
     */
    public void addReservation(int telephoneNumber, int showId, int rowNumber, int seatNumber){
        // Virker kun såfremt at telefonnummeret peger på en oprettet customer.
        MySQL.queryUpdate("INSERT INTO reservations (telephone_number, show_id, row_number, seat_number) VALUES ('"+ telephoneNumber +"', '" + showId + "', '" + rowNumber + "', '" + seatNumber + "');"); 
    }
    
    /**
     * SQL-query til at hente en film fra databasen, og returneres som et Movie objekt
     */
    public Movie getMovie(int id){
        ResultSet r = MySQL.query("SELECT * FROM movies WHERE movie_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                String title = r.getString("title");
                int movieId = r.getInt("movie_id");
                
                Movie movie = new Movie(movieId, title);
                // Finally will still be called, even if we return here!
                return movie;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getMovie");
        } 
        return null; 
    }
    
    /**
     * SQL-query, som kunne anvendes til at checke for, om en customer allerede er oprettet i databasen.
     * Da vi allerede har en primary key i form af telephone_number i vores customertabel
     * bliver der dog allerede checket for, at samme customer ikke kan oprettes flere gange.
     */
    public Customer getCustomer(int id){
        ResultSet r = MySQL.query("SELECT * FROM customers WHERE telephone_number = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                String name = r.getString("name");
                int telephone_number = r.getInt("telephone_number");
                
                Customer customer = new Customer(telephone_number, name);
                // Finally will still be called, even if we return here!
                return customer;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getCustomer");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en sal fra databasen, og returneres som et Auditorium objekt.
     */
    public Auditorium getAuditorium(int id){
        ResultSet r = MySQL.query("SELECT * FROM auditorium WHERE auditorium_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                int auditorium_id = r.getInt("auditorium_id");
                String name = r.getString("name");
                int row_number = r.getInt("row_number");
                int seat_number = r.getInt("seat_number");
                
                Auditorium auditorium = new Auditorium(auditorium_id, name, row_number, seat_number);
                
                return auditorium;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getAuditorium");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en forestilling fra databasen, og returneres som et Show objekt.
     */
    public Show getShow(int id){
        ResultSet r = MySQL.query("SELECT * FROM shows WHERE show_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                int showId = id;
                int movieId = r.getInt("movie_id");
                int auditorium = r.getInt("auditorium_id");
                Timestamp timestamp = r.getTimestamp("start_time");
                String time = timestamp.toString();
                
                
                Show show = new Show(showId, movieId, auditorium, time);
                // Finally will still be called, even if we return here!
                return show;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getShow");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en reservation fra databasen, og returneres som et Reservation objekt.
     */
    public Reservation getReservation(int id){
        // Skal der ikke selectes fra reservations, og ud fra reservationID? - Ikke shows.
        ResultSet r = MySQL.query("SELECT * FROM reservations WHERE reservation_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int resId = id;
                int telNr = r.getInt("telephone_number");
                int showId = r.getInt("show_id");
                int rowNr = r.getInt("row_number");
                int seatNr = r.getInt("seat_number");
                
                Reservation reservation = new Reservation(resId, telNr, showId, rowNr, seatNr);
                // Finally will still be called, even if we return here!
                return reservation;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getReservation");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at slette reservationer i databasen ud fra reservationens id.
     */
    public void deleteReservation(int id){
        MySQL.queryUpdate("DELETE FROM reservations WHERE reservation_id = " + id + ";");
    }
    
    /**
     * SQL-query til at slette reservationer i databasen ud fra reservationens showid og telefonnummer
     */
     public void deleteReservation(int showId, int customerId ){
        MySQL.queryUpdate("DELETE FROM reservations WHERE show_id = " + showId + " AND telephone_number = " + customerId + ";");
    }
    
    /**
     * SQL-query til at hente alle movie_id's i databasen, og returneres som en arrayliste af integers.
     */
    public List<Integer> getAllMovieIds(){
        List<Integer> movieIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT movie_id FROM movies;");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int movieId = r.getInt("movie_id");
                // Finally will still be called, even if we return here!
                movieIds.add(movieId);
            }
            return movieIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getAllMovieIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle show_id's i databasen, og returneres som en arrayliste af integers.
     */
    public List<Integer> getAllShowIds() {
        List<Integer> showIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT show_id FROM shows;");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int showId = r.getInt("show_id");
                // Finally will still be called, even if we return here!
                showIds.add(showId);
            }
            return showIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getAllShowIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle reservation_id's i databasen til en bestemt forestilling, og returneres som en arrayliste af integers.
     */
    public List<Integer> getAllShowReservationIds(int showId){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT reservation_id FROM reservations WHERE show_id = " + showId + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int reservationId = r.getInt("reservation_id");
                // Finally will still be called, even if we return here!
                reservationIds.add(reservationId);
            }
            return reservationIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getAllReservationIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle reservation_id's i databasen til en bestemt forestilling til en bestemt kunde, og returneres som en arrayliste af integers.
     */
    public List<Integer> getAllCustomerShowIds(int showId, int telephoneNumber){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT reservation_id FROM reservations WHERE show_id = " + showId + " AND telephone_number = " + telephoneNumber +";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int reservationId = r.getInt("reservation_id");
                // Finally will still be called, even if we return here!
                reservationIds.add(reservationId);
            }
            return reservationIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getallcustomerShowIds");
        } 
        return null;
    }
    /**
     * SQL-query til at hente alle aktive show_id's i databasen, og returneres som en arrayliste af integers.
     */
    public List<Integer> getActiveShows(int movieId){
        List<Integer> showIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT show_id FROM shows WHERE movie_id = " + movieId + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int showId = r.getInt("show_id");
                // Finally will still be called, even if we return here!
                showIds.add(showId);
            }
            return showIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getActiveShows");
        } 
        return null;
    }
   
    /**
     * SQL-query til at hente alle reservationer i databasen, og returneres som en arrayliste af Reservation objekter.
     */
    public ArrayList<Reservation> getDetailsForAllReservations(){
       ArrayList<Reservation> reservations = new ArrayList<>();
       ResultSet r = MySQL.query("SELECT * FROM reservations" + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                int resId = r.getInt("reservation_id");
                int telNr = r.getInt("telephone_number");
                int showId = r.getInt("show_id");
                int rowNr = r.getInt("row_number");
                int seatNr = r.getInt("seat_number");
                
                 Reservation reservation = new Reservation(resId, telNr, showId, rowNr, seatNr);
                // Finally will still be called, even if we return here!
                reservations.add(reservation);
            }
            return reservations;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataFactory   Metode: getDeatailsForAllReservations");
        } 
        return null;
    }
    
    
}
