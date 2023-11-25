package personalPSSTesters;

import java.io.FileNotFoundException;
import personalPlotterSalterSmoother.Salter;
/**
 * Salt Class - Runs the Salter class and passed through a range to alter the y values
 * in a CSV file by
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Salt 
{
	public static void main (String [] args) throws FileNotFoundException
	{		
		Salter solve;
		solve = new Salter ();
		
		solve.salting(5, 25);
	}
}
