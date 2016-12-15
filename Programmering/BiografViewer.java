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
public class BiografViewer
{
    //string som står nede i bunden, versionen af programmet :) en meme
    private static final String VERSION = "Version 1337";

    //reservation system reference

    DataFactory dataFactory = DataFactory.getInstance();
    // fields:
    private JFrame frame;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private JPanel contentPane;
    private JPanel InnerGrid;
    private JPanel CenterWestGrid;

    private JPanel frame1;
    private TableView tableView;
    private CardLayout cardLayout = new CardLayout();
    private JTabbedPane jtp;
    
    JTable table;
    DefaultTableModel model;
    
    JPanel jp3;
    JPanel jp1;

    private Color pinkColor;
    private JTextField phoneField;
    private List<Seat> selectedSeats;
    private int showIdSelected;
    
    // konstruktoren som kalder funktionen til at lave framen
    public BiografViewer()
    {
        pinkColor = new Color(138,43,226);

        selectedSeats = new ArrayList<>();
        
        
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

        jtp = new JTabbedPane();        
        jp1 = new JPanel(new BorderLayout(6, 6));
        jp3 = new JPanel(new BorderLayout(6, 6));                        
        jp1.add(InnerGrid, BorderLayout.WEST);
        jp1.add(CenterBorder, BorderLayout.CENTER);
      
        makeTableView();
        jp3.add(tableView);
        
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
      
        public void makeTableView(){
            tableView = new TableView(this, selectedSeats, jtp, frame, jp3, table, model);
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
            
            // muligvis en til filmene også JScrollPane scrollPaneInnerGrid = new JScrollPane();
            JButton forestilling = new JButton(movieTitle);
            forestilling.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel buttonGrid = new JPanel(new GridLayout(100,1));
                    ShowView showView = new ShowView(buttonGrid, movie, BiografViewer.this, CenterWestGrid, cardLayout, selectedSeats);
                            }
            });
             forestilling.setBackground(contentPane.getBackground ());
             InnerGrid.add(forestilling);
        }
                    
    }
    

        
        public void displayBookingPage(Show show){
            
            List<Integer> allReservationIds = dataFactory.getAllShowReservationIds(show.show_id());
            
            
            JPanel bookingLayout = new JPanel(); 
            bookingLayout.setLayout(new BorderLayout(6, 6));
            bookingLayout.setBorder(new EtchedBorder());
            // de forskellige borderlayout laves i seperate metoder
            
            // 
            AuditoriumView auditoriumView = new AuditoriumView(show, allReservationIds, table, selectedSeats,  frame, CenterWestGrid, cardLayout, tableView, this);
            
            //viser bookinglayoutet i rammen
            CenterWestGrid.add(auditoriumView, "showView");
            cardLayout.show(CenterWestGrid, "showView");
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
        
        public void updateJTable(){
            jp3.remove(tableView);
            tableView = new TableView(this, selectedSeats, jtp, frame, jp3, table, model);
            jp3.add(tableView);
        }

}
    
   
    

