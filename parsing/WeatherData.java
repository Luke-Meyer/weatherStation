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

public class WeatherData
{
	private static Hashtable<Integer, Year> dictOfYears;  // data structure to hold the weather data
	
	public 
	
    public static void getWeatherData( File dir )//String dirName )//main( String[] args )
    {        
		
		String dirName = dir.getName();
		
		File folder;
		
		System.out.println( "HEREERERERERERER" );
		
		if( dirName.isEmpty() )  // default behaviour if a null/empty string is supplied
		{
			 folder = new File( "..\\data\\" );
		}
		else // if there is a non-empty name, assumption is that there is also a valid absolute path
		{
			System.out.println("The name of the directory is: " + dirName );
			dirName = dir.getAbsolutePath();
			folder = new File( dirName );
		}
		//File folder = new File( "..\\data\\" );
        //File[] listOfFiles = folder.listFiles();
		
		System.out.println( "made it past dirName check" );
		
		File[] listOfFiles = folder.listFiles( new FilenameFilter(){  // only get .xml files included in list of files to be processed in the directory
			public boolean accept(  File folder, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		});
		
		int totalDataFileCount = listOfFiles.length;  // get the total number of data files
		int completeYearsOfData = totalDataFileCount / 12;  // get the total number of complete years of data to be processed
		int excessMonths = totalDataFileCount - ( 12 * completeYearsOfData ); // get the number of remaining month.xml data files to make up last incomplete year
		
		
		//System.out.println( "Number of .xml files: " + totalDataFileCount );
		
		int fileCount = 0;
		int currYear = -1;
		int prevYear = -1;
		int prevMonth = -1;
		int currMonth = -1;
		
	
		
		//Hashtable<Integer, Year> dictOfYears = new Hashtable<Integer, Year>();		
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
                        item.setTemperature(Float.parseFloat(element.getChildText("temperature")));
                        item.setHumidity(Float.parseFloat(element.getChildText("humidity")));
                        item.setBarometer(Float.parseFloat(element.getChildText("barometer")));
                        item.setWindspeed(Float.parseFloat(element.getChildText("windspeed")));
                        item.setWinddirection(element.getChildText("winddirection"));
                        item.setWindgust(Float.parseFloat(element.getChildText("windgust")));
                        item.setWindchill(Float.parseFloat(element.getChildText("windchill")));
                        item.setHeatindex(Float.parseFloat(element.getChildText("heatindex")));
                        item.setUvindex(Float.parseFloat(element.getChildText("uvindex")));
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
								
							Month tempMonth = new Month( mun ); // create a new month in memory
										
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
							
													
							
							if( prevMonth != currMonth )  // check to see if the next day is in a new month
							{
							    							
							
								mun.setMonth( prevMonth );
								mun.setYear( prevYear );
								
								
								Month tempMonth = new Month( mun ); // create a new month in memory
								
									
								year.setMonthlySamples( prevMonth, tempMonth );  // add month to year
								
								mun.reset();
								
								//}
								
								/*
								if( i == totalDataFileCount - 1 )  // if we are processing the last year, consisting of incomplete month data, include it 
								{
									year.setYear( prevYear );
									Year savedYear = new Year( year );
									dictOfYears.put( prevYear, savedYear ); 									
									
								}
								*/
								
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
        System.out.println(dictOfYears);
		Year tempYear = dictOfYears.get(13);
		System.out.println( tempYear.getYear() );
		Hashtable<Integer, Month> dictofMonths = new Hashtable<Integer, Month>();
		dictofMonths.putAll(tempYear.getAllMonthlySamples() );
		System.out.println(dictofMonths);
		
		Hashtable<Integer, Day> dictOfDays = new Hashtable<Integer, Day>();
		Month tempMonth = dictofMonths.get(12);
		System.out.println( tempMonth.getMonth() );
		dictOfDays.putAll( tempMonth.getAllDaySamples() );
		System.out.println(dictOfDays);
		
		Hashtable<Integer, ArrayList<wItem> > dictOfSamples = new Hashtable<Integer, ArrayList<wItem>>();
		Day tempDay = dictOfDays.get(31);
		System.out.println( tempDay.getDay() );
		dictOfSamples.put( 1,  tempDay.getSamples() );
		System.out.println( dictOfSamples );
		
		
		
    }
	
	public static void main( String[] args )
	{
		File dir = new File("");
		//File dir = new File("..\\data\\sub\\");
		
		System.out.println( "The name of the directory is : " + dir.getName() );
		
		WeatherData.getWeatherData( dir );
	}
}
