package pieces;

import board.*;

/**
 * An implementation of the Knight chess piece.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Knight extends Piece {
	/**
	 * A constructor for the Knight class. Calls a super constructor inside.
	 * 
	 * @param pieceColor
	 * 		The color of the chess piece
	 */
	public Knight(String pieceColor) {
		super(pieceColor, "N");
	}

	/**
	 * A method to determine if the move being made with the Knight is legal or not
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
		int[] finalVals = new int[8];
		int arrayCounter = 0;

		if(!((initPos % 8 == 1) || (initPos % 8 == 2))) {
			//Not Restricted by the Left
			if((initPos - 10) > 0) {
				if(game.getTile(initPos - 10).getPiece() != null) {
					if(!(game.getTile(initPos - 10).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos - 10;
						arrayCounter++;
					}
				}
				else {
					//The space is null so we can just add it to the array
					finalVals[arrayCounter] = initPos - 10;
					arrayCounter++;
				}
			}
			if((initPos + 6) < 64) { 
				if(game.getTile(initPos + 6).getPiece() != null) {
					if(!(game.getTile(initPos + 6).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos + 6;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos + 6;
					arrayCounter++;
				}
			}
		}
		if(!((initPos <= 64) && (initPos >= 49))) {
			//Not Restricted by the Bottom
			if((initPos + 15) < 64) {
				if(game.getTile(initPos + 15).getPiece() != null) {
					if(!(game.getTile(initPos + 15).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos + 15;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos + 15;
					arrayCounter++;
				}
			}
			if((initPos + 17) < 64) {
				if(game.getTile(initPos + 17).getPiece() != null) {
					if(!(game.getTile(initPos + 17).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos + 17;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos + 17;
					arrayCounter++;
				}
			}
		}
		if(!((initPos % 8 == 7) || (initPos % 8 == 0))) {
			//Not Restricted by the Right
			if((initPos - 6) > 0) {
				if(game.getTile(initPos - 6).getPiece() != null) {
					if(!(game.getTile(initPos - 6).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos - 6;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos - 6;
					arrayCounter++;
				}
			}
			if((initPos + 10) < 64) {
				if(game.getTile(initPos + 10).getPiece() != null) {
					if(!(game.getTile(initPos + 10).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos + 10;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos + 10;
					arrayCounter++;
				}
			} 
		}
		if(!((initPos <= 16) && (initPos >= 1))) {
			//Not Restricted by the Top
			if((initPos - 15) > 0) {
				if(game.getTile(initPos - 15).getPiece() != null) {
					if(!(game.getTile(initPos - 15).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos - 15;
						arrayCounter++;
					}
				}
				else {
					finalVals[arrayCounter] = initPos - 15;
					arrayCounter++;
				}
			}
			if((initPos - 17) > 0) {
				if(game.getTile(initPos - 17).getPiece() != null) {
					if(!(game.getTile(initPos - 17).getPiece().getpieceColor().equals(currColor))) {
						finalVals[arrayCounter] = initPos - 17;
						arrayCounter++; 
					}
				}
				else {
					finalVals[arrayCounter] = initPos - 17;
					arrayCounter++; 
				}
			}
		}

		for(int x = 0; x < arrayCounter; x++)
		{
			if(finalVals[x] == endPos) {
				return true;
			}
		}

		return false;
	}
}
