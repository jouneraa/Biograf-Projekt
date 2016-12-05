import java.util.List;
import java.util.ArrayList;



public class Reservation
{
    private Sal sal;
    private List<Seat> seats;
    public Reservation(Sal sal, List<Seat> seats)
    {
        this.sal = sal;
        this.seats = seats;
    }

}
