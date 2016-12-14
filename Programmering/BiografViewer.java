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

public class BiografViewer
{
    // static fields:
    //string som står nede i bunden, versionen af programmet :) en meme
    private static final String VERSION = "Version 1337";

    //reservation system reference
    ReservationSystem reservationSystem;
    DataFactory dataFactory;
    // fields:
    private JFrame frame;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private JPanel contentPane;
    private JPanel InnerGrid;
    private JPanel CenterWestGrid;

    private JPanel frame1;
    private CardLayout cardLayout = new CardLayout();
    private JTabbedPane jtp;
    
    JTable table;
    DefaultTableModel model;
    
    JPanel jp3;

    
    private Color pinkColor;
    private JTextField phoneField;
    private List<Seat> selectedSeats;
    private int showIdSelected;
    
    // konstruktoren som kalder funktionen til at lave framen
    public BiografViewer()
    {
        pinkColor = new Color(138,43,226);
        reservationSystem = new ReservationSystem();
        selectedSeats = new ArrayList<>();
        dataFactory = DataFactory.getInstance();
        
        makeFrame();
    }

    // quitfunction til at udbygge senere
    private void quit()
    {
        System.exit(0);
    }
    
    //about function, til at se oplysnigner om programmet
    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, 
                    "Sub me on youtube.com/joennegee\n" + VERSION,
                    "xddd", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    

    // ---- bygger selve frmaen
   
    private void makeFrame()
    {
        //laver JFramet som er hele vinduet i sig selv
        frame = new JFrame("BiografViewer");
        
        // sætter layoutet til at matche styresystemet
                
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // sætter et jpanel = maineframet
        contentPane = (JPanel)frame.getContentPane();
        //sætter en border rundt om og størrelsen på framet + sætter menubaren
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        frame.setPreferredSize(new Dimension(900, 600));
        makeMenuBar(frame);
        
        // laver  jpanel contentPane om til et borderlayout dvs nord syd øst vest
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // laver et nyt bordrlayout som kommer ti at blive nestet i contentpane
        JPanel innerBorderLayout = new JPanel(); 
        
        innerBorderLayout.setLayout(new BorderLayout(6, 6));
        
        innerBorderLayout.setBorder(new EtchedBorder());
        
        // lave et gridlayout som skal nestes ind i Tab1 
        
        InnerGrid = new JPanel(new GridLayout(20,1)); 
        JPanel CenterBorder = new JPanel(new BorderLayout(6,6));
        //lavet til reservation tabben
        JPanel resTabBorder = new JPanel(new BorderLayout(6,6));
        
         // -----------------------------------------------------
        //CenterCenterBorder = new JPanel(new BorderLayout(6,6));
        CenterWestGrid = new JPanel(); 
        CenterWestGrid.setLayout(cardLayout);
        JPanel startGrid = new JPanel(new GridLayout(20,2));
           
        CenterWestGrid.add(startGrid, "startGrid");
        cardLayout.show(CenterWestGrid, "startGrid");
        
        
        //CenterCenterBorder.add(CenterWestGrid, BorderLayout.WEST);
        CenterBorder.add(CenterWestGrid, BorderLayout.CENTER);
        
        // lave et gridbaglayout som reservationerne skal opbevares i 
        
        
            addJTable();
                  

        // ----------------------------------------------------
        
      // --> slettet colorcodekoden og sat den ned i en metode 
        
        // laver fanerne som innerBorderLayout skal være inde i og det næste layout som skal vise forestillinger
        
        jtp = new JTabbedPane();
        
        JPanel jp1 = new JPanel(new BorderLayout(6, 6));
      
        jp3 = new JPanel(new BorderLayout(6, 6));
        
        jp1.add(InnerGrid, BorderLayout.WEST);
        jp1.add(CenterBorder, BorderLayout.CENTER);
        //jp2.add(CenterBorder);
      
        jp3.add(frame1);
        
        jtp.addTab("Forestillinger", jp1);
       
        jtp.addTab("Ret reservationer", jp3);
        
        // højst sandsynligt sætte focuspainted ind i en metode så man undgår kodeduplikering


        addShowsInBar();
        
     
        // sætter jtp aka TabbedPane ind i contentPame
        contentPane.add(jtp, BorderLayout.CENTER);

        // sætter en lille titel oppe i toppen og i bunden
        filenameLabel = new JLabel("Bookingsystem til Biograf");
        filenameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        
        contentPane.add(filenameLabel, BorderLayout.NORTH);

        


 

        //frame.pack();
        frame.setSize(1200,800);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
 

        }
        
        public void addJTable(){
            frame1 = new JPanel(new BorderLayout()); 
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
            
            
           
            
           
            frame1.add(pane);
            JPanel lowerFlowLayout = new JPanel(new FlowLayout()); 
            lowerFlowLayout.add(searchCustomer);
            lowerFlowLayout.add(searchInfo);
            lowerFlowLayout.add(btnDelete);
            lowerFlowLayout.add(btnEdit);
            frame1.add(lowerFlowLayout, BorderLayout.SOUTH);   

            
            
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
                                displayBookingPage(selectedShow);
                                
                                jtp.setSelectedIndex(0);
                            }
                            else{
                                JOptionPane.showMessageDialog(frame,
                                "ingen reservation valgt!");
                            
                            }
                          
                            jp3.remove(frame1);
                            addJTable();
                            jp3.add(frame1);
                            
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
    

    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =       
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("");
        menubar.add(menu);

        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About BiografHelper...");
            item.addActionListener(e -> showAbout());
        menu.add(item);

    }
    
    public void addShowsInBar(){
        //tilføj title på panelet
        JLabel forestilling1 = new JLabel();
        forestilling1.setText("Film:");
        InnerGrid.add(forestilling1);
        
        
        //tilføj knapperne der viser spillefilm
        List<Integer> movieIds = dataFactory.getAllMovieIds();
        for(int x : movieIds){
            Movie movie = dataFactory.getMovie(x);
            String movieTitle = movie.title();
            int movieId = movie.getMovieId();
            // muligvis en til filmene også JScrollPane scrollPaneInnerGrid = new JScrollPane();
            JButton forestilling = new JButton(movieTitle);
            forestilling.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel buttonGrid = new JPanel(new GridLayout(100,1));
                    // initialiser scrollpanet, til sidst i løkken sættes panet ind i centerwestgrid
                    JScrollPane scrollPaneWestGrid = new JScrollPane(buttonGrid);
                    List<Integer> showIds = dataFactory.getActiveShows(movieId);
                    
                    for(int y : showIds){
                        Show show = dataFactory.getShow(y);
                        String buttonInfo = "Auditorium: " + show.auditorium_id() + " Tid: " + show.start_time();
                        JButton showButton = new JButton(buttonInfo);
                        // nogle ekstra knapper jeg har addet fordi jeg ikke kan komme ind i databasen :/ 
                        JButton showButton1 = new JButton(buttonInfo);
                        JButton showButton2 = new JButton(buttonInfo);
                        JButton showButton3 = new JButton(buttonInfo);
                        JButton showButton4 = new JButton(buttonInfo);
                        JButton showButton5 = new JButton(buttonInfo);
                        JButton showButton6 = new JButton(buttonInfo);
                        JButton showButton7 = new JButton(buttonInfo);
                        JButton showButton8 = new JButton(buttonInfo);
                        //tilføjer listenere igen, til sædefordelingen
                        addShowButtonListeners(showButton, show);
                        buttonGrid.add(showButton);
                        // nogle ekstra knapper jeg har addet fordi jeg ikke kan komme ind i databasen :/ 
                        buttonGrid.add(showButton1);
                        buttonGrid.add(showButton2);
                        buttonGrid.add(showButton3);
                        buttonGrid.add(showButton4);
                        buttonGrid.add(showButton5);
                        buttonGrid.add(showButton6);
                        buttonGrid.add(showButton7);
                        buttonGrid.add(showButton8);
                    }
                    //viser listen af spilletider i rammen
                    CenterWestGrid.add(scrollPaneWestGrid, "buttonGrid");
                    cardLayout.show(CenterWestGrid, "buttonGrid");
                            }
            });
             forestilling.setBackground(contentPane.getBackground ());
             InnerGrid.add(forestilling);
        }
                    
    }
    
    public void addShowButtonListeners(JButton showButton, Show show){
            showButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedSeats.clear();
                    displayBookingPage(show);
                            }
            });
        } 
        
        public void displayBookingPage(Show show){
            
            List<Integer> allReservationIds = dataFactory.getAllShowReservationIds(show.show_id());
            
            
            JPanel bookingLayout = new JPanel(); 
            bookingLayout.setLayout(new BorderLayout(6, 6));
            bookingLayout.setBorder(new EtchedBorder());
            // de forskellige borderlayout laves i seperate metoder
            
            // 
            AuditoriumView auditoriumView = new AuditoriumView(show, allReservationIds, table, selectedSeats,  frame, CenterWestGrid, cardLayout, frame1, this);
            
            //viser bookinglayoutet i rammen
            CenterWestGrid.add(auditoriumView, "showView");
            cardLayout.show(CenterWestGrid, "showView");
        }
        
       
        
       
        
        
        // sætter colorcoden ind ved siden af biografen, lavet som en metode for at undgå kodeduplikering. 
       
        
       

        
        public void Show_Users_In_JTable()
        {
            ArrayList<Reservation> list = dataFactory.getDetailsForAllReservations();
            Object[] row = new Object[5];
            for(int x = 0; x < list.size(); x++) {
                row[0] = list.get(x).reservation_id();
                row[1] = list.get(x).telephone_number();
                row[2] = list.get(x).show_id();
                row[3] = list.get(x).row_number();
                row[4] = list.get(x).seat_number();
                
                
            }
        }
        
        public void updateJTable(){
            jp3.remove(frame1);
            addJTable();
            jp3.add(frame1);
        }

    
    
    
    
    
}
    
   
    

