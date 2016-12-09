import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class Login
{
    // static fields:
    private static final String VERSION = "Version 1.0";


    // fields:
    private JFrame frame;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    
    
    public Login()
    {

        makeFrame();
    }


    // en eventuel quit function til senere
    private void quit()
    {
        System.exit(0);
    }
    

    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, 
                    "I live my day as if it was the last\n" + VERSION,
                    "xd",
                    JOptionPane.INFORMATION_MESSAGE);
    }
    

    // ---- bygger selve frmaen
    

    private void makeFrame()
    {
          frame = new JFrame("BiografViewer");        
          JPanel panel = (JPanel)frame.getContentPane();
          frame.setPreferredSize(new Dimension(900, 600));
          makeMenuBar(frame);
             
          
          panel.setSize(900,600);
          
          // har brugt GridBagLayout for at få knapperne til at stacke oven på hinanden i midten af skærmen
          panel.setLayout(new GridBagLayout());        
          GridBagConstraints gbc = new GridBagConstraints();
          
         
          // gridx og gridy er hvor henne i gridbaglayoutet den specifikke button skal være. 
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.ipady = 20;
          gbc.ipady = 20;  
          gbc.gridx = 0;
          gbc.gridy = 0;
          panel.add(new JTextField("Telephone number"),gbc); 
        
          
        
        
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.ipady = 20; 
          gbc.ipadx = 50;
          gbc.gridx = 0;
          gbc.gridy = 1;
          panel.add(new TextField("Name"),gbc); 
          
          // laver knappen og dens onclick function
          JButton button1 = new JButton("Confirm"); 
          button1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); 
                BiografViewer f1 = new BiografViewer(); // NewFrame1 is the constructor of another class in which the properties of the second frame is defined.
                
                
            }
          });           
               
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.ipady = 5;
          gbc.ipadx = 5;
          gbc.gridx = 0;
          gbc.gridy = 2;
          panel.add(button1 ,gbc);
          
            // building is done - arrange the components and show        
        
          frame.pack();
          frame.setVisible(true);
 

    }
    
     private void makeMenuBar(JFrame frame)
    {
        
        Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("");
        menubar.add(menu);
                

        // create the Filter menu

        
        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About BiografHelper...");
        item.addActionListener(e -> showAbout());
        menu.add(item);

    }

}
