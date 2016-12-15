
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
    private int show_id;
    // Forestillingens movie_id
    private int movie_id;
    // Forestillingens auditorium_id;
    private int auditorium_id;
    // Forestillingens starttidspunkt
    private String start_time; 

    /**
     * Konstruktør for objekter af klassen Show
     */
    public Show(int show_id, int movie_id,int auditorium_id, String start_time)
    {
        this.show_id = show_id;
        this.movie_id = movie_id;
        this.auditorium_id = auditorium_id;
        this.start_time = start_time;
    }

    /**
     * Accessormetode for forestillingens id
     */
    public int show_id()
    {
        return show_id;
    }
    /**
     * Accessormetode for forestillingens movie_id
     */
    public int movie_id()
    {
        return movie_id;
    }
    /**
     * Accessormetode for forestillingens auditorium_id
     */
    public int auditorium_id()
    {
        return auditorium_id;
    }
    /**
     * Accessormetode for forestillingens starttidspunkt
     */
    public String start_time()
    {
        return start_time;
    }
}