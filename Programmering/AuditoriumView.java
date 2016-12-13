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
public class AuditoriumView extends JPanel
{
     private DataFactory dataFactory = DataFactory.getInstance();
    private JLabel AntalPladser;
    private int showIdSelected;
    private JPanel CenterWestGrid;
    private CardLayout cardLayout;
    private JPanel frame1;
    private JFrame frame;
    private BiografViewer biografViewer;
    private List<Seat> selectedSeats;
    private Color pinkColor = new Color(138,43,226);
    public AuditoriumView(Show show, List<Integer> allReservationIds, JTable table, List<Seat> selectedSeats, JFrame frame, JPanel CenterWestGrid, CardLayout cardLayout, JPanel frame1, BiografViewer biografViewer ){
        super(new BorderLayout(6,6));
        this.setBorder(new EtchedBorder());
        showIdSelected = show.show_id();
        this.CenterWestGrid = CenterWestGrid;
        this.cardLayout = cardLayout;
        this.frame1 = frame1;
        this.selectedSeats = selectedSeats;
        this.frame = frame;
        this.biografViewer = biografViewer;
        makeFrame(show, allReservationIds, table);
    }
    
    public void makeFrame(Show show, List<Integer> allReservationIds, JTable table ){
        JPanel northPanel = makeNorthPanel(show);
        JPanel southPanel = makeSouthPanel(show, allReservationIds, table);
        JPanel centerPanel = makeCenterPanel(show, allReservationIds); 
        
         this.add(northPanel, BorderLayout.NORTH);       
            this.add(southPanel, BorderLayout.SOUTH);
            this.add(centerPanel, BorderLayout.CENTER);
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
                    String status = "null";
                    //boolean status = false;
                    if(!allReservations.isEmpty()){
                        for(Reservation y : allReservations){
                            if(y.row_number() == row && y.seat_number() == col){
                                status = "reserved";
                                //status = true;
                            }
                        }
                    }
                    
                    if(!selectedSeats.isEmpty()){
                        for(Seat s : selectedSeats){
                            if(s.getRow() == row && s.getColumn() == col){
                                status = "selected";
                            }
                        }
                    }
                   
                    //sætter sædets farve ud fra status
                    switch (status){
                        case "reserved":
                            btn.setBackground(Color.RED);
                            break;
                        case "selected":
                            btn.setBackground(pinkColor);
                            break;
                        default:
                            btn.setBackground(Color.GREEN);
                            break;
                        
                            
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
                                updateSelectedSeats(); 
                            }
                            else if(btn.getBackground() == Color.GREEN){
                                btn.setBackground(pinkColor);
                                selectedSeats.add(new Seat(rowNr, colNr));
                                updateSelectedSeats(); 
                                
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
        
         public JPanel makeSouthPanel(Show show, List<Integer> allReservationIds, JTable table){
            
            // nyt JPanel som nestes ind i southPanel, bemærk flowlayout og ikke borderlayout da knapperne skal "floate på en række" i højre hjørne
            JPanel DownRight = new JPanel(new FlowLayout());
            JButton Knap1 = new JButton("Reservér");
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
                         boolean validInput = testInputString(nameResult, phoneResult, frame);
                         int phoneParsed = Integer.parseInt(phoneResult);

                         if(validInput){
                             finalizeReservation(nameResult, phoneParsed);
                         }
                        }
                         table.repaint();
                    
                    }});
                    
            AntalPladser =  new JLabel("Antal valgte pladser: " + selectedSeats.size()); 
            
            DownRight.add(AntalPladser); 
            DownRight.add(Knap1);
          
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
        
        public boolean testInputString(String nameResult, String phoneResult, JFrame frame){
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
    
    public void finalizeReservation(String name, int phone){
        dataFactory.addCustomer(phone, name);
        
        
        for(Seat x : selectedSeats){
            dataFactory.addReservation(phone, showIdSelected, x.getRow(), x.getColumn());
        }
        selectedSeats.clear();
        cardLayout.show(CenterWestGrid, "startGrid");
       //sletter gammelt frame med jtable og laver et nyt, for at opdaterer indhold
       
        biografViewer.updateJTable();
     /*
        addJTable();
        model.fireTableDataChanged();
        */
       
        
    }
    
    public void updateSelectedSeats(){
        AntalPladser.setText("Antal valgte pladser: " + selectedSeats.size());
        
    }
     
       public JPanel centerNorthScreenPanel(){
           JPanel centerNorthScreenPanel = new JPanel(new GridBagLayout());
                  GridBagConstraints rbc = new GridBagConstraints();
          centerNorthScreenPanel.setBorder(new EmptyBorder(30, 0, 30, 122));
            rbc.fill = GridBagConstraints.HORIZONTAL;

            rbc.weightx = 20;
            rbc.weighty = 20;
            rbc.gridx = 1;
            rbc.gridy = 1;
            
           JLabel Screen = new JLabel("---- SKÆRM ----");
           Screen.setOpaque(true);
           Screen.setPreferredSize(new Dimension(500, 20));
           Screen.setBackground(Color.BLACK);
           Screen.setForeground (Color.WHITE);
           Screen.setHorizontalAlignment(SwingConstants.CENTER);

          centerNorthScreenPanel.add(Screen);
          
          return centerNorthScreenPanel;
        }
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
        
}
