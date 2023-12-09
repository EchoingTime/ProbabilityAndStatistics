package apacheJFreeChartPSS;

import org.apache.commons.math4.legacy.analysis.interpolation.LoessInterpolator;
import org.apache.commons.math4.legacy.analysis.polynomials.PolynomialSplineFunction;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.WindowConstants;
/**
 * Smoother Class - Follows the same mistake as the previous smoother in the personal package
 * @author Dante Anzalone
 * @version
 * @referneced https://www.programcreek.com/java-api-examples/?class=org.apache.commons.math3.analysis.interpolation.LoessInterpolator&method=smooth
 * Referenced portions are commented out
 */
public class Smoother
{
	private String titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis;
	private ArrayList <Double> x, y;
	private int windowValue;

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
	
	// https://www.programcreek.com/java-api-examples/?class=org.apache.commons.math3.analysis.interpolation.LoessInterpolator&method=smooth
	// Used link, fixed error by seeing it needed to be sorted (had check if sorted methods)
	private void smoothIt ()
	{
		/*
		LoessInterpolator interpolator; // Referenced 
		PolynomialSplineFunction spline;
		*/
		BufferedWriter writer;
		double [] xA, yA; //, smoother; 
		String aLine;
		
		/*
		double bandwidth;
		
		bandwidth = .25;
		interpolator = new LoessInterpolator (bandwidth, 2);
		*/
		
		xA = new double [x.size()];
		// smoother = new double [x.size()];
		yA = new double [y.size()];
				
		// Using my bubble sort from other class
		y = sort(y);
		
		for (int i = 0; i < x.size(); i++)
		{
			xA[i] = x.get(i);
			yA[i] = y.get(i);
		}
		
		/*
		interpolator.smooth(xA, yA);
		spline = interpolator.interpolate(xA, yA);
		smoother = spline.getKnots();
		*/
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			writer = new BufferedWriter (new FileWriter ("apacheSmoothedXY.csv"));
			
			aLine = "x, y";
			writer.write(aLine); // Writes the title of the file into a new salted file
			writer.newLine();
			
			/*
			for (int i = 0; i < x.size(); i++)
			{
				writer.write(""+ xA[i] + ", " + smoother[i]); 

				writer.newLine();
			}		
			*/
			
			for (int i = 0; i < y.size(); i++) // yData is all filled out, so smooth each and write it to new smoothed file 
			{				
				yA[i] = average(windowValue, i, yA);
				
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
	
	// Used from personal smoother
	private double average (int windowValue, int index, double [] array)
	{
		// Should have an array list of y coordinates
		int low, high; 
		double average, toDivideBy;
		boolean runLow, runHigh;
		
		toDivideBy = 0;
		average = 0;
		runLow = true;
		runHigh = true;
		low = index - windowValue;
		high = index + windowValue;
			
		// Check if it is within the bounds
		while (runLow == true || runHigh == true)
		{
			if (low < 0)
			{
				low++;
			}
			else
			{
				runLow = false; // Within bounds
			}

			if (high >= array.length)
			{
				high--;
			}
			else
			{
				runHigh = false;
			}
		}
		
		// Should have legal index bounds to add and find averages of
		while (low != index)
		{
			average += (array[low]);
			toDivideBy++;
			low++;
		}

		while (high != index)
		{
			average += (array[high]);
			toDivideBy++;
			high--;
		}
		average = average / toDivideBy + 1;

		return average;
	}
	
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
	
	// Sorting x coordinates - Added y coordinates in to swap alongside the x coordinates
	// Used sorter from previous class
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
