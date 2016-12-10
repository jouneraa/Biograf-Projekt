
/**
 * Write a description of class Show here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Show
{
    // dato-format?
    private int show_id;
    private int movie_id;
    private int auditorium_id;
    private String start_time; 

    /**
     * Constructor for objects of class Show
     */
    public Show(int show_id, int movie_id,int auditorium_id, String start_time)
    {
        this.show_id = show_id;
        this.movie_id = movie_id;
        this.auditorium_id = auditorium_id;
        this.start_time = start_time;
    }

    
    public int show_id()
    {
        return show_id;
    }
    
    public int movie_id()
    {
        return movie_id;
    }
    
    public int auditorium_id()
    {
        return auditorium_id;
    }
    
    public String start_time()
    {
        return start_time;
    }
}