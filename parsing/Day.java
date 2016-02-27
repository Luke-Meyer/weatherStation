

public class Day
{
	private int day;
	private int month;
	private int year;
	private ArrayList<wItem> samples;
	
	public Day()
	{
		day = -1;
		month = -1;
		year = -1;
		samples = new ArrayList<wItem>();
	}
	
	
	public int getDay()
	{
		return day;
	}
	
	public void setDay( int tempDay )
	{
		day = tempDay;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setMonth( int tempMonth)
	{
		month = tempMonth;
	}
	
	public int getYear()
	{	
		return year;
	}
	
	public void setYear( int tempYear )
	{
		year = tempYear;
	}
	
	public void setSamples( List<wItem> tempSamples )
	{
		samples = new ArrayList( tempSamples );
	}
	
	public ArrayList<wItem> getSamples()
	{
		return samples;
	}
}