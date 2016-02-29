package weather.station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/************************************************************************
   Class:   DirectoryChooser()
   Author:  All
   Description:
   Parameters:
 ************************************************************************/
public class DirectoryChooser extends JPanel implements ActionListener {

    static private final String newline = "\n";
    JButton openButton;//, saveButton;
    JTextArea log;
    JFileChooser fc;
    
    /************************************************************************
       Class: public DirectoryChooser()
       Author: All
       Description: This is the constructor for the DirectoryChooser() class.
       * It creates a new JFileChooser.  This file chooser is where the user can
       * select a directory filled with XML data to be read into the application.
       * The constructor takes care of any necessary modifications to the 
       * window such as borders, scroll bar layout, etc.
       Parameters:
     ************************************************************************/  
    public DirectoryChooser() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Choose a Directory");
        openButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }
    
    /************************************************************************
       Function: actionPerformed()
       Author: All
       Description: This method of DirectoryChooser() handles the button press
       * for the open button. When the open button is selected from the
       * FileChooser, this directory is then opened and a message is displayed
       * in the original window.
       Parameters:
     ************************************************************************/
    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(DirectoryChooser.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            //Handle save button action.
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    /************************************************************************
       Function: createImageIcon()
       Author: all
       Description: This method creates the icon to be displayed next to the
       * file name in the file chooser.
       Parameters: String path - string holding path name
     ************************************************************************/
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DirectoryChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    /************************************************************************
       Class: createAndShowGUI()
       Author: All
       Description: This function adds a window with a button "DirectoryChooser".
       * This window will display all messages dealing with the file chooser.
       * When this button is clicked, the file chooser will open.
       Parameters: None
     ************************************************************************/
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DirectoryChooser");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Add content to the window.
        frame.add(new DirectoryChooser());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
