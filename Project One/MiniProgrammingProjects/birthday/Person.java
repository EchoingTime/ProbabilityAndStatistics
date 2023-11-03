package birthday;
/**
 * Person Class - Responsible for creating Person (of people) objects
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Person
{
	private int birthMonth;
	private int birthDay;

	
    public Person (int birthMonth, int birthDay)
    {
    	this.birthMonth = birthMonth;
    	this.birthDay = birthDay;
    }
    
    public int getBirthMonth ()
    {
    	return birthMonth;
    }
    
    public int getBirthDay ()
    {
    	return birthDay;
    }
}