
import java.util.*;

public class Year
{
	private int year;
	private Hashtable<Integer, Month> monthlySamples;
	
	public Year()
	{
		monthlySamples = new Hashtable<Integer, Month > ();
		year = -1;
	}
	
	public Year( Year tempYear )
	{
		monthlySamples = new Hashtable<Integer, Month>();
		
		year = tempYear.getYear();
		
		monthlySamples.putAll( tempYear.getAllMonthlySamples() );
		
	}
	
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear( int tempYear )
	{
		year = tempYear;
	}
	
	public void setMonthlySamples( int monthKey, Month monthOfSamples )//Month monthOfSamples, int monthKey )
	{
		
		monthlySamples.put( monthKey, monthOfSamples );
	}
	
	public Hashtable<Integer, Month> getAllMonthlySamples()
	{
		return monthlySamples;
	}
	
	public Month getMonthlySamplesByMonth( int monthKey )
	{
		return monthlySamples.get(monthKey);
	}
	

}
