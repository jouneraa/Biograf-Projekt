
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Seat-klassen..
 */
public class Seat {
    // Sædets stolerække
    private int row;
    // Sædets stolekolonne
    private int column;
    
    /**
     * Konstruktør for objekter af klassen Seat
     */
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }
    /**
     * Accessormetode for sædets stolerække
     */
    public int getRow() {
        return row;
    }
    /**
     * Accessormetode for sædets stolekolonne
     */
    public int getColumn() {
        return column;
    }
}

