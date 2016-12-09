import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;



public class BiografViewer
{
    // static fields:
    //string som står nede i bunden, versionen af programmet :) en meme
    private static final String VERSION = "Version 1337";


    // fields:
    private JFrame frame;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    
    // konstruktoren som kalder funktionen til at lave framen
    public BiografViewer()
    {

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
        
        
        // sætter et jpanel = maineframet
        JPanel contentPane = (JPanel)frame.getContentPane();
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
        
        JPanel InnerGrid = new JPanel(new GridLayout(20,1)); 
        JPanel CenterBorder = new JPanel(new BorderLayout(6,6));
        
        // laver fanerne som innerBorderLayout skal være inde i og det næste layout som skal vise forestillinger
        
        JTabbedPane jtp = new JTabbedPane();
        
        JPanel jp1 = new JPanel(new BorderLayout(6, 6));
        JPanel jp2 = new JPanel(new BorderLayout(6, 6));
        JPanel jp3 = new JPanel(new BorderLayout(6, 6));
        
        jp1.add(InnerGrid, BorderLayout.WEST);
        jp1.add(CenterBorder, BorderLayout.CENTER);
        jp2.add(innerBorderLayout);
       
        
        jtp.addTab("Forestillinger", jp1);
        jtp.addTab("Reservation", jp2);
        jtp.addTab("Ret reservationer", jp3);
        
        // højst sandsynligt sætte focuspainted ind i en metode så man undgår kodeduplikering
        JButton VenstreKnap = new JButton("<--");
        VenstreKnap.setFocusPainted(false);
        JButton HøjreKnap = new JButton("-->"); 
        HøjreKnap.setFocusPainted(false);
        
        CenterBorder.add(VenstreKnap, BorderLayout.WEST); 
        CenterBorder.add(HøjreKnap, BorderLayout.EAST); 
        
        ImageIcon imageIcon = new ImageIcon("parishilton.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(500, 250,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        
        JLabel label = new JLabel("", imageIcon, JLabel.CENTER);
        CenterBorder.add( label, BorderLayout.CENTER );
        
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        
        
        JLabel forestilling1 = new JLabel();
        forestilling1.setText("Film:");
        JButton forestilling2 = new JButton("One Night in Paris with Paris Hilton");
        forestilling2.setBackground(contentPane.getBackground ());
       // forestilling2.setForeground(Color.BLACK);
        InnerGrid.add(forestilling1); 
        InnerGrid.add(forestilling2);
        
       
        
        
         
        
        // laver et til borderlayout som nestes ind i contentpane senere
        JPanel northPanel = new JPanel(new BorderLayout());
        // sætter to jlabel til west og east i northPanel så de kan være ud i siden, senere kommer northpanel til at sættes mod north i contentpane
        northPanel.add(new JLabel("Forestilling: 15. Dec, 2015, kl 12:50"), BorderLayout.EAST);
        JLabel Tekst = new JLabel("Film: One Night in Paris with Paris Hilton ");
        Tekst.setFont(new Font("Serif", Font.PLAIN, 20));
        northPanel.add(Tekst, BorderLayout.WEST);
          
            //sætteer comboboxen til at vælge pladser med et ArrayList af integers
         JComboBox<Integer> myNumbers = new JComboBox<Integer>();
         myNumbers.addItem(1);
         myNumbers.addItem(2);
         myNumbers.addItem(3);
         myNumbers.addItem(4);
         myNumbers.addItem(5);
         myNumbers.addItem(6);
          
         // nyt JPanel som nestes ind i southPanel, bemærk flowlayout og ikke borderlayout da knapperne skal "floate på en række" i højre hjørne
          JPanel DownRight = new JPanel(new FlowLayout());
          JButton Knap1 = new JButton("Antal Pladser");
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
        midterFlowPanel.setBorder(new EmptyBorder(30, 110, 30, 110));
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 1; row < 21; row++) {
            for (int col = 1; col < 21; col++) {
            JButton btn = new JButton();
            /*JButton btn = new JButton("(" + row + ", " + col + ")");*/
            btn.putClientProperty("column", col);
            btn.putClientProperty("row", row);
            
            // tekst streng der skal stå over hover
            ToolTipManager.sharedInstance().setInitialDelay(0);
            String sutmig = ("Række " + row + " " +"\n" + "Sæde " + col +  " ");
            
            
            btn.setBackground(Color.GREEN);
            btn.setBorder(new LineBorder(Color.BLACK));
            // fjerner blå highlihght når man klikker på knappen
            btn.setFocusPainted(false);
            // gør så at UI.manageLookAndFeel ikke farver knapperne grå som UI/baggrunden 
            btn.setContentAreaFilled(false);
            btn.setOpaque(true);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btn = (JButton) e.getSource();
                    btn.setBackground(Color.RED);
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
          
        // nu nestes de forskellige borderlayouts ind i det store borderlayout  
        innerBorderLayout.add(northPanel, BorderLayout.NORTH);       
        innerBorderLayout.add(southPanel, BorderLayout.SOUTH);
        innerBorderLayout.add(centerPanel, BorderLayout.CENTER);

        // sætter jtp aka TabbedPane ind i contentPame
        contentPane.add(jtp, BorderLayout.CENTER);

        // sætter en lille titel oppe i toppen og i bunden
        filenameLabel = new JLabel("Bookingsystem til Biograf");
        filenameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        
        contentPane.add(filenameLabel, BorderLayout.NORTH);

        //statusLabel = new JLabel(VERSION);
        //contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // sætter layoutet til at matche styresystemet
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // arrangerer componenterne   

        frame.pack();
        
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
}
