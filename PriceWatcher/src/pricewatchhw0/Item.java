package pricewatchhw0;
// create interface for item in item view 
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/** */
public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -664831671178259193L;
	private float lastPrice = 0;
	private float currentPrice = 0;
	private float percent = 0;
	private String firstPrice ="";
	private String currentPriceString = "";
	private String percentString = "";
	private String name = "19IN 720p HDTV";
	private String url = "";
	private String date;
	private Boolean hasPrice = false;
	/** returns the first price */
	public String returnLastPrice() //returns
	{
		DecimalFormat df = new DecimalFormat("#.##");
		firstPrice = df.format(lastPrice);
		return firstPrice;
		
	}
	/** returns the current price */
	public Boolean doesHavePrice()
	{
		return hasPrice;
	}
	public String returnCurrentPrice()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		currentPriceString = df.format(currentPrice);
		return currentPriceString;
	}
	/** returns current formatted string of percent */
	public String returnPercentString()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		percentString = df.format(percent);
		if(percent < 0)
		{
			percentString = new StringBuffer(percentString).insert(1,"%").toString();
			
		}
		else
		{
			percentString = new StringBuffer(percentString).insert(0,"%").toString();
			
		}
		
		return percentString;
		
	}
	/** returns the float of percent  */
	public float returnPercent()
	{
		return percent;
	}
	/**  returns product name */
	public String returnName()
	{
		return name;
	}
	public void setDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");    
		Date resultdate = new Date(System.currentTimeMillis());
		date = sdf.format(resultdate);
	}
	public String returnDate()
	{
		return date;
	}
	/** sets price from input of float */
	public void setPrice(float price)
	{
		if(lastPrice == 0)
		{
			lastPrice = price;
		}
		currentPrice = price;
		if(currentPrice > lastPrice)
		{
			percent = (currentPrice-lastPrice)/lastPrice*100;
		}
		else
		{
			percent = ((lastPrice-currentPrice)/lastPrice)*100;
			percent = percent *-1;
		}
		
	}
	/** returns string of url */

	public void setName(String newName)
	{
		name = newName;
	}
	public void setURL(String newURL)
	{
		url = newURL;
	}
	public String returnURL()
	{
		return url;
	}
}
