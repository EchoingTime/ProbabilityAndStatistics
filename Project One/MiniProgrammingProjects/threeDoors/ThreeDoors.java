package threeDoors;
import java.util.Random;
/**
 * ThreeDoors Class - Provides three doors and only one has the winning prize | GOAT | GOAT | PRIZE | 
 * Will show win probability if a given player plays 10,000 times and does not change door after given
 * the chance to do so. Will also provide the win probability if, after given the chance to change their
 * door, that they took the offer and did so.
 * a) 	E1: {G, D, D}, E2: {D, G, D}, E3: {D, D, G}
 * 		The player has a 1/3 probability of guessing the curtain with the prize
 * b)	i: 33.33% (also 1/3)
 * 		ii: It would be a zero chance if the player initially choose correctly but changed it after finding out
 * 		one of the doors containing a goat and swapping to another
 * 		iii: Would be 66.66% or 2/3
 * 		iv: 2/3 if incorrect with first pick and 100% if first pick was also wrong
 * 		v: Switching to the other curtain would maximize the player's probability of winning the good prize.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class ThreeDoors
{
	private int loops, doorOne, doorTwo, doorThree; 
	private double winsChangeDoor, winsKeepDoor;
	
	/**
	 * Constructor - Allows user to create a ThreeDoors object and assign the parameter,
	 * timesPlayed, the amount of times they wish to run the program
	 */
	public ThreeDoors (int timesPlayed)
	{
		loops = timesPlayed;    
	}
   
   /**
    * fillDoors Method - Responsible for randomly filling the doors with either a goat, given as one,
    * or a prize, given as a 2. All doors are 1 until the number, prize, with possible values
    *  0, 1, or 2, is randomly assigned. 
    */
   public void fillDoors ()
   {
       int prize;
       Random rand = new Random();
       
       // If the door's value is not changed, that means there is a goat!
       doorOne = 1;
       doorTwo = 1;
       doorThree = 1;
       prize = rand.nextInt(3);
       
       if (prize == 0)
       {
           // Door is even means there is a prize
           doorOne = 2;
       }   
       else if (prize == 1)
       {
           doorTwo = 2;        
       }
       else
       {
           doorThree = 2;    
       } 
   }
   
   /**
    * constructAndPlayGame Method - Creates the game via fillDoors method, runs the game by calling
    * gameIsPlaying method until loops has reached 0, and calling getWins to show the percentage of wins
    * for both scenarios.
    */
   public void constructAndPlayGame ()
   {
       boolean playing;
	   fillDoors();

       playing = true;
       
       while (playing == true)
       {
           if (loops > 0)
           {
               loops--;
               gameIsPlaying();
           }
           else
           {
               playing = false;
           }
       }
       getWins();
   }
   
   /**
    * gameIsPlaying Method - Is the automatic player, picking doors for the first game scenario, if the user keep their door,
    * if they win then winsKeepDoor is incremented by one, and will not account for host taking away an incorrect door as 
    * for this scenario, we know they will not. Second scenario takes away a no prize door and lets user randomly pick a new door
    */
   public void gameIsPlaying ()
   {
	   int pick;
	   Random rand = new Random();
	   
	   // The first pick goes for both games being played, that is the game were the user keeps their door and the second game they change their door
	   pick = rand.nextInt(3);
	   	   
	   // 0, 1, or 2
	   
	   // a door has 2 if prize else 1 for goat
	   
	   // First game scenario (User keeps their door --> if they win, winsKeepDoor++. In a live scenario, the host would take away an incorrect door, but since we know beforehand the user will not change, we do not have to account for this)
	   if (pick == 0) 		// doorOne was picked
	   {
		   if (doorOne == 2)
		   {
			   winsKeepDoor++;	
		   }
	   }
	   else if (pick == 1) 	// doorTwo was picked
	   {
		   if (doorTwo == 2)
		   {
			   winsKeepDoor++;
		   }
	   }
	   else 						// initialPick == 2 --> hence doorThree was picked
	   {
		   if (doorThree == 2)
		   {
			   winsKeepDoor++;
		   }
	   }
	   
	   // Second game
	   
	   // Now, say the game host picks one of the doors that has the goat (-2), or no prize, the user, in this case, changes their initial pick to a new door (other game they keep their initial pick)
	   pick = rand.nextInt(3);
	   
	   if (doorOne == 1 && doorTwo == 1)  // This means doorTwo (0) and doorThree (1) are left
	   {
		   if (pick == 0)
		   {
			   if (doorTwo == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
		   else
		   {
			   if (doorThree == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
		  
	   }
	   else if (doorTwo == 1 && doorThree == 1)
	   {
		   if (pick == 0)
		   {
			   if (doorOne == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
		   else
		   {
			   if (doorThree == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
	   }
	   else // (doorOne == 1 && doorThree == 1)
	   {
		   if (pick == 0)
		   {
			   if (doorOne == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
		   else
		   {
			   if (doorTwo == 2)
			   {
				   winsChangeDoor++;
			   }
		   }
	   }
   }
   
   /**
    * getWins Method - Will print out both win probabilities for scenario one, keeping the door picked, and scenario two, changing the door picked
    */
   public void getWins ()
   {   
	   System.out.printf("winsChangeDoor = Win probability of %.2f that the host removes one incorrect door and the player changes their initial pick to a new door, out of the remaining doors"
	   		+ "%nwinsKeepDoor   = Win probability of %.2f that player keeps the same door after getting the chance to change, when the hosts removes an incorrect door, and not taking it%n", (winsChangeDoor / 10000) * 100, (winsKeepDoor / 10000) * 100);
   }
   
}
