package weather.station;
import java.util.*;

public class Year
{
	private int year;
	private Hashtable<Integer, Month> monthlySamples;
	
	public Year()
	{
		this.monthlySamples = new Hashtable<Integer, Month > ();
		this.year = -1;
	}
	
	public Year( Year tempYear )
	{
		this.monthlySamples = new Hashtable<Integer, Month>();
		
		this.year = tempYear.getYear();
		
		this.monthlySamples.putAll( tempYear.getAllMonthlySamples() );
		
	}
	
	
	public int getYear()
	{
		return this.year;
	}
	
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
	public void setMonthlySamples( int monthKey, Month monthOfSamples )//Month monthOfSamples, int monthKey )
	{
		
		this.monthlySamples.put( monthKey, monthOfSamples );
	}
	
	public Hashtable<Integer, Month> getAllMonthlySamples()
	{
		return this.monthlySamples;
	}
	
	public Month getMonthlySamplesByMonth( int monthKey )
	{
		return this.monthlySamples.get(monthKey);
	}
	
	public void reset()
	{
		this.monthlySamples = new Hashtable<Integer, Month >();
		this.year = -1;
	}

}
