
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Show-klassen er en model i henhold til MVC-designet. Al data om forestillinger fra databasen, som skal
 * tilgås i systemet, lagres i Show-objekter. 
 */
public class Show
{
    // Forestillingens id
    private int showId;
    // Forestillingens movie_id
    private int movieId;
    // Forestillingens auditorium_id;
    private int auditoriumId;
    // Forestillingens starttidspunkt
    private String startTime; 

    /**
     * Konstruktør for objekter af klassen Show
     */
    public Show(int showId, int movieId,int auditoriumId, String startTime)
    {
        this.showId = showId;
        this.movieId = movieId;
        this.auditoriumId = auditoriumId;
        this.startTime = startTime;
    }

    /**
     * Accessormetode for forestillingens id
     */
    public int getShowId()
    {
        return showId;
    }
    
    /**
     * Accessormetode for forestillingens movie_id
     */
    public int getMovieId()
    {
        return movieId;
    }
    
    /**
     * Accessormetode for forestillingens auditorium_id
     */
    public int getAuditoriumId()
    {
        return auditoriumId;
    }
    
    /**
     * Accessormetode for forestillingens starttidspunkt
     */
        public String getStartTime()
    {
        return startTime;
    }
}