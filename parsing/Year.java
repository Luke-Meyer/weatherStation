


public class Year
{
	private int year;
	
	private Map<Integer, Month> monthlySamples;
	
	public Year()
	{
		monthlySamples = new Hashtable<Integer, Month > ();
		year = -1;
	}
	
	public Year( Year tempYear )
	{
		monthlySamples = new Hashtable<Integer, Month>();
		
		year = tempYear.getYear();
		
		monthlySamples.putAll( tempYear.getAllMonthlySamples() );
		
		
		/*
		monthlySamples.Clear();
		
		foreach(object key in tempYear.Keys)
        {
            monthlySamples.Add(key, tempYear[key]);
        }
		*/
		/*
		Set<String> keys = monthlySamples.keySet();
		
		
		for(String key: keys)
		{
			monthlySamples.Add( key, tempYear.get(key) );
			
        }\
		*/
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
	
	public Hashtable<Integer, Month> getAllMonthlySamples()
	{
		return monthlySamples;
	}
	
	public Month getMonthlySamplesByMonth( int monthKey )
	{
		return monthlySamples.get(monthKey);
	}
	

}