package pricewatchhw0;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
public class Item {
	Random rand = new Random();
	private double currentPrice;
	private String name = "TV";
	private double lastPrice = ThreadLocalRandom.current().nextDouble(0, 1000);
	DecimalFormat dF = new DecimalFormat("#.##");
	public String getNewPrice(){
		currentPrice = ThreadLocalRandom.current().nextDouble(0, 1000);
		return dF.format(currentPrice);
	}
	public String getName()
	{
		return name;
	}
	public String getFirstP(){

		return dF.format(lastPrice);
	}
	public String percentChange(){
		double pC = 0;
		pC = currentPrice - lastPrice;
		pC = pC / lastPrice;
		pC = pC * 100;
		return dF.format(pC);
	}
}
