package pricewatchhw0;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class PriceFinder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4412669004686236287L;

	/** generates new price float formats it and returns it */
	public float getNewPrice()
	{
		double rn = ThreadLocalRandom.current().nextDouble(0,1000);//(min value, max value)
		DecimalFormat df = new DecimalFormat("#.##");
		float twoDig = Float.valueOf(df.format(rn));
		return twoDig;
	}

}
