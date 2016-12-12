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

// combobox integer = klik på reserver plads = klik på specifik sæde + highlighter de sæder til højre for sædet + comboboxnummer 



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
    private CardLayout cardLayout = new CardLayout();
    
    
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
        
         // -----------------------------------------------------
        CenterCenterBorder = new JPanel(new BorderLayout(6,6));
        CenterWestGrid = new JPanel(); 
        CenterWestGrid.setLayout(cardLayout);
        JPanel startGrid = new JPanel(new GridLayout(20,1));
           
        CenterWestGrid.add(startGrid, "startGrid");
        cardLayout.show(CenterWestGrid, "startGrid");
        
        
        CenterCenterBorder.add(CenterWestGrid, BorderLayout.WEST);
        CenterBorder.add(CenterCenterBorder, BorderLayout.CENTER);
        
        // lave et gridbaglayout som reservationerne skal opbevares i 
        
        JPanel retReservationGrid = new JPanel();
         
        retReservationGrid.setLayout(new GridBagLayout());
        retReservationGrid.setBorder(new EmptyBorder(30, 110, 30, 110));
        GridBagConstraints dbc = new GridBagConstraints();
        dbc.insets = new Insets(15,15,15,15);
        dbc.gridx = 1;
        dbc.gridy = 0;
        retReservationGrid.add(new JLabel("First row, first column"), dbc);
        dbc.gridx = 2;
        dbc.gridy = 0;
        retReservationGrid.add(new JLabel("First row, second column"), dbc);
        dbc.gridx = 3;
        dbc.gridy = 0;
        retReservationGrid.add(new JLabel("First row, first column"), dbc);
        dbc.gridx = 4;
        dbc.gridy = 0;
        retReservationGrid.add(new JLabel("First row, second column"), dbc);
        dbc.gridx = 5;
        dbc.gridy = 0;
        retReservationGrid.add(new JLabel("First row, second column"), dbc);
        dbc.gridx = 6;
        dbc.gridy = 0;
        retReservationGrid.add(new JButton("Ret reservation"), dbc);
        
       
        
       /* for (int row = 1; row < 21; row++) {
            for (int col = 1; col < 21; col++) {
                JButton btn = new JButton();
                dbc.gridx = col;
                dbc.gridy = row;
                dbc.gridwidth = dbc.gridheight = 1;
                dbc.fill = GridBagConstraints.BOTH;
                dbc.anchor = GridBagConstraints.NORTHWEST;
                dbc.weightx = 20;
                dbc.weighty = 20;
                retReservationGrid.add(btn, dbc);
            }
        } */ 
        
        
        
        // ----------------------------------------------------
          
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
          
          
        
        
        
        
        
        
        // laver fanerne som innerBorderLayout skal være inde i og det næste layout som skal vise forestillinger
        
        JTabbedPane jtp = new JTabbedPane();
        
        JPanel jp1 = new JPanel(new BorderLayout(6, 6));
        JPanel jp2 = new JPanel(new BorderLayout(6, 6));
        JPanel jp3 = new JPanel(new BorderLayout(6, 6));
        
        jp1.add(InnerGrid, BorderLayout.WEST);
        jp1.add(CenterBorder, BorderLayout.CENTER);
        jp2.add(innerBorderLayout);
        jp3.add(retReservationGrid);
        
        jtp.addTab("Forestillinger", jp1);
        jtp.addTab("Reservation", jp2);
        jtp.addTab("Ret reservationer", jp3);
        
        // højst sandsynligt sætte focuspainted ind i en metode så man undgår kodeduplikering
        /*JButton VenstreKnap = new JButton("<--");
        VenstreKnap.setFocusPainted(false);
        JButton HøjreKnap = new JButton("-->"); 
        HøjreKnap.setFocusPainted(false);
        
        CenterBorder.add(VenstreKnap, BorderLayout.WEST); 
        
        
       /*
        
        JPanel gridEastCenter = new JPanel(new BorderLayout());
        JLabel phoneLabelField = new JLabel("telephone number");
        gridEastCenter.add(phoneLabelField, BorderLayout.SOUTH);
        */
        

        /*JPanel gridEastSouth = new JPanel(new BorderLayout());
        phoneField = new JTextField(20);
        gridEastSouth.add(phoneField, BorderLayout.SOUTH);
        gridEastSouth.add(gridEastCenter, BorderLayout.CENTER);
        
       
        CenterBorder.add(gridEastSouth, BorderLayout.EAST); 
        
        
        */
       
       
        
        
        addShowsInBar();
        
       
        
        
         
        
        // laver et til borderlayout som nestes ind i contentpane senere
        JPanel northPanel = new JPanel(new BorderLayout());
        // sætter to jlabel til west og east i northPanel så de kan være ud i siden, senere kommer northpanel til at sættes mod north i contentpane
        northPanel.add(new JLabel("Forestilling: 15. Dec, 2015, kl 12:50"), BorderLayout.EAST);
        JLabel Tekst = new JLabel("Film: One Night in Paris with Paris Hilton ");
        Tekst.setFont(new Font("Serif", Font.PLAIN, 20));
        northPanel.add(Tekst, BorderLayout.WEST);
          
            //sætteer comboboxen til at vælge pladser med et ArrayList af integers
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
          
          DownRight.add(Knap1);
          DownRight.add(myNumbers); 
          
          // nyt JPanel som nestes ind i southPanel som nestes ind i ContentPane
          JPanel DownLeft = new JPanel(new GridLayout(2,2));
          DownLeft.setBorder(new EtchedBorder());
          JLabel xd = new JLabel("Ledige Pladser");
          JLabel xddd = new JLabel("  3");
          JLabel xdddd = new JLabel("  5/100");
          JLabel xdd = new JLabel("Sal");
          // adder ovenstående labels til gridlayoutet
          DownLeft.add(xdd);
          DownLeft.add(xddd);
          DownLeft.add(xd);
          DownLeft.add(xdddd);
          
          
          
          
          
         
         // opretter et JPanel som DownRight og DownLeft skal nestes ind i 
          JPanel southPanel = new JPanel(new BorderLayout());
         
          southPanel.add(DownRight, BorderLayout.EAST);
          southPanel.add(DownLeft, BorderLayout.WEST);
          
          
          //2 nye JPanels som skal bruges til at få pladserne til at være i midten af det hele
          JPanel centerPanel = new JPanel(new BorderLayout());
          //JPanel midterFlowPanel = new JPanel(new FlowLayout());
          JPanel midterFlowPanel = new JPanel();
         
        midterFlowPanel.setLayout(new GridBagLayout());
        midterFlowPanel.setBorder(new EmptyBorder(30, 110, 30, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 1; row < 21; row++) {
            for (int col = 1; col < 21; col++) {
            JButton btn = new JButton();

            btn.putClientProperty("column", col);
            btn.putClientProperty("row", row);
            
            // tekst streng der skal stå over hover
            ToolTipManager.sharedInstance().setInitialDelay(0);
            String sutmig = ("Række " + row + " " +"\n" + "Sæde " + col +  " ");
            
            
            btn.setBackground(Color.GREEN);
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
                    btn.setBackground(Color.RED);
                    JButton btn = (JButton) e.getSource();
                    for(int i =0; i < BiografPladserValgte; i++){                  
                        gbc.gridx = kolonne + 1;
                        btn.setBackground(Color.RED);
                    }
                     
                    System.out.println("clicked column "
                            + btn.getClientProperty("column")
                            + ", row " + btn.getClientProperty("row"));
                }
            });
            
            btn.addMouseListener( new MouseAdapter() {
            public void mouseEntered( MouseEvent e ) {
                btn.setBackground(new Color(138,43,226));
                
                btn.setToolTipText(sutmig);
                
                
            }
            });
            btn.addMouseListener( new MouseAdapter() {
            public void mouseExited( MouseEvent e ) {
                btn.setForeground(Color.GREEN);
                btn.setBackground(Color.GREEN);
            }
            } );
            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.weightx = 20;
            gbc.weighty = 20;
            midterFlowPanel.add(btn, gbc);
        }   
              
          //adder midterflowlayout til et centerpanel for at få det til at være centreret
          centerPanel.add(midterFlowPanel, BorderLayout.CENTER);
          
          
          // adder skærmen øverst i borderlayoutet så man kan se hvor salen vender XD 
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
          centerPanel.add(centerNorthScreenPanel, BorderLayout.NORTH); 
          
        // nu nestes de forskellige borderlayouts ind i det store borderlayout  
        innerBorderLayout.add(northPanel, BorderLayout.NORTH);       
        innerBorderLayout.add(southPanel, BorderLayout.SOUTH);
        innerBorderLayout.add(centerPanel, BorderLayout.CENTER);
        innerBorderLayout.add(eastPanel, BorderLayout.EAST); 
        
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
                    
                    List<Integer> allReservationIds = dataFactory.getAllShowReservationIds(show.show_id());
                    showIdSelected = show.show_id();
                    displayBookingPage(show, allReservationIds);
                            }
            });
        } 
        
        public void displayBookingPage(Show show, List<Integer> allReservationIds){
            JPanel bookingLayout = new JPanel(); 
            bookingLayout.setLayout(new BorderLayout(6, 6));
            bookingLayout.setBorder(new EtchedBorder());
            // de forskellige borderlayout laves i seperate metoder
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
                    /*
                    btn.addMouseListener( new MouseAdapter() {
                        public void mouseEntered( MouseEvent e ) {
                            btn.setBackground(new Color(138,43,226));
                
                            btn.setToolTipText(sutmig);
                
                
                        }
                    });
                    btn.addMouseListener( new MouseAdapter() {
                        public void mouseExited( MouseEvent e ) {
                            btn.setForeground(Color.GREEN);
                            btn.setBackground(Color.GREEN);
                        }
                    } );
                    */
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
        
            return centerPanel;
        }
        
       
        public void addReservationTable()
        {
            // Tilføje reverseknap, hvis man kommer til at slette den forkerte reservation
            
            // Ny JTable
            JFrame frame = new JFrame();
            JTable table = new JTable();
            
            Object[] columns = {"Reservation ID","Telephone Number","Show ID","Row Number","Seat Number"};
            
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(columns);
            table.setModel(model);
            
            // find Reservations and display them in the table
             ArrayList<Reservation> list = dataFactory.getDetailsForAllReservations();
            Object[] row = new Object[5];
            for(int x = 0; x < list.size(); x++) {
                row[0] = list.get(x).reservation_id();
                row[1] = list.get(x).telephone_number();
                row[2] = list.get(x).show_id();
                row[3] = list.get(x).row_number();
                row[4] = list.get(x).seat_number();
                
                model.addRow(row);
            }
            
            
            table.setBackground(Color.GREEN);
            table.setForeground(Color.BLACK);
            table.setRowHeight(30);
            
            JTextField searchCustomer = new JTextField();
            JLabel searchInfo = new JLabel("Search for Customer:");
            JButton btnDelete = new JButton("Delete Reservation");
            
            searchCustomer.setBounds(210,265,150,25);
            searchInfo.setBounds(80,265,150,25);
            btnDelete.setBounds(210,310,150,25);
            
           
            JScrollPane pane = new JScrollPane(table);
            pane.setBounds(0,0,880,200);
            
            frame.setLayout(null);
            frame.add(pane);
            
            frame.add(searchCustomer);   
            frame.add(searchInfo);
            frame.add(btnDelete);
            
            
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
                                //MySQL.queryUpdate("DELETE FROM reservations WHERE reservation_id = " + reservation_id.getText() + ";");  virker ikke :(
                                model.removeRow(i);                                
                            }
                            else{
                                System.out.println("No rows to delete");
                            }
                        }
                    });
                  

            frame.setSize(1500,400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            
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
    
   
    

