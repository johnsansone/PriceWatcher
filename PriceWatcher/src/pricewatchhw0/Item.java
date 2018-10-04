package pricewatchhw0;

public class Item {
	private float lastPrice = 0;
	private float currentPrice = 0;
	private float percent = 0;
	private String name = "TV";
	private String url = "";
	public float returnLastPrice()
	{
		return lastPrice;
		
	}
	public float returnCurrentPrice()
	{
		return currentPrice;
	}
	public float returnPercent()
	{
		return percent;
		
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
	}
	public String returnURL()
	{
		return url;
	}
}
