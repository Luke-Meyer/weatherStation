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
	private String prevailingWindDir;
	
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
	
	public String getPrevailingWindDir()
	{
		return this.prevailingWindDir;
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
		
		String north = "N";
		int nCount = 0;
		String east = "E";
		int eCount = 0;
		String south = "S";
		int sCount = 0;
		String west = "W";
		int wCount = 0;
		String nEast = "NE";
		int neCount = 0;
		String sEast = "SE";
		int seCount = 0;
		String sWest = "SW";
		int swCount = 0;
		String nWest = "NW";
		int nwCount = 0;
		
		int maxCount = -1;
		
		
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
			
			rainSum += item.getRainfall();	// accumulate rainfall
			
		    String windDir = item.getWinddirection();
			
            // calculate prevailing wind direction
			if( north.equals( windDir ) )
			{
				nCount += 1;
				
				if( nCount > maxCount )
				{
					maxCount = nCount;
					this.prevailingWindDir = "N";
				}
			}
			else if( east.equals( windDir ) )
			{
				eCount +=1;
				
				if( eCount > maxCount )
				{
					maxCount = eCount;
					this.prevailingWindDir = "E";
				}
			}
			else if( south.equals( windDir ) )
			{
				sCount += 1;
				
				if( sCount > maxCount )
				{
					maxCount = sCount;
					this.prevailingWindDir = "S";
				}
			}
			else if( west.equals( windDir ) )
			{
				wCount +=1;
				
				if( wCount > maxCount )
				{
					maxCount = wCount;
					this.prevailingWindDir = "W";
				}
			}
			else if( nEast.equals( windDir ) )
			{
				neCount += 1;
				
				if( neCount > maxCount )
				{
					maxCount = neCount;
					this.prevailingWindDir = "NE";
				}
			}
			else if( sEast.equals( windDir ) )
			{
				seCount += 1;
				
				if( seCount > maxCount )
				{
					maxCount = seCount;
					this.prevailingWindDir = "SE";
				}
			}
			else if( sWest.equals( windDir ) )
			{
				swCount += 1;
				
				if( swCount > maxCount )
				{
					maxCount = swCount;
					this.prevailingWindDir = "SW";
				}
			}
			else if( nWest.equals( windDir ) )
			{
				nwCount += 1;
				
				if( nwCount > maxCount )
				{
					maxCount = nwCount;
					this.prevailingWindDir = "NW";
				}
			}			
			
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
