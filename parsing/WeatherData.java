/*

    **** ReadXML2.java - read and dump contents of an XML file ****

Illustrates use of JDOM to parse an XML file.
This version will dive into XML tree structure.

Usage:
javac -cp .:jdom.jar ElementLister.java         (use ; instead of : on Windoze)
java  -cp .:jdom.jar ElementLister file.xml     (use ; instead of : on Windoze)

Based on Java example in Processing XML with Java (Elliotte Harold).
For more info, see e.g. https://docs.oracle.com/javase/tutorial/jaxp/dom/readingXML.html

JMW 160205


http://www.journaldev.com/1206/jdom-parser-read-xml-file-to-object-in-java
*/

import org.jdom2.*;
import java.io.File;
import java.io.FileInputStream;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.util.*;
import java.io.FilenameFilter;
import org.apache.commons.io.FileUtils;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/************************************************************************
    Class:  WeatherData
    Author: All
    Description: Class representing an over-arching object to contain
	a hash table of year objects containing all sample data given via
	the default/user specified directory; 
 ************************************************************************/
public class WeatherData
{
	/* Dictionary object containing all years of weather samples */
	private static Hashtable<Integer, Year> dictOfYears;  	
	
	/* get the number of days of samples in the data set */
	private static int dayCount;
	
	/* get the number of weeks of samples in the data set */
	private static int weekCount;
	
	/* get the number of months of samples in the data set */
	private static int monthCount;
	
	/* get the number of years of samples in the data set */
	private static int yearCount;
	
	
	/**
     * **********************************************************************
     * Function: getMinDate() 
	 * Author: All 
	 * Description: gets the date of the first sample in the data set
     * Parameters: n/a
     * **********************************************************************
     */
	public static int[] getMinDate()
	{
		int minYearKey = 999999;
		int minMonthKey = 999999;
		int minDayKey = 999999;
		int temp;
		int[] minDate = new int[3];
		Hashtable<Integer, Year> yearSet = new Hashtable<Integer, Year>( dictOfYears);

        Set<Integer> ykeys = yearSet.keySet();
        for(Integer key: ykeys)
		{
			temp = key;
			if( temp < minYearKey )
			{
				minYearKey = temp;
			}
		}
		
		Year year = WeatherData.dictOfYears.get( minYearKey );
		
		Hashtable<Integer, Month> months = year.getAllMonthlySamples();
		
		Set<Integer> mkeys = months.keySet();
		
		for(Integer key: mkeys)
		{
			temp = key;
			if( temp < minMonthKey )
			{
				minMonthKey = temp;
			}
		}
            
        Month month = months.get( minMonthKey );
		
		Hashtable<Integer, Day> days = month.getAllDaySamples();
		
		Set<Integer> dkeys = days.keySet();
		
		for(Integer key: mkeys)
		{
			temp = key;
			if( temp < minDayKey )
			{
				minDayKey = temp;
			}
		}
		
		minDate[0] = minMonthKey;
		minDate[1] = minDayKey;
		minDate[2] = minYearKey;
		
		return minDate;

	}
	
	/**
     * **********************************************************************
     * Function: checkNullTag() 
	 * Author: All 
	 * Description: checks for null tags in .xml data; sets default value if null
     * Parameters: n/a
     * **********************************************************************
     */
	public static String checkNullTag( String tag )  // used to check for null tags in a .xml file
	{
		String attr = tag;
		
		if(  attr== null )
		{
			attr = "50.0";
		}
		
		return attr;
	}
	
	/**
     * **********************************************************************
     * Function: getDayPrevailingWindDir()
	 * Author: All 
	 * Description: returns the prevailing wind direction for a given day
     * Parameters: int yearIndex - used to index a specific year
	 *             int monthIndex - used to index a specific month
	 *             int dayIndex - used to index a specific day 
     * **********************************************************************
     */
	public static String getDayPrevailingWindDir( int yearIndex, int monthIndex, int dayIndex )
	{
		// get the specific day
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String dir = day.getPrevailingWindDir();  // return the wind direction
		
		return dir;
	}
	
	/**
     * **********************************************************************
     * Function: getWeekPrevailingWindDir()
	 * Author: All 
	 * Description: returns the prevailing wind direction for a given week
     * Parameters: int yearIndex - used to index a specific year
	 *             int monthIndex - used to index a specific month
	 *             int weekIndex - used to index a specific week
     * **********************************************************************
     */
	public static String getWeekPrevailingWindDir( int yearIndex, int monthIndex, int weekIndex )
	{
		//get specific month
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		//returns the wind direction 
		String dir = month.getWeekPrevailingWindDir( weekIndex );
		
		return dir;
	}
	
	/**
     * **********************************************************************
     * Function: getMonthPrevailingWindDir()
	 * Author: All 
	 * Description: returns the prevailing wind direction for a given month
     * Parameters: int yearIndex - used to index a specific year
	 *             int monthIndex - used to index a specific month
     * **********************************************************************
     */
	public static String getMonthPrevailingWindDir( int yearIndex, int monthIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String dir = month.getPrevailingWindDir();
		
		return dir;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearPrevailingWindDir()
	 * Author: All 
	 * Description: returns the prevailing wind direction for a given year
     * Parameters: int yearIndex - used to index a specific year
     * **********************************************************************
     */
	public static String getYearPrevailingWindCir( int yearIndex )
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String dir = year.getPrevailingWindDir();
		
		return dir; 
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayCount()
	 * Author: All 
	 * Description: returns the number of days worth of samples in the dataset
     * Parameters: n/a
     * **********************************************************************
     */
	public static int getDayCount()
	{
		return WeatherData.dayCount;
	}
	
	/**
     * **********************************************************************
     * Function: getWeekCount()
	 * Author: All 
	 * Description: returns the number of weeks worth of samples in the dataset
     * Parameters: n/a
     * **********************************************************************
     */
	public static int getWeekCount()
	{
		return WeatherData.weekCount;
	}
	
	/**
     * **********************************************************************
     * Function: getMonthCount()
	 * Author: All 
	 * Description: returns the number of months worth of samples in the dataset
     * Parameters: n/a
     * **********************************************************************
     */
	public static int getMonthCount()
	{
		
		return WeatherData.monthCount;
	}
	
	/**
     * **********************************************************************
     * Function: getYearCount()
	 * Author: All 
	 * Description: returns the number of years worth of samples in the dataset
     * Parameters: n/a
     * **********************************************************************
     */
	public static int getYearCount()
    {
		
		return WeatherData.yearCount;
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekAvgTemp()
	 * Author: All 
	 * Description: returns the average temperature of a specified week
     * Parameters: int yearIndex - index for specific year
	 *             int monthIndex - index for specific month
	 *             int weekIndex - index for specific week
     * **********************************************************************
     */
	public static float getWeekAvgTemp( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		float avg = temp.getMeanTemp();
		
		return avg;	
		
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekMaxTemp()
	 * Author: All 
	 * Description: returns the high temp for a given week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static float getWeekMaxTemp( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxTemp();
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekMaxTempDate()
	 * Author: All 
	 * Description: returns the date of the the high temp of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekMaxTempDate( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxTempDate();
	}
	
	/**
     * **********************************************************************
     * Function: getWeekMaxTempTime()
	 * Author: All 
	 * Description: returns the time of the the high temp of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekMaxTempTime( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxTempTime();
	}

	/**
     * **********************************************************************
     * Function: getWeekLowTemp()
	 * Author: All 
	 * Description: returns the low temp of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static float getWeekLowTemp( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getLowTemp();
	}
	
	/**
     * **********************************************************************
     * Function: getWeekLowTempDate()
	 * Author: All 
	 * Description: returns the date of the low temp of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekLowTempDate( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getLowTempDate();
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekLowTempTime()
	 * Author: All 
	 * Description: returns the time of the low temp of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekLowTempTime( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getLowTempTime();
	}
	
	/**
     * **********************************************************************
     * Function: getWeekMeanWind()
	 * Author: All 
	 * Description: returns the average wind speed of a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static float getWeekMeanWind( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		float avg = temp.getMeanWind();
		
		return avg;		
		
	}
	
	/**
     * **********************************************************************
     * Function: getWeekMaxWind()
	 * Author: All 
	 * Description: returns the high wind gust for a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static float getWeekMaxWind( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxWind();
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekWindDate()
	 * Author: All 
	 * Description: returns the date of the high wind gust for a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekMaxWindDate( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxWindDate();
	}
	
	/**
     * **********************************************************************
     * Function: getWeekWindTime()
	 * Author: All 
	 * Description: returns the time of the high wind gust for a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static String getWeekMaxWindTime( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getMaxWindTime();
	}
	
	
	/**
     * **********************************************************************
     * Function: getWeekRainfall()
	 * Author: All 
	 * Description: returns the accumulated precipiation for a specific week
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int weekIndex - index for a specific week
     * **********************************************************************
     */
	public static float getWeekRainfall( int yearIndex, int monthIndex, int weekIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		WeekStats temp = month.getWeekStats( weekIndex );
		
		return temp.getRainfall();
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayAvgTemp()
	 * Author: All 
	 * Description: returns the average temperature for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayAvgTemp( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float avg = day.getMeanTemp();
		
		return avg;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayHighTemp()
	 * Author: All 
	 * Description: returns the high temperature for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayHighTemp( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float high = day.getHighTemp();
		
		return high;
	}
	
	/**
     * **********************************************************************
     * Function: getDayHighTempDate()
	 * Author: All 
	 * Description: returns the date of the high temp of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayHighTempDate( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String date = day.getHighTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayHighTempTime()
	 * Author: All 
	 * Description: returns the time of the high temp of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayHighTempTime( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String time = day.getHighTempTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayLowTemp()
	 * Author: All 
	 * Description: returns the low temp of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayLowTemp( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float low = day.getLowTemp();
		
		return low;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayLowTempDate()
	 * Author: All 
	 * Description: returns the date of the low temp of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayLowTempDate( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String date = day.getLowTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayLowTempTime()
	 * Author: All 
	 * Description: returns the time of the low temp of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayLowTempTime( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String time = day.getLowTempTime();
		
		return time;
	}
	
	/**
     * **********************************************************************
     * Function: getDayAvgWind()
	 * Author: All 
	 * Description: returns the average wind speed of a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayAvgWind( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float avg = day.getMeanWind();
		
		return avg;
		
	}
		
	/**
     * **********************************************************************
     * Function: getDayMaxWind()
	 * Author: All 
	 * Description: returns the high wind gust for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayMaxWind( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float low = day.getMaxWind();
		
		return low;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayMaxWindDate()
	 * Author: All 
	 * Description: returns the date of the high wind gust for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayMaxWindDate( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String date = day.getMaxWindDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayMaxWindTime()
	 * Author: All 
	 * Description: returns the time of the high wind gust for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static String getDayMaxWindTime( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		String time = day.getMaxWindTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getDayTotalRainfall()
	 * Author: All 
	 * Description: returns the accumulated rainfall for a specific day
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
	 *             int dayIndex - index for a specific day
     * **********************************************************************
     */
	public static float getDayTotalRainfall( int yearIndex, int monthIndex, int dayIndex )
	{
		Day day = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex ).getDayofSamples( dayIndex );
		
		float rain = day.getRainfall();
		
		return rain;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthAvgTemp()
	 * Author: All 
	 * Description: returns the average temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthAvgTemp( int yearIndex, int monthIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float avg = month.getMeanTemp();
		
		return avg;
	}
	
	/**
     * **********************************************************************
     * Function: getMonthHighTemp()
	 * Author: All 
	 * Description: returns the high temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthHighTemp( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float high = month.getHighTemp();
		
		return high;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthHighTempDate()
	 * Author: All 
	 * Description: returns the date of the high temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthHighTempDate( int yearIndex, int monthIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String date = month.getHighTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthHighTempTime()
	 * Author: All 
	 * Description: returns the time of the high temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthHighTempTime( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String time = month.getHighTempTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthLowTemp()
	 * Author: All 
	 * Description: returns the low temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthLowTemp( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float low = month.getLowTemp();
		
		return low;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthLowTempDate()
	 * Author: All 
	 * Description: returns the date of the low temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthLowTempDate( int yearIndex, int monthIndex )
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String date = month.getLowTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthLowTempTime()
	 * Author: All 
	 * Description: returns the date of the low temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthLowTempTime( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String time = month.getLowTempTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthAvgTemp()
	 * Author: All 
	 * Description: returns the average temperature for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthAvgWind( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float avg = month.getMeanWind();
		
		return avg;
		
	}
		
	/**
     * **********************************************************************
     * Function: getMonthMaxWind()
	 * Author: All 
	 * Description: returns the max wind gust for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthMaxWind( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float low = month.getMaxWind();
		
		return low;
	}
	
	/**
     * **********************************************************************
     * Function: getMonthMaxWindDate()
	 * Author: All 
	 * Description: returns the date of the max wind gust for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthMaxWindDate( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String date = month.getMaxWindDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthMaxWindTime()
	 * Author: All 
	 * Description: returns the time of the max wind gust for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static String getMonthMaxWindTime( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		String time = month.getMaxWindTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getMonthTotalRainfall()
	 * Author: All 
	 * Description: returns the accumulated rainfall for a given month
     * Parameters: int yearIndex - index for a specific year
	 *             int monthIndex - index for a specific month
     * **********************************************************************
     */
	public static float getMonthTotalRainfall( int yearIndex, int monthIndex)
	{
		Month month = WeatherData.dictOfYears.get( yearIndex ).getMonthlySamplesByMonth( monthIndex );
		
		float rain = month.getRainfall();
		
		return rain;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearAvgTemp()
	 * Author: All 
	 * Description: returns the average temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearAvgTemp( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float avg = year.getMeanTemp();
		
		return avg;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearHighTemp()
	 * Author: All 
	 * Description: returns the high temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearHighTemp( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float high = year.getHighTemp();
		
		return high;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearHighTempDate()
	 * Author: All 
	 * Description: returns the date of the high temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearHighTempDate( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String date = year.getHighTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearHighTempTime()
	 * Author: All 
	 * Description: returns the time of the high temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearHighTempTime( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String time = year.getHighTempTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearLowTemp()
	 * Author: All 
	 * Description: returns the low temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearLowTemp( int yearIndex )
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float low = year.getLowTemp();
		
		return low;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearLowTempDate()
	 * Author: All 
	 * Description: returns the date of the low temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearLowTempDate( int yearIndex )
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String date = year.getLowTempDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearLowTempTime()
	 * Author: All 
	 * Description: returns the time of the low temperature for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearLowTempTime( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String time = year.getLowTempTime();
		
		return time;
	}
	
	/**
     * **********************************************************************
     * Function: getYearAvgWind()
	 * Author: All 
	 * Description: returns the average wind speed for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearAvgWind( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float avg = year.getMeanWind();
		
		return avg;
		
	}
		
	/**
     * **********************************************************************
     * Function: getYearMaxWind()
	 * Author: All 
	 * Description: returns the max wind speed for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearMaxWind( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float low = year.getMaxWind();
		
		return low;
	}
	
	/**
     * **********************************************************************
     * Function: getYearMaxWindDate()
	 * Author: All 
	 * Description: returns the date of the max wind speed for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearMaxWindDate( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String date = year.getMaxWindDate();
		
		return date;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearMaxWindTime()
	 * Author: All 
	 * Description: returns the time of the max wind speed for a given year 
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static String getYearMaxWindTime( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		String time = year.getMaxWindTime();
		
		return time;
	}
	
	
	/**
     * **********************************************************************
     * Function: getYearTotalRainfall()
	 * Author: All 
	 * Description: returns the accumulated precipitation
     * Parameters: int yearIndex - index for a specific year
     * **********************************************************************
     */
	public static float getYearTotalRainfall( int yearIndex)
	{
		Year year = WeatherData.dictOfYears.get( yearIndex );
		
		float rain = year.getRainfall();
		
		return rain;
	}


	
	public static XYSeriesCollection getDaySetOfData( int yearIndex, int monthIndex, int dayIndex )
	{
		
		XYSeries tempz = new XYSeries("Temperature");
		XYSeries windz = new XYSeries("Wind");
		XYSeries baroz = new XYSeries("Barometer");
		XYSeries heatindexz = new XYSeries("Heat Index" );
		XYSeries uvindexz = new XYSeries("UV Index" );
		XYSeries humidity = new XYSeries("Humidity" );
		XYSeries precipitation = new XYSeries("Precipitation");
		XYSeries windspeed = new XYSeries("Windspeed" );
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();

		
		
		Year year = WeatherData.dictOfYears.get( yearIndex ); // grab the year of data in question
		
		System.out.println( "The year index is: " + yearIndex );
		
		System.out.println( "The month index is: " + monthIndex );
		
		System.out.println( "The day index is: " + dayIndex );				
		
		Month mun = year.getMonthlySamplesByMonth( monthIndex );  // grab the month of data in question
		
		Day daa = mun.getDayofSamples( dayIndex );
		
		if( daa == null )  // if the day doesn't exist, populate some uninteresting data for JFreeChart
		{	
			tempz.add( 1, 0.0 );
			windz.add( 1, 0.0 );
			baroz.add( 1, 0.0 );
			heatindexz.add( 1, 0.0 );
			uvindexz.add( 1, 0.0 ) ;
            humidity.add( 1, 0.0);
            precipitation.add( 1, 0.0 );	
            windspeed.add( 1, 0.0 );			
            dataset.addSeries( tempz );
		    dataset.addSeries( windz );
		    dataset.addSeries( baroz );
		    dataset.addSeries( heatindexz );
		    dataset.addSeries( uvindexz );
			dataset.addSeries( humidity );
			dataset.addSeries( precipitation );
			dataset.addSeries( windspeed );
		
		    return dataset;					
			
		}
		
		ArrayList<wItem> samples = daa.getSamples();
				
		int i = 0;
		
		System.out.println( "Made it here" );
		for (wItem item: samples) // for each sample in a day
		{
			
		    // convert data into xy coordinate sets for JFreeChart use
            tempz.add( i, item.getTemperature() );
			windz.add( i, item.getWindspeed() );
			baroz.add( i, item.getBarometer() );
			heatindexz.add( i, item.getHeatindex() );
			uvindexz.add( i, item.getUvindex() );
			humidity.add( i, item.getHumidity() );
			precipitation.add( i, item.getRainfall() );
			windspeed.add( i, item.getWindspeed() );
			
			i += 1;
					
		} 

        // construct the graph-able data set for the year
		dataset.addSeries( tempz );
		dataset.addSeries( windz );
		dataset.addSeries( baroz );
		dataset.addSeries( heatindexz );
		dataset.addSeries( uvindexz );
		dataset.addSeries( humidity );
		dataset.addSeries( precipitation);
		dataset.addSeries( windspeed );
		
		return dataset;				
				
	}
	
	public static XYSeriesCollection getWeekSetOfData( int yearIndex, int monthIndex, int weekIndex )
	{
		
		XYSeries tempz = new XYSeries("Temperature");
		XYSeries windz = new XYSeries("Wind");
		XYSeries baroz = new XYSeries("Barometer");
		XYSeries heatindexz = new XYSeries("Heat Index" );
		XYSeries uvindexz = new XYSeries("UV Index" );
		XYSeries humidity = new XYSeries("Humidity" );
		XYSeries precipitation = new XYSeries("Precipitation");
		XYSeries windspeed = new XYSeries("Windspeed" );
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		
		Year year = WeatherData.dictOfYears.get( yearIndex ); // grab the year of data in question
		
		Month mun = year.getMonthlySamplesByMonth( monthIndex );  // grab the month of data in question
		
		if( mun == null ) // if month doesn't exist, create uninteresting data for JFreeChart and return
		{
			
			tempz.add( 1, 0.0 );
			windz.add( 1, 0.0 );
			baroz.add( 1, 0.0 );
			heatindexz.add( 1, 0.0 );
			uvindexz.add( 1, 0.0 ) ;
            humidity.add( 1, 0.0);
            precipitation.add( 1, 0.0 );	
            windspeed.add( 1, 0.0 );			
            dataset.addSeries( tempz );
		    dataset.addSeries( windz );
		    dataset.addSeries( baroz );
		    dataset.addSeries( heatindexz );
		    dataset.addSeries( uvindexz );
			dataset.addSeries( humidity );
			dataset.addSeries( precipitation );
			dataset.addSeries( windspeed );
			
		
		    return dataset;					
		}
		
		ArrayList<Day> week = mun.getWeekOfSamples( weekIndex );//mun.getAllWeekSamples();  // get all samples in weekly chunks
		
		
		int j = 0;
		
			
		for( Day daa : week )
		{
			ArrayList<wItem> samples = daa.getSamples();					
			
			for (wItem item: samples) // for each sample in a day
			{			
				// convert data into xy coordinate sets for JFreeChart use
				tempz.add( j, item.getTemperature() );
				windz.add( j, item.getWindspeed() );
				baroz.add( j, item.getBarometer() );
				heatindexz.add( j, item.getHeatindex() );
				uvindexz.add( j, item.getUvindex() );
				humidity.add( j, item.getHumidity() );
				precipitation.add( j, item.getRainfall() );
				windspeed.add( j, item.getWindspeed() );
			
				j += 1;				
			}  			
		}
		//}
		
		// construct the graph-able data set for the year
		dataset.addSeries( tempz );
		dataset.addSeries( windz );
		dataset.addSeries( baroz );
		dataset.addSeries( heatindexz );
		dataset.addSeries( uvindexz );
		dataset.addSeries( humidity );
		dataset.addSeries( precipitation);
		dataset.addSeries( windspeed );
		
		return dataset;	
		
		
	}	
	
	
	public static XYSeriesCollection getMonthSetOfData( int yearIndex, int monthIndex )
	{
		
		XYSeries tempz = new XYSeries("Temperature");
		XYSeries windz = new XYSeries("Wind");
		XYSeries baroz = new XYSeries("Barometer");
		XYSeries heatindexz = new XYSeries("Heat Index" );
		XYSeries uvindexz = new XYSeries("UV Index" );
		XYSeries humidity = new XYSeries("Humidity" );
		XYSeries precipitation = new XYSeries("Precipitation");
		XYSeries windspeed = new XYSeries("Windspeed" );
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		Year year = WeatherData.dictOfYears.get( yearIndex ); // grab the year of data in question
		
		Month mun = year.getMonthlySamplesByMonth( monthIndex );  // grab the month of data in question
		
		if( mun == null ) // if month doesn't exist, create uninteresting data for JFreeChart and return
		{
			
			tempz.add( 1, 0.0 );
			windz.add( 1, 0.0 );
			baroz.add( 1, 0.0 );
			heatindexz.add( 1, 0.0 );
			uvindexz.add( 1, 0.0 ) ;
            humidity.add( 1, 0.0);
            precipitation.add( 1, 0.0 );	
            windspeed.add( 1, 0.0 );			
            dataset.addSeries( tempz );
		    dataset.addSeries( windz );
		    dataset.addSeries( baroz );
		    dataset.addSeries( heatindexz );
		    dataset.addSeries( uvindexz );
			dataset.addSeries( humidity );
			dataset.addSeries( precipitation );
			dataset.addSeries( windspeed );
		
		    return dataset;
		}
		
		Hashtable<Integer, Day> days = mun.getAllDaySamples();
		
		int i = 1;
		
		int j = 0;
			
		for( i = 1; i <= days.size(); i++ )
		{
			
			Day daa = days.get( i );
			
			if( daa == null )  // if day doesn't exist, skip to next day lookup
				{
					continue;
				}
				
			ArrayList<wItem> samples = daa.getSamples();
					
				
			for (wItem item: samples) // for each sample in a day
			{
				
				// convert data into xy coordinate sets for JFreeChart use
                tempz.add( j, item.getTemperature() );
				windz.add( j, item.getWindspeed() );
				baroz.add( j, item.getBarometer() );
				heatindexz.add( j, item.getHeatindex() );
				uvindexz.add( j, item.getUvindex() );
				humidity.add( j, item.getHumidity() );					
				precipitation.add( j, item.getRainfall() );
				windspeed.add( j, item.getWindspeed() );
				
				
				j += 1;
					
			}  			
		}
			
		// construct the graph-able data set for the year
		dataset.addSeries( tempz );
		dataset.addSeries( windz );
		dataset.addSeries( baroz );
		dataset.addSeries( heatindexz );
		dataset.addSeries( uvindexz );
		dataset.addSeries( humidity );
		dataset.addSeries( precipitation);
		dataset.addSeries( windspeed );
		
		return dataset;	
		
	}
	
	
	public static XYSeriesCollection getYearSetOfData( int yearIndex )
	{
		
		XYSeries tempz = new XYSeries("Temperature");
		XYSeries windz = new XYSeries("Wind");
		XYSeries baroz = new XYSeries("Barometer");
		XYSeries heatindexz = new XYSeries("Heat Index" );
		XYSeries uvindexz = new XYSeries("UV Index" );
		XYSeries humidity = new XYSeries("Humidity" );
		XYSeries precipitation = new XYSeries("Precipitation");
		XYSeries windspeed = new XYSeries( "Windspeed");
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		Year year = WeatherData.dictOfYears.get( yearIndex ); // grab the year of data in question
		
		if( year == null )  // if year doesn't exist, create uniteresting data to graph and return;
		{
			
			tempz.add( 1, 0.0 );
			windz.add( 1, 0.0 );
			baroz.add( 1, 0.0 );
			heatindexz.add( 1, 0.0 );
			uvindexz.add( 1, 0.0 ) ;
            humidity.add( 1, 0.0);
            precipitation.add( 1, 0.0 );
            windspeed.add( 1, 0.0 );			
            dataset.addSeries( tempz );
		    dataset.addSeries( windz );
		    dataset.addSeries( baroz );
		    dataset.addSeries( heatindexz );
		    dataset.addSeries( uvindexz );
			dataset.addSeries( humidity );
			dataset.addSeries( precipitation );
			dataset.addSeries( windspeed );
			
		
		    return dataset;
		}
		
		Hashtable<Integer, Month> months = year.getAllMonthlySamples(); // grab the months in the current year
				
			
		int i = 0;
		int z = 1;
		
		for( z = 1; z <= months.size(); z++ )  // for each month in the year
		{
			Month mun = months.get(z);//mkey);  // grab a single month in the list of months
			
			if( mun == null )  // if the month doesn't exist, skip it
			{
				continue;
			}
			
			
			Hashtable<Integer, Day> days = mun.getAllDaySamples();
			
			int j = 1;
			
			for( j = 1; j <= days.size(); j++ )  // for each day in a month
			{
				Day daa = days.get( j );
				
				if( daa == null ) // if day doesn't exist, skip to next day lookup
				{
					continue;
				}
				
				
				ArrayList<wItem> samples = daa.getSamples();
				
				
				for (wItem item: samples) // for each sample in a day
				{
					// convert data into xy coordinate sets for JFreeChart use
					tempz.add( i, item.getTemperature() );
					windz.add( i, item.getWindspeed() );
					baroz.add( i, item.getBarometer() );
					heatindexz.add( i, item.getHeatindex() );
					uvindexz.add( i, item.getUvindex() );
					humidity.add( i, item.getHumidity() );					
					precipitation.add( i, item.getRainfall() );
					windspeed.add( i, item.getWindspeed() );
					
					i += 1;
                }
			}
		}
		
		// construct the graph-able data set for the year
		dataset.addSeries( tempz );
		dataset.addSeries( windz );
		dataset.addSeries( baroz );
		dataset.addSeries( heatindexz );
		dataset.addSeries( uvindexz );
		dataset.addSeries( humidity );
		dataset.addSeries( precipitation);
		dataset.addSeries( windspeed );
		
		return dataset;
    }
		
	
    public static void getWeatherData( File folder )
    {        	
		
		
		File[] listOfFiles = folder.listFiles( new FilenameFilter()
		{  // only get .xml files included in list of files to be processed in the directory
			public boolean accept(  File folder, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		});
		
		int totalDataFileCount = listOfFiles.length;  // get the total number of data files
		
		
		int fileCount = 0;
		int currYear = -1;
		int prevYear = -1;
		int prevMonth = -1;
		int currMonth = -1;
		
			
		WeatherData.dictOfYears = new Hashtable<Integer, Year>();
		
		ArrayList<wItem> daySamples = new ArrayList<wItem>();  // will contain all samples for one day
		Month mun = new Month();
		Year year = new Year();
				

		int currDay = -1;
		int prevDay = -1;
		
		int i = 0;

        for ( i = 0; i < listOfFiles.length; i++) 
		{
        File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".xml")) {
                try {
                    String content = FileUtils.readFileToString(file);
                } catch ( IOException e ) {
                    System.out.println( e );
                }                      					
				
                
                // read and parse XML document
                SAXBuilder builder = new SAXBuilder();
                try
                {
					
                    Document doc = builder.build( file );	// parse XML tags
                    Element root = doc.getRootElement();	// get root of XML tree
                    List<Element> xmlContent = root.getChildren("weather");
																		
					
					int numOSamples = xmlContent.size();  // store number of samples in .xml file; for flagging the last file processed
					
					int j = 0;  // loop flag for the last file processed
					
                    for (Element element : xmlContent) 
					{
                        wItem item = new wItem();
                        item.setDate(element.getChildText("date"));
                        item.setTime(element.getChildText("time"));																
						
						// check for null tags; set default value if data is missing
						String tag = element.getChildText("temperature");  						
						String attr = WeatherData.checkNullTag( tag );
						item.setTemperature(Float.parseFloat(attr));												
						
						String atag = element.getChildText("humidity");  
						String aattr = WeatherData.checkNullTag( atag );							
                        item.setHumidity(Float.parseFloat(element.getChildText(aattr)));//"humidity")));
						
						String btag = element.getChildText("barometer");  
						String battr = WeatherData.checkNullTag( btag );					
                        item.setBarometer(Float.parseFloat(element.getChildText(battr)));//"barometer")));
						
						String ctag = element.getChildText("windspeed");  
						String cattr = WeatherData.checkNullTag( ctag );
                        item.setWindspeed(Float.parseFloat(element.getChildText(cattr)));//"windspeed")));
						
						String dtag = element.getChildText("winddirection");  
						String dattr = WeatherData.checkNullTag( dtag );						
                        item.setWinddirection(element.getChildText(dattr));//"winddirection"));
						
						String etag = element.getChildText("windgust");  
						String eattr = WeatherData.checkNullTag( etag );
                        item.setWindgust(Float.parseFloat(element.getChildText(eattr)));//"windgust")));
						
						String ftag = element.getChildText("windchill");  					
						String fattr = WeatherData.checkNullTag( ftag );
                        item.setWindchill(Float.parseFloat(element.getChildText(fattr)));//"windchill")));
						
						String gtag = element.getChildText("heatindex");  					
						String gattr = WeatherData.checkNullTag( gtag );
                        item.setHeatindex(Float.parseFloat(element.getChildText(gattr)));//"heatindex")));
						
						String htag = element.getChildText("uvindex");  
						String hattr = WeatherData.checkNullTag( htag );						
                        item.setUvindex(Float.parseFloat(element.getChildText(hattr)));//"uvindex")));
						
						String itag = element.getChildText("rainfall");  
						String iattr = WeatherData.checkNullTag( itag );							
                        item.setRainfall(Float.parseFloat(element.getChildText(iattr)));//"rainfall")));
						
						
						j += 1;
						
						if( j == numOSamples && i == totalDataFileCount - 1)  // if we are on the last sample of the last file
						{
							daySamples.add( item );
							
					        Day daa = new Day();
							daa.setSamples( daySamples );  // set the samples for the previous day
							daa.setDay( currDay );  // set the day field
							
							daa.setMonth( currMonth );
							
							daa.setYear( currYear );
							
							daa.calcStats();
							
							WeatherData.dayCount += 1;
							
							//System.out.println( "The number of days is: " + WeatherData.dayCount );
							mun.setDailySamples( currDay, daa ); 
							
							mun.setMonth( currMonth );
							
							mun.setYear( currYear );
							
							mun.setWeeklySamples();
							
							if( mun.getMonth() == 2 )
							{
								WeatherData.weekCount += 5;
							}
							else
							{
								WeatherData.weekCount += 4;
							}
							
								
							Month tempMonth = new Month( mun ); // create a new month in memory
						
							
							tempMonth.calcStats();
							tempMonth.calcWeekStats();
							
							
							//System.out.println( "The number of weeks is: " + WeatherData.weekCount );
							
							WeatherData.monthCount += 1;
							
							//System.out.println( "The number of months is: " + WeatherData.monthCount );
							
							year.setMonthlySamples( currMonth, tempMonth );
							
							year.setYear( currYear );
							
							year.calcStats();
							
							Year savedYear = new Year( year );
							
							year.calcStats();
							
							WeatherData.yearCount += 1;
							
							System.out.println( "The number of years is: " + WeatherData.yearCount );
							
							WeatherData.dictOfYears.put( currYear, savedYear );
						}
				        						
						currDay = item.getDay();
						
						currMonth = item.getMonth();
						
						currYear = item.getYear();
						
						
						if( prevDay == -1 )  // if there hasn't been a previous day yet
						{
							prevDay = currDay;
						}
						
						if( prevMonth == -1 )  
						{
							prevMonth = currMonth;
						}
						
						if( prevYear == -1 )
						{
							prevYear = currYear;
						}
											
						
						if( prevDay != currDay )  // if we started processing samples from a different day
						{
						
							Day daa = new Day();
							daa.setSamples( daySamples );  // set the samples for the previous day
							daa.setDay( prevDay );  // set the day field
							
							daa.setMonth( prevMonth );
							
							daa.setYear( prevYear );
							
							daa.calcStats();
							
							WeatherData.dayCount += 1;
							
							
							
							mun.setDailySamples( prevDay, daa );  // set that day of samples in a month object
																				
							
							if( prevMonth != currMonth )  // check to see if the next day is in a new month
							{							    							
							
								mun.setMonth( prevMonth );
								
								mun.setYear( prevYear );
								
								mun.setWeeklySamples();
								
								if( mun.getMonth() == 2 )
								{
									WeatherData.weekCount += 5;
								}
								else
								{
									WeatherData.weekCount += 4;
								}
								
								Month tempMonth = new Month( mun ); // create a new month in memory
								
								tempMonth.calcStats();
								
								tempMonth.calcWeekStats();
								
								
								WeatherData.monthCount += 1;
								
								
								year.setMonthlySamples( prevMonth, tempMonth );  // add month to year
								
								mun.reset();
																
								
								if( prevYear != currYear )
								{									
									year.setYear( prevYear );
									
									Year savedYear = new Year( year );
									
									savedYear.calcStats();
									
									WeatherData.yearCount += 1;
									
									WeatherData.dictOfYears.put( prevYear, savedYear ); 
									
								    year.reset();
									
									prevYear = currYear;
								}
							
								prevMonth = currMonth;
								
							}	
							
							daySamples.clear();  // remove previous day's sample
							
							daySamples.add( item );  // add first sample to new day
							
							prevDay = currDay;  // shift flags						
							
						}
						else
						{
							daySamples.add( item ); // if we are still processing the same day, keep adding samples 
						}
						
                    }
  
                    //WeatherData.setWeeklySamples(); // create the weekly sample sets from the .xml data  
					
                }
                // JDOMException indicates a well-formedness error
                catch ( JDOMException e )
                {
                    System.out.println( "File is not well-formed." );
                    System.out.println( e.getMessage() );
                }
                catch ( IOException e )
                {
                    System.out.println( e );
                }
            } 
        }
		
		/*
        System.out.println(dictOfYears);
		Year tempYear = dictOfYears.get(10);
		System.out.println( tempYear.getYear() );
		Hashtable<Integer, Month> dictofMonths = new Hashtable<Integer, Month>();
		dictofMonths.putAll(tempYear.getAllMonthlySamples() );
		System.out.println(dictofMonths);
		
		Hashtable<Integer, Day> dictOfDays = new Hashtable<Integer, Day>();
		Month tempMonth = dictofMonths.get(1);
		System.out.println( tempMonth.getMonth() );
		dictOfDays.putAll( tempMonth.getAllDaySamples() );
		System.out.println(dictOfDays);
		
		Hashtable<Integer, ArrayList<wItem> > dictOfSamples = new Hashtable<Integer, ArrayList<wItem>>();
		Day tempDay = dictOfDays.get(1);
		System.out.println( tempDay.getDay() );
		dictOfSamples.put( 1,  tempDay.getSamples() );
		System.out.println( dictOfSamples );
		*/
			
		
    }
	
	public static void main( String[] args )
	{
		//File dir = new File("");
		//WeatherData data = new WeatherData();
		File dir = new File("..\\data\\");
		
		//System.out.println( "The name of the directory is : " + dir.getName() );
		
		WeatherData.getWeatherData( dir );

        int[] minDate = WeatherData.getMinDate();

        for( int i = 0; i < 3; i++ )
        {
            System.out.println( "Keys: " + minDate[i] ); 
        }	
		
		//System.out.println( "Got data from .xml" );
		
		//XYSeriesCollection dataSet = WeatherData.getYearSetOfData( 15 ); // grab year 2010 weather data
		
	    XYSeriesCollection dataSet = WeatherData.getMonthSetOfData( 10, 9 );
		
		//TimeSeriesCollection dataSet = WeatherData.getWeekSetOfData( 14, 12, 5 ); // grab data from the first week in Feb. 2010
		
		/*
		System.out.println( "The number of days is: " + WeatherData.dayCount );
		
		System.out.println( "The number of weeks is: " + WeatherData.weekCount );
		
		System.out.println( "The number of months is: " + WeatherData.monthCount );
		
		System.out.println( "The number of years is: " + WeatherData.yearCount );
		*/
		
		
		//XYSeriesCollection dataSet = WeatherData.getDaySetOfData( 12, 2, 10 );  // grab jan. 1st 2010
		 //TimeSeriesCollection dataSet = WeatherData.getDaySetOfData( 10, 1, 1 );  // grab jan. 1st 2010
		
		//System.out.println( "Created XY coord sets for graphing" );		
		
		//  debug for getYearSetOfData
		XYSeries series0 = dataSet.getSeries("Temperature");
		//TimeSeries series0 = dataSet.getSeries("Temperature");
		
		if( series0.isEmpty() )
		{
			System.out.println("THERE IS NO WEEKLY DATA!" );
		}
		
		int count = 0;
        for (Object i : series0.getItems()) 
		{
            XYDataItem item = (XYDataItem) i;
            double x = item.getXValue();
            double y = item.getYValue();
			
			if( count < 50 )
			{
			    System.out.println(" Sample#: " + x + " Temp: " + y );
			}
			
			count += 1;
        }
		
	}
}
