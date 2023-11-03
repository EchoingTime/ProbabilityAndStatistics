package statsOperations.Testers;
import java.util.ArrayList;


import statsOperations.MeanMedianModeSD;
/**
 * TestMMMS Class - Constructs an ArrayList of type double, 
 * which is to represent a set of elements, to be utilized in the MeanMedianModeSD class. Each
 * operation is tested here. 
 * 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestMMMS
{
    public static void main (String [] args)
    {
        MeanMedianModeSD test;			// Declaring the test object of class MeanMedianModeSD
        ArrayList<Double> testNumbers;	// Declaring the set of elements as type Double ArrayList
        double testerResults;			// Used to gather the results of various methods
		
        // Initializing the test Object and testNumbers ArrayList
        test = new MeanMedianModeSD();
        testNumbers = new ArrayList<Double>();
        
        /* 
         * The list of elements inside the ArrayList
         * LIST OF ELEMENTS' VALUES MUST BE ORDERED LEAST TO GREATST
         * MODE WILL NOT CHECK IF THERE ARE MORE THAN ONE MODE
         */
        testNumbers.add(0.8);
        testNumbers.add(0.8);
        testNumbers.add(3.3);
        testNumbers.add(3.9);
        testNumbers.add(5.4);
        testNumbers.add(5.5);
        testNumbers.add(7.0);
        testNumbers.add(15.0);
        
        // Testing each method contained in the StatsLibrary class and outputting the result
        testerResults = test.getMean(testNumbers);
        System.out.printf("Mean   - %5.2f%n", testerResults);
        testerResults = test.getMedian(testNumbers);
        System.out.printf("Median - %5.2f%n", testerResults);
        
        System.out.printf("Mode   -  %s%n", test.getMode(testNumbers));   
        testerResults = test.getStandardDeviation(testNumbers);
        System.out.printf("SD     - %5.2f%n", testerResults); 
        testerResults = test.getVariance(testNumbers);
        System.out.printf("Variance - %5.2f%n", testerResults); 

    }
}
