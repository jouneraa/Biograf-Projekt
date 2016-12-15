import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 * Gruppe 33: Jonas, Jonathan, Julius
 * Itu-mails: jskr@itu.dk - josn@itu.dk - jufi@itu.dk
 */
public class TableView extends JPanel
{
    private BiografViewer biografViewer;
    private JTable table;
    private DefaultTableModel model;
    private DataFactory dataFactory = DataFactory.getInstance();
    private List<Seat> selectedSeats;
    private JTabbedPane jtp;
    private JFrame frame;
    JPanel jp3;
    /**
     * 
     */
    public TableView(BiografViewer biografViewer, List<Seat> selectedSeats, JTabbedPane jtp, JFrame frame, JPanel jp3, JTable table, DefaultTableModel model){
        super(new BorderLayout());
        this.biografViewer = biografViewer;
        this.selectedSeats = selectedSeats;
        this.jtp = jtp;
        this.frame = frame;
        this.jp3 = jp3;
        this.table = table;
        this.model = model;
        makeFrame();
    }
    
    /**
     * 
     */
    public void makeFrame(){
        table = new JTable();

        makeTableModel();
            
            
        table.setBackground(Color.GREEN);
        table.setForeground(Color.BLACK);
        table.setRowHeight(30);
            
        JTextField searchCustomer = new JTextField();
        JLabel searchInfo = new JLabel("Søg efter reservationsoplysninger:");
        JButton btnDelete = new JButton("Slet reservation");
        JButton btnEdit = new JButton("Ret reservation");
            
        searchCustomer.setBounds(210,265,150,25);
        searchInfo.setBounds(80,265,150,25);
        searchCustomer.setPreferredSize( new Dimension( 200, 24 ) );
        btnDelete.setBounds(210,310,150,25);
        btnEdit.setBounds(210,310,150,25);
            
           
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0,0,880,200);
            
                      
        this.add(pane);
        JPanel lowerFlowLayout = new JPanel(new FlowLayout()); 
        lowerFlowLayout.add(searchCustomer);
        lowerFlowLayout.add(searchInfo);
        lowerFlowLayout.add(btnDelete);
        lowerFlowLayout.add(btnEdit);
        this.add(lowerFlowLayout, BorderLayout.SOUTH);   
            
            
        // Filter rows by adding a KeyListener to the Search Textfield - using TableRowSorter library class.}
            
        searchCustomer.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<DefaultTableModel>(model);
                        table.setRowSorter(rowSorter);
                        String text = searchCustomer.getText();
                        
                        rowSorter.setRowFilter(RowFilter.regexFilter(text));
                    }
                });
            
           
        // Delete rows by adding an ActionListener to the delete button
            
        btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int i = table.getSelectedRow();
                           
                        if(i>=0){
                             int selectedColumnIndex = table.getSelectedColumn();
                             int selectedReservationId = (Integer) table.getModel().getValueAt(i, 0);
                             dataFactory.deleteReservation(selectedReservationId);
                             //MySQL.queryUpdate("DELETE FROM reservations WHERE reservation_id = " + reservation_id.getText() + ";");  virker ikke :(
                             model.removeRow(i);                                
                        }
                        else{
                             JOptionPane.showMessageDialog(frame,
                             "Ingen reservation valgt!");
                        }
                    }
                });
                    
        btnEdit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedSeats.clear();
                        int i = table.getSelectedRow();
                         
                        if(i>=0){
                            int selectedColumnIndex = table.getSelectedColumn();
                            int selectedShowId = (Integer) table.getModel().getValueAt(i, 2);
                            int selectedCustomerId = (Integer) table.getModel().getValueAt(i, 1);
                               
                            Show selectedShow = dataFactory.getShow(selectedShowId);
                                
                            List<Integer> allCustomerShowId = dataFactory.getAllCustomerShowIds(selectedShowId, selectedCustomerId);
                               
                            //showIdSelected = show.show_id();
                            List<Reservation> allReservationsToEdit = new ArrayList<>();
                            for(int x : allCustomerShowId){
                                allReservationsToEdit.add(dataFactory.getReservation(x));
                            }
                            for(Reservation y : allReservationsToEdit){
                                selectedSeats.add(new Seat(y.getRowNumber(), y.getColumnNumber()));
                            }
                            //slet alle reservationer
                            dataFactory.deleteReservation(selectedShowId, selectedCustomerId);
                            biografViewer.displayBookingPage(selectedShow);
                                
                            jtp.setSelectedIndex(0);
                        }
                        else{
                            JOptionPane.showMessageDialog(frame,"Ingen reservation valgt!");
                           
                        }
                        biografViewer.updateJTable();
                    }
                });
    }
    
    /**
    * 
    *///resetter data table modellen!
    public void makeTableModel(){
        Object[] columns = {"Reservation ID","Telephone Number","Show ID","Row Number","Seat Number","Film","Auditorium","Start Time"};
            
        // Sikrer, at der ikke kan ændres i cellerne i JTablen.
        model = new DefaultTableModel(){   
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        model.setColumnIdentifiers(columns);
            
        // find Reservations and related shows to each reservation and display them in the table
        ArrayList<Reservation> list = dataFactory.getDetailsForAllReservations();
 
        Object[] row1 = new Object[8];
        for(int x = 0; x < list.size(); x++) {
            Show show = dataFactory.getShow(list.get(x).getShowId());
            row1[0] = list.get(x).getReservationId();
            row1[1] = list.get(x).getTelephoneNumber();
            row1[2] = list.get(x).getShowId();
            row1[3] = list.get(x).getRowNumber();
            row1[4] = list.get(x).getColumnNumber();
            //
            row1[5] = dataFactory.getMovie(show.getMovieId()).getTitle();
            row1[6] = show.getAuditoriumId();
            row1[7] = show.getStartTime();     
                
            model.addRow(row1);
        }
        table.setModel(model);
    }
}
