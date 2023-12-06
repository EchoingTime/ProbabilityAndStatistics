package apacheJFreeChartPSS;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class TestAJPSS 
{
	public static void main (String [] args)
	{
		SwingUtilities.invokeLater(() -> // Referenced
		{
			Plotter plot;
			plot = new Plotter ("JFreeChart: A Scatter Chart - Plotter", 
					"JFreeChart: Parabola", 
					"Parabola Points", 
					"x-Coordinates", 
					"y-Coordinates",
					"personalPlotsXY.csv");
			plot.setSize(1000, 1000);
			plot.setLocationRelativeTo(null);
			plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			plot.setVisible(true);
		});
	}
}
