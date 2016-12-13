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
    private int BiografPladserValgte;

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
    private JPanel CenterCenterBorder;
    private JPanel frame1;
    private CardLayout cardLayout = new CardLayout();
    private JTabbedPane jtp;

    
    
    private ButtonValue[][] buttonValues;
    
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
        dataFactory = new DataFactory();
        
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
        JButton bbb = new JButton("jonathan");
        startGrid.add(bbb);
           
        CenterWestGrid.add(startGrid, "startGrid");
        cardLayout.show(CenterWestGrid, "startGrid");
        
        
        //CenterCenterBorder.add(CenterWestGrid, BorderLayout.WEST);
        CenterBorder.add(CenterWestGrid, BorderLayout.CENTER);
        
        // lave et gridbaglayout som reservationerne skal opbevares i 
        
        
        frame1 = new JPanel(new BorderLayout()); 
        
        
        
            addJTable();
                  

        
       
        // ----------------------------------------------------
        
      // --> slettet colorcodekoden og sat den ned i en metode 
        
        // laver fanerne som innerBorderLayout skal være inde i og det næste layout som skal vise forestillinger
        
        jtp = new JTabbedPane();
        
        JPanel jp1 = new JPanel(new BorderLayout(6, 6));
      
        JPanel jp3 = new JPanel(new BorderLayout(6, 6));
        
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

        //statusLabel = new JLabel(VERSION);
        //contentPane.add(statusLabel, BorderLayout.SOUTH);
        


        // arrangerer componenterne   

        //frame.pack();
        frame.setSize(1200,800);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
 

        }
        
        public void addJTable(){
        JTable table = new JTable();
            
            Object[] columns = {"Reservation ID","Telephone Number","Show ID","Row Number","Seat Number"};
            
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);
            table.setModel(model);
            
            // find Reservations and display them in the table
             ArrayList<Reservation> list = dataFactory.getDetailsForAllReservations();
            Object[] row1 = new Object[5];
            for(int x = 0; x < list.size(); x++) {
                row1[0] = list.get(x).reservation_id();
                row1[1] = list.get(x).telephone_number();
                row1[2] = list.get(x).show_id();
                row1[3] = list.get(x).row_number();
                row1[4] = list.get(x).seat_number();
                
                model.addRow(row1);
            }
            
            
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
                            
                            int selectedColumnIndex = table.getSelectedColumn();
                            int selectedReservationId = (Integer) table.getModel().getValueAt(i, 0);
                            //int selectedReservationId = selectedReservation.reservation_id();
                            
                            if(i>=0){
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
                            int i = table.getSelectedRow();
                            
                            
                            
                            int selectedColumnIndex = table.getSelectedColumn();
                            int selectedShowId = (Integer) table.getModel().getValueAt(i, 2);
                            
                            if(i>=0){
                                jtp.setSelectedIndex(0);
                            }
                            else{
                                System.out.println("No rows to delete");
                               
                            }
                             
                            
                            
                        }
                    });
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
            JButton forestilling = new JButton(movieTitle);
            forestilling.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel buttonGrid = new JPanel(new GridLayout(20,1));
                    List<Integer> showIds = dataFactory.getActiveShows(movieId);
                    for(int y : showIds){
                        Show show = dataFactory.getShow(y);
                        String buttonInfo = "Auditorium: " + show.auditorium_id() + " Tid: " + show.start_time();
                        JButton showButton = new JButton(buttonInfo);
                        //tilføjer listenere igen, til sædefordelingen
                        addShowButtonListeners(showButton, show);
                        buttonGrid.add(showButton);
                    }
                    //viser listen af spilletider i rammen
                    CenterWestGrid.add(buttonGrid, "buttonGrid");
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
                    
                   
                    displayBookingPage(show);
                            }
            });
        } 
        
        public void displayBookingPage(Show show){
            
            
            JPanel bookingLayout = new JPanel(); 
            bookingLayout.setLayout(new BorderLayout(6, 6));
            bookingLayout.setBorder(new EtchedBorder());
            // de forskellige borderlayout laves i seperate metoder
            
            List<Integer> allReservationIds = dataFactory.getAllShowReservationIds(show.show_id());
            showIdSelected = show.show_id();
            JPanel northPanel = makeNorthPanel(show);
            JPanel southPanel = makeSouthPanel(show, allReservationIds);
            JPanel centerPanel = makeCenterPanel(show, allReservationIds); 
            // nu nestes de forskellige borderlayouts ind i det store borderlayout  
            bookingLayout.add(northPanel, BorderLayout.NORTH);       
            bookingLayout.add(southPanel, BorderLayout.SOUTH);
            bookingLayout.add(centerPanel, BorderLayout.CENTER);
            //viser bookinglayoutet i rammen
            CenterWestGrid.add(bookingLayout, "bookingLayout");
            cardLayout.show(CenterWestGrid, "bookingLayout");
        }
        
        public JPanel makeNorthPanel(Show show){
             JPanel northPanel = new JPanel(new BorderLayout());
             // sætter to jlabel til west og east i northPanel så de kan være ud i siden, senere kommer northpanel til at sættes mod north i contentpane
             String showTime = show.start_time();
             northPanel.add(new JLabel("Forestilling: " + showTime), BorderLayout.EAST);
             String movieName = dataFactory.getMovie(show.movie_id()).title();
             JLabel Tekst = new JLabel("Film: " + movieName);
             Tekst.setFont(new Font("Serif", Font.PLAIN, 20));
             northPanel.add(Tekst, BorderLayout.WEST);
             
             return northPanel;
        }
        
        public JPanel makeSouthPanel(Show show, List<Integer> allReservationIds){
            JComboBox<Integer> myNumbers = new JComboBox<Integer>();
            myNumbers.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    if ((e.getStateChange() == ItemEvent.SELECTED)) {
                        BiografPladserValgte = (Integer)myNumbers.getSelectedItem();
                        System.out.println(BiografPladserValgte);
                    
                    
                    }
                }
            });
        
        
            myNumbers.addItem(1);
            myNumbers.addItem(2);
            myNumbers.addItem(3);
            myNumbers.addItem(4);
            myNumbers.addItem(5);
            myNumbers.addItem(6);
          
        
            
            // nyt JPanel som nestes ind i southPanel, bemærk flowlayout og ikke borderlayout da knapperne skal "floate på en række" i højre hjørne
            JPanel DownRight = new JPanel(new FlowLayout());
            JButton Knap1 = new JButton("Book");
            Knap1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                     //input felterne
                     JTextField nameField = new JTextField(20);
                     JTextField phoneField = new JTextField(20);
                        
                     JPanel dialogPanel = new JPanel();
                     dialogPanel.add(new JLabel("Navn: "));
                     dialogPanel.add(nameField);
                     //lav mellemrum
                     dialogPanel.add(Box.createHorizontalStrut(15)); // a spacer
                     dialogPanel.add(new JLabel("Telefon: "));
                     dialogPanel.add(phoneField);
                     
                     
                     int result = JOptionPane.showConfirmDialog(null, dialogPanel, 
                     null, JOptionPane.OK_CANCEL_OPTION);
                     if (result == JOptionPane.OK_OPTION) {
                         String nameResult = nameField.getText();
                         String phoneResult = phoneField.getText();
                         //test for om navn og telefonnummer er gyldige
                         boolean validInput = testInputString(nameResult, phoneResult);
                         int phoneParsed = Integer.parseInt(phoneResult);

                         if(validInput){
                             finalizeReservation(nameResult, phoneParsed);
                         }
                        }
                        
                    
                    }});
            DownRight.add(Knap1);
            DownRight.add(myNumbers); 
          
            // nyt JPanel som nestes ind i southPanel som nestes ind i ContentPane
            JPanel DownLeft = new JPanel(new GridLayout(2,2));
            DownLeft.setBorder(new EtchedBorder());
            JLabel freeLabel = new JLabel("Ledige Pladser");
            
            //checker hvor mange sæder er optagede
            int seatsTaken = allReservationIds.size();
            Auditorium auditorium = dataFactory.getAuditorium(show.auditorium_id());
            int seatsInAudit = auditorium.row_number() * auditorium.seat_number();
            JLabel freeSeats = new JLabel("  " + (seatsInAudit - seatsTaken) + "/" + seatsInAudit);
            
            JLabel auditLabel = new JLabel("Sal");
            int auditoriumId = show.auditorium_id();
            JLabel curAudit = new JLabel("  " + auditoriumId);
            
            // adder ovenstående labels til gridlayoutet
            DownLeft.add(auditLabel);
            DownLeft.add(curAudit);
            DownLeft.add(freeLabel);
            DownLeft.add(freeSeats);
    
            JPanel southPanel = new JPanel(new BorderLayout());
         
            southPanel.add(DownRight, BorderLayout.EAST);
            southPanel.add(DownLeft, BorderLayout.WEST);
            
            return southPanel;
        }
        
        

         public JPanel makeCenterPanel(Show show, List<Integer> allReservationIds){
            JPanel seatsGraphical = new JPanel();
         
            seatsGraphical.setLayout(new GridBagLayout());
            seatsGraphical.setBorder(new EmptyBorder(20, 90, 20, 90));
            GridBagConstraints gbc = new GridBagConstraints();
        
            int auditoriumId = show.auditorium_id();
            Auditorium auditorium = dataFactory.getAuditorium(auditoriumId);
            int rowNumbers = auditorium.row_number();
            int colNumbers = auditorium.seat_number();
            
            //find alle reservationerne til showet
            List<Reservation> allReservations = new ArrayList<>();
            for(int x : allReservationIds){
                allReservations.add(dataFactory.getReservation(x));
            }
            
            for (int row = 1; row < (rowNumbers + 1); row++) {
                for (int col = 1; col < (colNumbers + 1); col++) {
                    JButton btn = new JButton();
                    
                    btn.putClientProperty("column", col);
                    btn.putClientProperty("row", row);
                
                    // tekst streng der skal stå over hover
                    ToolTipManager.sharedInstance().setInitialDelay(0);
                    String sutmig = ("Række " + row + " " +"\n" + "Sæde " + col +  " ");
                
                    //tjekker om pladsen er reserveret
                    boolean reserved = false;
                    if(!allReservations.isEmpty()){
                        for(Reservation y : allReservations){
                            if(y.row_number() == row && y.seat_number() == col){
                                reserved = true;
                            }
                        }
                    }
                    
                    if(reserved){
                        btn.setBackground(Color.RED);
                    }
                    else{
                        btn.setBackground(Color.GREEN);
                    }
                    
                   
                    btn.setBorder(new LineBorder(Color.WHITE));
                    // fjerner blå highlihght når man klikker på knappen
                    btn.setFocusPainted(false);
                    // gør så at UI.manageLookAndFeel ikke farver knapperne grå som UI/baggrunden 
                    btn.setContentAreaFilled(false);
                    btn.setOpaque(true);
            
                    int kolonne = col; 
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int rowNr = Integer.parseInt(btn.getClientProperty("row").toString());
                            int colNr = Integer.parseInt(btn.getClientProperty("column").toString());
                            if(btn.getBackground() == pinkColor){
                                btn.setBackground(Color.GREEN);
                                //slet sædet fra selectedSeats
                                Iterator<Seat> it = selectedSeats.iterator();
                                while(it.hasNext()){
                                    Seat x = it.next();
                                    if(x.getRow() == rowNr && x.getColumn() == colNr){
                                        it.remove();
                                    }
                                }
                            }
                            else if(btn.getBackground() == Color.GREEN){
                                btn.setBackground(pinkColor);
                                selectedSeats.add(new Seat(rowNr, colNr));
                            }
                            JButton btn = (JButton) e.getSource();
                            

                            System.out.println("clicked column "
                                + btn.getClientProperty("column")
                                + ", row " + btn.getClientProperty("row"));
                            }
                    });
                    
                    btn.addMouseListener( new MouseAdapter() {
                        public void mouseEntered( MouseEvent e ) {
                            
                            btn.setToolTipText(sutmig);

                        }

                    }); 
                    
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.gridwidth = gbc.gridheight = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.anchor = GridBagConstraints.NORTHWEST;
                    gbc.weightx = 20;
                    gbc.weighty = 20;
                    seatsGraphical.add(btn, gbc);
                }   
            }
            //2 nye JPanels som skal bruges til at få pladserne til at være i midten af det hele
            JPanel centerPanel = new JPanel(new BorderLayout());
            //adder midterflowlayout til et centerpanel for at få det til at være centreret
            centerPanel.add(seatsGraphical, BorderLayout.CENTER);
               // opretter et JPanel som DownRight og DownLeft skal nestes ind i 
               //JPanel panelResult = addRest(seatsGraphical);
               
            centerPanel.add(ColorCode(), BorderLayout.EAST); 
            centerPanel.add(centerNorthScreenPanel(), BorderLayout.NORTH);
        
            return centerPanel;
        }
        
        // sætter colorcoden ind ved siden af biografen, lavet som en metode for at undgå kodeduplikering. 
       public JPanel ColorCode(){
           
          JPanel eastPanel = new JPanel(new GridBagLayout());
          GridBagConstraints ebc = new GridBagConstraints();
          ebc.insets = new Insets(5,5,5,5);

          ebc.gridx = 1;
          ebc.gridy = 0;
          eastPanel.add(new JLabel("Ledige pladser"), ebc);          
          ebc.gridx = 0;
          ebc.gridy = 0;
          JButton ledigeKnap = new JButton();          
          ledigeKnap.setBackground(Color.GREEN);
          ledigeKnap.setContentAreaFilled(false);
          ledigeKnap.setOpaque(true);
          eastPanel.add(ledigeKnap, ebc);
          
          ebc.gridx = 1;
          ebc.gridy = 1;
          eastPanel.add(new JLabel("Optagede"), ebc);
          ebc.gridx = 0;
          ebc.gridy = 1;
          JButton optagetKnap = new JButton();          
          optagetKnap.setBackground(Color.RED);
          optagetKnap.setContentAreaFilled(false);
          optagetKnap.setOpaque(true);
          eastPanel.add(optagetKnap, ebc);
          
          ebc.gridx = 1;
          ebc.gridy = 2;
          eastPanel.add(new JLabel("Valgte pladser"), ebc);
          ebc.gridx = 0; 
          ebc.gridy = 2;
          JButton valgteKnap = new JButton();          
          valgteKnap.setBackground(new Color(138,43,226));
          valgteKnap.setContentAreaFilled(false);
          valgteKnap.setOpaque(true);
          eastPanel.add(valgteKnap, ebc);
          
          return eastPanel;
        }
        
        
       public JPanel centerNorthScreenPanel(){
           JPanel centerNorthScreenPanel = new JPanel(new GridBagLayout());
                  GridBagConstraints rbc = new GridBagConstraints();
          centerNorthScreenPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
            rbc.fill = GridBagConstraints.HORIZONTAL;

            rbc.weightx = 20;
            rbc.weighty = 20;
            rbc.gridx = 1;
            rbc.gridy = 1;
            
           JLabel Screen = new JLabel("---- SCREEN ----");
           Screen.setOpaque(true);
           Screen.setPreferredSize(new Dimension(500, 20));
           Screen.setBackground(Color.BLACK);
           Screen.setForeground (Color.WHITE);
           Screen.setHorizontalAlignment(SwingConstants.CENTER);

          centerNorthScreenPanel.add(Screen);
          
          return centerNorthScreenPanel;
        }

        
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

    
    
    public void finalizeReservation(String name, int phone){
        dataFactory.addCustomer(phone, name);
        
        for(Seat x : selectedSeats){
            dataFactory.addReservation(phone, showIdSelected, x.getRow(), x.getColumn());
        }
        selectedSeats.clear();
    }
    
    public boolean testInputString(String nameResult, String phoneResult){
        //checker at strengen er længere end null
        if(nameResult.length() < 1){
            JOptionPane.showMessageDialog(frame,
            "Navn skal være mindst ét bogstav!");
            return false;
        }
        //checker at der kun indtastes bogstaver
        else if(!nameResult.chars().allMatch(Character::isLetter)){
            JOptionPane.showMessageDialog(frame,
            "Navn må kun indeholde bogstaver!");
            return false;
        }
        if(phoneResult.length() != 8){
            JOptionPane.showMessageDialog(frame,
            "Telefonnummer skal være otte tal!");
            return false;
        }
        else{
            try {
                Integer.parseInt(phoneResult);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame,
                "Telefonnummer skal bestå af tal!");
                return false;
            }
        }
        return true;
        
    }
}
    
   
    

