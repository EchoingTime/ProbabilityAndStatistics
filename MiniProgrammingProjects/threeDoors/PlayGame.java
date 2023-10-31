package threeDoors;
/**
 * PlayGame Class - Runs the ThreeDoors class
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class PlayGame
{
	public static void main (String [] args)
	{
	    ThreeDoors game = new ThreeDoors (10000);
	    game.constructAndPlayGame();
	}
}
