import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;

public class ReservationSystem{
  
    public ReservationSystem(){
        
    }
    
    
    public void addMovie(String title)
    {
        MySQL.queryUpdate("INSERT INTO movies (title) VALUES ('"+ title +"');");
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
                //get the title
                int auditorium = r.getInt("auditorium_id");
               
                Timestamp timestamp = r.getTimestamp("start_time");
                String time = timestamp.toString();
                
                
                Show show = new Show(auditorium, time);
                // Finally will still be called, even if we return here!
                return show;
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
   
    public void addCustomer(int telephone_number, String name)
    {
         MySQL.queryUpdate("INSERT INTO customers (telephone_number, name) VALUES ('"+ telephone_number + "', '" + name +"');");
    }
}
