package statsOperations;
import java.util.ArrayList;
/**
 * UnionIntersectionComplement Class - Utilizes three statistics operations, union, intersection, and
 * complement for two ArrayLists, a and b, that represents two sets. 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class UnionIntersectionComplement 
{
	private ArrayList<Integer> a;
	private ArrayList<Integer> b;
	
	/**
	 * Constructor Default - For problems that do not involve a set of elements
	 */
	public UnionIntersectionComplement ()
	{
		
	}
	
	/**
	 * Constructor SetOperations accepts two ArrayList parameters, a and b, that 
	 * will point to the global instance variables of the class. Each list 
	 * represents a subset of set S.
	 */
	public UnionIntersectionComplement (ArrayList<Integer> a, ArrayList<Integer> b)
	{
		this.a = removeDuplicates(a);
		this.b = removeDuplicates(b);
	}
	
	/**
	 * getUnion - Method that will join two subsets together and their elements 
	 * without any duplicates 
	 * Elements in A, or in B, or in both (A∪B) NO DUBLICATES 
	 * @return unionArray - An ArrayList that joined two subsets, represented as a and b
	 */
	public ArrayList<Integer> getUnion ()
	{
		ArrayList<Integer> unionArray, temp1, temp2;
		unionArray = new ArrayList<Integer>();
		temp1 = new ArrayList<Integer>(a);
		temp2 = new ArrayList<Integer>(b);
		
		int checkOne, checkTwo;
		
		if (temp1.size() > temp2.size())
		{
			checkOne = 0;
			// Going through the indexes of temp1 and checking each index of temp2
			while (checkOne < temp1.size())
			{
				checkTwo = 0;
				while (checkTwo < temp2.size())
				{
					if (temp1.get(checkOne) == temp2.get(checkTwo))
					{
						unionArray.add(temp1.get(checkOne));
						temp1.remove(checkOne);
						temp2.remove(checkTwo);
					}
					else if (temp1.get(checkOne) < temp2.get(checkTwo))			
					{
						unionArray.add(temp1.get(checkOne));
						temp1.remove(checkOne);
					}
					else // if (a.get(checkOne) > b.get(checkTwo))
					{
						unionArray.add(temp2.get(checkTwo));
						temp2.remove(checkTwo);
					}
				}
				checkOne++;
			}
		}
		else // This deals with a larger ArrayList temp2 (b)
		{
			checkOne = 0;
			// Going through the indexes of temp2 and checking each index of temp1
			while (checkOne < temp2.size())
			{
				checkTwo = 0;
				while (checkTwo < temp1.size())
				{
					if (temp2.get(checkOne) == temp1.get(checkTwo))
					{
						unionArray.add(temp2.get(checkOne));
						temp2.remove(checkOne);
						temp1.remove(checkTwo);
					}
					else if (temp2.get(checkOne) < temp1.get(checkTwo))			
					{
						unionArray.add(temp2.get(checkOne));
						temp2.remove(checkOne);
					}
					else // if (temp2.get(checkOne) > temp1.get(checkTwo))
					{
						unionArray.add(temp1.get(checkTwo));
						temp1.remove(checkTwo);
					}
				}
				checkOne++;
			}
		}
		
		
		checkOne = 0;
		// In case one ArrayList was bigger than the other -> Finishes the union
		if (temp1.size() != 0)
		{
			while (temp1.size() != 0)
			{
				unionArray.add(temp1.get(checkOne));
				temp1.remove(checkOne);
			}
		}
		else
		{
			while (temp2.size() != 0)
			{
				unionArray.add(temp2.get(checkOne));
				temp2.remove(checkOne);
			}
		}		
		
		return unionArray;
	}
	
	/**
	 *  getIntersection - This method will collect the shared elements from two 
	 *  sets a and b, and return the result as the ArrayList intersectArray
	 *  Elements in A and B, (A∩B) the SHARED collection
	 *  @return intersectArray - An arrayList of shared elements
	 */
	public ArrayList<Integer> getIntersection ()
	{
		int checkOne, checkTwo;
		ArrayList<Integer> intersectArray;
		intersectArray = new ArrayList<Integer>();
		
		checkOne = 0;
		// Going through the indexes of arrayListOne and checking each index of arrayListTwo
		while (checkOne < a.size())
		{
			checkTwo = 0;
			while (checkTwo < b.size())
			{
				if (a.get(checkOne) == b.get(checkTwo))
				{
					intersectArray.add(a.get(checkOne));
					checkTwo = b.size();
				}
				else
				{
					checkTwo++;
				}
			}
			checkOne++;
		}
		
		return intersectArray;
	}
	
	/**
	 * removeDuplicates - This method insures that an array has no duplicates
	 * 
	 * @param array - The ArrayList that is having it's duplicated elements removed
	 * @return array - The new arrayList without any duplicated elements
	 */
	public ArrayList<Integer> removeDuplicates (ArrayList <Integer> array)
	{
		// Removing duplicates 
		int singleElement;
		singleElement = 0;
				
		while (singleElement < array.size() - 1)
		{
			if (array.get(singleElement) == array.get(singleElement + 1))
			{
				array.remove(singleElement);
			}
			else
			{
				singleElement++;
			}
		}
				
		if (array.size() - 2 == array.size() - 1)
		{
			array.remove(array.size() - 1);
		}
		
		return array;
	}
	
	/**
	 * Complement - Is a method that takes two ArrayLists representing
	 * a set and subset and will return a new ArrayList of all the elements 
	 * not in the given subset.
	 * @param set - An ArrayList the represents a set
	 * @param given - The given ArrayList representing the subset
	 * @return complementArray - The complement of the given ArrayList
	 */
	public ArrayList<Integer> getComplement (ArrayList<Integer> set, ArrayList<Integer> given)
	{
		ArrayList<Integer> complementArray, temp;
		temp = new ArrayList <> (set);	// Setting up a temporary array as its elements will be removed if it matches with the given ArrayList
						
		for (int indexS = 0; indexS < temp.size(); indexS++) // Set
		{
			for (int indexG = 0; indexG < given.size(); indexG++) // Subset
			{
				if (temp.get(indexS).equals(given.get(indexG)))
				{
					temp.remove(indexS);
				}
			}
		}
		
		complementArray = new ArrayList <> (temp);		
		return complementArray;
	}
}
