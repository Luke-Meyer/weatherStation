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
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.xpath.*;
//import org.w3c.dom.*;

public class WeatherData
{
	private static Hashtable<Integer, Year> dictOfYears;  // data structure to hold the weather data
	
	public static String checkNullTag( String tag )  // used to check for null tags in a .xml file
	{
		String attr = tag;
		
		if(  attr== null )
		{
			attr = "50.0";
		}
		
		return attr;
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
		//XYSeries winddirection = new XYSeries("Winddirection" );
		//XYSeries windgust = new XYSeries("Windgust");
		//XYSeries windchill = new XYSeries("Windchill");
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		Year year = WeatherData.dictOfYears.get( yearIndex ); // grab the year of data in question
		
		Month mun = year.getMonthlySamplesByMonth( monthIndex );  // grab the month of data in question
		
		Day daa = mun.getDayofSamples( dayIndex );
		
		if( daa == null )  // if the day doesn't exist, populate some uninteresting data for JFreeChart
		{
			//System.out.println( "Day key: " + j );			
			//continue;
			//daa = mun.getDayofSamples( dayIndex - 1 ); // if the day doesn't exist, just duplicate the previous day
			
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
		
		//System.out.println( "There are " + weeks.size() + " in the month of " + mun.getMonth() );
		
		//int i = 1;
		
		int j = 0;
		
		//for( i = 1; i <= weeks.size(); i++ )  // for each of the weeks in this month
		//{
			//ArrayList<Day> week = weeks.get(i);
			
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
			
		//for( int dkey : days.keySet() )  // for each day in the month
		for( i = 1; i <= days.size(); i++ )
		{
			
			Day daa = days.get( i );//dkey );
			
			if( daa == null )  // if day doesn't exist, skip to next day lookup
				{
				    //System.out.println( "Day key: " + j );
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
			//System.out.println("------------------------------------------------");
            //System.out.println("Iterating or looping map using java5 foreach loop");
            //System.out.println("key: " + key + " value: " + loans.get(key));
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
				    //System.out.println( "Day key: " + j );
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
		
	
    public static void getWeatherData( File dir )//String dirName )//main( String[] args )
    {        	
		String dirName = dir.getName();
		
		File folder;
		
		if( dirName.isEmpty() )  // default behaviour if a null/empty string is supplied
		{
			 folder = new File( "..\\data\\" );
		}
		else // if there is a non-empty name, assumption is that there is also a valid absolute path
		{
			dirName = dir.getAbsolutePath();
			folder = new File( dirName );
		}	
		

		
		File[] listOfFiles = folder.listFiles( new FilenameFilter()
		{  // only get .xml files included in list of files to be processed in the directory
			public boolean accept(  File folder, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		});
		
		int totalDataFileCount = listOfFiles.length;  // get the total number of data files
		
		
		//System.out.println( "Number of .xml files: " + totalDataFileCount );
		
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
					
                    Document doc = builder.build( file );	    // parse XML tags
                    Element root = doc.getRootElement();	    // get root of XML tree
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
						
						tag = element.getChildText("humidity");  
						attr = WeatherData.checkNullTag( tag );							
                        item.setHumidity(Float.parseFloat(element.getChildText("humidity")));
						
						tag = element.getChildText("barometer");  
						attr = WeatherData.checkNullTag( tag );					
                        item.setBarometer(Float.parseFloat(element.getChildText("barometer")));
						
						tag = element.getChildText("windspeed");  
						attr = WeatherData.checkNullTag( tag );
                        item.setWindspeed(Float.parseFloat(element.getChildText("windspeed")));
						
						tag = element.getChildText("winddirection");  
						attr = WeatherData.checkNullTag( tag );						
                        item.setWinddirection(element.getChildText("winddirection"));
						
						tag = element.getChildText("windgust");  
						attr = WeatherData.checkNullTag( tag );
                        item.setWindgust(Float.parseFloat(element.getChildText("windgust")));
						
						tag = element.getChildText("windchill");  					
						attr = WeatherData.checkNullTag( tag );
                        item.setWindchill(Float.parseFloat(element.getChildText("windchill")));
						
						tag = element.getChildText("heatindex");  					
						attr = WeatherData.checkNullTag( tag );
                        item.setHeatindex(Float.parseFloat(element.getChildText("heatindex")));
						
						tag = element.getChildText("uvindex");  
						attr = WeatherData.checkNullTag( tag );						
                        item.setUvindex(Float.parseFloat(element.getChildText("uvindex")));
						
						tag = element.getChildText("rainfall");  
						attr = WeatherData.checkNullTag( tag );							
                        item.setRainfall(Float.parseFloat(element.getChildText("rainfall")));
						
						
						j += 1;
						
						if( j == numOSamples && i == totalDataFileCount - 1)  // if we are on the last sample of the last file
						{
							daySamples.add( item );
							
					        Day daa = new Day();
							daa.setSamples( daySamples );  // set the samples for the previous day
							daa.setDay( currDay );  // set the day field
							
							daa.setMonth( currMonth );
							
							daa.setYear( currYear );
							
							
							mun.setDailySamples( currDay, daa ); 
							
							mun.setMonth( currMonth );
							
							mun.setYear( currYear );
							
							mun.setWeeklySamples();
							
							//System.out.println( "The last month is " + currMonth );
							
							
								
							Month tempMonth = new Month( mun ); // create a new month in memory
							
							//tempMonth.setWeeklySamples();
										
							year.setMonthlySamples( currMonth, tempMonth );  // add month to year
							
							year.setYear( currYear );
							Year savedYear = new Year( year );
							
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
							
							
							mun.setDailySamples( prevDay, daa );  // set that day of samples in a month object
							//mun.setWeeklySamples();  // set weekly chunks of samples
																				
							
							if( prevMonth != currMonth )  // check to see if the next day is in a new month
							{							    							
							
								mun.setMonth( prevMonth );
								
								mun.setYear( prevYear );
								
								mun.setWeeklySamples();
								
								Month tempMonth = new Month( mun ); // create a new month in memory
								
								//tempMonth.setWeeklySamples();
									
								year.setMonthlySamples( prevMonth, tempMonth );  // add month to year
								
								mun.reset();
																
								
								if( prevYear != currYear )
								{									
									year.setYear( prevYear );
									Year savedYear = new Year( year );
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
		Year tempYear = dictOfYears.get(13);
		System.out.println( tempYear.getYear() );
		Hashtable<Integer, Month> dictofMonths = new Hashtable<Integer, Month>();
		dictofMonths.putAll(tempYear.getAllMonthlySamples() );
		System.out.println(dictofMonths);
		
		Hashtable<Integer, Day> dictOfDays = new Hashtable<Integer, Day>();
		Month tempMonth = dictofMonths.get(9);
		System.out.println( tempMonth.getMonth() );
		dictOfDays.putAll( tempMonth.getAllDaySamples() );
		System.out.println(dictOfDays);
		
		Hashtable<Integer, ArrayList<wItem> > dictOfSamples = new Hashtable<Integer, ArrayList<wItem>>();
		Day tempDay = dictOfDays.get(30);
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
		
		//System.out.println( "Got data from .xml" );
		
		//XYSeriesCollection dataSet = WeatherData.getYearSetOfData( 15 ); // grab year 2010 weather data
		
	    //XYSeriesCollection dataSet = WeatherData.getMonthSetOfData( 10, 1 );
		
		XYSeriesCollection dataSet = WeatherData.getWeekSetOfData( 14, 12, 5 ); // grab data from the first week in Feb. 2010
		
		//XYSeriesCollection dataSet = WeatherData.getDaySetOfData( 15, 2, 29 );  // grab jan. 1st 2010
		
		//System.out.println( "Created XY coord sets for graphing" );		
		
		//  debug for getYearSetOfData
		XYSeries series0 = dataSet.getSeries("Temperature");
		
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
