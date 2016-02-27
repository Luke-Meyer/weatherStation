import java.util.*;

public class Month
{
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	
	public Month()
	{
		dailySamples = new Hashtable<Integer, Day>();
		month = -1;
		year = -1;
	}
	
	public Month( Month tempMonth )
	{
		dailySamples = new Hashtable<Integer, Day>();
		
		year = tempMonth.getYear();
		month = tempMonth.getMonth();
		
		dailySamples.putAll( tempMonth.getAllDaySamples() );
		
		
		/*
		Set<String> keys = dailySamples.keySet();
		
		
		for(String key: keys)
		{
			dailySamples.Add( key, tempMonth.get(key) );
			
        }
		
		/*
		foreach(object key in tempMonth.Keys)
        {
            dailySamples.Add(key, tempMonth[key]);
        }
		*/
		

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
	
	public void setDailySamples( int dayKey, Day dayOfSamples ) //Day dayOfSamples, int dayKey )
	{
		
		dailySamples.put( dayKey, dayOfSamples );
	}
	
	public Hashtable<Integer, Day> getAllDaySamples()
	{
		return dailySamples;
	}
	public Day getDayofSamples( int dayKey)
	{
		return dailySamples.get(dayKey);
	}
	
	/*(public ArrayList<Day> getWeekOfSamples( int weekKey )
	{
		
	}*/
}
