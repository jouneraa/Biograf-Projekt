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
 * Write a description of class TableView here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    public void makeFrame(){
           table = new JTable();

           makeTableModel();
            
            
            table.setBackground(Color.GREEN);
            table.setForeground(Color.BLACK);
            table.setRowHeight(30);
            
            JTextField searchCustomer = new JTextField();
            JLabel searchInfo = new JLabel("Search for Customer:");
            JButton btnDelete = new JButton("Delete Reservation");
            JButton btnEdit = new JButton("Edit Reservation");
            
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
                                System.out.println("No rows to delete");
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
                                    selectedSeats.add(new Seat(y.row_number(), y.seat_number()));
                                }
                                //slet alle reservationer
                                dataFactory.deleteReservation(selectedShowId, selectedCustomerId);
                                biografViewer.displayBookingPage(selectedShow);
                                
                                jtp.setSelectedIndex(0);
                            }
                            else{
                                JOptionPane.showMessageDialog(frame,
                                "ingen reservation valgt!");
                            
                            }
                          
                            biografViewer.updateJTable();
                        }
                    });
        }
    
        //resetter data table modellen!
         public void makeTableModel(){
             Object[] columns = {"Reservation ID","Telephone Number","Show ID","Row Number","Seat Number","Film","Auditorium","Start Time"};
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
                Show show = dataFactory.getShow(list.get(x).show_id());
                row1[0] = list.get(x).reservation_id();
                row1[1] = list.get(x).telephone_number();
                row1[2] = list.get(x).show_id();
                row1[3] = list.get(x).row_number();
                row1[4] = list.get(x).seat_number();
                    row1[5] = dataFactory.getMovie(show.movie_id()).title();
                    row1[6] = show.auditorium_id();
                    row1[7] = show.start_time();
                    
                
                model.addRow(row1);
                }
            //model.fireTableDataChanged();
            table.setModel(model);
    }
}
