package weather.station;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/************************************************************************
    Class: MainDisplay
    Author: All
    Description: Creates a main display with form
    Parameters: n/a
 ************************************************************************/
public class MainDisplay extends javax.swing.JFrame {

    private String chartTitle = "";
    private String xLabel = "";
    private String yLabel = "";
    private XYDataset dataSet = null;
    private int tabFlag = 1;
    private int radioFlag = 0;

    /************************************************************************
       Function: MainDisplay Constructor
       Author: All
       Description: Constructor for main class
       Parameters: n/a
     ************************************************************************/
    public MainDisplay() {
        super("Weather Station");
        initComponents();
    }

    /************************************************************************
       Function: initComponents
       Author: All
       Description: method called from constructor to init form
       Parameters: n/a
     ************************************************************************/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dailyTab = new javax.swing.JPanel();
        weeklyTab = new javax.swing.JPanel();
        monthlyTab = new javax.swing.JPanel();
        yearlyTab = new javax.swing.JPanel();
        temperatureRadioButton = new javax.swing.JRadioButton();
        windsRadioButton = new javax.swing.JRadioButton();
        barometricRadioButton = new javax.swing.JRadioButton();
        heatUVindex = new javax.swing.JRadioButton();
        dataSelector = new javax.swing.JSlider();
        humidityRadioButton = new javax.swing.JRadioButton();
        rainfallRadioButton = new javax.swing.JRadioButton();
        maindisplayMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        specifyDirectoryItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        quitItem = new javax.swing.JMenuItem();
        statsMenu = new javax.swing.JMenu();
        averageTempItem = new javax.swing.JMenuItem();
        averageWindItem = new javax.swing.JMenuItem();
        averagePrecipItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));

        dailyTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                dailyTabComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                dailyTabComponentShown(evt);
            }
        });

        javax.swing.GroupLayout dailyTabLayout = new javax.swing.GroupLayout(dailyTab);
        dailyTab.setLayout(dailyTabLayout);
        dailyTabLayout.setHorizontalGroup(
            dailyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        dailyTabLayout.setVerticalGroup(
            dailyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Daily", dailyTab);

        weeklyTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                weeklyTabComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                weeklyTabComponentShown(evt);
            }
        });

        javax.swing.GroupLayout weeklyTabLayout = new javax.swing.GroupLayout(weeklyTab);
        weeklyTab.setLayout(weeklyTabLayout);
        weeklyTabLayout.setHorizontalGroup(
            weeklyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        weeklyTabLayout.setVerticalGroup(
            weeklyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Weekly", weeklyTab);

        monthlyTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                monthlyTabComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                monthlyTabComponentShown(evt);
            }
        });

        javax.swing.GroupLayout monthlyTabLayout = new javax.swing.GroupLayout(monthlyTab);
        monthlyTab.setLayout(monthlyTabLayout);
        monthlyTabLayout.setHorizontalGroup(
            monthlyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        monthlyTabLayout.setVerticalGroup(
            monthlyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Monthly", monthlyTab);

        yearlyTab.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                yearlyTabComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                yearlyTabComponentShown(evt);
            }
        });

        javax.swing.GroupLayout yearlyTabLayout = new javax.swing.GroupLayout(yearlyTab);
        yearlyTab.setLayout(yearlyTabLayout);
        yearlyTabLayout.setHorizontalGroup(
            yearlyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        yearlyTabLayout.setVerticalGroup(
            yearlyTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Yearly", yearlyTab);

        radioButtonGroup.add(temperatureRadioButton);
        temperatureRadioButton.setText("Temperature");
        temperatureRadioButton.setToolTipText("Plot Temperature Data");
        temperatureRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temperatureRadioButtonActionPerformed(evt);
            }
        });

        radioButtonGroup.add(windsRadioButton);
        windsRadioButton.setText("Wind Speed");
        windsRadioButton.setToolTipText("Plot Wind Speed Data");
        windsRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                windsRadioButtonActionPerformed(evt);
            }
        });

        radioButtonGroup.add(barometricRadioButton);
        barometricRadioButton.setText("Barometric Pressure");
        barometricRadioButton.setToolTipText("Plot Barometric Pressure Data");
        barometricRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barometricRadioButtonActionPerformed(evt);
            }
        });

        radioButtonGroup.add(heatUVindex);
        heatUVindex.setText("UV Index");
        heatUVindex.setToolTipText("Plot Heat/UV Index Data");
        heatUVindex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heatUVindexActionPerformed(evt);
            }
        });

        radioButtonGroup.add(humidityRadioButton);
        humidityRadioButton.setText("Humidity");
        humidityRadioButton.setToolTipText("Plot Humidity Data");
        humidityRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humidityRadioButtonActionPerformed(evt);
            }
        });

        radioButtonGroup.add(rainfallRadioButton);
        rainfallRadioButton.setText("Rainfall");
        rainfallRadioButton.setToolTipText("Plot Rainfall Data");
        rainfallRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rainfallRadioButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        specifyDirectoryItem.setText("Open Directory...");
        specifyDirectoryItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specifyDirectoryItemActionPerformed(evt);
            }
        });
        fileMenu.add(specifyDirectoryItem);
        fileMenu.add(jSeparator1);

        quitItem.setText("Quit");
        quitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitItemActionPerformed(evt);
            }
        });
        fileMenu.add(quitItem);

        maindisplayMenuBar.add(fileMenu);

        statsMenu.setText("Statistics");

        averageTempItem.setText("Temperature");
        averageTempItem.setToolTipText("Compute mean/min/max values for temperature.");
        averageTempItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averageTempItemActionPerformed(evt);
            }
        });
        statsMenu.add(averageTempItem);

        averageWindItem.setText("Wind");
        averageWindItem.setToolTipText("Compute mean/max/direction values for wind speeds.");
        averageWindItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averageWindItemActionPerformed(evt);
            }
        });
        statsMenu.add(averageWindItem);

        averagePrecipItem.setText("Precipitation");
        averagePrecipItem.setToolTipText("Compute total rainfall.");
        averagePrecipItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averagePrecipItemActionPerformed(evt);
            }
        });
        statsMenu.add(averagePrecipItem);

        maindisplayMenuBar.add(statsMenu);

        setJMenuBar(maindisplayMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heatUVindex)
                    .addComponent(barometricRadioButton)
                    .addComponent(windsRadioButton)
                    .addComponent(temperatureRadioButton)
                    .addComponent(humidityRadioButton)
                    .addComponent(rainfallRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dataSelector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(dataSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(temperatureRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(windsRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(barometricRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(heatUVindex)
                        .addGap(18, 18, 18)
                        .addComponent(humidityRadioButton)
                        .addGap(18, 18, 18)
                        .addComponent(rainfallRadioButton)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /************************************************************************
       Function: quitItemActionPerformed
       Author: All
       Description: Denotes action when exiting program
       Parameters: Action Event
     ************************************************************************/
    private void quitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitItemActionPerformed
    
    /************************************************************************
       Function: specifyDirectoryItemActionPerformed
       Author: All
       Description: Specifies a directory to choose xml files
       Parameters: Action Event
     ************************************************************************/
    private void specifyDirectoryItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specifyDirectoryItemActionPerformed
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        //System.out.println("Specify directory was clicked");
        SwingUtilities.invokeLater(new Runnable() {
            DirectoryChooser dir = new DirectoryChooser();

            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                dir.createAndShowGUI();
            }
        });
    }//GEN-LAST:event_specifyDirectoryItemActionPerformed
    
    /************************************************************************
       Function: dailyTabComponentShown
       Author: All
       Description: Displays the daily tab
       Parameters: Component Event
     ************************************************************************/
    private void dailyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dailyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 1;
        setChartTitle(radioFlag);
        generateGraph(dailyTab);
    }//GEN-LAST:event_dailyTabComponentShown

    /************************************************************************
       Function: dailyTabComponentResized
       Author: All
       Description: Renders the graph when window is resized
       Parameters: Component Event
     ************************************************************************/
    private void dailyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dailyTabComponentResized
        // TODO add your handling code here:
        generateGraph(dailyTab);
    }//GEN-LAST:event_dailyTabComponentResized

    /************************************************************************
       Function: weeklyTabComponentShown
       Author: All
       Description: Displays the weekly tab
       Parameters: Component Event
     ************************************************************************/
    private void weeklyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_weeklyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 2;
        setChartTitle(radioFlag);
        generateGraph(weeklyTab);
    }//GEN-LAST:event_weeklyTabComponentShown

    /************************************************************************
       Function: weeklyTabComponentResized
       Author: All
       Description: Renders the graph when window is resized
       Parameters: Component Event
     ************************************************************************/
    private void weeklyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_weeklyTabComponentResized
        // TODO add your handling code here:
        generateGraph(weeklyTab);
    }//GEN-LAST:event_weeklyTabComponentResized

    /************************************************************************
       Function: monthlyTabComponentShown
       Author: All
       Description: Displays the monthly tab
       Parameters: Component Event
     ************************************************************************/
    private void monthlyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_monthlyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 3;
        setChartTitle(radioFlag);
        generateGraph(monthlyTab);
    }//GEN-LAST:event_monthlyTabComponentShown

    /************************************************************************
       Function: monthlyTabComponentResized
       Author: All
       Description: Renders the graph when window is resized
       Parameters: Component Event
     ************************************************************************/
    private void monthlyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_monthlyTabComponentResized
        // TODO add your handling code here:
        generateGraph(monthlyTab);
    }//GEN-LAST:event_monthlyTabComponentResized

    /************************************************************************
       Function: yearlyTabComponentShown
       Author: All
       Description: Displays the yearly tab
       Parameters: Component Event
     ************************************************************************/
    private void yearlyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_yearlyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 4;
        setChartTitle(radioFlag);
        generateGraph(yearlyTab);
    }//GEN-LAST:event_yearlyTabComponentShown

    /************************************************************************
       Function: yearlyTabComponentResized
       Author: All
       Description: Renders the graph when window is resized
       Parameters: Component Event
     ************************************************************************/
    private void yearlyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_yearlyTabComponentResized
        // TODO add your handling code here:
        generateGraph(yearlyTab);
    }//GEN-LAST:event_yearlyTabComponentResized

    /************************************************************************
       Function: averageTempItemActionPerformed
       Author: All
       Description: Displays information regarding temperature
       Parameters: Action Event
     ************************************************************************/
    private void averageTempItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageTempItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Average: ##F\nHigh:       ##F  (#/##/## @#:##)\nLow:        ##F  (#/##/## @#:##)", "Temperature Stats", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_averageTempItemActionPerformed

    /************************************************************************
       Function: averageWindItemActionPerformed
       Author: All
       Description: Displays information regarding wind data
       Parameters: Action Event
     ************************************************************************/
    private void averageWindItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageWindItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Average:   ## mph\nHigh:         ## mph  (#/##/## @#:##)\nDirection:  E", "Wind Stats", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_averageWindItemActionPerformed

    /************************************************************************
       Function: averagePrecipItemActionPerformed
       Author: All
       Description: Returns rainfall data
       Parameters: Action Event
     ************************************************************************/
    private void averagePrecipItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averagePrecipItemActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Rainfall: ## in", "Rain Stats", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_averagePrecipItemActionPerformed

    /************************************************************************
       Function: temperatureRadioButtonActionPerformed
       Author: All
       Description: Radio button for temperature
       Parameters: Action Event
     ************************************************************************/
    private void temperatureRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temperatureRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 1;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_temperatureRadioButtonActionPerformed

    /************************************************************************
       Function: windsRadioButtonActionPerformed
       Author: All
       Description: Selects wind data for display
       Parameters: Action Event
     ************************************************************************/
    private void windsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_windsRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 2;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_windsRadioButtonActionPerformed

    /************************************************************************
       Function: barometricRadioButtonActionPerformed
       Author: All
       Description: Selects pressure data for display
       Parameters: Action Event
     ************************************************************************/
    private void barometricRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barometricRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 3;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_barometricRadioButtonActionPerformed

    /************************************************************************
       Function: heatUVindexActionPerformed
       Author: All
       Description: displays uv data on graph
       Parameters: Action Event
     ************************************************************************/
    private void heatUVindexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heatUVindexActionPerformed
        // TODO add your handling code here:
        radioFlag = 4;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_heatUVindexActionPerformed

    /************************************************************************
       Function: humidityRadioButtonActionPerformed
       Author: All
       Description: selects humidity data to display
       Parameters: Action Event
     ************************************************************************/
    private void humidityRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humidityRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 5;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_humidityRadioButtonActionPerformed

    /************************************************************************
       Function: rainfallRadioButtonActionPerformed
       Author: All
       Description: selects rainfall data for display
       Parameters: Action Event
     ************************************************************************/
    private void rainfallRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rainfallRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 6;
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_rainfallRadioButtonActionPerformed

    /************************************************************************
       Function: makeChart
       Author: All
       Description: creates a chart using JFreeChart
       Parameters: n/a
     ************************************************************************/
    public JFreeChart makeChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle, // chart title
                xLabel, // x axis label
                yLabel, // y axis label
                dataSet, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        return chart;
    }

    /************************************************************************
       Function: generateGraph
       Author: All
       Description: generates a graph utilizing makeChart
       Parameters: tab - a JPanel type to set the appropriate graphs
     ************************************************************************/
    public void generateGraph(JPanel tab) {
        tab.removeAll();

        ChartPanel graph = new ChartPanel(makeChart());

        graph.setSize(tab.getWidth(), tab.getHeight());
        graph.setVisible(true);
        tab.add(graph);
        tab.repaint();
    }

    /************************************************************************
       Function: callTabs
       Author: All
       Description: generates a graph for the appropriate tab
       Parameters: n/a
     ************************************************************************/
    public void callTabs() {
        switch (tabFlag) {
            case 1:
                generateGraph(dailyTab);
                break;
            case 2:
                generateGraph(weeklyTab);
                break;
            case 3:
                generateGraph(monthlyTab);
                break;
            case 4:
                generateGraph(yearlyTab);
                break;
        }

    }

    /************************************************************************
       Function: setChartTitle
       Author: All
       Description: sets the labels on the chart
       Parameters: radioFlag - denotes which radio button is selected
     ************************************************************************/
    public void setChartTitle(int radioFlag) {
        String name = jTabbedPane1.getTitleAt(tabFlag - 1);

        switch (radioFlag) {
            case 1:
                this.chartTitle = name + " Temperature";
                setYlabel("Degrees Farenheit (F)");
                break;
            case 2:
                this.chartTitle = name + " Wind Speed";
                setYlabel("Miles per Hour (MPH)");
                break;
            case 3:
                this.chartTitle = name + " Barometric Pressure";
                setYlabel("Inches of Mercury (inHg)");
                break;
            case 4:
                this.chartTitle = name + " UV Index";
                setYlabel("Index");
                break;
            case 5:
                this.chartTitle = name + " Humidity";
                setYlabel("Percent (%)");
                break;
            case 6:
                this.chartTitle = name + " Rainfall";
                setYlabel("Inches (in)");
                break;
            default:
                this.chartTitle = "Please Select Data Types on Left";
                break;
        }

    }

    /************************************************************************
       Function: setXlabel
       Author: All
       Description: sets the label on the x axis
       Parameters: xlabel
     ************************************************************************/
    public void setXlabel(String xLabel) {
        this.xLabel = xLabel;
    }

    /************************************************************************
       Function: setYlabel
       Author: All
       Description: sets the label on the Y axis
       Parameters: yLabel
     ************************************************************************/
    public void setYlabel(String yLabel) {
        this.yLabel = yLabel;
    }

    /************************************************************************
       Function: setDataSet
       Author: All
       Description: creates a data set to render the data on the graph
       Parameters: dataSet - data created/used for the JFreeChart
     ************************************************************************/
    public void setDataSet(XYDataset dataSet) {
        this.dataSet = dataSet;
    }

    /************************************************************************
       Function: getDataSet
       Author: All
       Description: returns the data set created for the graph
       Parameters: n/a
     ************************************************************************/
    public XYDataset getDataSet() {
        return dataSet;
    }

    /**
     * @param args the command line arguments
     */
    /************************************************************************
       Function: main
       Author: All
       Description: runs the main thread for MainDisplay (and associated form)
       Parameters: args[] - command line args
     ************************************************************************/
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem averagePrecipItem;
    private javax.swing.JMenuItem averageTempItem;
    private javax.swing.JMenuItem averageWindItem;
    private javax.swing.JRadioButton barometricRadioButton;
    private javax.swing.JPanel dailyTab;
    private javax.swing.JSlider dataSelector;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JRadioButton heatUVindex;
    private javax.swing.JRadioButton humidityRadioButton;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar maindisplayMenuBar;
    private javax.swing.JPanel monthlyTab;
    private javax.swing.JMenuItem quitItem;
    private javax.swing.ButtonGroup radioButtonGroup;
    private javax.swing.JRadioButton rainfallRadioButton;
    private javax.swing.JMenuItem specifyDirectoryItem;
    private javax.swing.JMenu statsMenu;
    private javax.swing.JRadioButton temperatureRadioButton;
    private javax.swing.JPanel weeklyTab;
    private javax.swing.JRadioButton windsRadioButton;
    private javax.swing.JPanel yearlyTab;
    // End of variables declaration//GEN-END:variables
}
