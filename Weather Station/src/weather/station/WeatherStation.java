/************************************************************************
   Program: Programming Assignment #1 - Weather Station Display
   Author:  Luke Meyer, Kayhan Karatekeli, and Jayson Kjenstad
   Class:   CSC 468/568 - GUI Programming
   Instructor:  Dr. Weiss
   Date:    3/1/2016
   Description:    (program requirements)
   Input:   
   Output:
   Compilation instructions:
   Usage:
   Known bugs/missing features:
 ************************************************************************/
package weather.station;

import java.io.File;

/************************************************************************
    Class:  WeatherStation()
    Author: All
    Description: This class is the starting point for the program. First the
    * wanted directory is specified.  This directory is where all the XML data
    * files containing the weather information is held.  The main section of
    * this class calls the WeatherData() class.  That class takes care of all
    * data parsing and sorting.  This class then creates a GUI by making a new
    * instance of the MainDisplay() class.  The GUI is then set to be visible
    * and interact with the user
    Parameters: None
 ************************************************************************/
public class WeatherStation {
 
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainDisplay().setVisible(true);
        });
    }
}
