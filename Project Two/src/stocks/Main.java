package stocks;

import java.io.FileNotFoundException;
/**
 * Main Class - Passes through a Stock Historical CSV File and passes through a balance
 * for StockDataLoader class
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0) 
 */
public class Main 
{
	public static void main (String [] args) throws FileNotFoundException
	{
		StockDataLoader load;
		load = new StockDataLoader("AMZNDaily.csv");
		load.run(100000);			
	}
}
