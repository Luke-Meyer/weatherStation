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

//import weather.station.wItem; //not sure if needed

public class ReadXML2
{
    public static void main( String[] args )
    {
        // check usage
        if ( args.length == 0 )
        {
            System.out.println( "Usage: java -cp .:jdom.jar ReadXML2 file.xml" );
            return;
        }

        // read and parse XML document
        SAXBuilder builder = new SAXBuilder();
        try
        {
            Document doc = builder.build( args[0] );	// parse XML tags
            Element root = doc.getRootElement();	    // get root of XML tree
            List<Element> weatherData = root.getChildren("weather");
            List<wItem> wList = new ArrayList<>();
            for (Element element : weatherData) {
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
                
                System.out.println(item);
            }
            
        }
        // JDOMException indicates a well-formedness error
        catch ( JDOMException e )
        {
            System.out.println( args[0] + " is not well-formed." );
            System.out.println( e.getMessage() );
        }
        catch ( IOException e )
        {
            System.out.println( e );
        }
    }
}
