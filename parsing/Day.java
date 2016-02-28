import java.util.*;

public class Day
{
	private int day;
	private int month;
	private int year;
	private ArrayList<wItem> samples;
	
	public Day()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
	}
	
	
	public int getDay()
	{
		return this.day;
	}
	
	public void setDay( int tempDay )
	{
		this.day = tempDay;
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
	
	public void setSamples( List<wItem> tempSamples )
	{
		this.samples = new ArrayList( tempSamples );
	}
	
	public ArrayList<wItem> getSamples()
	{
		return this.samples;
	}
	
	public void reset()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
	}
}
