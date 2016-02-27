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
//package weather.station;
import org.jdom2.*;
import java.io.File;
import java.io.FileInputStream;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;

//import weather.station.wItem; //not sure if needed

public class WeatherData
{
    public static void main( String[] args )
    {        
        File folder = new File("./");
        File[] listOfFiles = folder.listFiles();
		
		int fileCount = 0;
		int currYear = -1;
		int prevYear = -1;
		int prevMonth = -1;
		int currMonth = -1;
		int excessMonths = 0;
		Dictionary dictOfYears = new HashTable< int, Year >();
		Month mun = new Month();  
		Year year = new Year();
		
		
		//Map<int, Map> yearData = new HashMap< int, Map>();
		//Dictionary yearData = new HashTable();
		
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
                    
                System.out.print(file + "\n");			
				
				
                
                // read and parse XML document
                SAXBuilder builder = new SAXBuilder();
                try
                {
					int currDay = -1;
					int prevDay = -1;
                    Document doc = builder.build( file );	    // parse XML tags
                    Element root = doc.getRootElement();	    // get root of XML tree
                    List<Element> xmlContent = root.getChildren("weather");
								
					
					ArrayList<wItem> daySamples = new ArrayList<wItem>();  // will contain all samples for one day
					
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
								
								Month tempMonth = new Month( mun); // create a new month in memory
								year.setMonthlySamples( prevMonth, mun );  // add month to year
								
								if( prevYear != currYear )
								{									
									year.setYear( prevYear );
									
									Year savedYear = new Year( year );
									
									dictOfYears( prevYear, savedYear );  
									
									prevYear = currYear;
								}
								
								prevMonth = currMonth;
							}					
							
							
							
							daySamples.clear();  // remove previous day's samples
							
							daySamples.add( item );  // add first sample to new day
							
							prevDay = currDay;  // shift flags
						
							
						}
						else
						{
							daySamples.add( item ); // if we are still processing the same day, keep adding samples 
						}
						
						
						
						
                    }
									
					fileCount += 1;
                    
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
        // Get size and display.
	    //int count = weatherData.size();
	    //System.out.println("Count: " + count);

	    // Loop through elements.
	    /*for (int i = 0; i < weatherData.size(); i++) {
	        int value = weatherData.get(i);
	        System.out.println("Element: " + value);
	    }*/
    }
	
	
}
