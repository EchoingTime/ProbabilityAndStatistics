package statsOperations;
/**
 * Distributions Class - Has methods for various distributions by using the formulas
 * and calculating the results. Negative Binomial Probability Distribution, Hypergeometric Distribution,
 * Binomial Distribution, Geometric Distribution, Poisson Distribution, and Tchebysheff’s Theorem.
 * Expected and Variance methods are listed, but to find the standard deviation, square root the variance.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Distributions
{
	private CombinationPermutationFactorial combo;
	
	public Distributions ()
	{
		combo = new CombinationPermutationFactorial();
	}
	
	/**
	 * negative Method - Finds the Negative Binomial Probability Distribution by
	 * using the formula (y minus 1 choose r minus 1) * p^r * q^(y minus r)
	 * @param p - Probability of success (q, probability of failure, is taken care of inside method)
	 * @param y - Can also be called x, number of trails Y that must occur until we have r successes
	 * @param r - Amount of successes
	 * @return result - (y minus 1 choose r minus 1) * p^r * q^(y minus r)
	 */
	public double negative (double p, int y, int r)
	{
		int a; // y - 1
		int b; // r - 1
		
		double yMinusOneChooserMinusOne; 	// a choose b
		double pRaisedToR;					// p^r
		double qRaisedToYMinusR;			// q^(y-r)
		double result;						// (y - 1 choose r - 1) * p^r * q^(y - r)
		double q;
		
		a = y - 1;
		b = r - 1;
		q = 1 - p;
		
		yMinusOneChooserMinusOne = (combo.getCombination(a, b)).doubleValue();
		pRaisedToR = Math.pow(p, r);
		qRaisedToYMinusR = Math.pow(q, y-r);
		
		result = yMinusOneChooserMinusOne * pRaisedToR * qRaisedToYMinusR;
		
		return result;
	}
	
	/**
	 * expectedNegative Method - Returns the expected value of Y of Negative Binomial distribution
	 * @param p - Probability of success
	 * @param r - Amount of successes
	 * @return result - Expected Y
	 */
	public double expectedNegative (double p, double r)
	{
		double result;
		result = r / p;
		return result;
	}
	
	/**
	 * varianceNegative Method - Returns the variance of Y in Negative Binomial distribution
	 * @param p - Probability of success
	 * @param r - Amount of successes
	 * @return result - Variance of Y
	 */
	public double varianceNegative (double p, double r)
	{
		double result;
		result = r * (1 - p);
		result = result / Math.pow(p, 2);
		return result;
	}
	
	/**
	 * hyper Method - Finds the Hypergeometric Distribution by
	 * using the formula ((r choose y) x (N minus r choose n minus y)) / N choose n
	 * @param bigN - Probability Size
	 * @param n - Amount of trials or items from the population
	 * @param y - Can also be called x, number of trails Y that must occur until we have r successes
	 * @param r - Amount of successes
	 * @return result - ((r choose y) x (N minus r choose n minus y)) / N choose n
	 */
	public double hyper (int bigN, int n, int y, int r)
	{		
		int a; // N - r
		int b; // n - y
		
		double rChooseY; // r choose y
		double aChooseB; // N - r choose n - y
		double bigNChoosen; // N choose n
		double result; // ((r choose y) x (N -r choose n - y)) / N choose n
		
		rChooseY = (combo.getCombination(r, y)).doubleValue();
		a = bigN - r;
		b = n - y;
		aChooseB = (combo.getCombination(a, b).doubleValue());
		bigNChoosen = (combo.getCombination(bigN, n).doubleValue());
		
		result = (rChooseY * aChooseB) / bigNChoosen;
		return result;
	}
	
	/**
	 * expectedHyper Method - Returns the expected value of Y of Hyper distribution
	 * @param bigN - Probability Size
	 * @param n - Amount of trials 
	 * @param r - Amount of successes
	 * @return result - Expectation of Y
	 */
	public double expectedHyper (double bigN, double n, double r)
	{
		double result;
		result = n * r;
		result /= bigN;
		return result;
	}
	
	/**
	 * varianceHyper Method - Returns the variance of Y in Hyper distribution
	 * @param bigN - Probability Size
	 * @param n - Amount of trials 
	 * @param r - Amount of successes
	 * @return result - Variance of Y
	 */
	public double varianceHyper (double bigN, double n, double r)
	{
		double result;
		result = n * (r / bigN);
		result = result * ((bigN - r)/bigN);
		result = result * ((bigN - n)/(bigN - 1));
		return result;
	}
	
	/**
	 * binomial Method - Finds the Binomial Distribution by using the formula
	 * (n choose x)(p^x)(q^(n minus x)) and returning the result
	 * @param p - Probability of success of each trail (q, probability of failure, is taken care of inside method)
	 * @param n - Amount of trials
	 * @param x - Random variable 
	 * @return result - (n choose x)(p^x)(q^(n minus x))
	 */
	public double binomial (double p, int n, int x)
	{
		double q; // Probability of failure
		double nChoosex; // n choose x
		double pPOWx; // p^x
		double qPOWnMinusx; // q^(n-x)
		double result; // (n choose x)(p^x)(q^(n-x))
		
		q = 1 - p;
		nChoosex = (combo.getCombination(n, x)).doubleValue();
		pPOWx = Math.pow(p, x);	
		qPOWnMinusx = Math.pow(q, n - x);
		
		result = nChoosex * pPOWx * qPOWnMinusx;
		return result;
	}
	
	/**
	 * geometric Method - Finds the Geometric Distribution by using the formula
	 * q^(y minus 1)p and returning the result
	 * @param p - Probability of success
	 * @param y - Trials
	 * @return result - q^(y minus 1)p 
	 */
	public double geometric (double p, int y)
	{
		double q; // Probability of failure
		double qPOWyMinus1; // q^(y - 1)
		double result; // q^(y-1)p
		
		q = 1 - p; 
		qPOWyMinus1 = Math.pow(q, y - 1);
		result = qPOWyMinus1 * p;
		
		return result;
	}
	
	/**
	 * expectedGeometric Method - Returns the expected value of Y of geometric distribution
	 * @param p - Probability of success
	 * @return result - Expectation of Y 
	 */
	public double expectedGeometric (double p)
	{
		double result;
		result = 1/p;
		return result;
	}
	
	/**
	 * varianceGeometric Method - Returns the variance of Y in geometric distribution
	 * @param p - Probability of success
	 * @return result - Variance of Y
	 */
	public double varianceGeometric (double p)
	{
		double result;
		result = (1-p);
		result /= Math.pow(p, 2);
		return result;
	}
	
	/**
	 * poisson Method - Finds the Poisson Distribution by using the formula
	 * (Lambda^y * e^((negative)Lambda)) / y! and returning the result
	 * @param lambda - Mean number of occurrences in the interval
	 * @param y - Number of successes 
	 * @return result - (Lambda^y * e^((negative)Lambda)) / y!
	 */
	public double poisson (int lambda, int y)
	{
		double e; // Euler's constant approximately 2.72182818
		double lambdaPOWy; // Lambda^y
		double yFactorial; // y!
		double ePOWnegLambda; // e^-Lambda
		double result; // (Lambda^y * e^(-Lambda)) / y!
		
		e = 2.72182818;
		lambdaPOWy = Math.pow(lambda, y);
		
		yFactorial = combo.getFactorial(y).doubleValue();
		
		ePOWnegLambda = Math.pow(e, -lambda);
		
		result = lambdaPOWy * ePOWnegLambda;
		
		result /= yFactorial; 
		
		return result;
	}
	
	/**
	 * expectedPoisson Method - Returns parameter, as this is the expected Y
	 * @param lambda - Mean number of occurrences in the interval
	 * @return lambda - The expected Y
	 */
	public double expectedPoisson (double lambda)
	{
		return lambda;
	}
	
	/**
	 * variancePoisson Method - Returns parameter, as this is the variance Y
	 * @param lambda - Mean number of occurrences in the interval
	 * @return lambda - The variance Y
	 */
	public double variancePoisson (double lambda)
	{
		return lambda;
	}
	
	/**
	 * tchebysheff Method - Finds the result using Tchebysheff’s Theorem and its formula
	 * 1 minus (1/k^2) with given k
	 * @param k
	 * @return result - 1 minus (1/k^2)
	 */
	public double tchebysheff (double k)
	{
		double result; // 1 - (1/k^2)
		result = 1 - (1/Math.pow(k, 2));
		return result;
	}
	
	/**
	 * tchebysheff Method - Finds the result using Tchebysheff’s Theorem and its formula
	 * 1 minus (1/k^2) with mean, standard deviation, and highest value given.
	 * @param mean 
	 * @param standardDeviation
	 * @param highestValue
	 * @return result - Calls method tchebysheff(findK)
	 */
	public double tchebysheff (double mean, double standardDeviation, double highestValue)
	{
		double findK;
		double result; // 1 - (1/k^2)
	
		// mean + k (standard deviation) = highest value
		
		findK = highestValue - mean;
		findK /= standardDeviation;
		
		result = tchebysheff(findK);
		return result;
	}
}
