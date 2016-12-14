import java.util.*;

/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    DataFactory dataFactory = new DataFactory();
            
    public static void main(String[] args){
        Test test = new Test();
        test.addReservation();
    }
    
    public void addReservation(){
        
        List<Integer> allReservationIds = dataFactory.getAllShowReservationIds(1);
            List<Reservation> allReservations = new ArrayList<>();
            for(int x : allReservationIds){
                System.out.println(x);
                
                Reservation res = dataFactory.getReservation(x);
                System.out.println("reservationens telefonnr" + res);
                //allReservations.add(dataFactory.getReservation(x));
            }
            /*
            for(Reservation y : allReservations){
                System.out.println(y.row_number());
            }
            */
       
    }
    
    
    
}
