

public class Month
{
	private month;
	private year;
	private Dictionary dailySamples;
	
	public Month()
	{
		dailySamples = new HastTable<int, Day>();
		month = -1;
		year = -1;
	}
	
	public Month( Month tempMonth )
	{
		dailySamples = new HastTable<int, Day>();
		
		year = tempMonth.getYear();
		month = tempMonth.getMonth();
		
		foreach(object key in tempMonth.Keys)
        {
            dailySamples.Add(key, tempMonth[key]);
        }
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
	
	public List<Day> getMonthOfSamples( int dayKey)
	{
		return dailySamples.get(dayKey);
	}
	
	public List<Day> getWeekOfSamples( int weekKey )
	{
		
	}
}