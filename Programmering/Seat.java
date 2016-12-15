
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * Seat-klassen bruges ikke i forbindelse med databasen, men internt i view Klasserne
 * i modsætning til de andre modelklasser
 */
public class Seat {
    // Sædets stolerække
    private int row;
    // Sædets stolekolonne
    private int column;
    
    /**
     * Konstruktør for objekter af klassen Seat
     * @param row, sædets række
     * @param column, sædets kolonne
     */
    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * Accessormetode for sædets stolerække
     * @return sædets række
     */
    public int getRow() {
        return row;
    }
    /**
     * Accessormetode for sædets stolekolonne
     * @return sædets kolonne
     */
    public int getColumn() {
        return column;
    }
}

