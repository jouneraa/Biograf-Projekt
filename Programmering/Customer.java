
/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer
{
    // instance variables - replace the example below with your own
    private int telephone_number;
    private String name;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(int telephone_number, String name)
    {
        // initialise instance variables
        this.telephone_number = telephone_number;
        this.name = name;
    }

    
    public int getTelephone_Number()
    {
        return telephone_number;
    }
    
    public String name()
    {
        return name;
    }
}