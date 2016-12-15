
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Auditorium-klassen er en model i henhold til MVC-designet. Al data om salene fra databasen, som skal
 * tilgås i systemet, lagres i Auditorium-objekter. 
 */
public class Auditorium
{
    // Salens id
    private int auditorium_id;
    // Salens navn
    private String name;
    // Salens antal stolerækker
    private int row_number;
    // Salens antal sæder
    private int seat_number;

    /**
     * Konstruktør for objekter af klassen Auditorium
     */
    public Auditorium(int auditiorium_id, String name, int row_number, int seat_number)
    {
        this.auditorium_id = auditorium_id;
        this.name = name;
        this.row_number = row_number;
        this.seat_number = seat_number;
    }

    /**
     * Accessormetode for salens id
     */
    public int auditorium_id()
    {
        return auditorium_id;
    }
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
    {
        return row_number;
    }
    /**
     * Accessormetode for salens antal sæder
     */
    public int seat_number()
    {
        return seat_number;
    }
}
