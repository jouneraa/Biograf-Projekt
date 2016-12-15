
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Auditorium-klassen er en model i henhold til MVC-designet. Al data om salene fra databasen, som skal
 * tilgås i systemet, lagres i Auditorium-objekter. 
 */
public class Auditorium
{
    private int auditoriumId;
    private String name;
    private int rowNumber;
    private int columnNumber;
    public Auditorium(int auditoriumId, String name, int rowNumber, int columnNumber)
    {
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
