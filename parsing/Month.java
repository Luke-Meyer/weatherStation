import java.util.*;

public class Month
{
	private int month;
	private int year;
	private Hashtable<Integer, Day> dailySamples;
	//private int daysInmonth;
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
	
	public int getNumberOfDays()
	{
		return this.dailySamples.size();
	}
	
	
	
	public void setWeeklySamples()
	{
		int numOdays = this.dailySamples.size();  // number of days in the month
		
		ArrayList<Day> daze = new ArrayList<Day>();
		
		int weekIndex = 1;
		
		//System.out.println( "THere are : " + numOdays + "number of days in the month");

		
		for( int i = 1; i <= numOdays; i++ )  // for each day in the month
		{
			Day daa = this.dailySamples.get(i);
			
			daze.add( daa );  // add the day to our week 
			
			boolean flag = false;  // flag tells us if there is a fifth week in the month
			
			if( i % 7 == 0 ) // if we have processed seven days, consider this a week
			{
				//System.out.println( "THere are : " + daze.size() + "number of days in week" + weekIndex );

				ArrayList<Day> temp = new ArrayList<Day>(daze);
				this.weeklySamples.put( weekIndex, temp );
				
				//System.out.println( "THere are : " + temp.size() + "number of days in temp week" + weekIndex );
			
				daze.clear(); 
				weekIndex += 1;
				flag = true;
			}
			
			if( i == numOdays && !flag )//weekIndex == 5 && numOweeks == 5 ) // if we have processed the last day in the fifth week of a month
			{
				// add the last incomplete week to the week list
				this.weeklySamples.put( weekIndex, daze );
				//System.out.println( "THere are : " + daze.size() + "number of days in week" + weekIndex + "LAST WEEK IN MONTH!");
				
			}												
		}	

         //System.out.println( "THere are " + this.weeklySamples.size() + " weeks of samples in " + this.getMonth() );		
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
