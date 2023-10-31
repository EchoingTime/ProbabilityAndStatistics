package pokerHandEvaluator;
/**
 * Card - Creates a card and assigns a face and value to it
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Card 
{
	private String face;	// The face of a card
	private int value;		// The value of the card
	
	public Card ()
	{
	}
	
	/**
	 * setFace Method - Accepts a String face variable, like Clubs, Diamonds, 
	 * Hearts, and Spades.
	 * @param face - Face of the card
	 */
	public void setFace (String face)
	{
		this.face = face;
	}
	
	/**
	 * setValue Method - Accepts an integer called value, which will determine 
	 * if it is a 1,2,3,..., 10 or a special face card, like a Jack, Queen, King, 
	 * or Ace.
	 * @param value - Card's value
	 */
	public void setValue (int value)
	{
		this.value = value;
	}
	
	/**
	 * getFace Method - Retrieves the face of the card
	 * @return face - Card's face
	 */
	public String getFace ()
	{
		return face;
	}
	
	/**
	 * getValue Method - Retrieves the value of the card
	 * @return value - Card's value
	 */
	public int getValue ()
	{
		return value;
	}
}
