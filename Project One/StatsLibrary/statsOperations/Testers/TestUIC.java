package statsOperations.Testers;
import java.util.ArrayList;

import statsOperations.UnionIntersectionComplement;
/**
 * TestUIC Class - Creates three ArrayLists representing a set and 
 * the two subsets, a and b, to test the UnionIntersectionComplement class and its operations
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class TestUIC
{
    public static void main (String [] args)
    {
        UnionIntersectionComplement operating;		// Declaring the test object of class UnionIntersectionComplement
		ArrayList<Integer> a, b, set;	// Declaring the set and subsets a and b ArrayLists
		
        System.out.printf("______________________%n%n");	
        
        set = new ArrayList <> (); // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		set.add(0);
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		set.add(5);
		set.add(6);
		set.add(7);
		set.add(8);
		set.add(9);
		set.add(10);
		
		a = new ArrayList <> (); // 0, 0, 4, 4, 6, 8, 10
		a.add(0);
		a.add(0);
		a.add(4);
		a.add(4);
		a.add(6);
		a.add(8);
		a.add(10);
		
		b = new ArrayList <> (); // 0, 1, 2, 4, 5, 9, 10
		b.add(0);
		b.add(1);
		b.add(2);
		b.add(4);
		b.add(5);
		b.add(9);
		b.add(10);
		
		operating = new UnionIntersectionComplement (a, b);
		System.out.printf("Intersection of the two arrays: %s%n", operating.getIntersection());
		System.out.printf("Union of the two arrays: %s%n", operating.getUnion());
		System.out.printf("The complement of arrayListOne: %s%n", operating.getComplement(set, a));
		System.out.printf("The complement of arrayListTwo: %s%n", operating.getComplement(set, b));

    }
}
