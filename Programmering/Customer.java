
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Customer-klassen er en model i henhold til MVC-designet. Al data om kunder fra databasen, som skal
 * tilgås i systemet, lagres i Customer-objekter. 
 */
public class Customer
{
    // Kundens telefonnummer
    private int telephone_number;
    // Kundens navn
    private String name;

    /**
     * Konstruktør for objekter af klassen Customer
     */
    public Customer(int telephone_number, String name)
    {
        this.telephone_number = telephone_number;
        this.name = name;
    }

    /**
     * Accessormetode for kundens telefonnummer
     */
    public int getTelephone_Number()
    {
        return telephone_number;
    }
    /**
     * Accessormetode for kundens navn
     */
    public String name()
    {
        return name;
    }
    
    
    
}