public class Reservation{

    private int reservation_id;
    private int telephone_number;
    private int show_id;
    private int row_number;
    private int seat_number;
    
    public Reservation(int reservation_id,int telephone_number, int show_id, int row_number, int seat_number){
        this.reservation_id = reservation_id;
        this.telephone_number = telephone_number;
        this.show_id = show_id;
        this.row_number = row_number;
        this.seat_number =seat_number;
    }
    
    public int reservation_id() {
        return reservation_id;
    }

    public int telephone_number() {
        return telephone_number;
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