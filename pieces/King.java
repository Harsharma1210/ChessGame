package pieces;

import java.util.ArrayList;
import board.*;

/**
 * An implementation of the King chess piece.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class King extends Piece {
	//true if king has castled
	boolean castled;
	
	//true if king in check
	public boolean inCheck;
	
	//true if king in checkmate
	public boolean inCheckMate;
	
	/**
	 * A constructor for the King class. Calls a super constructor inside.
	 * 
	 * @param pieceColor
	 * 		The color of the chess piece
	 */
	public King(String pieceColor) {
		super(pieceColor, "K");
		castled = false;
		inCheck = false;
		inCheckMate = false;
	}
	
	/**
	 * A method to determine if the move being made with the King is legal or not
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
	public boolean isLegalMove(String color, String[] move, ChessBoard game) {
		
		String color2 = color;
		if(color == "White")
			color = "w";
		if(color == "Black")
			color = "b";
		
		int initPos = game.getTileNum(move[0]);
		int endPos = game.getTileNum(move[1]);
		
		Tile t = game.getTile(endPos);
		Piece p = t.getPiece();
		if (p !=null && !(p.getpieceColor() == color2)) {
			return false;
		}
				
		ArrayList<Integer> legalMoves = new ArrayList<>();

		if (color.equals("w") && initPos==61 && !castled) {
			if (endPos==63) {
				Tile corner = game.getTile(64);
				Piece cornerPiece = corner.getPiece();
				System.out.println(cornerPiece.getpieceType());
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					Rook rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos+1; i<=63; i++) {
							if (game.getTile(i).getPiece()!=null) {
								return false;
							}
						} return true;
					}
				} return false;
			} else if (endPos==59) {
				Tile corner = game.getTile(57);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					Rook rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos-1; i>=58; i--) {
							if (game.getTile(i).getPiece()!=null) {
								return false;
							}
						} return true;
					}
				} return false;
			}
		} else if (color.equals("b") && initPos==5 && !castled) {
			if (endPos==7) {
				Tile corner = game.getTile(8);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					Rook rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos+1; i<=7; i++) {
							if (game.getTile(i).getPiece()!=null) {
								return false;
							}
						} return true;
					}
				} return false;
			} else if (endPos==3) {
				Tile corner = game.getTile(1);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					Rook rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos-1; i>=2; i--) {
							if (game.getTile(i).getPiece()!=null) {
								return false;
							}
						} return true;
					}
				} return false;
			}
		}
		
		//Normal move (non-castling)
		if (game.isCorner(initPos)) {
			if (initPos==1) {
				legalMoves=addTileNums(2, legalMoves, game, color);
				legalMoves=addTileNums(9, legalMoves, game, color);
				legalMoves=addTileNums(10, legalMoves, game, color);
			} else if (initPos==8) {
				legalMoves=addTileNums(7, legalMoves, game, color);
				legalMoves=addTileNums(15, legalMoves, game, color);
				legalMoves=addTileNums(16, legalMoves, game, color);
			} else if (initPos==57) {
				legalMoves=addTileNums(49, legalMoves, game, color);
				legalMoves=addTileNums(50, legalMoves, game, color);
				legalMoves=addTileNums(58, legalMoves, game, color);
			} else {
				legalMoves=addTileNums(55, legalMoves, game, color);
				legalMoves=addTileNums(56, legalMoves, game, color);
				legalMoves=addTileNums(63, legalMoves, game, color);
			}
		} else if (game.isEdge(initPos)) {
			if (initPos%8==0) {
				legalMoves=addTileNums(initPos-8, legalMoves, game, color);
				legalMoves=addTileNums(initPos+8, legalMoves, game, color);
				legalMoves=addTileNums(initPos-1, legalMoves, game, color);
				legalMoves=addTileNums(initPos-9, legalMoves, game, color);
				legalMoves=addTileNums(initPos+7, legalMoves, game, color);
			} else if (initPos%8==1) {
				legalMoves=addTileNums(initPos-8, legalMoves, game, color);
				legalMoves=addTileNums(initPos-7, legalMoves, game, color);
				legalMoves=addTileNums(initPos+1, legalMoves, game, color);
				legalMoves=addTileNums(initPos+8, legalMoves, game, color);
				legalMoves=addTileNums(initPos+9, legalMoves, game, color);
			} else if (initPos<8) {
				if (color.equals("b") && initPos==5 && !castled) {
					addTileNums(initPos+2,legalMoves,game,color);
					addTileNums(initPos-2,legalMoves,game,color);
				}
				legalMoves=addTileNums(initPos-1, legalMoves, game, color);
				legalMoves=addTileNums(initPos+1, legalMoves, game, color);
				legalMoves=addTileNums(initPos+7, legalMoves, game, color);
				legalMoves=addTileNums(initPos+8, legalMoves, game, color);
				legalMoves=addTileNums(initPos+9, legalMoves, game, color);
			} else {
				if (color.equals("w") && initPos==61 && !castled) {
					addTileNums(initPos+2,legalMoves,game,color);
					addTileNums(initPos-2,legalMoves,game,color);
				} 
				legalMoves=addTileNums(initPos-9, legalMoves, game, color);
				legalMoves=addTileNums(initPos-8, legalMoves, game, color);
				legalMoves=addTileNums(initPos-7, legalMoves, game, color);
				legalMoves=addTileNums(initPos-1, legalMoves, game, color);
				legalMoves=addTileNums(initPos+1, legalMoves, game, color);
			}
		} else {
			legalMoves=addTileNums(initPos-7, legalMoves, game, color);
			legalMoves=addTileNums(initPos-8, legalMoves, game, color);
			legalMoves=addTileNums(initPos-9, legalMoves, game, color);
			legalMoves=addTileNums(initPos-1, legalMoves, game, color);
			legalMoves=addTileNums(initPos+1, legalMoves, game, color);
			legalMoves=addTileNums(initPos+7, legalMoves, game, color);
			legalMoves=addTileNums(initPos+8, legalMoves, game, color);
			legalMoves=addTileNums(initPos+9, legalMoves, game, color);
		}
		if (legalMoves.contains(endPos)) {
			castled=true;
			return true;
		} else return false;		
	}

	/**
	 * Determines if a tile is a potential destination for a piece and if so, adds it to an arraylist
	 * 
	 * @param i
	 * 		The tile number that is in question
	 * @param legalMoves
	 * 		An arraylist of potential tiles the piece can move to
	 * @param game
	 * 		A variable that gives access to the rest of the game
	 * @param color
	 * 		The color of the piece that is moving
	 * @return
	 * 		The arraylist of potential tiles (either the same or with an added number)
	 */
	private ArrayList<Integer> addTileNums(int i, ArrayList<Integer> legalMoves, ChessBoard game, String color) {
		if (game.getTile(i).getPiece()==null || 
				!game.getTile(i).getPiece().getpieceColor().equals(color)) {
			legalMoves.add(i);
		}
		return legalMoves;
	}
}
