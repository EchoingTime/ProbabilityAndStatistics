package apacheJFreeChartPSS;

// Referenced imports via link in class comment
import java.awt.Color;  
import javax.swing.JFrame;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.xy.XYDataset;  
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Plotter Class - Uses JFreeChart to plots x and y-coordinates via a given csv file supplied by the user
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced www.javatpoint.com/jfreechart-scatter-chart
 */
public class Plotter extends JFrame
{
	private static final long serialVersionUID = 6294689542092367723L; // Auto generated/required/referenced
	private XYDataset dataPoints; // Creates the data set
	private XYPlot plot; // Creates the plot
	private JFreeChart chart; // Creates the JFreeChart
	private ChartPanel panel; // Creates the ChartPanel
	private ArrayList <Double> x, y; // ArrayList of x and y points
	
	/**
	 * Plotter Constructor - Takes in parameters that determines titles, labels, and the folder containing the x and y-coordinates
	 * Also initializes ArrayLists and calls private methods.
	 */
	public Plotter (String titleOfWindow, String titleOfGraph, String xySeriesTitle, String xAxis, String yAxis, String dataCSV)
	{
		super(titleOfWindow); // Title of the window sent to superclass, JFrame
		x = new ArrayList <Double> ();
		y = new ArrayList <Double> ();
		
		gatherCoordinates(dataCSV); // Gathers coordinates from the personalPlotsXY.csv file
		
		dataPoints = createDataset(xySeriesTitle); // Initializes the data set
		chart = ChartFactory.createScatterPlot(titleOfGraph, xAxis, yAxis, dataPoints); // Initializes and produces the chart
		
		plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color (51, 51, 51)); // Retrieved from https://teaching.csse.uwa.edu.au/units/CITS1001/colorinfo.html
		
		panel = new ChartPanel (chart);
		setContentPane(panel);
	}
	
	/**
	 * XYDataset Method - A private method that is responsible for adding x and y-coordinates to the XYSeries
	 * data set.
	 * @param xySeriesTitle - Part of the legend
	 * @return dataPoint - All the plotted points
	 */
	private XYDataset createDataset (String xySeriesTitle)
	{
		XYSeries series;
		XYSeriesCollection dataPoint;
		
		dataPoint = new XYSeriesCollection ();

		series = new XYSeries (xySeriesTitle);

		for (int i = 0; i < x.size(); i++) // Will add values of every x and y point in both ArrayLists (ArrayList sizes are the same)
		{
			series.add(x.get(i), y.get(i));
		}
		
		dataPoint.addSeries(series);
	    
		return dataPoint;
	}
	
	// Referenced Initial Salter class
	/**
	 * gatherCoordinates Method - Referenced initializes Salter class in personalPlotterSalterSmoother package.
	 * Reads given file via parameter and moves x values and y values in respective ArrayLists
	 * @param dataCSV - The file provided by the user
	 */
	public void gatherCoordinates (String dataCSV)
	{
		BufferedReader reader;
		String aLine;
		String [] data; // To store all values from the file
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			reader = new BufferedReader (new FileReader (dataCSV));
			
			aLine = reader.readLine(); // Gathers the first line of the file, the indicators/title
			
			while ((aLine = reader.readLine()) != null) //	Storing a line until no more lines are left in the file
			{
				data = aLine.split(","); // Separating the x and y coordinates
				x.add(Double.parseDouble(data[0])); // Gets the value in the first column, in this case the x coordinate, and adds it to the ArrayList of x doubles
				y.add(Double.parseDouble(data[1])); // Gets the value in the second column, in this case the y coordinate, and adds it to the ArrayList of y doubles
			}
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
	
	/**
	 * getXCoord Method - Retrieves the x-coordinate ArrayList
	 * @return x - ArrayList of x-coordinates
	 */
	public ArrayList <Double> getXCoord ()
	{
		return x;
	}
	
	/**
	 * getYCoord Method - Retrieves the y-coordinate ArrayList
	 * @return y - ArrayList of y-coordinates
	 */
	public ArrayList <Double> getYCoord ()
	{
		return y;
	}
}
