package board;

import pieces.Piece;

/**
 * An implementation of each tile on the chessboard.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Tile {
	public Piece piece;
	private int tileNum;
	private String color;
	
	/**
	 * A constructor for the piece class
	 * 
	 * @param t
	 * 		The number that corresponds with the tile
	 * @param p
	 * 		The piece object that is at the tile
	 * @param c
	 * 		The color of the tile
	 */
	public Tile(int t, Piece p, String c) {
		this.setTileNum(t);
		this.setPiece(p);
		this.setColor(c);
	}
	
	/**
	 * A method to return the color of the tile
	 * 
	 * @return
	 * 		The color of the tile
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * A method to set the color of the tile
	 * 
	 * @param color
	 * 		The color you want to set the tile to
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * A method to return the piece object on the tile
	 * 
	 * @return
	 * 		The piece object on the tile
	 */
	public Piece getPiece() {
		return this.piece;
	}
	
	/**
	 * A method to place a piece object on a tile
	 * 
	 * @param piece
	 * 		The piece object you wish to place
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	/**
	 * A method to return the number of the tile
	 * 
	 * @return
	 * 		The number of the tile
	 */
	public int getTileNum() {
		return this.tileNum;
	}
	
	/**
	 * A method to set the number of the tile
	 * 
	 * @param tileNum
	 * 		The number you wish to assign to the tile
	 */
	public void setTileNum(int tileNum) {
		this.tileNum = tileNum;
	}
	
	/**
	 * A toString method that prints the color of the color and piece of the tile
	 */
	public String toString() {
		String s = "";
		s = s + color + " " + piece;
		return s;
	}
}
