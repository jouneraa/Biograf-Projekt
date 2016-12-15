/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * Movie-klassen er en model i henhold til MVC-designet. Al data om film fra databasen, som skal
 * tilgås i systemet, lagres i Movie-objekter. 
 */
public class Movie{
    // Filmens id
    private int movie_id;
    // Filmens navn
    private String title;
    
    /**
     * Konstruktør for objekter af klassen Movie
     */
    public Movie(int movie_id, String title){
        this.movie_id = movie_id;
        this.title = title;
    }
    /**
     * Accessormetode for filmens titel
     */
    public String title(){
        return title;
    }
    /**
     * Accessormetode for filmens id
     */
    public int getMovieId(){
        return movie_id;
    }
}