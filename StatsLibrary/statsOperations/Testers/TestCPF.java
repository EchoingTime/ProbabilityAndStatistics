package statsOperations.Testers;
import statsOperations.CombinationPermutationFactorial;
/**
 * TestCPF - Used to test the CombinationPermutationFactorial class and its operations
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestCPF 
{
	public static void main (String [] args)
	{
		CombinationPermutationFactorial solve;
		solve = new CombinationPermutationFactorial ();
		
		System.out.printf("Factorial   : %s%n", solve.getFactorial(15));
		System.out.printf("Permutation : %s%n", solve.getPermutation(30, 3));
		System.out.printf("Combination : %s%n", solve.getCombination(5, 2));

	}
}
