import java.util.List;
import java.util.ArrayList;

/**
 * Hver kunde kan have flere reservationer tilknyttet til forskellige film som kan hentes og modificeres
 */
public class Customer
{
    private String name;
    private String telephone;
    private List<Reservation> reservations;
    
    public Customer(String name, String telephone){
        this.name = name;
        this.telephone = telephone;
        
        reservations = new ArrayList<>();
    }
    
    /**
     * tilføj en reservation
     */
    public void addReservation(Sal sal, List<Seat> seats){
        reservations.add(new Reservation(sal, seats));
    }
    
    
    
    
   
}
