package apacheJFreeChartPSS;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.WindowConstants;

public class Salter
{
	private String titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis;
	private ArrayList <Double> x, y;

	public Salter (String titleOfWindow, String titleOfGraph, String xySeriesTitle, String xAxis, String yAxis, ArrayList <Double> x, ArrayList <Double> y) 
	{
		this.titleOfWindow = titleOfWindow;
		this.titleOfGraph = titleOfGraph;
		this.xySeriesTitle = xySeriesTitle;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.x = x;
		this.y = y;
	}
	
	private void saltIt (double changeLow, double changeHigh)
	{
		BufferedWriter writer;
		NumberFormat formater;
		Random rand;
		String aLine;
		double alter;
		
		rand = new Random ();
		formater = new DecimalFormat("#0.00"); // Reformatting after the altered y value
		
		try // Create BufferReader and read file, but if the file is not there, print stack trace
		{
			writer = new BufferedWriter (new FileWriter ("apacheSaltedXY.csv"));
			
			aLine = "x, y";
			writer.write(aLine); // Writes the title of the file into a new salted file
			writer.newLine();
			
			for (int i = 0; i < x.size(); i++)
			{
				alter = rand.nextDouble(changeHigh - changeLow) + changeLow;
				writer.write(""+ x.get(i) + ", " + formater.format(y.get(i) + alter)); 

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
	
	public void saltPlot (double changeLow, double changeHigh)
	{
		Plotter plot;

		saltIt (changeLow, changeHigh);
		plot = new Plotter (titleOfWindow, titleOfGraph, xySeriesTitle, xAxis, yAxis, "apacheSaltedXY.csv");
		plot.setSize(1000, 1000);
		plot.setLocationRelativeTo(null);
		plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		plot.setVisible(true);
	}
}