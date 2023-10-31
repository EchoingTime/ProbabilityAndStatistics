package pokerHandEvaluator;
import java.util.ArrayList;
import java.util.Random;
/**
 * HandEvaluator - A program that evaluates the odds of common Poker hands via the
 * Monte Carlo Simulation following the rules of Texas hold 'em. 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class HandEvaluator 
{
	private Card card;
	private ArrayList <Card> hand; // Array of cards, a hand
	private ArrayList <Card> deck; // Array of cards, the deck
	
	/**
	 * HandEvaluator Constructor - Initializes the deck of 52 cards
	 */
	public HandEvaluator ()
	{		
		deck = new ArrayList<Card> (52);		
	}
	
	/**
	 * createDeck Method - Responsible for creating the deck of 52 cards
	 */
	public void createDeck ()
	{
		int number;
		number = 1;
		
		// 1-52
		while (number < 53)
		{
			card = new Card ();
			if (number < 14) // 1 - 13
			{
				card.setFace("Club");
				card.setValue(number);
				deck.add(card);
			}
			else if (number > 13 && number < 27) // 14 - 26
			{
				card.setFace("Diamond");
				card.setValue(number - 13);
				deck.add(card);
			}
			else if (number >= 27 && number < 40) // 27 - 39
			{
				card.setFace("Heart");
				card.setValue(number - 26);
				deck.add(card);
			}
			else // 40 - 52
			{
				card.setFace("Spade");
				card.setValue(number - 39);
				deck.add(card);
			}
			number++;
		}
				
	}
	
	/*
	 * drawCard - A method that draws a single card from the deck
	 */
	public void drawCard ()
	{
		Random rand;
		int randomCard;
		rand = new Random ();
		randomCard = rand.nextInt(52);  // 0 - 51
		
		hand.add(deck.get(randomCard));
	}
	
	/**
	 * drawHand Method - Draws a hand of a size of the parameter
	 * @param handSize - The size of the hand
	 */
	public void drawHand (int handSize)
	{		
		hand = new ArrayList<Card> (handSize);

		while (handSize != 0)
		{
			drawCard();
			handSize--;
		}
	}
	
	/**
	 * run Method - Responsible for running the program by calling the class' methods
	 * @param runAmount - The times the program will run
	 * @param handSize - The size of the hand
	 */
	public void run (int runAmount, int handSize)
	{
		double pairCount, threeKind, fourKind, containStraight, containsFlush, fullHouse, straightFlush, royalFlush, probability;
		int hands;
		boolean result;
		createDeck();
		
		hands = runAmount;
		pairCount = 0;
		threeKind = 0;
		fourKind = 0;
		containStraight = 0;
		containsFlush = 0;
		fullHouse = 0;
		straightFlush = 0;
		royalFlush = 0;
		
		while (runAmount != 0)
		{
			drawHand(handSize);
			result = containPair();
			if (result == true)
			{
				pairCount++;
			}
			clearHand();
			
			drawHand(handSize);
			result = containThreeOfAKind();
			if (result == true)
			{
				threeKind++;
			}
			clearHand();
			
			drawHand(handSize);
			result = containFourOfAKind();
			if (result == true)
			{
				fourKind++;
			}
			clearHand();
			
			drawHand(handSize);
			sortHand();
			result = containStraight();
			if (result == true)
			{
				containStraight++;
			}
			clearHand();
			
			drawHand(handSize);
			result = containsFlush();
			if (result == true)
			{
				containsFlush++;
			}
			clearHand();
			
			drawHand(handSize);
			sortHand();
			result = fullHouse();
			if (result == true)
			{
				fullHouse++;
			}
			clearHand();
			
			drawHand(handSize);
			sortHand();
			result = straightFlush();
			if (result == true)
			{
				straightFlush++;
			}
			clearHand();
			
			drawHand(handSize);
			sortHand();
			result = royalFlush();
			if (result == true)
			{
				royalFlush++;
			}
			clearHand();
			

			runAmount--;
		}
		probability = pairCount / hands;
		System.out.printf("The probability of getting a pair in %s hands is %s%n", hands, probability);
		probability = threeKind / hands;
		System.out.printf("The probability of getting a three kind in %s hands is %s%n", hands, probability);
		probability = fourKind / hands;
		System.out.printf("The probability of getting a four kind in %s hands is %s%n", hands, probability);
		probability = containStraight / hands;
		System.out.printf("The probability of getting a straight in %s hands is %s%n", hands, probability);
		probability = containsFlush / hands;
		System.out.printf("The probability of getting a flush in %s hands is %s%n", hands, probability);
		probability = fullHouse / hands;
		System.out.printf("The probability of getting a full house in %s hands is %s%n", hands, probability);
		probability = straightFlush / hands;
		System.out.printf("The probability of getting a straight Flush in %s hands is %s%n", hands, probability);
		probability = royalFlush / hands;
		System.out.printf("The probability of getting a royal Flush in %s hands is %s%n", hands, probability);

	}
	
	/**
	 * clearHand Method - Clears the current hand for a new shuffle
	 */
	public void clearHand ()
	{
		hand.clear();
	}
	
	/**
	 * containPair Method - Checks to see if the hand has a pair, meaning the same value
	 * @return boolean - True if there is a pair and false if not
	 */
	public boolean containPair ()
	{
		int current, next;
		
		for (int i = 0; i < hand.size(); i++)
		{
			current = hand.get(i).getValue();
			for (int j = 0; j < hand.size(); j++)
			{
				if (i != j)
				{
					next = hand.get(j).getValue();
					if (current == next)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * containThreeOfAKind Method - Checks to see if the hand has a three of a kind
	 * @return boolean - True if there is a three of a kind and false if not
	 */
	public boolean containThreeOfAKind ()
	{
		int current, next, nextTwo;
		
		for (int i = 0; i < hand.size(); i++)
		{
			current = hand.get(i).getValue();
			for (int j = 0; j < hand.size(); j++)
			{
				for (int k = 0; k < hand.size(); k++)
				{
					if (i != j && i != k && j != k)
					{
						next = hand.get(j).getValue();
						nextTwo = hand.get(k).getValue();
						if (current == next && current == nextTwo && next == nextTwo)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * containFourOfAKind Method - Checks to see if the hand has a four of a kind
	 * @return boolean - True if there is a four of a kind and false if not
	 */
	public boolean containFourOfAKind ()
	{
		int current, next, nextTwo, nextThree;
		
		for (int i = 0; i < hand.size(); i++)
		{
			current = hand.get(i).getValue();
			for (int j = 0; j < hand.size(); j++)
			{
				for (int k = 0; k < hand.size(); k++)
				{
					for (int z = 0; z < hand.size(); z++)
					{
						if (i != j && i != k && j != k && z != i && z != k && z != j)
						{
							next = hand.get(j).getValue();
							nextTwo = hand.get(k).getValue();
							nextThree = hand.get(z).getValue();
							if (current == next && current == nextTwo && next == nextTwo && current == nextThree)
							{
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * sortHand Method - Sorts the hand from least to greatest
	 */
	public void sortHand ()
	{
		int current, swaps;
		boolean run;
		current = hand.get(0).getValue();
		swaps = 0;
		run = true;
		
		while (run == true)
		{
			for (int index = 1; index < hand.size(); index++)
			{
				current = hand.get(index - 1).getValue();

				if (hand.get(index).getValue() < current) 	
				{
					hand.add(index - 1, hand.get(index)); 	
					hand.remove(index + 1); 	
					swaps++;
				}
			}
			
			if (swaps > 0)
			{
				swaps = 0;
			}
			else
			{
				run = false;
			}
		}
	}
	
	/**
	 * containStraight Method - Checks to see if the hand has a straight
	 * @return boolean - True if there is a straight and false if not
	 */
	public boolean containStraight ()
	{
		int fivePass;
		fivePass = 0;
		
		int current, next;
		
		for (int i = 0; i < hand.size(); i++)
		{
			current = hand.get(i).getValue();
			for (int j = 0; j < hand.size(); j++)
			{
				if (i != j)
				{
					next = hand.get(j).getValue();
					if (current - 1 == next)
					{
						fivePass++;
					}
				}
			}
		}
		
		if (fivePass == 5)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * containsFlush Method - Checks to see if the hand is a flush
	 * @return boolean - True if there is a flush and false if not
	 */
	public boolean containsFlush ()
	{
		String current, next, nextTwo, nextThree, nextFour;
		
		for (int i = 0; i < hand.size(); i++)
		{
			current = hand.get(i).getFace();
			for (int j = 0; j < hand.size(); j++)
			{
				for (int k = 0; k < hand.size(); k++)
				{
					for (int z = 0; z < hand.size(); z++)
					{
						for (int x = 0; x < hand.size(); x++)
						{
							if (i != j && i != k && j != k && z != i && z != k && z != j && x != i && x != j && x != z && x != k)
							{
								next = hand.get(j).getFace();
								nextTwo = hand.get(k).getFace();
								nextThree = hand.get(z).getFace();
								nextFour = hand.get(x).getFace();
								if (current.equals(next) && current.equals(nextTwo) && next.equals(nextTwo) && current.equals(nextThree) && current.equals(nextFour))
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * fullHouse Method - Checks to see if the hand is a full house
	 * @return boolean - True if there is a full house and false if not
	 */
	public boolean fullHouse ()
	{
		boolean three, pair;
		
		three = containThreeOfAKind();;
		pair = containPair();
		
		if (three && pair == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * straightFlush Method - Checks to see if the hand is a straight flush
	 * @return boolean - True if there is a straight flush and false if not
	 */
	public boolean straightFlush ()
	{
		boolean flush, straight;
		
		flush = containsFlush();
		straight = containStraight();
		
		if (flush && straight == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * royalFlush Method - Checks to see if the hand is a royal flush
	 * @return boolean - True if there is a royal flush and false if not
	 */
	public boolean royalFlush ()
	{
		boolean flush, straight;
		
		flush = containsFlush();
		straight = containStraight();
		
		// 1 ace, 2, 3, 4, 5, 6, 7, 8, 9, --> 10, --> 11, 12, 13
		if (hand.get(0).getValue() == 1 && hand.get(1).getValue() == 10 && hand.get(2).getValue() == 11 && hand.get(3).getValue() == 12 && hand.get(4).getValue() == 13)
		{
			straight = true;
		}
		else
		{
			straight = false;
		}
		
		if (flush && straight == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
