package stocks;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * StockDataLoader Class - Represents a Stock Bot that will use various methods such as obtaining data from
 * CSV files via loading into ArrayLists, calculating RSI, and determining whether to buy or sell stock shares.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced https://www.macroption.com/rsi-calculation/
 * @referenced https://stackoverflow.com/questions/33839008/storing-csv-file-contents-into-multiple-arrays
 */
public class StockDataLoader 
{
	private NumberFormat formater;

	private String fileName;	
	
	private ArrayList <String> date;
	private ArrayList <String> open;
	private ArrayList <String> high;
	private ArrayList <String> low;
	private ArrayList <String> close;
	private ArrayList <String> adjClose;
	private ArrayList <String> volume;
	
	private ArrayList <String> theRsi; // Relative Strength Index
	private ArrayList <String> ma; // Smoothed RSI
	
	private ArrayList <Double> networth;
	
	private double stock;
	
	private double balance;
	
	/**
	 * StockDataLoader Constructor - Initializes variables
	 */
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
		
		theRsi = new ArrayList <String> ();
		ma = new ArrayList <String> ();
		
		networth = new ArrayList <Double> ();
		
		formater = new DecimalFormat("#0.00");

		stock = 0;
		balance = 0;
	}
	
	/**
	 * run Method - Responsible for running the Program
	 * @param balance - Initializes balance
	 * @throws FileNotFoundException - File not found
	 */
	public void run (int balance) throws FileNotFoundException
	{
		PrintWriter toFile;
		int choice;
		double changeUp;
		double changeDown;
		changeUp = 0;
		changeDown = 0;
		choice = 0;
		copyFileToArrayList(false);
		toFile();
		setBalance(balance); 
		relativeStrengthIndex();
		
		fileName = "StockGraphRSIDaily.csv";
		copyFileToArrayList(true);
		fileName = "BotProcess.txt";
		toFile = new PrintWriter(fileName);
		// Will go until days = the most recent date (index 0)
		toFile.printf("Stock Bot's Action Per Date%n");
		for (int i = date.size() - 1; i >= 0; i--) // From oldest to most recent order
		{
			choice = tradeEvaulator(i);
			
			if (choice == 1) // Buy stocks
			{
				changeUp = (this.balance * .33) / Double.parseDouble(close.get(i)); // Stock increase
				changeDown = (this.balance * .33); // Taking out from balance
				
				stock += changeUp; // Number of stock
				this.balance -= changeDown;
				toFile.printf("(B on %s) Change: %.2f, Stock: %.2f, Balance: %.2f%n", date.get(i), changeUp, stock, this.balance);
				networth.add(this.balance + (stock * Double.parseDouble(close.get(i))));
			}
			else if (choice == -1) // Sell stocks
			{
				changeDown = (stock * .22);
				changeUp = (stock * .22) * Double.parseDouble(close.get(i));
				
				stock -= changeDown;
				this.balance += changeUp;
				toFile.printf("(S on %s) Change: %.2f, Stock: %.2f, Balance: %.2f%n", date.get(i), changeDown, stock, this.balance);
				networth.add(this.balance + (stock * Double.parseDouble(close.get(i))));
			}
			else
			{
				networth.add(this.balance + (stock * Double.parseDouble(close.get(i))));
				toFile.printf("(N on %s) Stock Bot Did Nothing%n", date.get(i));
			}
		}
		toFile.close();
		// Net Worth File
		
		fileName = "networth.csv";
		toFile = new PrintWriter(fileName);
		toFile.printf("Date, Net Worth%n");
		
		for (int i = 0; i < networth.size(); i++)
		{
			toFile.printf("%s, %.2f%n", date.get(((networth.size() - 1) - i)), networth.get(i));
		}
		toFile.close();
	}
	
	/**
	 * tradeEvaulator Method - Determines when to buy shares, sell shares, or do nothing. Utilizes
	 * moving average and the relative strength index to make its calculations
	 * @param index - The index of the specific date
	 * @return 0, if doing nothing, 1 if buying stocks, and -1 if selling stocks
	 * @throws FileNotFoundException
	 */
	public int tradeEvaulator (int index) throws FileNotFoundException
	{	
		if (balance > 0)
		{
			if (Double.parseDouble(ma.get(index)) < Double.parseDouble(theRsi.get(index)))
			{
				return 1; // Down, we buy! (33% of balance)
			}
			else if (Double.parseDouble(ma.get(index)) > Double.parseDouble(theRsi.get(index)))
			{
				if (stock > 0)
				{
					return -1; // Up, we sell!
				}
			}
			else
			{
				return 0;
			}
		}
		return 0; // Out of balance
	}
	
	/**
	 * relativeStrengthIndex Method - Calculates the RSI and writes the up movement, down movement, average up, average down,
	 * relative strength, and relative strength index to the stocksCloseRSI.csv file.
	 * @throws FileNotFoundException 
	 * @referenced https://www.macroption.com/rsi-calculation/
	 */
	public void relativeStrengthIndex () throws FileNotFoundException
	{
		PrintWriter toFile, toRSI;	// Declaring the PrintWriter object toFile
		toFile = new PrintWriter("stocksCloseRSI.csv");
		toRSI = new PrintWriter("rsi.txt"); // Will be used in MatLab to smooth
		String f;
		// Getting the closing prices of the last 15 days... In this case, the last 15 weeks
		double [] arrayClose;
		String [] dateArray;
		double [] upMove, downMove, avgU, avgD, rs, rsi;
		double chng;
		int n, stop;
		n = 15;
		
		chng = 0;

		arrayClose = new double [close.size()];
		dateArray = new String [close.size()];
		upMove = new double [close.size()];
		downMove = new double [close.size()];
		avgU =  new double [close.size()];
		avgD = new double [close.size()];
		
		rs = new double [close.size()];
		rsi = new double [close.size()];
		
		for (int i = 0; i < arrayClose.length; i++) // Gets close prices, starting from most recent
		{
			arrayClose[i] = Double.parseDouble(close.get((close.size() - 1) - i));
			dateArray[i] = (date.get((date.size() - 1) - i));
		}
		
		toFile.printf("Date, Close, Up, Down, Average Up, Average Down, RS, RSI, Smooth RSI%n");

		// STEP 1: Calculating Up Moves and Down Moves
		// Most recent is in index 0. Will get the increases and declines
		
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
		}
		
		// Step 2: Averaging the Advances and Declines
		// Using Simple Moving Method
		// N = 14
		
		avgU[0] = upMove[0];
		avgD[0] = downMove[0];

		for (int i = 1; i < upMove.length; i++)
		{
			avgU[i] = upMove[i];
			avgD[i] = downMove[i];
			
			stop = 0;
			
			for (int j = i + 1; j < upMove.length; j++)
			{
				if (stop == n)
				{
					break;
				}
				else
				{
					stop++;
					avgU[i] += upMove[j];
					avgD[i] += downMove[j];
				}
			}
			
			avgU[i] = avgU[i] / n; // Moved out of if (stop == n)
			avgD[i] = avgD[i] / n;
		}
				
		// Step 3: Calculating Relative Strength 
		// and
		// Step 4: Calculating the Relative Strength Index (RSI)
		
		for (int i = 0; i < upMove.length; i++)
		{
			if (avgD[i] == 0)
			{
				rsi[i] = 100;
			}
			else
			{
				rs[i] = avgU[i]/avgD[i];
				rsi[i] = 100 - 100 / (1 + rs[i]);	
			}
		}
				
		for (int i = 0; i < arrayClose.length; i++)
		{
			f = "" + dateArray[i] + ", " + formater.format(arrayClose[i]) + ", " + formater.format(upMove[i]) + ", " + formater.format(downMove[i]) + ", " + formater.format(avgU[i]) + ", " + formater.format(avgD[i]) + ", " + formater.format(rs[i]) + ", " + formater.format(rsi[i]);
			toFile.printf("%s%n", f);
			
			f= "" + formater.format(rsi[i]);
			toRSI.printf("%s ", f);
		}
		toFile.close();
		toRSI.close();
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
	 * @param rsi - True if the file contains the RSI and smooth RSI, false if not
	 * @referenced https://stackoverflow.com/questions/33839008/storing-csv-file-contents-into-multiple-arrays
	 * Had to figure out a better way that doing substrings to get the data from the file, as it became difficult after
	 * data and open prices. This new way is definitely more efficient and straight forward.
	 */
	public void copyFileToArrayList (boolean rsi) throws FileNotFoundException
	{
		File input;
		Scanner in;
		String [] dataLine;
		
		input = new File(fileName);
		in = new Scanner(input);
		
		in.nextLine(); // Skips the initial title of stock file
		if (rsi == false)
		{
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
		}
		else
		{
			date.clear();
			close.clear();
			while (in.hasNext())
			{
				dataLine = in.nextLine().split(",");
				
				date.add(dataLine[0]);
				close.add(dataLine[1]);
				theRsi.add(dataLine[7]);
				ma.add(dataLine[8]);
			}
		}
		
		in.close();
	}
	
	/**
	 * setBalance Method - Sets the new balance that the stock bot can spend
	 * @param newBalance - Bot's balance
	 */
	public void setBalance (double newBalance)
	{
		balance = newBalance;
	}
}
