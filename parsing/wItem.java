//package weather.station;

public class wItem {
    private String date;
    private int year;
    private int month;
    private int day;
    private String time;
    private float temperature;
	//private int temperature;
    private float humidity;
    private float barometer;
    private float windspeed;
    private String winddirection;
    private float windgust;
    private float windchill;
    private float heatindex;
    private float uvindex;
    private float rainfall;
     
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
        
        String temp = date.trim();
        String delims = "[/]";
        String[] token = temp.split(delims);
        
		
		this.month = Integer.valueOf(token[0]);
		this.day = Integer.valueOf(token[1]);
		this.year = Integer.valueOf(token[2]);
    }    
    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }    
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public float getTemperature() {
        return temperature;
    }
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    public float getHumidity() {
        return humidity;
    }
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
    public float getBarometer() {
        return barometer;
    }
    public void setBarometer(float barometer) {
        this.barometer = barometer;
    }
    public float getWindspeed() {
        return windspeed;
    }
    public void setWindspeed(float windspeed) {
        this.windspeed = windspeed;
    }
    public String getWinddirection() {
        return winddirection;
    }
    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }
    public float getWindgust() {
        return windgust;
    }
    public void setWindgust(float windgust) {
        this.windgust = windgust;
    }
    public float getWindchill() {
        return windchill;
    }
    public void setWindchill(float windchill) {
        this.windchill = windchill;
    }
    public float getHeatindex() {
        return heatindex;
    }
    public void setHeatindex(float heatindex) {
        this.heatindex = heatindex;
    }
    public float getUvindex() {
        return uvindex;
    }
    public void setUvindex(float uvindex) {
        this.uvindex = uvindex;
    }
    public float getRainfall() {
        return rainfall;
    }
    public void setRainfall(float rainfall) {
        this.rainfall = rainfall;
    }
      
    @Override
    public String toString() {
        return "wItem:: date="+this.date + " Time: " +this.time + "Temperature: " + this.temperature; //+" time=" + this.time + " temperature=" + this.temperature;
    }
     
}
