package weather.station;

import java.util.*;

public class Year
{
	private int year;
	
	private Hashtable<Integer, Month> monthlySamples;
	
	private float meanTemp;
	private float highTemp;
	private String hTempDate;
	private String hTempTime;
	private float lowTemp;
	private String lTempDate;
	private String lTempTime;
	private float meanWind;
	private float maxWind;
	private String mWindDate;
	private String mWindTime;
	private float rainfall;
	
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
	
	public void calcStats()
	{
		float tempSum = 0.0f;
		float sampCount = 0.0f;
		float windSum = 0.0f;
		float maxTemp = -50.0f;
		float minTemp = 1000.0f;
		String maxTempDate = "";
		String maxTempTime = "";
		String minTempDate = "";
		String minTempTime = "";
		float maxWind = 0.0f;
		String windDate = "";
		String windTime = "";
		float rainSum = 0.0f;
		
		
		Hashtable<Integer, Month> months = this.getAllMonthlySamples(); // grab the months in the current year
		
		
		for( int i = 1; i <= months.size(); i++ )  // for each month in the year
		{
			Month mun = months.get(i);//mkey);  // grab a single month in the list of months
			
			if( mun == null )  // if the month doesn't exist, skip it
			{
				continue;
			}
			
			Hashtable<Integer, Day> days = mun.getAllDaySamples();  // get all daily samples in the month
		
			for( int j = 1; j <= days.size(); j++ )  // for each day 
			{
				Day daa = days.get( j );//dkey );
			
				if( daa == null )  // if day doesn't exist, skip to next day lookup
				{
					continue;
				}
				
				ArrayList<wItem> samples = daa.getSamples();
			
				for( wItem item: samples )  //for each sample
				{
					sampCount += 1.0;  // count for upcoming mean calculation
			
					float temp = item.getTemperature();  // get relevant sample info to save later
					String tempDate = item.getDate();
					String tempTime = item.getTime();
			
					tempSum += temp;  // get running sum of temp for the day
			
					if( temp > maxTemp )  // find max temp for day
					{
						maxTemp = temp;
						maxTempDate = tempDate;
						maxTempTime = tempTime;
					}
			
					if( temp < minTemp )  // find min temp for day
					{
						minTemp = temp;
						minTempDate = tempDate;
						minTempTime = tempTime;
					}
			
					temp = item.getWindspeed();  // get running sum of wind speed for the month
			
					windSum += temp;
			
					if( temp > maxWind )
					{
						maxWind = temp;
						windDate = tempDate;
						windTime = tempTime;
					}
			
					rainSum += item.getRainfall();						
			
				}			
			
			}
		}
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for year
			
		this.setLowTemp( minTemp, minTempDate, minTempTime );  // set low temp
		
		this.setMaxWind( maxWind, windDate, windTime );  // set max wind speed
		
		this.setMeanWind( (float) windSum / sampCount );  // set avg wind speed
		
		this.setMeanTemp( (float) tempSum / sampCount ); // set avg temp speed 
		
		this.setRainfall( rainSum );  // set accumulated precipitation		
		
		
		
	}
	
	public float getMeanTemp()
	{
		return this.meanTemp;
	}
	
	public void setMeanTemp( float avg )
	{
		this.meanTemp = avg;
	}
	
	public float getHighTemp()
	{
		return this.highTemp;
	}
	
	public void setHighTemp( float high, String date, String time )
	{
		this.highTemp = high;
		this.hTempDate = date;
		this.hTempTime = time;
	}
	
	public String getHighTempDate()
	{
		return this.hTempDate;
	}
	
	public String getHighTempTime()
	{
		return this.hTempTime;
	}
	
	public float getLowTemp()
	{
		return this.lowTemp;
	}
	
	public void setLowTemp( float high, String date, String time )
	{
		this.lowTemp = high;
		this.lTempDate = date;
		this.lTempTime = time;
	}
	
	public String getLowTempDate()
	{
		return this.lTempDate;
	}
	
	public String getLowTempTime()
	{
		return this.lTempTime;
	}
	
	public float getMeanWind()
	{
		return this.meanWind;
	}
	
	public void setMeanWind( float avg )
	{
		this.meanWind = avg;
	}
	
	public float getMaxWind()
	{
		return this.maxWind;
	}
	
	public void setMaxWind( float max, String date, String time )
	{
		this.maxWind = max;
		this.mWindDate = date;
		this.mWindTime = time;
	}
	
	public String getMaxWindDate()
	{
		return this.mWindDate;
	}
	
	public String getMaxWindTime()
	{
		return this.mWindTime;
	}
	
	public float getRainfall()
	{
		return this.rainfall;
	}
	
	public void setRainfall( float rain )
	{
		this.rainfall = rain;
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
