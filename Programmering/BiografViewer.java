import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;

import java.io.File;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTabbedPane;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class BiografViewer
{
    // static fields:
    private static final String VERSION = "Version 1.0";


    // fields:
    private JFrame frame;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    
    /**
     * Create an ImageViewer show it on screen.
     */
    public BiografViewer()
    {

        makeFrame();
    }


    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    

    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, 
                    "BiografViewer\n" + VERSION,
                    "About BiografViewer", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    // ---- support methods ----

    
    /**
     * Display a status message in the frame's status bar.
     * @param text The status message to be displayed.
     */
    private void showStatus(String text)
    {
        statusLabel.setText(text);
    }
    
    // ---- bygger selve frmaen
    

    private void makeFrame()
    {
        frame = new JFrame("BiografViewer");        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        makeMenuBar(frame);
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // Create the image pane in the center

        /*JTabbedPane jtp = new JTabbedPane();  prøver at få faner ind i GUI 
        frame.add(jtp);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JLabel label1 = new JLabel();
        label1.setText("You are in area of Tab1");
        JLabel label2 = new JLabel();
        label2.setText("You are in area of Tab2");
        jp1.add(label1);
        jp2.add(label2);
        jtp.addTab("Tab1", jp1);
        jtp.addTab("Tab2", jp2);*/
        
        filenameLabel = new JLabel();
        filenameLabel.setBorder(new EtchedBorder());
        contentPane.add(filenameLabel, BorderLayout.CENTER);
        
        // Create two labels at top and bottom for the file name and status messages
        filenameLabel = new JLabel();
        contentPane.add(filenameLabel, BorderLayout.NORTH);

        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JButton smallerButton = new JButton("Book");
        //smallerButton.addActionListener(e -> makeSmaller());
        toolbar.add(smallerButton);
        
        JButton largerButton = new JButton("Sal");
        //largerButton.addActionListener(e -> makeLarger());
        toolbar.add(largerButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        contentPane.add(flow, BorderLayout.NORTH);
        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        
        // building is done - arrange the components and show        

        frame.pack();
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
 

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


        // create the Filter menu

        
        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About ImageViewer...");
            item.addActionListener(e -> showAbout());
        menu.add(item);

    }
}
