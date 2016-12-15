import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Timestamp;

/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 * 
 * DataController-klassen er vores controller i henhold til MVC-designet. Klassens metoder er SQL-queries, der anvendes ved at bruge MYSQL-klassens metoder til at forbinde til databasen.
 * Klassen står for al logikken til at specificere hvad og hvor der skal opdateres i databasen, samt specificere hvilke informationer der skal hentes ud fra databasen, og i hvilke modeller
 * informationen skal lagres.
 * Controlleren tager imod inputs fra viewet, når viewet skal opdateres.
 */
public class DataController
{
    public static DataController dataController = null;
    private MySQL mySql = MySQL.getInstance();
    private DataController(){}
    /**
     *  DataController er lavet ud fra et singleton pattern og skal tilgås gennem getInstance metoden
     */
    public static DataController getInstance(){
        if(dataController == null){
            dataController = new DataController();
        }
        return dataController;
    }
    
    /**
     * SQL-query til at tilføje film til databasen. retunerer en boolean for test formål. retur værdien bliver ignoreret af kalderen.
     * @param title, filmens title som skal tilføjes i databasen. filmens id bliver autoinkrementeret
     * @return kan bruges af evt. tests
     */
    public boolean addMovie(String title)
    {
        try{
            mySql.queryUpdate("INSERT INTO movies (title) VALUES ('"+ title +"');");
            return true;
        }
        catch(Exception e){
            return false;
        }
        
    }
    
    /**
     * SQL-query til at tilføje kunder til databasen. retunerer en boolean for test formål. retur værdien bliver ignoreret af kalderen
     * @param telephoneNumber, kundens telefonnummer
     * @param name, kundens navn
     * @return kan bruges af evt. tests
     */
     public boolean addCustomer(int telephoneNumber, String name)
    {
        if(getCustomer(telephoneNumber) == null){ 
            mySql.queryUpdate("INSERT INTO customers (telephone_number, name) VALUES ('"+ telephoneNumber + "', '" + name +"');");
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * SQL-query til at tilføje reservationer til databasen. retunerer en boolean for test formål. retur værdien bliver ignoreret af kalderen
     * @param telephoneNumber, kundens telefonnummer
     * @param showId, showets id
     * @param rowNumber, rækkens nummer
     * @columnNumber, kolonnens nummer
     * @return kan bruges af evt. tests
     */
    public boolean addReservation(int telephoneNumber, int showId, int rowNumber, int columnNumber){
        // Virker kun såfremt at telefonnummeret peger på en oprettet customer.
        if(getCustomer(telephoneNumber) != null){
            mySql.queryUpdate("INSERT INTO reservations (telephone_number, show_id, row_number, seat_number) VALUES ('"+ telephoneNumber +"', '" + showId + "', '" + rowNumber + "', '" + columnNumber+ "');");
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * SQL-query til at hente en film fra databasen, og returneres som et Movie objekt
     * @param id, filmens id
     * @return film objekt associeret med id'et
     */
    public Movie getMovie(int id){
        ResultSet r = mySql.query("SELECT * FROM movies WHERE movie_id = " + id + ";");
        try{
            // How to get data from the ResultSet
            while(r.next())
            {
                //get the title
                String title = r.getString("title");
                int movieId = r.getInt("movie_id");
                
                Movie movie = new Movie(movieId, title);
                // Finally will still be called, even if we return here!
                return movie;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getMovie");
        } 
        return null; 
    }
    
  
    
    /**
     * SQL-query, som kunne anvendes til at checke for, om en customer allerede er oprettet i databasen.
     * Da vi allerede har en primary key i form af telephone_number i vores customertabel
     * bliver der dog allerede checket for, at samme customer ikke kan oprettes flere gange.
     * @param telephoneNumber, kundens telefonnummer som fungerer som unik id
     * @return kundeobjekt associeret med telefonnummeret
     */
    public Customer getCustomer(int telephoneNumber){
        ResultSet r = mySql.query("SELECT * FROM customers WHERE telephone_number = " + telephoneNumber + ";");
        try{
            while(r.next())
            {
                String name = r.getString("name");
                int telephone_number = r.getInt("telephone_number");    
                Customer customer = new Customer(telephone_number, name);
                return customer;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getCustomer");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en sal fra databasen, og returneres som et Auditorium objekt.
     * @param id, auditoriets id
     * @return auditoriumobjekt associeret med id'et
     */
    public Auditorium getAuditorium(int id){
        ResultSet r = mySql.query("SELECT * FROM auditorium WHERE auditorium_id = " + id + ";");
        try{
            while(r.next())
            {
                int auditorium_id = r.getInt("auditorium_id");
                String name = r.getString("name");
                int row_number = r.getInt("row_number");
                int seat_number = r.getInt("seat_number");
                Auditorium auditorium = new Auditorium(auditorium_id, name, row_number, seat_number);
                return auditorium;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getAuditorium");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en forestilling fra databasen, og returneres som et Show objekt.
     * @param id, showets id
     * @return showobjekt associeret med id'et
     */
    public Show getShow(int id){
        ResultSet r = mySql.query("SELECT * FROM shows WHERE show_id = " + id + ";");
        try{
            while(r.next())
            {
                int showId = id;
                int movieId = r.getInt("movie_id");
                int auditorium = r.getInt("auditorium_id");
                Timestamp timestamp = r.getTimestamp("start_time");
                String time = timestamp.toString();
                Show show = new Show(showId, movieId, auditorium, time);
                return show;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getShow");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at hente en reservation fra databasen, og returneres som et Reservation objekt.
     * @param id, reservationens id
     * @return reservationobjekt associeret med id'et
     */
    public Reservation getReservation(int id){
        ResultSet r = mySql.query("SELECT * FROM reservations WHERE reservation_id = " + id + ";");
        try{
            while(r.next())
            {
                int resId = id;
                int telNr = r.getInt("telephone_number");
                int showId = r.getInt("show_id");
                int rowNr = r.getInt("row_number");
                int seatNr = r.getInt("seat_number");
                Reservation reservation = new Reservation(resId, telNr, showId, rowNr, seatNr);
                return reservation;
            }
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getReservation");
        } 
        return null; 
    }
    
    /**
     * SQL-query til at slette reservationer i databasen ud fra reservationens id.
     * @param id, reservationens id
     * @return kan bruges af evt. tests
     */
    public boolean deleteReservation(int id){
        if(getReservation(id) != null){
            mySql.queryUpdate("DELETE FROM reservations WHERE reservation_id = " + id + ";");
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * SQL-query til at slette reservationer i databasen ud fra reservationens showid og telefonnummer
     * @param showId, showets id
     * @param customerId, kundens telefonnummer
     */
     public void deleteReservation(int showId, int customerId ){
        mySql.queryUpdate("DELETE FROM reservations WHERE show_id = " + showId + " AND telephone_number = " + customerId + ";");
    }
    
     /**
     * SQL-query til at slette reservationer i databasen ud fra reservationens telefonnummer. oprettet i forbindelse med testing
     * @param customerId, kundens telefonnummer
     */
     public void deleteAllReservationToCustomer(int telephoneNumber){
         mySql.queryUpdate("DELETE FROM reservations WHERE telephone_number = " + telephoneNumber + ";");
    }
    
     /**
     * SQL-query til at slette film i databasen ud fra title. Oprettet i forbindelse med testing og
     * med henblik på mulighed for at øge funktionaliteten
     * @param title, filmens titel
     */
     public void deleteMovieFromTitle(String title){
         mySql.queryUpdate("DELETE FROM movies WHERE title = '" + title + "';");
    }
    
    /**
     * SQL-query til at slette customer i databasen ud fra telefonnummer. for testformål.
     * @param telephoneNumber, kundens telefonnummer
     * @return kan bruges af evt. tests 
     */
     public boolean deleteCustomer(int telephoneNumber){
        if(getCustomer(telephoneNumber) != null){
            mySql.queryUpdate("DELETE FROM customers WHERE telephone_number = " + telephoneNumber + ";");
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * SQL-query til at hente alle movie_id's i databasen, og returneres som en arrayliste af integers.
     * @return Alle film id'er i databasen
     */
    public List<Integer> getAllMovieIds(){
        List<Integer> movieIds = new ArrayList<>();
        ResultSet r = mySql.query("SELECT movie_id FROM movies;");
        try{
            while(r.next())
            {
                int movieId = r.getInt("movie_id");
                movieIds.add(movieId);
            }
            return movieIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getAllMovieIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle show_id's i databasen, og returneres som en arrayliste af integers.
     * @return Alle film id'er i databasen
     */
    public List<Integer> getAllShowIds() {
        List<Integer> showIds = new ArrayList<>();
        ResultSet r = mySql.query("SELECT show_id FROM shows;");
        try{
            while(r.next())
            {
                int showId = r.getInt("show_id");
                showIds.add(showId);
            }
            return showIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getAllShowIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle reservation_id's i databasen til en bestemt forestilling, og returneres som en arrayliste af integers.
     * @param showId, showets id som man vil have reservations id fra
     * @return Alle show id'er i databasen associeret med show id'et
     */
    public List<Integer> getAllShowReservationIds(int showId){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = mySql.query("SELECT reservation_id FROM reservations WHERE show_id = " + showId + ";");
        try{
            while(r.next())
            {
                int reservationId = r.getInt("reservation_id");
                reservationIds.add(reservationId);
            }
            return reservationIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getAllReservationIds");
        } 
        return null;
    }
    
    /**
     * SQL-query til at hente alle reservation_id's i databasen til en bestemt forestilling til en bestemt kunde, og returneres som en arrayliste af integers.
     * @param showId, showets id
     * @param telephoneNumber, kundens telefonnummer
     * @return Alle reservationer associeret med kundens telefonnummer og showets id
     */
    public List<Integer> getAllCustomerShowIds(int showId, int telephoneNumber){
        List<Integer> reservationIds = new ArrayList<>();
        ResultSet r = mySql.query("SELECT reservation_id FROM reservations WHERE show_id = " + showId + " AND telephone_number = " + telephoneNumber +";");
        try{
            while(r.next())
            {
                int reservationId = r.getInt("reservation_id");
                reservationIds.add(reservationId);
            }
            return reservationIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getallcustomerShowIds");
        } 
        return null;
    }
    /**
     * SQL-query til at hente alle aktive show_id's i databasen, og returneres som en arrayliste af integers.
     * @param movieId, filmens id
     * @return alle shows associeret med filmens id
     */
    public List<Integer> getActiveShows(int movieId){
        List<Integer> showIds = new ArrayList<>();
        ResultSet r = mySql.query("SELECT show_id FROM shows WHERE movie_id = " + movieId + ";");
        try{
            while(r.next())
            {
                int showId = r.getInt("show_id");
                showIds.add(showId);
            }
            return showIds;
        } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getActiveShows");
        } 
        return null;
    }
   
    /**
     * SQL-query til at hente alle reservationer i databasen, og returneres som en arrayliste af Reservation objekter.
     * @return en liste med reservationsobjekter associeret med alle reservationer i databasen
     */
    public ArrayList<Reservation> getDetailsForAllReservations(){
       ArrayList<Reservation> reservations = new ArrayList<>();
       ResultSet r = mySql.query("SELECT * FROM reservations" + ";");
       try{
            while(r.next())
            {
                int resId = r.getInt("reservation_id");
                int telNr = r.getInt("telephone_number");
                int showId = r.getInt("show_id");
                int rowNr = r.getInt("row_number");
                int seatNr = r.getInt("seat_number");
                Reservation reservation = new Reservation(resId, telNr, showId, rowNr, seatNr);
                reservations.add(reservation);
            }
            return reservations;
       } catch (SQLException e) {
            System.out.println("Exception, klasse: DataController   Metode: getDeatailsForAllReservations");
       } 
        return null;
    }
}
