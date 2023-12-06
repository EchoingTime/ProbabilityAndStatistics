package stocks;

import java.io.FileNotFoundException;

public class Main 
{
	public static void main (String [] args) throws FileNotFoundException
	{
		StockDataLoader load;
		
		load = new StockDataLoader("AMZN.csv");
		
		load.copyFileToArrayList();
		load.toFile();
		
		load.setBalance(100000); // Sets my balance to $100,000 
		
	}
}
