package personalPlotterSalterSmoother;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Smoother Class - Responsible for loading a salted data csv file and smoothing it
 * by looping through the y values and replacing it with the average of the y values around it.
 * Will use a range to smooth the data. E.g., if range is 10 then take 10 points left and right of the index
 * and add them up and divide. Will use as many points to left and right of the value as possible to save as
 * the new y coordinate. Will take a personalSaltedXY.csv file and save the new smoothed data into 
 * a personalSmoothedXY.csv file
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Smoother 
{
	private ArrayList <String> yData;
	private ArrayList <String> xData;

	/**
	 * smoothing Method - Accepts a windowValue integer, which represents the amount the user wishes to get the average
	 * values around a specific point by, if within bounds of that specific point. BufferedReader will read personalSaltedXY.csv
	 * file inside a while loop, while the file's next line is not null, and will split the values by the comma and save x coordinates
	 * into an ArrayList and y coordinates into another. After this is completed, will go through a for loop that will first write the x
	 * coordinates into the new smoothed file, and then call the average private method to find the average of the values around a specific point,
	 * and then the average will be retrieved, formatted, and written into the new file. The statements are surrounded by a try-catch statement 
	 * to ensure there is a file and no errors occur with the reading and writing.
	 * @param windowValue - The range the user wishes to have for points to find averages by
	 */
	public void smoothing (int windowValue)
	{
		NumberFormat formater;
		BufferedReader reader;
		BufferedWriter writer;
		String aLine;
		String [] data;
		double newY;
		
		newY = 0;
		yData = new ArrayList <String> ();
		xData = new ArrayList <String> ();
		formater = new DecimalFormat("#0.00"); // Reformatting after the altered y value

		try 
		{
			reader = new BufferedReader (new FileReader("personalSaltedXY.csv"));
			writer = new BufferedWriter (new FileWriter("personalSmoothedXY"));
			aLine = reader.readLine(); // Gets the title (indicators, "x,y" of the file)
			
			writer.write(aLine);
			writer.newLine();
			
			while((aLine = reader.readLine()) != null)
			{
				data = aLine.split(",");
				
				for (int i = 0; i < data.length; i++)
				{
					if (i % 2 != 0)
					{
						yData.add(data[i]); // Gets all the y values in its own ArrayList
					}
					else
					{
						xData.add(data[i]); // Gets all the x values in its own ArrayList
					}
				}
			}
			
			for (int i = 0; i < yData.size(); i++) // yData is all filled out, so smooth each and write it to new smoothed file (x coordinates will be added before all this)
			{
				writer.write(xData.get(i));
				
				newY = average(windowValue, i); // Calls private method
				writer.write("," + formater.format(newY));
				writer.newLine();
			}
			
			writer.close();
			reader.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * average Method - A private method called via the smoothing method. Responsible for using two parameters,
	 * windowValue, the range of points to collect around a given y coordinate, and index, the specific y coordinate to
	 * work and alter on. Finds the lower points to add up, if within legal bounds of the overall ArrayList of y coordinates,
	 * and the upper legal points to add up, finds sum and divides it by the total points added up. Returns the average
	 * to be written to the new csv file via the smoothing method.
	 * @param windowValue - The range the user wishes to have for points to find averages by
	 * @param index - Specific y coordinate that will be used to find average of around
	 * @return average - The average of the specific y coordinate
	 */
	private double average (int windowValue, int index)
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

			if (high >= yData.size())
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
			average += Double.parseDouble(yData.get(low));
			toDivideBy++;
			low++;
		}

		while (high != index)
		{
			average += Double.parseDouble(yData.get(high));
			toDivideBy++;
			high--;
		}
				
		average = average / toDivideBy;

		return average;
	}
}
