package carFileToCSV;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * Factory Class - Uses Car class and an array of strings to produce one thousand cars with randomized
 * car type, age, paint, and mileage and then saves it into a file and also reads that file that was last made
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Factory 
{
	Car aCar;
	String [] cars;
	String [] copy;
	
	/**
	 * Factory Constructor - Initializes Car object and cars array to contain one thousand cars
	 */
	public Factory ()
	{
		aCar = new Car ();
		cars = new String [1000];
		copy = new String [cars.length];
	}
	
	/**
	 * produceCars Method - Responsible for creating cars and assigning random types,
	 * age, color, and mileage and then saving it into an array of strings.
	 */
	public void produceCars ()
	{
		int count;
		count = 0;
		
		while (count < 1000)
		{
			aCar.setCarType(carTypeFactory());
			aCar.setYear(findYear());
			aCar.setColor(paint());
			aCar.setMiles(findMiles());
			cars[count] = aCar.getCarType() + ", " + aCar.getYear() + ", " +  aCar.getColor() + ", " + aCar.getMiles();
			count++;
		}
	}
	
	/**
	 * carTypeFactory Method - Assigns a random model to a car and returns the car type
	 * @return carType - The car model
	 */
	public String carTypeFactory ()
	{
		String [] types;
		Random rand;
		String carType;
		int randomType;
		
		// List of car Types --> a total of 9
		types = new String [9];
		types [0] = "SUV";
		types [1] = "Sedan";
		types [8] = "Sedan"; // Slightly more popular 
		types [2] = "Van";
		types [3] = "Truck";
		types [4] = "Sports Car";
		types [5] = "Convertible";
		types [6] = "Hatchback";
		types [7] = "Minivan";
		rand = new Random ();
		
		randomType = rand.nextInt(9);
		carType = types[randomType];
		
		return carType;
	}
	
	/**
	 * findYear Method - Randomly assigns an age, 1 through 50, to a car and returns the age
	 * @return year - Age of car 1 through 50
	 */
	public int findYear ()
	{
		Random rand;
		int year;
		
		rand = new Random ();
		year = rand.nextInt(51 + 1);
		
		return year;
	}
	
	/**
	 * paint Method - Random assigns a paint color to a car and returns a String of the 
	 * color
	 * @return color - Color of the car
	 */
	public String paint ()
	{
		String [] colors;
		Random rand;
		String color;
		int randomColor;
		
		colors = new String [8];
		colors [0] = "Red";
		colors [1] = "Red"; // Slightly more popular
		colors [2] = "Blue";
		colors [3] = "Green";
		colors [4] = "Orange";
		colors [5] = "Pink";
		colors [6] = "Black";
		colors [7] = "Grey";
		rand = new Random ();
		
		randomColor = rand.nextInt(8);
		color = colors[randomColor];
		
		return color;
	}
	
	/**
	 * findMiles Method - Retrieves the miles of a car
	 * @return miles - The total miles a car has recorded
	 */
	public int findMiles ()
	{
		Random rand;
		int miles;
		
		rand = new Random ();
		miles = rand.nextInt(250001);
		
		return miles;
	}
	
	/**
	 * toFile Method - Gets the cars array of strings and writes it into a CSV
	 * file.
	 * @throws FileNotFoundException
	 */
	public void toFile () throws FileNotFoundException
	{
		PrintWriter toFile;	// Declaring the PrintWriter object toFile
		toFile = new PrintWriter("car.csv");
		
		for (int index = 0; index < cars.length; index++)
		{
			toFile.printf("%s%n", cars[index]);
		}
		toFile.close();
	}
	
	/**
	 * copyFile Method - Copies a CSV file by using File and Scanner classes to read the file
	 * and save it into an array of strings.
	 * @throws FileNotFoundException
	 */
	public void copyFile () throws FileNotFoundException
	{
		File input;
		Scanner in;
		String data;
		int index;
		
		index = 0;
		input = new File("car.csv");
		in = new Scanner(input);
		
		while (in.hasNextLine())
		{
			data = in.nextLine();
			copy[index] = data;
			index++;
		}
		in.close();
	}
	
	/**
	 * printArray Method - Prints the car Array copy to ensure copyFile functions correctly
	 */
	public void printArray ()
	{
		for (int i = 0; i < copy.length; i++)
		{
			System.out.printf("%s%n", copy[i]);
		}
	}
}
