
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Auditorium-klassen er en model i henhold til MVC-designet. Al data om salene fra databasen, som skal
 * tilgås i systemet, lagres i Auditorium-objekter. 
 */
public class Auditorium
{
<<<<<<< HEAD
    // instance variables - replace the example below with your own
    private int auditoriumId;
    private String name;
    private int rowNumber;
    private int columnNumber;
=======
    // Salens id
    private int auditorium_id;
    // Salens navn
    private String name;
    // Salens antal stolerækker
    private int row_number;
    // Salens antal sæder
    private int seat_number;
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6

    /**
     * Konstruktør for objekter af klassen Auditorium
     */
    public Auditorium(int auditoriumId, String name, int rowNumber, int columnNumber)
    {
<<<<<<< HEAD
        // initialise instance variables
        this.auditoriumId = auditoriumId;
=======
        this.auditorium_id = auditorium_id;
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6
        this.name = name;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

<<<<<<< HEAD
  
    public int getAuditoriumId()
=======
    /**
     * Accessormetode for salens id
     */
    public int auditorium_id()
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6
    {
        return auditoriumId;
    }
<<<<<<< HEAD
    
    public String getName()
    {
        return name;
    }
    
    public int getRowNumber()
=======
    /**
     * Accessormetode for salens navn
     */
    public String name()
    {
        return name;
    }
    /**
     * Accessormetode for salens antal stolerækker
     */
    public int row_number()
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6
    {
        return rowNumber;
    }
<<<<<<< HEAD
    
    public int getColumnNumber()
=======
    /**
     * Accessormetode for salens antal sæder
     */
    public int seat_number()
>>>>>>> 6c82a5641000e73203dab02d260a9d4232fc83c6
    {
        return columnNumber;
    }
}
