package stocks;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class StockDataLoader 
{
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
	
	public void run (int balance) throws FileNotFoundException
	{
		copyFileToArrayList();
		toFile();
		setBalance(balance); 
		relativeStrengthIndex();
	}
	
	/**
	 * relativeStrengthIndex Method - Calculates the RSI
	 * @referenced https://www.macroption.com/rsi-calculation/
	 */
	public void relativeStrengthIndex ()
	{
		NumberFormat formater;
		String f;
		// Getting the closing prices of the last 15 days... In this case, the last 15 weeks
		double [] arrayClose;
		double [] upMove, downMove;
		double chng;
		double avgU, avgD;
		double relativeStrength, relativeStrengthIndex;
		
		avgU = 0;
		avgD = 0;
		chng = 0;
		formater = new DecimalFormat("#0.00");

		arrayClose = new double [16];
		upMove = new double [15];
		downMove = new double [15];
		
		for (int i = 0; i < arrayClose.length; i++) // Gets the recent 15 close prices
		{
			arrayClose[i] = Double.parseDouble(close.get((close.size() - 1) - i));
		}
		
		System.out.printf("Close (Top Most Recent), Up, Down%n");

		// STEP 1: Calculating Up Moves and Down Moves
		// Most recent is in index 0. Will get the increases and declines in the last 15 days
		
		for (int i = 0; i < arrayClose.length - 1; i++)
		{
			chng = arrayClose[i] - arrayClose[i+1];
			
			if (chng > 0)
			{
				upMove[i] = chng;
			}
			else // if (chng == 0 || chng < 0)
			{
				upMove[i] = 0;
			}
			
			if (chng < 0)
			{
				downMove[i] = Math.abs(chng);
			}
			else // if (chng == 0 || chng > 0)
			{
				downMove[i] = 0;
			}
			
			f = "" + formater.format(arrayClose[i]) + ", " + formater.format(upMove[i]) + ", " + formater.format(downMove[i]);
			System.out.printf("%s%n", f);
		}
		
		// Step 2: Averaging the Advances and Declines
		// Using Simple Moving Average
		// N = 15
		
		for (int i = 0; i < upMove.length; i++)
		{
			avgU += upMove[i];
			avgD += downMove[i];
		}
		
		avgU /= 15;
		avgD /= 15;
				
		// Step 3: Calculating Relative Strength
		relativeStrength = avgU/avgD;

		// Step 4: Calculating the Relative Strength Index (RSI)
		
		relativeStrengthIndex = 100 - 100 / (1 + relativeStrength);
		
		f = "Average Up: " + formater.format(avgU) + "\nAverage Down: " + formater.format(avgD) + "\nRS: " + formater.format(relativeStrength) + "\nRSI: " + formater.format(relativeStrengthIndex);
		System.out.printf("%n%s", f);

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
