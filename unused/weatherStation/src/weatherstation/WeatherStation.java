package weatherstation;

// import statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author turkk
 */
public class WeatherStation extends JFrame {
    
    //default constructor
    public WeatherStation()
    {
        //super class constructor for window title
        super( "WeatherStation" );
        
        //set and register up menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //FILE menu
        JMenu file = new JMenu("File");
        menuBar.add(file);
        
        //file|open
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        
        //file|quit
        JMenuItem quit = new JMenuItem("Quit");
        file.addSeparator();
        file.add(quit);
        
        //set up content pane
        Container contents = getContentPane();
        contents.setLayout( new FlowLayout() );
        
        //suggest window default size
        setSize(400, 400);
        
        //window manager sets components
        //pack();
        
        //display window
        setVisible(true);
        
        
        //default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */  
    public static void main(String[] args) {
        // TODO code application logic here
        WeatherStation gui = new WeatherStation();
    }
    
}
