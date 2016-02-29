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
	private float lowTemp;
	private String lTempDate;
	private float meanWind;
	private float maxWind;
	private String mWindDate;
	private float rainfall;
	
	public Day()
	{
		this.day = -1;
		this.month = -1;
		this.year = -1;
		this.samples = new ArrayList<wItem>();
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
	
	public void setHighTemp( float high, String date )
	{
		this.highTemp = high;
		this.hTempDate = date;
	}
	
	public String getHighTempDate()
	{
		return this.hTempDate;
	}
	
	public float getLowTemp()
	{
		return this.lowTemp;
	}
	
	public void setLowTemp( float high, String date )
	{
		this.lowTemp = high;
		this.lTempDate = date;
	}
	
	public String getLowTempDate()
	{
		return this.lTempDate;
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
	
	public void setMaxWind( float max, String date )
	{
		this.maxWind = max;
		this.mWindDate = date;
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
