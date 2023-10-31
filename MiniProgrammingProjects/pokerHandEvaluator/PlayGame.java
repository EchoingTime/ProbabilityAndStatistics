package pokerHandEvaluator;
/**
 * PlayGame Class - Runs the HandEvaluator Class
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class PlayGame 
{
	public static void main (String [] args)
	{
		HandEvaluator hands;
		hands = new HandEvaluator();
		
		hands.run(10000, 7);
		
	}
	
}
