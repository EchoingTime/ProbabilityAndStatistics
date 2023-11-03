package carFileToCSV;
import java.io.FileNotFoundException;
/**
 * TestFactory Class - Responsible for running the program of Factory class
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestFactory 
{	
	public static void main (String [] args) throws FileNotFoundException
	{
		Factory production;
		production = new Factory ();
		// USING FACTORY TO CONSTRUCT A CAR, SAVE CAR INTO DATA STRUCTURE
		production.produceCars();
		production.toFile();
		production.copyFile();
		production.printArray();
	}
}
