package carFileToCSV;
/**
 * Car Class - Responsible for creating Car objects and the car data
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Car 
{
	private String carType, color;
	private int year, miles;
	
	public Car ()
	{

	}
	
	/**
	 * setCarType Method - Sets the car model
	 * @param carType - Car's Model
	 */
	public void setCarType (String carType)
	{
		this.carType = carType;
	}
	
	/**
	 * setColor Method - Sets the color of the car
	 * @param color - Car's color
	 */
	public void setColor (String color)
	{
		this.color = color;
	}
	
	/**
	 * setYear Method - Sets the age of the car
	 * @param year - Car's Age
	 */
	public void setYear (int year)
	{
		this.year = year;
	}
	
	/**
	 * setMiles Method - Sets the mileage of the car
	 * @param miles - Car's Mileage
	 */
	public  void setMiles (int miles)
	{
		this.miles = miles;
	}
	
	/**
	 * getCarType Method - Retrieves the car model
	 * @return carType - Car's model
	 */
	public String getCarType ()
	{
		return carType;
	}
	
	/**
	 * getColor Method - Retrieves the color of the car
	 * @return color - Car's color
	 */
	public String getColor ()
	{
		return color;
	}
	
	/**
	 * getYear Method - Retrieves the age of the car
	 * @return year - Car's age
	 */
	public int getYear ()
	{
		return year;
	}
	
	/**
	 * getMiles Method - Retrieves the mileage of the car
	 * @return miles - Car's mileage
	 */
	public int getMiles ()
	{
		return miles;
	}
}
