package weather.station;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * **********************************************************************
 * Class: MainDisplay Author: All Description: Creates a main display with form
 * Parameters: n/a
 * **********************************************************************
 */
public class MainDisplay extends javax.swing.JFrame {

    private String chartTitle = "";
    private String xLabel = "";
    private String yLabel = "";
    private XYDataset dataSet = null;
    private int tabFlag = 1;
    private int radioFlag = 0;
    private WeatherData data;
    private String radioLabel = "";

    /**
     * **********************************************************************
     * Function: MainDisplay Constructor Author: All Description: Constructor
     * for main class Parameters: n/a
     * **********************************************************************
     */
    public MainDisplay() {
        super("Weather Station");
        initComponents();

        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + workingDir);
        File dir = new File(workingDir);

        this.data = new WeatherData();
        data.getWeatherData(dir);
    }

    public void setTheData(String dataSpec) {
        if (tabFlag == 1) //if we want to look at the temp of one day
        {
            XYSeriesCollection dataCollection = this.data.getDaySetOfData(10, 1, 1); //Jan. 1, 2010
            XYSeries temp = dataCollection.getSeries(dataSpec);
            this.dataSet = new XYSeriesCollection(temp);
        } else if (tabFlag == 2) {
            XYSeriesCollection dataCollection = this.data.getWeekSetOfData(10, 1, 1); //Jan. 1, 2010
            XYSeries temp = dataCollection.getSeries(dataSpec);
            this.dataSet = new XYSeriesCollection(temp);
        } else if (tabFlag == 3) {
            XYSeriesCollection dataCollection = this.data.getMonthSetOfData(10, 1); //Jan. 1, 2010
            XYSeries temp = dataCollection.getSeries(dataSpec);
            this.dataSet = new XYSeriesCollection(temp);
        } else if (tabFlag == 4) {
            XYSeriesCollection dataCollection = this.data.getYearSetOfData(10); //Jan. 1, 2010
            XYSeries temp = dataCollection.getSeries(dataSpec);
            this.dataSet = new XYSeriesCollection(temp);
        }
    }

    public void setDataset() {
        switch (radioFlag) {
            case 1:  // Temp
                setTheData("Temperature");
                break;
            case 2: // Wind
                setTheData("Wind");
                break;
            case 3:  // Baro
                setTheData("Barometer");
                break;
            case 4: // UV inde
                setTheData("UV Index");
                break;

            case 5: //humid
                setTheData("Humidity");
                break;

            case 6: //rainfall
                setTheData("Precipitation");
                break;
        }
    }

    /**
     * **********************************************************************
     * Function: initComponents Author: All Description: method called from
     * constructor to init form Parameters: n/a
     * **********************************************************************
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioButtonGroup = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
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
        decrement = new javax.swing.JButton();
        increment = new javax.swing.JButton();
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

        dataSelector.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dataSelectorStateChanged(evt);
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

        decrement.setText("<");
        decrement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decrementActionPerformed(evt);
            }
        });

        increment.setText(">");
        increment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incrementActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(decrement, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(increment, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dataSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(increment)
                        .addComponent(decrement))
                    .addComponent(dataSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * **********************************************************************
     * Function: quitItemActionPerformed Author: All Description: Denotes action
     * when exiting program Parameters: Action Event
     * **********************************************************************
     */
    private void quitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitItemActionPerformed

    /**
     * **********************************************************************
     * Function: dailyTabComponentShown Author: All Description: Displays the
     * daily tab Parameters: Component Event
     * **********************************************************************
     */
    private void dailyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dailyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 1;
        setChartTitle(radioFlag);
        setDataset();
        generateGraph(dailyTab);
    }//GEN-LAST:event_dailyTabComponentShown

    /**
     * **********************************************************************
     * Function: dailyTabComponentResized Author: All Description: Renders the
     * graph when window is resized Parameters: Component Event
     * **********************************************************************
     */
    private void dailyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_dailyTabComponentResized
        // TODO add your handling code here:
        generateGraph(dailyTab);
    }//GEN-LAST:event_dailyTabComponentResized

    /**
     * **********************************************************************
     * Function: weeklyTabComponentShown Author: All Description: Displays the
     * weekly tab Parameters: Component Event
     * **********************************************************************
     */
    private void weeklyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_weeklyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 2;
        setChartTitle(radioFlag);
        setDataset();
        generateGraph(weeklyTab);
    }//GEN-LAST:event_weeklyTabComponentShown

    /**
     * **********************************************************************
     * Function: weeklyTabComponentResized Author: All Description: Renders the
     * graph when window is resized Parameters: Component Event
     * **********************************************************************
     */
    private void weeklyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_weeklyTabComponentResized
        // TODO add your handling code here:
        generateGraph(weeklyTab);
    }//GEN-LAST:event_weeklyTabComponentResized

    /**
     * **********************************************************************
     * Function: monthlyTabComponentShown Author: All Description: Displays the
     * monthly tab Parameters: Component Event
     * **********************************************************************
     */
    private void monthlyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_monthlyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 3;
        setChartTitle(radioFlag);
        setDataset();
        generateGraph(monthlyTab);
    }//GEN-LAST:event_monthlyTabComponentShown

    /**
     * **********************************************************************
     * Function: monthlyTabComponentResized Author: All Description: Renders the
     * graph when window is resized Parameters: Component Event
     * **********************************************************************
     */
    private void monthlyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_monthlyTabComponentResized
        // TODO add your handling code here:
        generateGraph(monthlyTab);
    }//GEN-LAST:event_monthlyTabComponentResized

    /**
     * **********************************************************************
     * Function: yearlyTabComponentShown Author: All Description: Displays the
     * yearly tab Parameters: Component Event
     * **********************************************************************
     */
    private void yearlyTabComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_yearlyTabComponentShown
        // TODO add your handling code here:
        tabFlag = 4;
        setChartTitle(radioFlag);
        setDataset();
        generateGraph(yearlyTab);
    }//GEN-LAST:event_yearlyTabComponentShown

    /**
     * **********************************************************************
     * Function: yearlyTabComponentResized Author: All Description: Renders the
     * graph when window is resized Parameters: Component Event
     * **********************************************************************
     */
    private void yearlyTabComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_yearlyTabComponentResized
        // TODO add your handling code here:
        generateGraph(yearlyTab);
    }//GEN-LAST:event_yearlyTabComponentResized

    /**
     * **********************************************************************
     * Function: averageTempItemActionPerformed Author: All Description:
     * Displays information regarding temperature Parameters: Action Event
     * **********************************************************************
     */
    private void averageTempItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageTempItemActionPerformed
        // TODO add your handling code here:
        switch (tabFlag) {
            case 1:
                JOptionPane.showMessageDialog(null, "Average: " + data.getDayAvgTemp(10, 1, 1) + "F\nHigh:       " + data.getDayHighTemp(10, 1, 1)  + "F  (" + data.getDayHighTempDate(10, 1, 1)  + "@" + data.getDayHighTempTime(10, 1, 1) + ")\nLow:        " + data.getDayLowTemp(10, 1, 1) + "(" + data.getDayLowTempDate(10, 1, 1)  + "@" + data.getDayLowTempTime(10, 1, 1) + ")", "Temperature Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Average: " + data.getMonthAvgTemp(10, 1) / 4 + "F\nHigh:       " + data.getMonthHighTemp(10, 1)  + "F  (" + data.getMonthHighTempDate(10, 1)  + "@" + data.getMonthHighTempTime(10, 1) + ")\nLow:        " + data.getMonthLowTemp(10, 1) + "(" + data.getMonthLowTempDate(10, 1)  + "@" + data.getMonthLowTempTime(10, 1) + ")", "Temperature Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Average: " + data.getMonthAvgTemp(10, 1) + "F\nHigh:       " + data.getMonthHighTemp(10, 1)  + "F  (" + data.getMonthHighTempDate(10, 1)  + "@" + data.getMonthHighTempTime(10, 1) + ")\nLow:        " + data.getMonthLowTemp(10, 1) + "(" + data.getMonthLowTempDate(10, 1)  + "@" + data.getMonthLowTempTime(10, 1) + ")", "Temperature Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Average: " + data.getYearAvgTemp(10) + "F\nHigh:       " + data.getYearHighTemp(10)  + "F  (" + data.getYearHighTempDate(10)  + "@" + data.getYearHighTempTime(10) + ")\nLow:        " + data.getYearLowTemp(10) + "(" + data.getYearLowTempDate(10)  + "@" + data.getYearLowTempTime(10) + ")", "Temperature Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }//GEN-LAST:event_averageTempItemActionPerformed

    /**
     * **********************************************************************
     * Function: averageWindItemActionPerformed Author: All Description:
     * Displays information regarding wind data Parameters: Action Event
     * **********************************************************************
     */
    private void averageWindItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageWindItemActionPerformed
        // TODO add your handling code here:
        switch (tabFlag) {
            case 1:
                JOptionPane.showMessageDialog(null, "Average:   " + data.getDayAvgWind(10, 1, 1) + "mph\nHigh:         " + data.getDayMaxWind(10, 1, 1) + "mph  (" + data.getDayMaxWindDate(10, 1, 1) + "@" + data.getDayMaxWindTime(10, 1, 1) + ")\nDirection:  E", "Wind Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Average:   " + data.getMonthAvgWind(10, 1) / 4 + "mph\nHigh:         " + data.getMonthMaxWind(10, 1) + "mph  (" + data.getMonthMaxWindDate(10, 1) + "@" + data.getMonthMaxWindTime(10, 1) + ")\nDirection:  E", "Wind Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Average:   " + data.getMonthAvgWind(10, 1) + "mph\nHigh:         " + data.getMonthMaxWind(10, 1) + "mph  (" + data.getMonthMaxWindDate(10, 1) + "@" + data.getMonthMaxWindTime(10, 1) + ")\nDirection:  E", "Wind Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Average:   " + data.getYearAvgWind(10) + "mph\nHigh:         " + data.getYearMaxWind(10) + "mph  (" + data.getYearMaxWindDate(10) + "@" + data.getYearMaxWindTime(10) + ")\nDirection:  E", "Wind Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }//GEN-LAST:event_averageWindItemActionPerformed

    /**
     * **********************************************************************
     * Function: averagePrecipItemActionPerformed Author: All Description:
     * Returns rainfall data Parameters: Action Event
     * **********************************************************************
     */
    private void averagePrecipItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averagePrecipItemActionPerformed
        // TODO add your handling code here:
        switch (tabFlag) {
            case 1:
                JOptionPane.showMessageDialog(null, "Rainfall: " + data.getDayTotalRainfall(10, 1, 1) + "in", "Rain Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Rainfall: " + data.getMonthTotalRainfall(10, 1) / 4 + "in", "Rain Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Rainfall: " + data.getMonthTotalRainfall(10, 1) + "in", "Rain Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Rainfall: " + data.getYearTotalRainfall(10) + "in", "Rain Stats", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }//GEN-LAST:event_averagePrecipItemActionPerformed

    /**
     * **********************************************************************
     * Function: temperatureRadioButtonActionPerformed Author: All Description:
     * Radio button for temperature Parameters: Action Event
     * **********************************************************************
     */
    private void temperatureRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temperatureRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 1;
        radioLabel = "Temperature";
        setChartTitle(radioFlag);
        callTabs();

    }//GEN-LAST:event_temperatureRadioButtonActionPerformed

    /**
     * **********************************************************************
     * Function: windsRadioButtonActionPerformed Author: All Description:
     * Selects wind data for display Parameters: Action Event
     * **********************************************************************
     */
    private void windsRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_windsRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 2;
        radioLabel = "Wind Speed";
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_windsRadioButtonActionPerformed

    /**
     * **********************************************************************
     * Function: barometricRadioButtonActionPerformed Author: All Description:
     * Selects pressure data for display Parameters: Action Event
     * **********************************************************************
     */
    private void barometricRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barometricRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 3;
        radioLabel = "Barometric Presure";
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_barometricRadioButtonActionPerformed

    /**
     * **********************************************************************
     * Function: heatUVindexActionPerformed Author: All Description: displays uv
     * data on graph Parameters: Action Event
     * **********************************************************************
     */
    private void heatUVindexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heatUVindexActionPerformed
        // TODO add your handling code here:
        radioFlag = 4;
        radioLabel = "UV Index";
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_heatUVindexActionPerformed

    /**
     * **********************************************************************
     * Function: humidityRadioButtonActionPerformed Author: All Description:
     * selects humidity data to display Parameters: Action Event
     * **********************************************************************
     */
    private void humidityRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humidityRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 5;
        radioLabel = "Humidity";
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_humidityRadioButtonActionPerformed

    /**
     * **********************************************************************
     * Function: rainfallRadioButtonActionPerformed Author: All Description:
     * selects rainfall data for display Parameters: Action Event
     * **********************************************************************
     */
    private void rainfallRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rainfallRadioButtonActionPerformed
        // TODO add your handling code here:
        radioFlag = 6;
        radioLabel = "Rainfall";
        setChartTitle(radioFlag);
        callTabs();
    }//GEN-LAST:event_rainfallRadioButtonActionPerformed

    private void dataSelectorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dataSelectorStateChanged
        // TODO add your handling code here:
        JSlider source = (JSlider) evt.getSource(); // get slider
        if (!source.getValueIsAdjusting()) // when user quits fiddling
        {
            int val = source.getValue();    // get slider value
            System.out.println(val);        // print it
        }
    }//GEN-LAST:event_dataSelectorStateChanged

    private void decrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decrementActionPerformed
        // TODO add your handling code here:
        int data = dataSelector.getValue();
        data--;
        dataSelector.setValue(data);
    }//GEN-LAST:event_decrementActionPerformed

    private void incrementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incrementActionPerformed
        // TODO add your handling code here:
        int data = dataSelector.getValue();
        data++;
        dataSelector.setValue(data);
    }//GEN-LAST:event_incrementActionPerformed

    private void specifyDirectoryItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specifyDirectoryItemActionPerformed
        // TODO add your handling code here:
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();

            File abspath = new File(path);

            System.out.println("Opening: " + abspath);
            data = null;
            this.data = new WeatherData();
            data.getWeatherData(abspath);
            callTabs();
        }
    }//GEN-LAST:event_specifyDirectoryItemActionPerformed

    /**
     * **********************************************************************
     * Function: makeChart Author: All Description: creates a chart using
     * JFreeChart Parameters: n/a
     * **********************************************************************
     */
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

        XYPlot plot = chart.getXYPlot();
        XYItemRenderer r = plot.getRenderer();

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        double size = 3;
        double delta = size / 2.0;
        Shape shape1 = new Ellipse2D.Double(-delta, -delta, size, size);
        renderer.setSeriesShape(0, shape1);
        //renderer.setSeriesShape(1, shape2); 
        renderer.setBaseShapesVisible(true);

        // define your own tooltip generator   
        StandardXYToolTipGenerator tooltipGenerator = new StandardXYToolTipGenerator() {
            @Override
            public String generateToolTip(XYDataset dataset, int series, int item) {
                return radioLabel + ": " + dataset.getYValue(series, item) + "  |  Date: x/xx/xx  |  Time: " + dataset.getXValue(series, item);
            }
        };
        // and assign it to the renderer
        r.setBaseToolTipGenerator(tooltipGenerator);

        return chart;
    }

    /**
     * **********************************************************************
     * Function: generateGraph Author: All Description: generates a graph
     * utilizing makeChart Parameters: tab - a JPanel type to set the
     * appropriate graphs
     * **********************************************************************
     */
    public void generateGraph(JPanel tab) {
        tab.removeAll();

        ChartPanel graph = new ChartPanel(makeChart());

        graph.setSize(tab.getWidth(), tab.getHeight());
        graph.setVisible(true);
        tab.add(graph);
        tab.repaint();
    }

    /**
     * **********************************************************************
     * Function: callTabs Author: All Description: generates a graph for the
     * appropriate tab Parameters: n/a
     * **********************************************************************
     */
    public void callTabs() {
        switch (tabFlag) {
            case 1:
                dailyTab.removeAll();
                setDataset();
                dataSelector.setMaximum(data.getDayCount());
                dataSelector.setValue(1);
                generateGraph(dailyTab);
                dailyTab.repaint();
                break;
            case 2:
                weeklyTab.removeAll();
                setDataset();
                dataSelector.setMaximum(data.getDayCount() / 4);
                dataSelector.setValue(1);
                generateGraph(weeklyTab);
                weeklyTab.repaint();
                break;
            case 3:
                monthlyTab.removeAll();
                setDataset();
                dataSelector.setMaximum(data.getMonthCount());
                dataSelector.setValue(1);
                generateGraph(monthlyTab);
                monthlyTab.repaint();
                break;
            case 4:
                yearlyTab.removeAll();
                setDataset();
                dataSelector.setMaximum(data.getYearCount());
                dataSelector.setValue(1);
                generateGraph(yearlyTab);
                yearlyTab.repaint();
                break;
        }
    }

    /**
     * **********************************************************************
     * Function: setChartTitle Author: All Description: sets the labels on the
     * chart Parameters: radioFlag - denotes which radio button is selected
     * **********************************************************************
     */
    public void setChartTitle(int radioFlag) {
        String name = jTabbedPane1.getTitleAt(tabFlag - 1);

        switch (radioFlag) {
            case 1:
                this.chartTitle = name + " Temperature";
                setYlabel("Degrees Farenheit (F)");
                setXlabel("Samples");
                break;
            case 2:
                this.chartTitle = name + " Wind Speed";
                setYlabel("Miles per Hour (MPH)");
                setXlabel("Samples");
                break;
            case 3:
                this.chartTitle = name + " Barometric Pressure";
                setYlabel("Inches of Mercury (inHg)");
                setXlabel("Samples");
                break;
            case 4:
                this.chartTitle = name + " UV Index";
                setYlabel("Index");
                setXlabel("Samples");
                break;
            case 5:
                this.chartTitle = name + " Humidity";
                setYlabel("Percent (%)");
                setXlabel("Samples");
                break;
            case 6:
                this.chartTitle = name + " Rainfall";
                setYlabel("Inches (in)");
                setXlabel("Samples");
                break;
            default:
                this.chartTitle = "Please Select Data Types on Left";
                break;
        }

    }

    /**
     * **********************************************************************
     * Function: setXlabel Author: All Description: sets the label on the x axis
     * Parameters: xlabel
     * **********************************************************************
     */
    public void setXlabel(String xLabel) {
        this.xLabel = xLabel;
    }

    /**
     * **********************************************************************
     * Function: setYlabel Author: All Description: sets the label on the Y axis
     * Parameters: yLabel
     * **********************************************************************
     */
    public void setYlabel(String yLabel) {
        this.yLabel = yLabel;
    }

    /**
     * **********************************************************************
     * Function: setDataSet Author: All Description: creates a data set to
     * render the data on the graph Parameters: dataSet - data created/used for
     * the JFreeChart
     * **********************************************************************
     */
    public void setDataSet(XYDataset dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * **********************************************************************
     * Function: getDataSet Author: All Description: returns the data set
     * created for the graph Parameters: n/a
     * **********************************************************************
     */
    public XYDataset getDataSet() {
        return dataSet;
    }

    /**
     * @param args the command line arguments
     */
    /**
     * **********************************************************************
     * Function: main Author: All Description: runs the main thread for
     * MainDisplay (and associated form) Parameters: args[] - command line args
     * **********************************************************************
     */
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
    private javax.swing.JButton decrement;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JRadioButton heatUVindex;
    private javax.swing.JRadioButton humidityRadioButton;
    private javax.swing.JButton increment;
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
