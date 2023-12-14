package finalStatsLibrary;

import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * UniformDistribution Class - Utilizes the Uniform Distribution Probability, Expected, and Standard Deviation formulas and creates
 * their respective methods.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class UniformDistribution 
{
	private NumberFormat formater;;
	
	/**
	 * UniformDistribution Constructor - Initializes the formatting 
	 */
	public UniformDistribution ()
	{
		formater = new DecimalFormat ("#.##");
	}
	
	/**
	 * probability Method - Finds the uniform distribution probability using the formula
	 * P(c <= X <= d) = d - c / b - a
	 * @param a - Lower bound
	 * @param b - Upper bound
	 * @param c - Middle bound
	 * @param d - Middle bound
	 * @return The uniform probability resulting from the formula
	 */
	public double probability (double a, double b, double c, double d)
	{
		double probability;
		probability = d - c;
		probability /= b - a;
		return probability;
	}
	
	/**
	 * expected Method - Finds the uniform expected, or average, value using the formula
	 * E(x) = a + b / 2
	 * @param a - Lower bound
	 * @param b - Upper bound
	 * @return The uniform expected value resulting from the formula
	 */
	public double expected (double a, double b)
	{
		double expected;
		expected = a + b;
		expected /= 2;
		return expected;
	}
	
	/**
	 * standardDeviation Method - Finds the uniform standard deviation value using the formula
	 * SD = square root of ((b-a)^2/12))
	 * @param a - Lower bound
	 * @param b - Upper bound
	 * @return The standard deviation value resulting from the formula
	 */
	public Double standardDeviation (double a, double b)
	{
		double standard;
		String formatting;
		standard = Math.sqrt((Math.pow(b-a, 2))/12);
		formatting = "" + formater.format(standard);
		standard = Double.parseDouble(formatting);
		return standard;
	}
}
