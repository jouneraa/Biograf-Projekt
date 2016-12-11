
/**
 * Write a description of class Auditorium here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Auditorium
{
    // instance variables - replace the example below with your own
    private int auditorium_id;
    private String name;
    private int row_number;
    private int seat_number;

    /**
     * Constructor for objects of class Auditorium
     */
    public Auditorium(int auditiorium_id, String name, int row_number, int seat_number)
    {
        // initialise instance variables
        this.auditorium_id = auditorium_id;
        this.name = name;
        this.row_number = row_number;
        this.seat_number = seat_number;
    }

  
    public int auditorium_id()
    {
        return auditorium_id;
    }
    
    public String name()
    {
        return name;
    }
    
    public int row_number()
    {
        return row_number;
    }
    
    public int seat_number()
    {
        return seat_number;
    }
}
