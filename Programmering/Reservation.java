public class Reservation{

    private int reservation_id;
    private int customer_id;
    private int show_id;
    private int row_number;
    private int seat_number;
    
    public Reservation(int reservation_id,int customer_id, int show_id, int row_number, int seat_number){
        this.reservation_id = reservation_id;
        this.customer_id = customer_id;
        this.show_id = show_id;
        this.row_number = row_number;
        this.seat_number =seat_number;
    }
    
    public int reservation_id() {
        return reservation_id;
    }

    public int customer_id() {
        return customer_id;
    }

    public int show_id() {
        return show_id;
    }

    public int row_number() {
        return row_number;
    }

    public int seat_number() {
        return seat_number;
    }
}