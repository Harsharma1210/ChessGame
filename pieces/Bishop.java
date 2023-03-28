package pieces;

import board.*;

/**
 * An implementation of the Bishop chess piece.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Bishop extends Piece{
	/**
	 * A constructor for the Bishop class. Calls a super constructor inside.
	 * 
	 * @param pieceColor
	 * 		The color of the chess piece
	 */
	public Bishop(String pieceColor) {
		super(pieceColor, "B");
	}

	/**
	 * A method to determine if the move being made with the Bishop is legal or not
	 * 
	 * @param currColor
	 * 		The color of the piece. It is used to make sure you do not take your own pieces.
	 * @param move
	 * 		An array with the two inputs provided by the user
	 * @param game
	 * 		A variable that gives access to the rest of the game
	 * @return
	 * 		A boolean value either true or false
	 */
	public boolean isLegalMove(String currColor, String[] move, ChessBoard game)
	{
		int initPos = game.getTileNum(move[0]);
		int endPos = game.getTileNum(move[1]);
		int[] finalVals = new int[17];
		int arrayCounter = 0;

		//Check the North-West Direction
		for(int x = initPos; x > 0; x -= 9) {
			//System.out.println("Position Added to Array: " + x);
			if((game.getTile(x).getPiece() != null) && (x != initPos)) {
				if(game.getTile(x).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = x;
			arrayCounter++;
			if((x % 8 == 1) || ((game.getTile(x).getPiece() != null) && (x != initPos))) {
				break;
			}
		}

		//Check the North-East Direction
		for(int y = initPos; y > 0; y -= 7) {
			//System.out.println("Position Added to Array: " + y);
			if((game.getTile(y).getPiece() != null) && (y != initPos)) {
				if(game.getTile(y).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = y;
			arrayCounter++;
			if((y % 8 == 0) || ((game.getTile(y).getPiece() != null) && (y != initPos))) {
				break;
			}
		}

		//Check the South-West Direction
		for(int a = initPos; a < 64; a += 7) {
			//System.out.println("Position Added to Array: " + a);
			if((game.getTile(a).getPiece() != null) && (a != initPos)) {
				if(game.getTile(a).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = a;
			arrayCounter++;
			if((a % 8 == 1) || ((game.getTile(a).getPiece() != null) && (a != initPos))) {
				break;
			}
		}

		//Check the South-East Direction
		for(int b = initPos; b < 64; b += 9) {
			//System.out.println("Position Added to Array: " + b);
			if((game.getTile(b).getPiece() != null) && (b != initPos)) {
				if(game.getTile(b).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = b;
			arrayCounter++;
			if((b % 8 == 0) || ((game.getTile(b).getPiece() != null) && (b != initPos))) {
				break;
			}
		}
		
		//Check to see if the target tile is an acceptable endpoint
		//System.out.println("These are the numbers in the array: " + finalVals);
		for(int c = 0; c < arrayCounter; c++) {
			if(finalVals[c] == endPos) {
				return true;
			}
		}

		return false; 
	}
}
