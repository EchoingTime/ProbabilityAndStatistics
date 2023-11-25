package personalPlotterSalterSmoother;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Salter Class - Accepts a csv file that holds x and y values.
 * The program will loop through the y values and add or subtract 
 * a random number from it, the choice is provided via user. 
 * The random numbers are provided via a range e.g. (-10, 10) to randomly
 * pick numbers from this range and either add or subtract from. User can include
 * negative upper and lower to subtract only or positive ranges to add only
 * Data is saved into a personalSaltedXY.csv file
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced https://www.youtube.com/watch?v=-Aud0cDh-J8&ab_channel=AlexLee To convert PrintWriter to a more effective BufferReader and BufferWriter
 */
public class Salter 
{
	/**
	 * salting Method - Alters the y values of a given personalPlotsXY.csv file by the parameter values
	 * changeLow and changeHigh, provided via Salt.java class. Will write the changed values in the 
	 * personalSaltedXY.csv file. Utilizes BufferedReader, BufferedWriter, Random, and NumberFormat classes.
	 * Throws FileNotFoundException if no file is found and IOException if readLine encounters an issue 
	 * @param changeLow - The lower range
	 * @param changeHigh - The upper value
	 */
	public void salting (double changeLow, double changeHigh)
	{
		BufferedReader reader;
		BufferedWriter writer;
		NumberFormat formater;
		Random rand;
		String aLine;
		String [] data; // To store all values inside the file
		double alter;
		
		rand = new Random ();
		formater = new DecimalFormat("#0.00"); // Reformatting after the altered y value
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			reader = new BufferedReader (new FileReader ("personalPlotsXY.csv"));
			writer = new BufferedWriter (new FileWriter ("personalSaltedXY.csv"));
			
			aLine = reader.readLine(); // Gathers the first line of the file, the indicators 
			writer.write(aLine); // Writes the title of the file into a new salted file
			writer.newLine();
			
			while ((aLine = reader.readLine()) != null) //	Storing a line until no more lines are left in the file
			{
				alter = rand.nextDouble(changeHigh - changeLow) + changeLow; // Gets a random value through changeLow and changeHigh
				data = aLine.split(","); // Separating the x and y coordinates
				writer.write(data[0]); // Gets all Values in first column!
				
				alter = alter + Double.parseDouble(data[1]); // Gets the value in the second column, in this case the y coordinate, and adds it to the altering value
				writer.write("," + formater.format(alter));
				writer.newLine();
			}
			writer.close();
			reader.close();
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
}
