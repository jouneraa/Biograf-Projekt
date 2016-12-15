
/**
 * Write a description of class Auditorium here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Auditorium
{
    // instance variables - replace the example below with your own
    private int auditoriumId;
    private String name;
    private int rowNumber;
    private int columnNumber;

    /**
     * Constructor for objects of class Auditorium
     */
    public Auditorium(int auditoriumId, String name, int rowNumber, int columnNumber)
    {
        // initialise instance variables
        this.auditoriumId = auditoriumId;
        this.name = name;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

  
    public int getAuditoriumId()
    {
        return auditoriumId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getRowNumber()
    {
        return rowNumber;
    }
    
    public int getColumnNumber()
    {
        return columnNumber;
    }
}
