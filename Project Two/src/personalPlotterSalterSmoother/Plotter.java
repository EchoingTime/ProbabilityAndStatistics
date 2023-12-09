package personalPlotterSalterSmoother;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
/**
 * Plotter Class - Using the y = ax^2 + bx + c formula, the quadratic equation, which creates a parabola. 
 * Will plot out into a CSV file to allow user to import file in excel and create a graph of the data. 
 * Accepts parameters to determine the range (e.g. -100 to 100) in constructor, another parameter in the 
 * plot method to determine number of points to plot, and parameters for the coefficients.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Plotter 
{
	private double lower;
	private double upper;
	
	/**
	 * Plotter Constructor - Has two parameters that determines the graph's lower and upper range
	 */
	public Plotter (double lowerRange, double upperRange)
	{
		this.lower = lowerRange;	
		this.upper = upperRange;
	}
	
	/**
	 * plot Method - Accepts a parameter which is an integer of points, via user, and creates random y variables 
	 * in a set range and puts it into the quadratic formula, solving for x to gather points. Other 
	 * parameters are the coefficients, provided by the user. Both variables are written into a csv file called 
	 * personalPlotsXY.csv. Throws an IOException if a problem is encountered.
	 * @param points - Given via user, number of points to generate
	 * @param a - Shape and direction of a parabola (negative for downward and positive for upward)
	 * @param b - Position of the vertex and the slope of the parabola
	 * @param c - y intercept of the parabola
	 * @referenced - Needed to see how to involve y in the formula, found formula b^2 - 4a(c-y)
	 */
	public void plot (int points, double a, double b, double c)
	{
		BufferedWriter writer;
		NumberFormat formater;
		Random rand;
		double x, y, otherX, temp;
	
		formater = new DecimalFormat("#0.000000");
		rand = new Random ();
		
		try 
		{
			writer = new BufferedWriter (new FileWriter ("personalPlotsXY.csv")); // Passing in a file for BufferWriter specific constructor, throws IOException if no file is found
			writer.write("x,y");
			writer.newLine();
			while (points != 0) // Will run until points value equals zero
			{
				y = rand.nextDouble(lower, upper);
				
				// Referenced equation 
				
				temp = b * b; // b^2
				temp = temp - 4 * a * c; // b^2 - 4ac
				temp = temp + 4 * a * y; // b^2 - 4ac + 4ay
				
				if (temp >= 0)
				{
					temp = Math.sqrt(temp);
					
					x = (-b + temp) / (2 * a); // Need to get both x values, as a parabola requires two x-coordinates for one y-coordinate
					otherX = (-b - temp) / (2 * a);
		 
					writer.write(formater.format(x) + "," + formater.format(y));
					writer.newLine();
					writer.write(formater.format(otherX) + "," + formater.format(y));
					writer.newLine();
				}
				else
				{
					System.out.printf("%ny-coordinate does not give a real solution and so both points have not been written to the file");
				}
				points--;
			}
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
	}
}
