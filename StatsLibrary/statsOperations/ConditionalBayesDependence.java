package statsOperations;
import java.util.ArrayList;
/**
 * ConditionalBayesDependence Class - Allows a user to find the probability of A given B has occurred, use Bayes' Theorem,
 * find the independence of two sets and its probability intersection, and if two sets are mutually exclusive and its probability
 * union.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class ConditionalBayesDependence 
{
	private UnionIntersectionComplement operate;
	
	/**
	 * findProbabilityAGivenB - A method that will use formulas to determine the 
	 * probability of A given that it is B has occurred. It also utilizes the package
	 * ProbabilityOperations and its class, SetOperations, to find the intersection.
	 * NOTE - SWAP A WITH B IF IT IS B GIVEN A
	 * @param a - Represents A in the conditional probability formula
	 * @param b - Represents B in the conditional probability formula
	 * @param totalObjects - The amount of objects to compare with
	 * @return probability - The conditional probability of A given B has occurred
	 */
	public Double findProbabilityAGivenB (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{
		ArrayList<Integer> aIntersectsB;
		double pAIntersectsB, probabilityB, conditional;
		
		operate = new UnionIntersectionComplement(a, b);
		
		// Finding the intersection of A and B
		aIntersectsB = operate.getIntersection();
				
		// Finding the probability of the intersection of A and B
		pAIntersectsB = aIntersectsB.size();
		pAIntersectsB = pAIntersectsB/totalObjects;
		
		// Finding the probability of B
		probabilityB = b.size();
		probabilityB = probabilityB/totalObjects;
				
		// Now finding what the probability of intersection A and B divided by probability of B is
		conditional = pAIntersectsB/probabilityB;
		
		return conditional;
	}
	
	/**
	 * isItIndependent Method - Checks if two sets are independent or dependent by checking three
	 * conditions: Probability a given b equals probability a, probability b given a equals probability b,
	 * or probability a intersects b equals probability a times probability b
	 * @param a - Set of elements one
	 * @param b - Set of elements two
	 * @param totalObjects - Total amount of objects
	 * @return boolean - True if independent and false if dependent 
	 */
	public boolean isItIndependent (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{		
		ArrayList<Integer> aIntersectsB;
		double conditionOne; // Probability a given b = probability a
		double conditionTwo; // Probability b given a = probability b
		double conditionThree; // Probability a intersects b = probability a times probability b
		double probA, probB;
		
		operate = new UnionIntersectionComplement(a, b);
		
		conditionOne = findProbabilityAGivenB(a, b, totalObjects);
		conditionTwo = findProbabilityAGivenB(b, a, totalObjects);
		aIntersectsB = operate.getIntersection();
		conditionThree = aIntersectsB.size();
		conditionThree /= totalObjects;
		
		probA = a.size();
		probA /= totalObjects;
		
		probB = b.size();
		probB /= totalObjects;
		
		if (conditionOne == probA)
		{
			return true;
		}
		else if (conditionTwo == probB)
		{
			return true;
		}
		else if (conditionThree == (probA * probB))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * probIntersection Method - Calculates the probability intersection of either dependent or independent sets
	 * and returns the result.
	 * @param a - Set of elements One
	 * @param b - Set of elements Two
	 * @param totalObjects - Total amount of elements
	 * @return probInter - The probability intersection of either independent or dependent sets
	 */
	public Double probIntersection (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{
		double bGivenA, probA, probB, probInter;
		
		bGivenA = findProbabilityAGivenB(b, a, totalObjects);
		probInter = -1;
		
		probA = a.size();
		probA /= totalObjects;
		probB = b.size();
		probB /= totalObjects;
		
		if (isItIndependent (a, b, totalObjects) == true) // independent 
		{
			probInter = probA * probB;
		}
		else // dependent
		{
			probInter = bGivenA * probA;
		}
		
		return probInter;
	}
	
	/**
	 * isMutuallyExclusive Method - Checks to see if two sets are mutually exclusive, meaning two sets have no 
	 * common elements.
	 * @param a - Set of elements One
	 * @param b - Set of elements Two
	 * @param totalObjects - Total amount of elements
	 * @return boolean - True if mutually exclusive and false if not
	 */
	public boolean isMutuallyExclusive (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{
		ArrayList<Integer> aIntersectsB;
		double paIntersectsB;
		
		operate = new UnionIntersectionComplement(a, b);
		aIntersectsB = operate.getIntersection();
		paIntersectsB = aIntersectsB.size();
		paIntersectsB /= totalObjects;
		
		if (paIntersectsB == 0) // mutually exclusive
		{
			return true;
		}
		else // not
		{
			return false;
		}
	}
	
	/**
	 * findProbUnion Method - Will check if set a and b are mutually exclusive, and if so, mutually exclusive union will be
	 * calculated. However, if the sets are not mutually exclusive then not mutually exclusive union is calculated 
	 * @param a - Set of elements One
	 * @param b - Set of elements Two
	 * @param totalObjects - Total amount of objects
	 * @return probUnion - The result from the mutually exclusive or not mutually exclusive formula
	 */
	public Double findProbUnion (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{
		ArrayList<Integer> aIntersectsB;
		double probUnion, probA, probB, probIntersects;
		
		operate = new UnionIntersectionComplement(a, b);
		aIntersectsB = operate.getIntersection();
		probIntersects = aIntersectsB.size();
		probIntersects /= totalObjects;
		
		probA = a.size();
		probA /= totalObjects;
		probB = b.size();
		probB /= totalObjects;
		
		probUnion = probA + probB;

		if (isMutuallyExclusive(a,b, totalObjects) == true)
		{
			return probUnion;
		}
		else
		{
			probUnion -= probIntersects;
			return probUnion;
		}
	}

	/**
	 * bayesTheorem Method - Solves problems using bayesTheorem formula
	 * @param a - Set of elements One
	 * @param b - Set of elements Two
	 * @param totalObjects - Total amount of objects
	 * @return bayes - Result according to the formula
	 */
	public Double bayesTheorem (ArrayList<Integer> a, ArrayList<Integer> b, int totalObjects)
	{
		double condition;
		double probA, probB;
		double bayes;
		
		condition = findProbabilityAGivenB(a, b, totalObjects);
		probA = a.size();
		probA /= totalObjects;
		probB = b.size();
		probB /= totalObjects;
		
		bayes = (condition * probB) / probA;
		
		return bayes;
	}
}
