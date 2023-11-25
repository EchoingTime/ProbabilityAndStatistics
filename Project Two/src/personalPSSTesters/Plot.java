package personalPSSTesters;

import java.io.FileNotFoundException;
import personalPlotterSalterSmoother.Plotter;
/**
 * Plot Class - Runs the Plotter Class and passes through range and amount of points to plot
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Plot 
{
	public static void main (String [] args) throws FileNotFoundException
	{
		Plotter solver;
		//			  (Lower Range, Upper Range)
		solver = new Plotter (10, 50); 
		//		   (Points to plot, a, b, c)
		solver.plot(100, 5, .2, -1); 
	}
}
