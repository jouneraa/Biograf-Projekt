
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
    private int telephoneNumber;
    // Kundens navn
    private String name;

    /**
     * Konstruktør for objekter af klassen Customer
     * @param telephoneNumber, kundens telefonnummer
     * @param name, kundens navn
     */
    public Customer(int telephoneNumber, String name)
    {
        this.telephoneNumber = telephoneNumber;
        this.name = name;
    }

    /**
     * Accessormetode for kundens telefonnummer
     * @return kundens telefonnummer
     */
    public int getTelephoneNumber()
    {
        return telephoneNumber;
    }
    
    /**
     * Accessormetode for kundens navn
     * return kundens navn
     */
    public String getName()
    {
        return name;
    }
    
    
    
}