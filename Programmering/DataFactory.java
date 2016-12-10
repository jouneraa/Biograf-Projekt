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
    public void addMovie(String title)
    {
        MySQL.queryUpdate("INSERT INTO movies (title) VALUES ('"+ title +"');");
    }
    
     public void addCustomer(int telephone_number, String name)
    {
         MySQL.queryUpdate("INSERT INTO customers (telephone_number, name) VALUES ('"+ telephone_number + "', '" + name +"');");
    }
    
    public void addReservation(int telephone_number, int show_id, int row_number, int seat_number, Timestamp reservation_time){
        MySQL.queryUpdate("INSERT INTO reservations (telephone_number, show_id, row_number, seat_number, reservation_time) VALUES ('"+ telephone_number +"', '" + show_id + "', '" + row_number + "', '" + seat_number + "', '" + reservation_time + "');"); 
    }
    //retunerer en titel på film
    public Movie getMovie(int id){
        ResultSet r = MySQL.query("SELECT * FROM movies WHERE movie_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            if(r.next())
            {
                //get the title
                String title = r.getString("title");
                int movieId = r.getInt("movie_id");
                
                Movie movie = new Movie(movieId, title);
                // Finally will still be called, even if we return here!
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null; 
    }
    
    //laver og retunerer et show object med alle information udfyldt
    public Show getShow(int id){
        ResultSet r = MySQL.query("SELECT * FROM shows WHERE show_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            if(r.next())
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
            e.printStackTrace();
        } 
        return null; 
    }
    
    public Reservation getReservation(int id){
        ResultSet r = MySQL.query("SELECT * FROM shows WHERE show_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            if(r.next())
            {
                //get the title
                int resId = id;
                int telNr = r.getInt("telephone_number");
                int showId = r.getInt("show_id");
                int rowNr = r.getInt("row_number");
                int seatNr = r.getInt("seat_number");
                Timestamp resTime = r.getTimestamp("reservation_time");
                String time = resTime.toString();
                
                
                Reservation reservation = new Reservation(resId, telNr, showId, rowNr, seatNr);
                // Finally will still be called, even if we return here!
                return reservation;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null; 
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
            e.printStackTrace();
        } 
        return null;
    }
    
    public List<Integer> getAllReservations(){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = MySQL.query("SELECT reservation_id FROM reservations;");
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
            e.printStackTrace();
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
            e.printStackTrace();
        } 
        return null;
    }
   
   
}
