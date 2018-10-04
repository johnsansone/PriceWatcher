package pricewatchhw0;

import java.text.DecimalFormat;
public class Item {
	private float lastPrice = 0;
	private float currentPrice = 0;
	private float percent = 0;
	private String firstPrice ="";
	private String currentPriceString = "";
	private String percentString = "";
	private String name = "TV";
	private String url = "";
	public String returnLastPrice()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		firstPrice = df.format(lastPrice);
		return firstPrice;
		
	}
	public String returnCurrentPrice()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		currentPriceString = df.format(currentPrice);
		return currentPriceString;
	}
	public String returnPercent()
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
	public String returnName()
	{
		return name;
	}
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
	public String returnURL()
	{
		return url;
	}
}
