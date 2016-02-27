


public class Year
{
	private year;
	
	private Dictionary monthlySamples;
	
	public Year()
	{
		monthlySamples = new HashTable<int, Month > ();
		year = -1;
	}
	
	public Year( Month tempYear )
	{
		monthlySamples = new HashTable<int, Month>();
		
		year = tempYear.getYear();
		
		foreach(object key in tempYear.Keys)
        {
            monthlySamples.Add(key, tempYear[key]);
        }
	}
	
	
	public int getYear()
	{
		return year;
	}
	
	public void setYear( int tempYear )
	{
		year = tempYear;
	}
	
	public void setMonthlySamples( int monthKey, Mont monthOfSamples )//Month monthOfSamples, int monthKey )
	{
		
		monthlySamples.put( monthKey, monthOfSamples );
	}
	
	public ArrayList<Month> getMonthlySamples( int monthKey )
	{
		return monthlySamples.get(monthKey);
	}
	

}