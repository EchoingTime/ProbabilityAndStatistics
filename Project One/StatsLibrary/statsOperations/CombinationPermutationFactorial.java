package statsOperations;
import java.math.BigInteger;
/**
 * CombinationPermutationFactorial Class - Includes three operations, a combination and permutation solver,
 * and a factorial method. 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class CombinationPermutationFactorial  
{
	/**
	 * getFactorial - This method accepts a user input, the variable number as type integer, via parameter 
	 * and returns the factorial of that number. The method utilizes BigInteger
	 * @Referenced - https://www.geeksforgeeks.org/biginteger-class-in-java/# & https://docs.oracle.com/javase/8/docs/api/ Wrote the code in integer form with a for loop and then converted it to BigInteger
	 * @param number - Supplied by the user, the number to find the factorial of
	 * @return factorial - The factorial of the number
	 */
	public BigInteger getFactorial (int number)
	{
		BigInteger factorial, one;
		int notZero;
		
		if (number == 0)
		{
			return BigInteger.valueOf(1);
		}
		else
		{
			factorial = BigInteger.valueOf(number); // Saving user input into the BigInteger factorial
			notZero = number - 1; // Getting the next number down from the parameter's value
			one = BigInteger.valueOf(notZero); // Now saving that value into BigInteger 
			
			while (notZero >= 1) // Will ensure that every variable will be multiplied
			{
				factorial = factorial.multiply(one); // Multiplying the factorial by the next number
				notZero--; // Finding the next number down
				one = BigInteger.valueOf(notZero); // Applying it to BigInteger form for next multiplication 
			}
			
			return factorial;
		}
	}
	
	/**
	 * getPermutation - A method that utilizes the getFactorial method to find the
	 * permutation which is the number of ways of ordering n distinct objects 
	 * taken r at a time.
	 * @param totalObjects - Represents n in the permutation formula
	 * @param objectsSelected - Represents r in the permutation formula
	 * @return permutations - A BigInteger variable of the permutation calculation
	 */
	public BigInteger getPermutation (int totalObjects, int objectsSelected)
	{
		BigInteger permutation, n_Minus_rFactorial;
		int n_Minus_r;
		
		n_Minus_r = totalObjects - objectsSelected; 	// n - r
		n_Minus_rFactorial = getFactorial(n_Minus_r); 	// (n - r)!
		
		permutation = BigInteger.valueOf(0); 			
		permutation = getFactorial(totalObjects); 		// n!
		permutation = permutation.divide(n_Minus_rFactorial); // n! / (n - r)!
		
		return permutation;
	}
	
	/**
	 * getCombination - A method that utilizes the getFactorial method to find the
	 * combination which is the number of subsets, each of size r, that can be
	 * formed from n objects.
	 * @param totalObjects - Represents n in the combination formula
	 * @param objectsSelected - Represents r in the combination formula
	 * @return combination - A BigInteger variable of the combination calculation
	 */
	public BigInteger getCombination (int totalObjects, int objectsSelected)
	{
		BigInteger combination, n_Minus_rFactorial;
		int n_Minus_r;
		
		n_Minus_r = totalObjects - objectsSelected; 		// n - r
		n_Minus_rFactorial = getFactorial(objectsSelected); // r!
		n_Minus_rFactorial = n_Minus_rFactorial.multiply(getFactorial(n_Minus_r)); 	// r!(n - r)!
		
		combination = BigInteger.valueOf(0); 			
		combination = getFactorial(totalObjects); 				// n!
		
		combination = combination.divide(n_Minus_rFactorial); 	// n! / r! (n - r)!
		
		return combination;
	}
}
