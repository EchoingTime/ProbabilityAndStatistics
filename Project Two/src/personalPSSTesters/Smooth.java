package personalPSSTesters;

import personalPlotterSalterSmoother.Smoother;
/**
 * Smooth Class - Tests the Smoother class and passes through total points
 * left and right of a y value to find average and divide by, saving as the new
 * y coordinate
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Smooth 
{
	public static void main (String [] args)
	{
		Smoother smooth;
		smooth = new Smoother ();
		//               Range around each specific y-coordinate
		smooth.smoothing(2);
	}
}
