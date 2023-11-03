package statsOperations.Testers;
import java.util.ArrayList;
import statsOperations.ConditionalBayesDependence;
/**
 * TestClasses - A class that is responsible for testing the methods inside
 * the ConditionalBayesDependence class.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestCBD 
{
	public static void main (String [] args)
	{
		ConditionalBayesDependence solve;
		ArrayList <Integer> a, b, c;
		int totalObjects;
		
		solve = new ConditionalBayesDependence();
		
		/*=================================================================================================================*/
		
		// For conditional probability problem
		a = new ArrayList<Integer> (); 
		b = new ArrayList<Integer> (); 
		
		// Add elements to subset A
		a.add(1);
		
		// Add elements to subset B
		b.add(1);
		b.add(3);
		b.add(5);
		
		totalObjects = 6;
		System.out.printf("The probability of A given B is: %.2f%%%n", (solve.findProbabilityAGivenB(a, b, totalObjects)*100));
		a.clear();
		b.clear();
		/*=================================================================================================================*/
		
		// Testing if two sets are dependent or independent 
		
		c = new ArrayList<Integer> (); 
		
		// 1, 3, 5 die numbers
		// 2, 4, 6 die numbers
		// 1, 2 die numbers
		
		a.add(1);
		a.add(3);
		a.add(5);
		
		b.add(2);
		b.add(4);
		b.add(6);
		
		c.add(1);
		c.add(2);
		
		totalObjects = 6;
		System.out.printf("Is the sets A and B independent? : %s%n", (solve.isItIndependent(a, b, totalObjects)));
		System.out.printf("Is the sets A and C independent? : %s%n", (solve.isItIndependent(a, c, totalObjects)));
		
		System.out.printf("Finding probability intersection of A and B : %.2f%n", (solve.probIntersection(a, b, totalObjects)));
		System.out.printf("Finding probability intersection of A and C : %.2f%n", (solve.probIntersection(a, c, totalObjects)));

		a.clear();
		b.clear();
		c.clear();
		/*=================================================================================================================*/

		// For Bayes-Theorem problem
		
		a.add(2); // Die containing numbers 2, 4, and 5
		a.add(4);
		a.add(6);
		
		b.add(5); // Die containing numbers 5 and 6
		b.add(6);
		
		totalObjects = 6;
		System.out.printf("Bayes Theorem result: %.2f%%%n", solve.bayesTheorem(a, b, totalObjects) * 100);
		a.clear();
		b.clear();
		/*=================================================================================================================*/

		// Testing if two sets are mutually or not mutually exclusive
		
		a.add(2);
		a.add(4);
		a.add(8);
		
		b.add(1);
		b.add(3);
		b.add(5);
		
		c.add(2);
		c.add(3);
		
		totalObjects = 6;
		System.out.printf("Is the sets A and B mutually exclusive? : %s%n", solve.isMutuallyExclusive(a, b, totalObjects));
		System.out.printf("Is the sets A and C mutually exclusive? : %s%n", solve.isMutuallyExclusive(a, c, totalObjects));
		
		System.out.printf("Finding probability union of A and B : %.2f%n", (solve.findProbUnion(a, b, totalObjects)));
		System.out.printf("Finding probability union of A and C : %.2f%n", (solve.findProbUnion(a, c, totalObjects)));
		
		/*=================================================================================================================*/

	}
}
