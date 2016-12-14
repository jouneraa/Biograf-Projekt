import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;

/**
 * Write a description of class DataFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DataFactory
{
    public static DataFactory dataFactory = null;
    private DataFactory(){}
    
    public static DataFactory getInstance(){
        if(dataFactory == null){
            dataFactory = new DataFactory();
        }
        return dataFactory;
    }
    public void addMovie(String title)
    {
        MySQL.queryUpdate("INSERT INTO movies (title) VALUES ('"+ title +"');");
    }
    
     public void addCustomer(int telephone_number, String name)
    {
         MySQL.queryUpdate("INSERT INTO customers (telephone_number, name) VALUES ('"+ telephone_number + "', '" + name +"');");
    }
    
    public void addReservation(int telephone_number, int show_id, int row_number, int seat_number){
        // Virker kun såfremt at telefonnummeret peger på en oprettet customer.
        MySQL.queryUpdate("INSERT INTO reservations (telephone_number, show_id, row_number, seat_number) VALUES ('"+ telephone_number +"', '" + show_id + "', '" + row_number + "', '" + seat_number + "');"); 
    }
    //retunerer en titel på film
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
     * Accessormetode, som kunne anvendes til at checke for, om en customer allerede er oprettet i databasen.
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
    
    //laver og retunerer et show object med alle information udfyldt
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
    
    public void deleteReservation(int id){
        MySQL.queryUpdate("DELETE FROM reservations WHERE reservation_id = " + id + ";");
    }
     public void deleteReservation(int showId, int customerId ){
        MySQL.queryUpdate("DELETE FROM reservations WHERE show_id = " + showId + " AND telephone_number = " + customerId + ";");
    }
    
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
    
    public List<Integer> getAllShowReservationIds(int show_id){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT reservation_id FROM reservations WHERE show_id = " + show_id + ";");
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
    
    public List<Integer> getAllCustomerShowIds(int show_id, int telephone){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT reservation_id FROM reservations WHERE show_id = " + show_id + " AND telephone_number = " + telephone +";");
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
    
    public List<Integer> getActiveShows(int movie_id){
        List<Integer> showIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT show_id FROM shows WHERE movie_id = " + movie_id + ";");
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
