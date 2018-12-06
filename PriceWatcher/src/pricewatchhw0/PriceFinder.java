package pricewatchhw0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
//import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//import java.net.UnknownHostException;
//import java.text.DecimalFormat;
//import java.util.concurrent.ThreadLocalRandom;





import javax.swing.JProgressBar;
//import java.util.zip.GZIPInputStream;

public class PriceFinder extends Thread implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412669004686236287L;
	String amazon = "baseProductBuyingPrice";//data-asin-price";
	String bestbuy = "Your price for this item is $";
	String walmart = "prod-PriceHero";
	String target = "price";
	String dollar = "$";
	ItemList items;
	ItemView view;
	inOut file = new inOut();
	Item item;
	boolean unloadbar = false;
	/** generates new price float formats it and returns it */
	/*public float getNewPrice()
	{
		double rn = ThreadLocalRandom.current().nextDouble(0,1000);//(min value, max value)
		DecimalFormat df = new DecimalFormat("#.##");
		float twoDig = Float.valueOf(df.format(rn));
		return twoDig;
	}//*/
	//questions on design patterns
	//viewList.returnItem(i).repaint(); item.repaint();
	JProgressBar bar;
	public PriceFinder(ItemView view1,Item item1,JProgressBar bar1,ItemList itemsO)
	{
		view = view1;
		item = item1;
		bar = bar1;
		items = itemsO;
	}
	public PriceFinder()
	{
		
		
	}
	
	public void run()
	{
		//System.out.println("run!!!");
		Boolean hasFinished = false;
		for (int i = 0; (i<5)&& (hasFinished == false);i++)
		{
			//System.out.println("run2!!!");
			try {
				hasFinished = getPriceFromPage(view,item);
				if(item.doesHavePrice())
				{
					view.re(view.getGraphics());
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public Boolean getPriceFromPage(ItemView view,Item item) throws IOException
	{
		//System.out.println("called get price from page!!! :"+item.returnURL());
		String ur = item.returnURL();
		//https://stackoverflow.com/questions/1600291/validating-url-in-java
		URL url = null;
		try {
			url = new URL(ur);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bar.setValue(bar.getValue()+1);
    	//System.out.println("bar value :"+bar.getValue());
		URLConnection con = null;
		try {
			con = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bar.setValue(bar.getValue()+1);
    	//System.out.println("bar value :"+bar.getValue());
		
		InputStream is = null;
        try {
        	//try {
			//	Thread.sleep(2000);
			//} catch (InterruptedException e) {
			//	// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
        	
			is =con.getInputStream();
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

	        try {
	        	try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
	        	
				is =con.getInputStream();
	        	
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			
		}
    	bar.setValue(bar.getValue()+1);
    	//System.out.println("bar value :"+bar.getValue());
        //try {
		//	Thread.sleep(2000);
		//} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        
        if(ur.contains("bestbuy"))
        {
        	int i;
        	while(((line = br.readLine())!= null)&&(line.contains(bestbuy)==false))
        	{
        		//System.out.println(line);
        	}
        	i = line.indexOf(bestbuy);
        	i = line.indexOf(dollar,i);
        	
        	int end = line.indexOf('\\',i);
        	String priceString = "";
        	priceString = line.substring(i+1,end);
        	Float price = Float.parseFloat(priceString);
        	//System.out.println(i);
        	//System.out.println(line);
        	//System.out.printf("%.2f",price);
        	//System.out.println("");
        	item.setPrice(price);
        	//System.out.println("set the price!!! :"+item.returnCurrentPrice());
        	bar.setValue(bar.getValue()+1);
        	//System.out.println("bar value :"+bar.getValue());
        	view.repaint();
        	if(bar.getPercentComplete()>65)
        	{
        		for(int in = bar.getValue();in< bar.getMaximum();in++)
        		{
        			bar.setValue(bar.getValue()+1);
        		}
        		if(unloadbar == true)
        		{
        			bar.setStringPainted(false);
        		}
        		try {
					file.saveItemToFile(items);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        	return true;
        }
        else if(ur.contains("walmart"))
        {
        	int i;

        	while(((line = br.readLine())!= null)&&(line.contains(walmart)==false))
        	{
        		//System.out.println(line);
        	}
        	i = line.indexOf(walmart);
        	i = line.indexOf(dollar,i);
        	
        	int end = line.indexOf('>',i)-1;
        	String priceString = "";
        	priceString = line.substring(i+1,end);
        	Float price = Float.parseFloat(priceString);
        	//System.out.println(i);
        	//System.out.println(line);
        	//System.out.printf("%.2f",price);
        	//System.out.println("");
        	item.setPrice(price);
        	//System.out.println("set the price!!! :"+item.returnCurrentPrice());
        	bar.setValue(bar.getValue()+1);
        	//System.out.println("bar value :"+bar.getValue());
        	view.repaint();
        	if(bar.getPercentComplete()>65)
        	{
        		for(int in = bar.getValue();in< bar.getMaximum();in++)
        		{
        			bar.setValue(bar.getValue()+1);
        		}
        		if(unloadbar == true)
        		{
        			bar.setStringPainted(false);
        		}
        		try {
					file.saveItemToFile(items);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	return true;
        	//System.out.println(line);
        }
        else if(ur.contains("amazon"))
        {

        	while(((line = br.readLine())!= null)&&(line.contains(amazon)==false))
        	{
        		try {
        			Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//System.out.println(line);
        	}
        	int i = line.indexOf(amazon);
        	i = line.indexOf(dollar,i);
        	
        	int end = line.indexOf('"',i)-1;
        	String priceString = "";
        	priceString = line.substring(i+1,end);
        	Float price = Float.parseFloat(priceString);
        	
        	//System.out.println(i);
        	//System.out.println(line);
        	//System.out.printf("%.2f",price);
        	//System.out.println("");
        	item.setPrice(price);
        	//System.out.println("set the price!!! :"+item.returnCurrentPrice());
        	bar.setValue(bar.getValue()+1);
        	//System.out.println("bar value :"+bar.getValue());
        	view.repaint();
        	if(bar.getPercentComplete()>65)
        	{
        		for(int in = bar.getValue();in< bar.getMaximum();in++)
        		{
        			bar.setValue(bar.getValue()+1);
        		}
        		if(unloadbar == true)
        		{
        			bar.setStringPainted(false);
        		}
        		try {
					file.saveItemToFile(items);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	return true;
        	
        }
        else if(ur.contains("target"))
        {

        	while(((line = br.readLine())!= null)&&(line.contains(target)==false))
        	{
        		
        	}
        	int i = line.indexOf(target);
        	i = line.indexOf("price",i);
        	i = line.indexOf(":",i)+2;
        	int end = line.indexOf('"',i)-1;
        	String priceString = "";
        	priceString = line.substring(i,end+1);
        	Float price = Float.parseFloat(priceString);
        	
        	//System.out.println(i);
        	//System.out.println(line);
        	//System.out.printf("%.2f",price);
        	//System.out.println("");
        	item.setPrice(price);
        	view.paint(view.getGraphics());
        	//System.out.println("set the price!!! :"+item.returnCurrentPrice());
        	bar.setValue(bar.getValue()+1);
        	//System.out.println("bar value :"+bar.getValue());
        	view.repaint();
        	if(bar.getPercentComplete()>65)
        	{
        		for(int in = bar.getValue();in< bar.getMaximum();in++)
        		{
        			bar.setValue(bar.getValue()+1);
        		}
        		if(unloadbar == true)
        		{
        			bar.setStringPainted(false);
        		}
        		try {
					file.saveItemToFile(items);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	return true;
        	
        }
        
		
		
		
		
		return false;
		
		
	}


}
