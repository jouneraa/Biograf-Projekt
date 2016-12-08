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
    public String getMovieTitle(int id){
        ResultSet r = MySQL.query("SELECT title FROM movies WHERE movie_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            if(r.next())
            {
                //get the title
                String title = r.getString("title");
                // Finally will still be called, even if we return here!
                return title;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null; 
    }
   
    
}
