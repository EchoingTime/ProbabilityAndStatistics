package stocks;

import java.io.File;
import apacheAndJFreeChartPSS.Smoother;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StockDataLoader 
{
	private Smoother smooth;
	private String fileName;	
	
	private ArrayList <String> date;
	private ArrayList <String> open;
	private ArrayList <String> high;
	private ArrayList <String> low;
	private ArrayList <String> close;
	private ArrayList <String> adjClose;
	private ArrayList <String> volume;
	
	private double balance;
	private int shares;

	public StockDataLoader (String fileName)
	{
		smooth = new Smoother ();
		this.fileName = fileName;
		
		date = new ArrayList <String> ();
		open = new ArrayList <String> ();
		high = new ArrayList <String> ();
		low = new ArrayList <String> ();
		close = new ArrayList <String> ();
		adjClose = new ArrayList <String> ();
		volume = new ArrayList <String> ();

		balance = 10000;
		shares = 0;
	}
	
	public void smoothData ()
	{
		
	}
	
	/**
	 * toFile Method - Gets the several ArrayLists of strings and writes it into a CSV
	 * file.
	 * @throws FileNotFoundException
	 */
	public void toFile () throws FileNotFoundException
	{
		PrintWriter toFile;	// Declaring the PrintWriter object toFile
		ArrayList <String> dataTransfer;

		toFile = new PrintWriter("stocks.csv");
		dataTransfer = new ArrayList <String> ();
		
		toFile.printf("Date, Open, High, Low, Close, Adj Close, Volume, %n");

		for (int index = 0; index < open.size(); index++)
		{
			dataTransfer.add(date.get(index) + ", " + open.get(index) + ", " + high.get(index) + ", " + low.get(index) + ", " + close.get(index) + ", " + adjClose.get(index) + ", " + volume.get(index));
		} 
		
		while (!(dataTransfer.isEmpty()))
		{
			toFile.printf("%s%n", dataTransfer.get(0));
			dataTransfer.remove(0);
		}
		
		toFile.close();
	}
	
	/**
	 * copyFileToArrayList Method - Copies a CSV file by using File and Scanner classes to read the file
	 * and save it into several ArrayLists of strings.
	 * @throws FileNotFoundException
	 * @referenced https://stackoverflow.com/questions/33839008/storing-csv-file-contents-into-multiple-arrays
	 * Had to figure out a better way that doing substrings to get the data from the file, as it became difficult after
	 * data and open prices. This new way is definitely more efficient and straight forward.
	 */
	public void copyFileToArrayList () throws FileNotFoundException
	{
		File input;
		Scanner in;
		String [] dataLine;
		
		input = new File(fileName);
		in = new Scanner(input);
		
		in.nextLine(); // Skips the initial title of stock file
		
		while (in.hasNext()) 
		{
			dataLine = in.nextLine().split(",");
			
			date.add(dataLine[0]);
			open.add(dataLine[1]);
			high.add(dataLine[2]);
			low.add(dataLine[3]);
			close.add(dataLine[4]);
			adjClose.add(dataLine[5]);
			volume.add(dataLine[6]);
		}
		in.close();
	}
	
	public double getBalance ()
	{
		return balance;
	}
	
	public double getShares ()
	{
		return shares;
	}
	
	public void setBalance (double newBalance)
	{
		balance = newBalance;
	}
	
	public void setShares (int shares)
	{
		this.shares = shares;
	}
}
