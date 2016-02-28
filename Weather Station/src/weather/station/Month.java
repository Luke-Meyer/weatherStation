package weather.station;
import java.util.*;

public class Month
{
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	
	public Month()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.month = -1;
		this.year = -1;
	}
	
	public Month( Month tempMonth )
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		
		this.year = tempMonth.getYear();
		this.month = tempMonth.getMonth();
		
		this.dailySamples.putAll( tempMonth.getAllDaySamples() );
		

	}
	
	public int getMonth()
	{
		return this.month;
	}
	
	public void setMonth( int tempMonth)
	{
		this.month = tempMonth;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public void setYear( int tempYear )
	{
		this.year = tempYear;
	}
	
	public void setDailySamples( int dayKey, Day dayOfSamples ) //Day dayOfSamples, int dayKey )
	{
		
		this.dailySamples.put( dayKey, dayOfSamples );
	}
	
	public Hashtable<Integer, Day> getAllDaySamples()
	{
		return this.dailySamples;
	}
	public Day getDayofSamples( int dayKey)
	{
		return this.dailySamples.get(dayKey);
	}
	
	public void reset()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.month = -1;
		this.year = -1;
	}
	
	/*(public ArrayList<Day> getWeekOfSamples( int weekKey )
	{
		
	}*/
}
