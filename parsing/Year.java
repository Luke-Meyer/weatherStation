


public class Year
{
	private int year;
	
	private Dictionary monthlySamples;
	
	public Year()
	{
		monthlySamples = new HashTable<Integer, Month > ();
		year = -1;
	}
	
	public Year( Month tempYear )
	{
		monthlySamples = new HashTable<Integer, Month>();
		
		year = tempYear.getYear();
		
		monthlySamples.Clear();
		
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
	
	public void setMonthlySamples( int monthKey, Month monthOfSamples )//Month monthOfSamples, int monthKey )
	{
		
		monthlySamples.put( monthKey, monthOfSamples );
	}
	
	public Month getMonthlySamples( int monthKey )
	{
		return monthlySamples.get(monthKey);
	}
	

}