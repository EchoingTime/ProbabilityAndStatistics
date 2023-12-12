package stocks;

import java.io.FileNotFoundException;

public class Main 
{
	public static void main (String [] args) throws FileNotFoundException
	{
		StockDataLoader load;
		load = new StockDataLoader("AMZN.csv");
		load.run(100000);			
	}
}
