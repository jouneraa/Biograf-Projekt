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
public class ShowView extends JScrollPane
{
    private DataFactory dataFactory = DataFactory.getInstance();
    private Movie movie;
    private BiografViewer biografViewer;
    private JPanel buttonGrid;
    private JPanel CenterWestGrid;
    private CardLayout cardLayout;
    private List<Seat> selectedSeats;
    public ShowView(JPanel buttonGrid, Movie movie, BiografViewer biografViewer, JPanel CenterWestGrid, CardLayout cardLayout,List<Seat> selectedSeats){
        super(buttonGrid);
        this.buttonGrid = buttonGrid;
        // this.setLayout(new GridLayout(100,1));
        this.movie = movie;
        this.biografViewer = biografViewer;
        this.CenterWestGrid = CenterWestGrid;
        this.cardLayout = cardLayout;
        this.selectedSeats = selectedSeats;
        makeFrame();
    }
    
    public void makeFrame(){
        
                    int movieId = movie.getMovieId();
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
                      
                        
                        showButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectedSeats.clear();
                            biografViewer.displayBookingPage(show);
                        }
                        });
                        
                        
                        
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
                    CenterWestGrid.add(this, "jScrollPane");
                    cardLayout.show(CenterWestGrid, "jScrollPane");
    }
    
        
    
}
