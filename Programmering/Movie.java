/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * Movie-klassen er en model i henhold til MVC-designet. Al data om film fra databasen, som skal
 * tilgås i systemet, lagres i Movie-objekter. 
 */
public class Movie{
    // Filmens id
    private int movieId;
    // Filmens navn
    private String title;
    
    /**
     * Konstruktør for objekter af klassen Movie
     */
    public Movie(int movieId, String title){
        this.movieId = movieId;
        this.title = title;
    }
    
    /**
     * Accessormetode for filmens titel
     * @return filmens titel
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * Accessormetode for filmens id
     * @param filmen id
     */
    public int getMovieId(){
        return movieId;
    }
}