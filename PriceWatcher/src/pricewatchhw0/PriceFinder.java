package pricewatchhw0;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class PriceFinder {
	/** generates new price float formats it and returns it */
	public float getNewPrice()
	{
		
		double rn = ThreadLocalRandom.current().nextDouble(0,1000);
		DecimalFormat df = new DecimalFormat("#.##");
		float twoDig = Float.valueOf(df.format(rn));
		return twoDig;
		
	}

}
