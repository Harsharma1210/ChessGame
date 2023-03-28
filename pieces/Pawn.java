package pieces;

import board.*;

/**
 * An implementation of the Knight chess piece.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Pawn extends Piece{
	/**
	 * A constructor for the Knight class. Calls a super constructor inside.
	 * 
	 * @param pieceColor
	 * 		The color of the chess piece
	 */
	public Pawn(String pieceColor) {
		super(pieceColor, "P");
	}

	/**
	 * A method to determine if the move being made with the Pawn is legal or not
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
		
		Tile target = game.getTile(endPos);

		if(target.getPiece() == null) {
			if(currColor.equals("White")) {
				if((initPos <= 56) && (initPos >= 49)) {
					//Check to see if the pawn is moving up one space or two spaces
					if((initPos - endPos == 8) || (initPos - endPos == 16)) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(initPos - endPos == 8) {
					//Anywhere else on the board. Check to see if the pawn is moving up only one space
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if((initPos <= 16) && (initPos >= 9)) {	
					if((endPos - initPos == 8) || (endPos - initPos == 16)) {
						return true;
					}
					else {
						return false;
					}
				}
				else if(endPos - initPos == 8) {
					return true;
				}
				else {
					return false;
				}
			}			
		}
		else {
			//There is a piece there. Check to see if the pawn is moving forward diagonally or not.
			if(currColor.equals("White")) {
				//White Player
				if(initPos % 8 == 0) {
					//Can only move -9
					if(initPos - endPos == 9) {
						if(game.getTile(initPos - 9).getPiece().getpieceColor().equals("White"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else {
						return false;
					}
				}
				else if (initPos % 8 == 1) {
					//Can only move -7
					if(initPos - endPos == 7) { 
						if(game.getTile(initPos - 7).getPiece().getpieceColor().equals("White"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else { 
						return false; 
					}
				}
				//It is not a side columns so it must be a middle column. Check to see if it is moving diagonally-forward one space.
				else if((initPos - endPos == 7) || (initPos - endPos == 9)) {
					if(initPos - endPos == 9) {
						if(game.getTile(initPos - 9).getPiece().getpieceColor().equals("White"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					if(initPos - endPos == 7) { 
						if(game.getTile(initPos - 7).getPiece().getpieceColor().equals("White"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else {
						return false;
					}
				}
				else {
					//Not moving diagonally-forward one space
					return false;
				}
			}
			else {
				//Black Player
				if(initPos % 8 == 0) {
					//Can only move +7
					if(endPos - initPos == 7) {
						if(game.getTile(initPos + 7).getPiece().getpieceColor().equals("Black"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else {
						return false;
					}
				}
				else if (initPos % 8 == 1) {
					//Can only move +9
					if(endPos - initPos == 9) { 
						if(game.getTile(initPos + 9).getPiece().getpieceColor().equals("Black"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else { 
						return false; 
					}
				}
				//It is not a side columns so it must be a middle column. Check to see if it is moving diagonally-forward one space.
				else if((endPos - initPos == 7) || (endPos - initPos == 9)) {
					if(endPos - initPos == 7) {
						if(game.getTile(initPos + 7).getPiece().getpieceColor().equals("Black"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					if(endPos - initPos == 9) { 
						if(game.getTile(initPos + 9).getPiece().getpieceColor().equals("Black"))
						{
							return false;
						}
						else {
							return true;
						}
					}
					else { 
						return false; 
					}
				}
				else {
					//Not moving diagonally-forward one space
					return false;
				}
			}
		}
	}
}
