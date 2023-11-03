package birthday;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Birthday Class - Determines the probability of any two people sharing a birthday
 * in the class (About 30 people)
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Birthday
{
    private ArrayList <Person> pplList;
    private int peopleAmount;
    
    public Birthday ()
    {
    	peopleAmount = 0;
    }
    
    /**
     * createPeople Method - Randomly generates people and their birthdays to save into an ArrayList of Person objects
     */
    public void createPeople ()
    {
    	Scanner user;
    	Person people;    	
    	Random rand;
    	int creating;
    	
    	int month, day;
    	
    	user = new Scanner (System.in);
    	rand = new Random ();
    	
    	System.out.printf("How many people are in your class?%n");
    	creating = user.nextInt();
    	peopleAmount += creating;
    	pplList = new ArrayList<Person>(creating);

    	while (creating != 0)
    	{
    		month = rand.nextInt(12) + 1;
    		
    		// Not counting for leap years!
    		
    		if (month == 2) // February
    		{
    			day = rand.nextInt(28) + 1;
    		}
    		else if (month == 4 || month == 6 || month == 9 || month == 11) // April, June, September, and November
    		{
    			day = rand.nextInt(30) + 1;
    		}
    		else // Rest of the months have 31 days
    		{
    			day = rand.nextInt(31) + 1;
    		}
    		
    		people = new Person (month, day);
    		pplList.add(people);
    		creating--;
    	}
    	
    	user.close();
    }
    
    /**
     * printProbabilityOfSharedBirthdays Method - Compares the ArrayList of Person objects and their data to tell if anyone
     * shares a birthday and then adds it to twoPeopleShare variable. 
     * @return probability - Probability of two people in a class of people sharing a birthday
     */
    public double printProbabilityOfSharedBirthdays ()
    { 
    	double twoPeopleShare, probability;
    	twoPeopleShare = 0;
    	
    	for (int i = 0; i < pplList.size(); i++)
    	{
    		for (int j = i + 1; j < pplList.size(); j++)
    		{
    			if ((pplList.get(i).getBirthMonth() == (pplList.get(j).getBirthMonth())) && (pplList.get(i).getBirthDay() == (pplList.get(j).getBirthDay())))
				{
    				pplList.remove(i);
					twoPeopleShare++;
				}
    		}
    	}
    	System.out.println (twoPeopleShare);
    	probability = twoPeopleShare / peopleAmount;
    	return probability;
    }
    
    /**
     * displayPeople Method - Prints out every person's birthday
     */
    public void displayPeople ()
    {
    	for (int i = 0; i < pplList.size(); i++)
    	{
    		System.out.printf("%s : %s%n", pplList.get(i).getBirthMonth(), pplList.get(i).getBirthDay());
    	}
    }
    
    /**
     * run Method - Responsible for running the program by calling the three methods to create the random people and their birthdays,
     * display the people and their birthdays, and print the sum of probabilities of given sets of people and two sharing a birthday and
     * dividing it by the amount of times the user ran the program.
     */
    public void run ()
    {
    	Scanner user;
    	user = new Scanner (System.in);
    	double probability;
    	int looper, amount;
    	probability = 0;
    	amount = 0;
    	
    	System.out.printf("How many times do you wish to run this program? : ");
    	looper = user.nextInt();
    	amount += looper;
    	
    	while (looper != 0)
    	{
    		createPeople();
        	displayPeople();
        	probability += printProbabilityOfSharedBirthdays();
        	looper--;
    	}
    	
    	System.out.printf("%nResult : %s%n", probability/amount);
    	user.close();
    }
    
}