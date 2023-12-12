package apacheJFreeChartPSS;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.WindowConstants;

import org.apache.commons.math4.legacy.stat.StatUtils;
/**
 * Smoother Class - Smoothes a salted graph
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referneced https://www.programcreek.com/java-api-examples/?class=org.apache.commons.math3.analysis.interpolation.LoessInterpolator&method=smooth
 * However, did not work as intended so I then referenced https://commons.apache.org/proper/commons-math/commons-math-docs/apidocs/org/apache/commons/math4/legacy/stat/StatUtils.html
 */
public class Smoother
{
	private String titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis;
	private ArrayList <Double> x, y;
	private int windowValue;

	/**
	 * Smoother Constructor - Initializes the global variables
	 */
	public Smoother (String titleOfWindow, String titleOfGraph, String xySeriesTitle, String xAxis, String yAxis, ArrayList <Double> x, ArrayList <Double> y, int windowValue) 
	{
		this.titleOfWindow = titleOfWindow;
		this.titleOfGraph = titleOfGraph;
		this.xySeriesTitle = xySeriesTitle;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.x = x;
		this.y = y;
		this.windowValue = windowValue;
	}
	
	/**
	 * smoothPlot Method - Responsible for running the smoothing methods and plotting the result
	 */
	public void smoothPlot ()
	{
		Plotter plot;
		
		smoothIt();
		plot = new Plotter (titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis, "apacheSmoothedXY.csv");
		plot.setSize(1000, 1000);
		plot.setLocationRelativeTo(null);
		plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		plot.setVisible(true);
	}
	
	/**
	 * smoothIt Method - Responsible for smoothing the salted graph by calling on apache's mean method
	 */
	private void smoothIt ()
	{
		BufferedWriter writer;
		double [] xA, yA;
		double average;
		String aLine;
		int begin, length;
		
		xA = new double [x.size()];
		yA = new double [y.size()];
		
				
		// Using my bubble sort from other class
		y = sort(y);
		
		for (int i = 0; i < x.size(); i++)
		{
			xA[i] = x.get(i);
			yA[i] = y.get(i);
		}
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			writer = new BufferedWriter (new FileWriter ("apacheSmoothedXY.csv"));
			
			aLine = "x, y";
			writer.write(aLine); // Writes the title of the file into a new salted file
			writer.newLine();
			
			for (int i = 0; i < y.size(); i++) // yData is all filled out, so smooth each and write it to new smoothed file 
			{				
				begin = Math.max(0, i - windowValue + 1); // Figured out via error: Exception in thread "AWT-EventQueue-0" org.apache.commons.math4.legacy.exception.NumberIsTooLargeException: subarray ends after array end
				length = Math.min(y.size() - 1, i);
				
				average = StatUtils.mean(yA, begin, length - begin + 1);
				yA[i] = average;

				writer.write(xA[i] + ", " + yA[i]);
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
	 * sort Method -  Sorting x coordinates - Added y coordinates in to swap alongside the x coordinates
	 * @param list - ArrayList of elements
	 * @return list - Sorted ArrayList of Elements
	 * @referenced Used from another class
	 */
	public ArrayList<Double> sort (ArrayList <Double> list)
	{
		double current, temp, swaps;
		boolean run;
		
		double tempY; // To keep y matching with x
		
		current = list.get(0); 
		
		swaps = 0;
		run = true;
		
		while (run == true)
		{
			for (int index = 1; index < list.size(); index++)
			{
				current = list.get(index - 1); 

				if (list.get(index) < current) 	// The next index's element after current index's element is greater than current's element
				{
					temp = list.get(index); 	// Saving the bigger element into temporary variable
					tempY = x.get(index);
					
					list.add(index - 1, temp); 	// adding bigger value at index
					x.add(index - 1, tempY);
					
					list.remove(index + 1); 	// removing the pushed variable copy
					x.remove(index + 1);
					swaps++;
				}
			}
			if (swaps > 0) // Tells the program when to end if swaps == 0, meaning no swaps were done!
			{
				swaps = 0;
			}
			else
			{
				run = false;
			}
		}
		return list;
	}
}
