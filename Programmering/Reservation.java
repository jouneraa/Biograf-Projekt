
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Reservation-klassen er en model i henhold til MVC-designet. Al data om reservationer fra databasen, som skal
 * tilgås i systemet, lagres i Reservation-objekter. 
 */
public class Reservation{
    // Reservationens id
    private int reservationId;
    // Reservationens telefonnummer
    private int telephoneNumber;
    // Reservationens show_id
    private int showId;
    // Reservationens stolerækkenummer
    private int rowNumber;
    // Reservationens sædenummer
    private int columnNumber;
    
    /**
     * Konstruktør for objekter af klassen Reservation
     */
    public Reservation(int reservationId,int telephoneNumber, int showId, int rowNumber, int columnNumber){
        this.reservationId = reservationId;
        this.telephoneNumber = telephoneNumber;
        this.showId = showId;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }
    
    /**
     * Accessormetode for reservationens id
     */
    public int getReservationId() {
        return reservationId;
    }
    /**
     * Accessormetode for reservationens telefonnummer
     */
    public int getTelephoneNumber() {
        return telephoneNumber;
    }
    /**
     * Accessormetode for reservationens show_id
     */
    public int getShowId() {
        return showId;
    }
    /**
     * Accessormetode for reservationens stolerækkenummer
     */
    public int getRowNumber() {
        return rowNumber;
    }
    /**
     * Accessormetode for reservationens sædenummer
     */
    public int getColumnNumber() {
        return columnNumber;
    }
}