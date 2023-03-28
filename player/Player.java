package player;

/**
 * An implementation of a chess player.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Player {
	private String playerColor;
	
	/**
	 * A constructor for the player class
	 * 
	 * @param color
	 * 		The color of the player. Either white or black
	 */
	public Player(String color) {
		this.playerColor = color;
	}
	
	/**
	 * A method to get the color of the player
	 * 
	 * @return
	 * 		The color of the player
	 */
	public String getPlayerColor() {
		return this.playerColor;
	}
	
	/**
	 * A toString method to print the color of the player
	 * 
	 * @return
	 * 		Either "White" or "Black"
	 */
	public String toString() {
		if(playerColor.equals("white")) {
			return "White";
		}
		else {
			return "Black";
		}
	}
}
