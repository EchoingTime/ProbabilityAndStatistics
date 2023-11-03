package statsOperations.Testers;
import statsOperations.Distributions;
/**
 * TestGeomeetrics - Class that tests the various methods inside the
 * GeometricDistribution class
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestDistributions 
{
	public static void main (String [] args)
	{
		Distributions solve = new Distributions();
		/*=============================================================================================================*/
		
		// Testing Hypergeometric Distribution 

		int bigN;
		int n;
		int y;
		int r;
		
		bigN = 20; // Total cards
		n = 5; // Total cards selected
		y = 4; // Number of red cards we want
		r = 6; // Number of red cards
		
		System.out.printf("The Hypergeometric Distribution is: %.2f%%", solve.hyper(bigN, n, y, r)*100);
		System.out.printf("%nThe Hypergeometric Expectence is: %.2f", solve.expectedHyper(bigN, n, r));
		System.out.printf("%nThe Hypergeometric Variance is: %.2f%n", solve.varianceHyper(bigN, n, r));

		/*=============================================================================================================*/

		// Testing Negative Binomial Probability Distribution
		
		double p;
		
		p = .2;
		y = 5;
		r = 3;
		
		System.out.printf("%nThe Negative Binomial Probability Distribution is: %.2f%%", solve.negative(p, y, r)*100);
		System.out.printf("%nThe Negative Binomial Expectence is: %.2f", solve.expectedNegative(p, r));
		System.out.printf("%nThe Negative Binomial Variance is: %.2f%n", solve.varianceNegative(p, r));

		/*=============================================================================================================*/

		// Testing Binomial Distribution
		
		int x;
		
		p = .80;
		n = 10;
		x = 7;
				
		System.out.printf("%nThe Binomial Distribution is: %.2f%%%n", solve.binomial(p, n, x)*100);

		/*=============================================================================================================*/
		
		// Testing Geometric Distribution
		
		p = .5;
		y = 5;
		
		System.out.printf("%nThe Geometric Distribution is: %.2f%%", solve.geometric(p, y)*100);
		System.out.printf("%nThe Geometric Expectation is: %.2f", solve.expectedGeometric(p));
		System.out.printf("%nThe Geometric Variance is: %.2f%n", solve.varianceGeometric(p));


		/*=============================================================================================================*/
		
		// Testing Poisson Distribution
		
		int lambda;
		
		lambda = 1;
		y = 0;
		
		System.out.printf("%nThe Poisson Distribution is: %.2f%%", solve.poisson(lambda, y));
		System.out.printf("%nThe Poisson Expectence is: %.2f", solve.expectedPoisson(lambda));
		System.out.printf("%nThe Poisson Variance is: %.2f%n", solve.variancePoisson(lambda));

		/*=============================================================================================================*/

		// Testing Tchebysheff’s Theorem
		
		double k;
		
		k = 2;
		
		System.out.printf("%nThe Tchebysheff’s Theorem result w/ k given is: %.2f%%", solve.tchebysheff(k)*100);
		
		// Finding k method
		
		double mean, standard, high;
		
		mean = 39;
		standard = 5;
		high = 49;
		
		System.out.printf("%nThe Tchebysheff’s Theorem result is: %.2f%%%n", solve.tchebysheff(mean, standard, high)*100);


		/*=============================================================================================================*/
	}
}

