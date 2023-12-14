package finalStatsLibrary;
/**
 * TestStats Class - Tests the UniformDistribution.java and DiscreteMultivariate.java classes
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestStats 
{
	public static void main (String [] args)
	{
		UniformDistribution uniform;
		uniform = new UniformDistribution ();
		
		System.out.printf("The Uniform Probability of the given parameters is: %s%n", uniform.probability(2, 10, 7, 10));
		System.out.printf("The Uniform Expected of the given parameters is: %s%n", uniform.expected(2, 10));
		System.out.printf("The Uniform Standard Deviation of the given parameters is: %s%n", uniform.standardDeviation(2, 10));

	}
}
