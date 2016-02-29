import java.util.*;

public class Month
{
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	private Hashtable<Integer, ArrayList<Day> > weeklySamples;
	
	public Month()
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		this.month = -1;
		this.year = -1;
	}
	
	public Month( Month tempMonth )
	{
		this.dailySamples = new Hashtable<Integer, Day>();
		this.weeklySamples = new Hashtable<Integer, ArrayList<Day> >();
		
		this.year = tempMonth.getYear();
		this.month = tempMonth.getMonth();
		
		this.dailySamples.putAll( tempMonth.getAllDaySamples() );
		this.weeklySamples.putAll( tempMonth.getAllWeekSamples() );

        		

	}
	
	public void setWeeklySamples()
	{
		int numOdays = this.dailySamples.size();  // number of days in the month
		
		int numOfullys = numOdays / 7; // get the number of whole weeks in the month
		
		int numOweeks = -1;
		
		if( numOdays % 7 == 0 )//- ( numOfullys * 7 ) == 0)  // if there are exactly four weeks in the month
		{
			numOweeks = 4;
		}
		else  // if there aren't exactly four weeks in the month, there must be five
		{
			numOweeks = 5;
		}
				
		ArrayList<Day> daze = new ArrayList<Day>();
		
		int weekIndex = 1;
		
		for( int i = 1; i <= numOdays; i++ )  // for each day in the month
		{
			Day daa = this.dailySamples.get(i);
			
			daze.add( daa );  // add the day to our week 
			
			if( i % 7 == 0 ) // if we have processed seven days, consider this a week
			{
				ArrayList<Day> temp = new ArrayList<Day>(daze);
				this.weeklySamples.put( weekIndex, temp );
				daze.clear(); 
				weekIndex += 1;
			}
			
			if( i == numOdays && weekIndex == 5 && numOweeks == 5 ) // if we have processed the last day in the fifth week of a month
			{
				// add the last incomplete week to the week list
				this.weeklySamples.put( weekIndex, daze );			
			}												
		}					
	}
	
	
	public Hashtable<Integer, ArrayList<Day> > getAllWeekSamples()
	{
		return this.weeklySamples;
	}
	
	public ArrayList<Day> getWeekOfSamples( int weekKey )
	{
		return this.weeklySamples.get( weekKey );
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
