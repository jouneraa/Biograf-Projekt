
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Reservation-klassen er en model i henhold til MVC-designet. Al data om reservationer fra databasen, som skal
 * tilgås i systemet, lagres i Reservation-objekter. 
 */
public class Reservation{
    // Reservationens id
    private int reservation_id;
    // Reservationens telefonnummer
    private int telephone_number;
    // Reservationens show_id
    private int show_id;
    // Reservationens stolerækkenummer
    private int row_number;
    // Reservationens sædenummer
    private int seat_number;
    
    /**
     * Konstruktør for objekter af klassen Reservation
     */
    public Reservation(int reservation_id,int telephone_number, int show_id, int row_number, int seat_number){
        this.reservation_id = reservation_id;
        this.telephone_number = telephone_number;
        this.show_id = show_id;
        this.row_number = row_number;
        this.seat_number =seat_number;
    }
    
    /**
     * Accessormetode for reservationens id
     */
    public int reservation_id() {
        return reservation_id;
    }
    /**
     * Accessormetode for reservationens telefonnummer
     */
    public int telephone_number() {
        return telephone_number;
    }
    /**
     * Accessormetode for reservationens show_id
     */
    public int show_id() {
        return show_id;
    }
    /**
     * Accessormetode for reservationens stolerækkenummer
     */
    public int row_number() {
        return row_number;
    }
    /**
     * Accessormetode for reservationens sædenummer
     */
    public int seat_number() {
        return seat_number;
    }
}