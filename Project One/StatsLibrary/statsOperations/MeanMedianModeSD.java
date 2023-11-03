package statsOperations;
import java.util.ArrayList;
/**
 * MeanMedianModeSD - A class that manipulates a set of elements via an ArrayList
 * by utilizing the following operations: getMean, getMedian, getMode, 
 * getStandardDeviation, and getVariance.
 * 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class MeanMedianModeSD
{    
	// Global instance variable result, a type Double
	private double result;
	
    /**
     *  Default constructor of the StatsLibrary class
     */
    public MeanMedianModeSD ()
    {
        
    }
    
    /**
     * Constructor of the StatsLibrary class that accepts a parameter String input
     * 
     * @param input - A String type used for notes
     */
    public MeanMedianModeSD (String input)
    {
        
    }
    
    /**
     * getMean Method - Finds the average of a set of elements
     * 
     * @param userInputNumbers - An ArrayList containing elements of type Double
     * @return result - The average or mean of the set of elements given
     */
    public double getMean (ArrayList<Double> userInputNumbers)
    {
        double sum;
        sum = 0;
        
        // Using a for each loop to add up every element in the sum variable
        for (double singleElement : userInputNumbers)
        {
            sum += singleElement;
        }
        
        // Storing a value for clarity, dividing sum by the size of elements in the ArrayList
        result = sum / userInputNumbers.size();
        return result;
    }
    
    /**
     * getMedian Method - Finds the element in the middle of a set of
     * elements. If the set is even, then take the two middle variables,
     * add them together, and then divide the sum by two.
     * 
     * @param userInputNumbers - An ArrayList containing elements of type Double
     * @return result - The median of the set of elements given
     */
    public double getMedian (ArrayList<Double> userInputNumbers)
    {
        int index, index2;
        
        // If the list has an odd amount of numbers
        if (userInputNumbers.size() % 2 != 0)
        {
        	index = userInputNumbers.size()/2;	// The value's decimal will be dropped, taking care of the formula and how Java operates (0 being the first element instead of 1)
        	result = userInputNumbers.get(index);
        }
        else 
        {
        	// If the list has an even amount of numbers
        	index = userInputNumbers.size()/2 - 1; // Takes the left value of the middle elements
        	index2 = index + 1;	// Takes the right value of the middle elements
        	result = userInputNumbers.get(index2) + userInputNumbers.get(index);
        	result = result/2;
        }
        
        return result;
    }
    
    /**
     * getMode Method - Responsible for finding the number in a set that appears most often
     * 
     * @Referenced https://www.tutorialspoint.com/Java-program-to-calculate-mode-in-Java 
     * @param userInputNumbers - An arrayList containing elements of type Double
     * @return result - The mode of the set of elements given
     */
    public String getMode (ArrayList<Double> userInputNumbers)
    {
    	// In the scenario that there is no repeating numbers
    	String scenarios;
    	// Keep track of times that first and second index are equal
    	int equalCounter;    	
    	// Used to ensure if a set of elements occurs more frequently than another
    	int total;
    	total = 0;
    	
    	scenarios = "";
    	// Compare first index with each index in the list
    	for (int firstPass = 0; firstPass < userInputNumbers.size(); firstPass++)
    	{
    		equalCounter = 0; // Will equal 0 after each pass of the for-loop above
    		
    		for (int secondPass = 0; secondPass < userInputNumbers.size(); secondPass++)
    		{
    			if (userInputNumbers.get(firstPass).equals(userInputNumbers.get(secondPass)))
    			{
    				equalCounter++;
    			}
    		}
    		
    		if (equalCounter > total)
    		{
    			total = equalCounter; 	// Next frequency of elements, if any, will have to occur more than the first repeated element frequency
    			result = firstPass; 	// Keeps the element's index of the highest equalCounter
    		}
    	}
    	
    	if (!(userInputNumbers.get((int)result).equals(userInputNumbers.get((int) result+1))))
		{
    		scenarios += "There is no repeating numbers in this set";
			return scenarios;
		}
    	else
    	{
    		return scenarios += userInputNumbers.get((int)result) + "0";
    	}
    }
    
    /**
     *  getStandardDeviation Method - Calls the getVariance method to calculate the Standard Deviation of the set
     *  
     *  @param userInputNumbers - An arrayList containing elements of type Double
     *  @return result - The value of the Standard Deviation of the set of elements
     */
    public double getStandardDeviation (ArrayList<Double> userInputNumbers)
    { 	
    	result = Math.sqrt(getVariance(userInputNumbers)); 
    	
    	return result;
    }
    
    /**
     * getVariance Method - Calculates the variance of a set of elements to be called by the getStandardDeviation method
     * 
     * @param userInputNumbers - An ArrayList containing elements of type Double
     * @return result - The value of the Variance of the set of elements
     */
    public double getVariance (ArrayList<Double> userInputNumbers)
    {
    	double mean, sum;
    	int index;
    	
    	// Retrieves the mean
    	mean = getMean(userInputNumbers);
    	
    	sum = 0;
    	index = 0;
 
    	while (index < userInputNumbers.size())
    	{
    		sum += Math.pow((userInputNumbers.get(index) - mean), 2);
    		index++;
    	}
    	
    	result = sum/userInputNumbers.size();
    	
    	return result;
    }
}

