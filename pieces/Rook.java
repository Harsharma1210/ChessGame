package pieces;

import board.*;

/**
 * An implementation of the Knight chess piece.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Rook extends Piece{
	
	//True if rook has not moved yet
	boolean firstMove;
	
	/**
	 * A constructor for the Rook class. Calls a super constructor inside.
	 * 
	 * @param pieceColor
	 * 		The color of the chess piece
	 */
	public Rook(String pieceColor) {
		super(pieceColor, "R");
		firstMove = true;
	}

	/**
	 * A method to determine if the move being made with the Rook is legal or not
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
		int[] finalVals = new int[18];
		int arrayCounter = 0;

		//Check the North Direction
		for(int x = initPos; x > 0; x -= 8) {
			if((game.getTile(x).getPiece() != null) && (x != initPos)) {
				if(game.getTile(x).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = x;
			arrayCounter++;
			if((game.getTile(x).getPiece() != null) && (x != initPos)) {
				break;
			}
		}

		//Check the South Direction
		for(int y = initPos; y < 64; y += 8) {
			if((game.getTile(y).getPiece() != null) && (y != initPos)) {
				if(game.getTile(y).getPiece().getpieceColor().equals(currColor)) {
					break;
				}
			}
			finalVals[arrayCounter] = y;
			arrayCounter++;
			if((game.getTile(y).getPiece() != null) && (y != initPos)) {
				break;
			}
		}

		//Check the West Direction
		for(int a = initPos; a > 0; a--) {
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

		//Check the East Direction
		for(int b = initPos; b < 64; b++) {
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

		for(int c = 0; c < arrayCounter; c++) {
			if(finalVals[c] == endPos) {
				return true;
			}
		}

		return false;
	}
} 
