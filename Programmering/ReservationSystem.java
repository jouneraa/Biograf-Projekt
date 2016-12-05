import java.util.ArrayList;
import java.util.List;
public class ReservationSystem{
    private Sal sal1;
    
    List<Customer> customers;
    public ReservationSystem(){
        addRooms();
        customers = new ArrayList();
    }
    
    public void addRooms(){
        sal1 = new Sal1(10,10);
        
    }
    
    /**
     * add customer
     */
    public void addCustomer(String name, String telephone){
        customers.add(new Customer(name, telephone));
    }
    
}
