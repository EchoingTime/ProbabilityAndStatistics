package apacheJFreeChartPSS;

import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
/**
 * TestAJPSS Class - Tests the classes utilizing JFreeChart and Apache Commons Math Libraries
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced www.javatpoint.com/jfreechart-scatter-chart
 */
public class TestAJPSS 
{
	public static void main (String [] args)
	{
		ArrayList <Double> x, y;
		Plotter plot;
		Salter salt;

		x = new ArrayList <Double> ();
		y = new ArrayList <Double> ();
		
		plot = new Plotter ("JFreeChart: A Scatter Chart - Plotter", 
				"JFreeChart: Parabola", 
				"Parabola Points", 
				"x-Coordinates", 
				"y-Coordinates",
				"personalPlotsXY.csv");
		
		// Plotting 
		SwingUtilities.invokeLater(() -> // Referenced
		{
			plot.setSize(1000, 1000);
			plot.setLocationRelativeTo(null);
			plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			plot.setVisible(true);
		});
		
		x = plot.getXCoord();
		y = plot.getYCoord();
		
		salt = new Salter ("JFreeChart: A Scatter Chart - Salted", 
				"JFreeChart: Parabola - Salted", 
				"Salted Parabola Points", 
				"x-Coordinates", 
				"y-Coordinates", x, y);
		
		// Salting 
		SwingUtilities.invokeLater(() -> 
		{
			salt.saltPlot(5, 25);
		});
		
	}
}
