import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
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
   
    
}
