// ser så fucking lækkert ud (y) - en test til sourcetree
import java.util.List;
import java.util.ArrayList;
public abstract class Sal
{
    private Seat[][] seats;
    
    public Sal(int rowX, int columnY){
        seats = new Seat[rowX][columnY];
    }
    
    /* returnerer alle de pladser i arrayet af sæder hvor pladsen = null, den her metode 
       skal muligvis hele tiden køres hver gang der sker en action, så gui´en bliver opdateret*/ 
    public void getEmptySpaces(){
        
    }
    
    /* viser hvilken person der har booket det pågældne sæde*/ 
    public void getSeatInformation(){
    }
    
    public List<Seat> getSeatsAvailable(){
        List<Seat> seatsAvailable = new ArrayList<>();
        for(int i = 0; i < seats.length ; i++){
            for(int j = 0 ; j < seats[i].length ; j++){
                if(seats[i][j] == null){
                    seatsAvailable.add(seats[i][j]);
                }
            }
        }
        return seatsAvailable;
    }
    
    
    
}
