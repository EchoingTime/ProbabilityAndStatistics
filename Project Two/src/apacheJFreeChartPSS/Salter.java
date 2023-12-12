package apacheJFreeChartPSS;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.WindowConstants;
/**
 * Salter Class - Salts a graph using personal salter methods and then plots the arrays using JFreeChart to short cut the process
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Salter
{
	private String titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis;
	private ArrayList <Double> x, y;

	/**
	 * Salter Constructor - Initializes the global variables
	 */
	public Salter (String titleOfWindow, String titleOfGraph, String xySeriesTitle, String xAxis, String yAxis, ArrayList <Double> x, ArrayList <Double> y) 
	{
		this.titleOfWindow = titleOfWindow;
		this.titleOfGraph = titleOfGraph;
		this.xySeriesTitle = xySeriesTitle;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * saltPlot method - Salts a graph using the parameters as the low and high bounds and then plots it via JFreeChart
	 * @param changeLow - Lower bound
	 * @param changeHigh - Upper bound
	 */
	public void saltPlot (double changeLow, double changeHigh)
	{
		Plotter plot;

		saltIt (changeLow, changeHigh);
		plot = new Plotter (titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis, "apacheSaltedXY.csv");
		plot.setSize(1000, 1000);
		plot.setLocationRelativeTo(null);
		plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		plot.setVisible(true);
	}
	
	/**
	 * saltIt Method - Responsible for salting the graph
	 * @param changeLow - Lower bounds
	 * @param changeHigh - Upper bounds
	 */
	private void saltIt (double changeLow, double changeHigh)
	{
		BufferedWriter writer;
		NumberFormat formater;
		Random rand;
		String aLine;
		double alter;
		
		rand = new Random ();
		formater = new DecimalFormat("#0.00000"); // Reformatting after the altered y value
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			writer = new BufferedWriter (new FileWriter ("apacheSaltedXY.csv"));
			
			aLine = "x, y";
			writer.write(aLine); // Writes the title of the file into a new salted file
			writer.newLine();
			
			for (int i = 0; i < x.size(); i++)
			{
				alter = rand.nextDouble(changeHigh - changeLow) + changeLow;
				y.set(i, y.get(i)+ alter);
				writer.write(""+ x.get(i) + ", " + formater.format(y.get(i))); 

				writer.newLine();
			}		
			writer.close();
		} 
		catch (FileNotFoundException e) // Handles the FileReader (file) statement
		{
			e.printStackTrace();
		}
		catch (IOException e) // Handles the readLine statement
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * getXCoordSalt Method - Retrieves the x-coordinates in class' ArrayList
	 * @return x - ArrayList of coordinate values
	 */
	public ArrayList <Double> getXCoordSalt ()
	{
		return x;
	}
	
	/**
	 * getYCoordSalt Method - Retrieves the salted y-coordinates in class' ArrayList
	 * @return y - ArrayList of coordinate values
	 */
	public ArrayList <Double> getYCoordSalt ()
	{
		return y;
	}
}
