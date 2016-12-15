
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
    
    /**
     * Accessormetode for salens id
     */
    public int getAuditoriumId()
    {
        return auditoriumId;
    }
    
    /**
     * Accessormetode for salens navn
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessormetode for salens antal sæderækker
     */
    public int getRowNumber()
    {
        return rowNumber;
    }
    
    /**
     * Accessormetode for salens antal sædekolonner
     */

    public int getColumnNumber()
    {
        return columnNumber;
    }
}
