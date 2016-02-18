/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        super( "WeatherStation" );
        Container contents = getContentPane();
        Container contents2 = getContentPane();
        contents.setLayout( new FlowLayout() );
        
        contents.add( new JButton( "Button 1" ) );

        
        setSize(300, 300);
        
        pack();
        setVisible(true);
        
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
