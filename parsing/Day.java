import java.util.*;

public class Day
{
	private int day;
	private int month;
	private int year;
	private ArrayList<wItem> samples;
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
	
	public Day()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
	}
	
	public int getSampleCount()
	{
		return this.samples.size();
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
		
		ArrayList<wItem> samples = this.getSamples();
		
		for( wItem item: samples )  // for each sample
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
			
			temp = item.getWindspeed();  // get running sum of wind speed for the day
			
			windSum += temp;
			
			temp = item.getWindgust();
			
			if( temp > maxWind )
			{
				maxWind = temp;
				windDate = tempDate;
				windTime = tempTime;
			}
			
			rainSum += item.getRainfall();						
			
		}
		
		this.setHighTemp( maxTemp, maxTempDate, maxTempTime );  // set the max temp for day
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
	
	public void setMaxWind( float max, String date, String time)
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
		
		this.calcStats(); // set the stats for the day
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
