package pieces;

import java.util.ArrayList;
import board.*;

/** 
 * Piece is an abstract class that represents any piece in the game: Queen, King, Bishop, Rook, Knight, Pawn
 * 
 * @author 	Harsh Sharma
 * @author 	Ethan Nguyen
 */
public abstract class Piece {
	
	/**
	 * The specific piece type (Q, K, B, R, N, P)
	 */
	private String pieceType; //Q, K, B, R, N, P
	
	/**
	 * The specific piece color (White or Black)
	 */
	private String pieceColor; //w or b
	
	/**
	 * Explicit constructor for Piece.
	 * 
	 * @param pieceColor	The color of the piece.
	 * @param pieceType 	The specific type of piece it is.
	 */
	public Piece(String pieceColor, String pieceType) {
		this.pieceType = pieceType;
		this.pieceColor = pieceColor;
	}
	
	/**
	 * The toString method for a Piece instance.
	 * 
	 * @return	The String representation 
	 */
	public String toString() {
		return pieceColor + pieceType;
	}
	
	/**
	 * Gets the color of the piece.
	 * @return	The piece color
	 */
	public String getpieceColor() {
		if(pieceColor.equals("w")) {
			return "White";
		}
		else {
			return "Black";
		}
	}
	
	/**
	 * Gets the type of the piece.
	 * @return	The piece type
	 */
	public String getpieceType() {
		return pieceType;
	}
	
	/**
	 * Performs a move on a piece from Tile to Tile, changing the state of the ChessBoard instance, 
	 * and thus the state of the game.
	 * 
	 * @param move	consists of filerank strings, move[0] is the initial position and move[1] is the end position.
	 * @param game	chessboard instance
	 * @return		Returns true if the piece was moved, or false if the move was not possible.
	 * @see 		board.ChessBoard
	 */
	public boolean move(String[] move, ChessBoard game) {
		String color = this.getpieceColor();
		int initPos = game.getTileNum(move[0]);
		int endPos = game.getTileNum(move[1]);
		
		
		boolean kingMove=false;
		boolean rookMove=false;
		
		//check if moving Rook, store firstMove boolean
		if (this.getpieceType().equals("R")) {
			Rook rook = (Rook)this;
			rookMove=rook.firstMove;
		}
		
		//check if moving King, store castled boolean
		if (this.getpieceType().equals("K")) {
			King king = (King)this;
			kingMove=king.castled;
		}
		
		if (this.getpieceType().equals("K") && isLegalMove(pieceColor, move, game)) {
			game.EnPassant=false;
			
			//Checks if King is castling
			if (this.getpieceType().equals("K")) {
				King king = (King)this;
				Rook rook = isCastling(game, initPos, endPos, king.getpieceColor(), kingMove);
				if (rook!=null) {
						if (!rook.firstMove) return false;
						king.castled=true;
						rookMove=rook.firstMove;
						rook.firstMove=false;
						//King movement
						Tile target = game.getTile(endPos);
						Tile source = game.getTile(initPos);
						Piece init = source.getPiece();
						Piece end = target.getPiece();
						
						target.piece = init;
						source.piece = null;
						

						//Rook movement
						int[] moveRook = getRookMoveCastle(initPos, endPos);
						target = game.getTile(moveRook[1]);
						source = game.getTile(moveRook[0]);
						init = source.getPiece();
						
						target.piece=init;
						source.piece=null;
						
						
						game.lastMove=endPos;
						
						return true;
		
				}
			}
			
			Tile target = game.getTile(endPos);
			Tile source = game.getTile(initPos);
			Piece init = source.getPiece();
			Piece end = target.getPiece();
			
			target.piece = init;
			source.piece = null;
			
				
			game.lastMove=endPos;
			return true;
		} 
			
		//Pawn Promotion
		if (this.getpieceType().equals("P")) { 
			if (endPos<=8 || endPos>=57) {
				game.lastMove=endPos;
				return PawnPromotion(game,move,color);
			}
		}
		
		Tile target = game.getTile(endPos);
		Tile source = game.getTile(initPos);
		Piece init = source.getPiece();
		
		target.piece = init;
		source.piece = null;
		
		//check if enemy is in check
		King kingEnemy = ifEnemyChecked(game, target.piece.getpieceColor());
		if (kingEnemy!=null) {
			kingEnemy.inCheck=true;
			//kingEnemy.inCheckMate 
			boolean checkmate = inCheckmate(game, target.piece.getpieceColor());
			if(checkmate) {
				kingEnemy.inCheckMate = true;
			}
		}
		

		//kingEnemy.inCheckMate = false;
		
		
		
		game.lastMove = endPos;
		return true;
	}
	
	/**
	 * Promotes pawn if pawn is at the end of chessboard
	 * 
	 * @param game	chessboard instance
	 * @param move	The move the Pawn is making to do promotion.
	 * @param color	The color of the Pawn
	 * @return		Returns true if pawn promotion is completed, or false if not.
	 */
	private boolean PawnPromotion(ChessBoard game, String[] move, String color) {
		int initPos = game.getTileNum(move[0]);
		int endPos = game.getTileNum(move[1]);
		
		Tile target = game.getTile(endPos);
		Tile source = game.getTile(initPos);
		
//		Piece init = source.getPiece();
//		Piece end = target.getPiece();
		
		if (move.length == 2) {
			target.piece = new Queen(color);
			source.piece = null;
	
		} else if (move.length>2) {
			String pieceType = move[2];
			if (pieceType.equals("Q")) 
				target.piece = new Queen(color);
			else if (pieceType.equals("B")) 
				target.piece = new Bishop(color);
			else if (pieceType.equals("N")) 
				target.piece = new Knight(color);
			else if (pieceType.equals("R")) 
				target.piece = new Rook(color);
			source.piece = null;
		}

		return true;
	}
	
	
	/**
	 * Checks if the opposing player is in checkmate.
	 * 
	 * @param game	chessboard instance
	 * @param color	the color of current player
	 * @return		Returns true if opposing player is in checkmate, or false if not.
	 */
	private boolean inCheckmate(ChessBoard game, String color) {
		
		
		int enemykingTile=0;
		King enemyKing = null;
		for (int i=1; i<=64; i++) {
			Piece p = game.getTile(i).getPiece();
			if (p!=null) {
				if (!p.getpieceColor().equals(color) && p.getpieceType().equals("K")) {
					enemykingTile=i;
					enemyKing = (King)game.getTile(i).getPiece();
				}
			}
		}
		
		//System.out.println(enemykingTile);
		
		ArrayList<Integer> possibleTiles = new ArrayList<>();
		
		String initFileRank = game.getFileRank(enemykingTile);
		for(int i = 1; i<=64;i ++) {
			Tile t = game.getTile(i);
			String endFileRank = game.getFileRank(i);
			String[] move = {initFileRank, endFileRank};
			if(enemyKing.isLegalMove(color, move, game)) {
				possibleTiles.add(i);
			}
		}
		
//		for(int i =0; i < possibleTiles.size(); i++) {
//			System.out.println(possibleTiles.get(i));
//		}
		
		for(int i =0; i < possibleTiles.size(); i ++){
			if (tileIsAttacked(game,possibleTiles.get(i),color)) {
				continue;
			}
			else
				return false;
		}
	
		return true;		
		
	}
	
	/**
	 * Checks if the current player is in check
	 * 
	 * @param game	The current state of the game via the ChessBoard instance.
	 * @param color	The color of the player that is being checked
	 * @return		Returns true if player is in check, or false if not.
	 */
	private boolean ifInCheck(ChessBoard game, String color) { //color is w or b
		ArrayList<Integer> enemyPieces = new ArrayList<>();
		int kingTile=0;
		for (int i=1; i<=64; i++) {
			Piece p = game.getTile(i).getPiece();
			if (p!=null) {
				if (!p.getpieceColor().equals(color)) {
					enemyPieces.add(i);
				} else if (p.getpieceColor().equals(color) && p.getpieceType().equals("K")) {
					kingTile=i;
				}
			}
		}
		for (int i=0; i<enemyPieces.size(); i++) {
			int temp = enemyPieces.get(i);
			String[] move = {game.getFileRank(temp), game.getFileRank(kingTile)};
			Piece p = game.getTile(temp).getPiece();
			if (p.isLegalMove(color, move, game)) return true;
		}
		return false;
	}
	
	/**
	 * Checks if the opposing player is in check
	 * 
	 * @param game	The current state of the game via the ChessBoard instance.
	 * @param color	The color of the player that is doing the check.
	 * @return		Returns true if opposing player is in check, or false if not.
	 */
	private King ifEnemyChecked(ChessBoard game, String color) {
		ArrayList<Integer> yourPieces = new ArrayList<>();
		int enemykingTile=0;
		King enemyKing = null;
		for (int i=1; i<=64; i++) {
			Piece p = game.getTile(i).getPiece();
			if (p!=null) {
				if (p.getpieceColor().equals(color)) {
					yourPieces.add(i);
				} else if (!p.getpieceColor().equals(color) && p.getpieceType().equals("K")) {
					enemykingTile=i;
					enemyKing = (King)game.getTile(i).getPiece();
				}
			}
		}
		for (int i=0; i<yourPieces.size(); i++) {
			int temp = yourPieces.get(i);
			String[] move = {game.getFileRank(temp), game.getFileRank(enemykingTile)};
			Piece p = game.getTile(temp).getPiece();
			if (p.isLegalMove(color, move, game)) {
				return enemyKing;
			}
		}
		return null;
	}
	
	/**
	 * Checks to see if the given move is a legal castling move.
	 * 
	 * @param game		chessboard instance
	 * @param initPos	The tile number of initial position
	 * @param endPos	The tile number of designated end position
	 * @param color		The color of the player 
	 * @param castled	The boolean value, true if King has castled
	 * @return			If the move is a legal castling move, it returns the Rook to be castled
	 * 					with, otherwise, it returns null.
	 */
	private Rook isCastling(ChessBoard game, int initPos, int endPos, String color, boolean castled) {
		Rook rook = null;
		if (color.equals("w") && initPos==61 && !castled) {
			if (endPos==63) {
				Tile corner = game.getTile(64);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos+1; i<=63; i++) {
							if (game.getTile(i).getPiece()!=null) {
								return null;
							}
						}
					} 
				} 
			} else if (endPos==59) {
				Tile corner = game.getTile(57);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos-1; i<=58; i--) {
							if (game.getTile(i).getPiece()!=null) {
								return null;
							}
						}
					} 
				}
			} 
		} else if (color.equals("b") && initPos==5 && !castled) {
			if (endPos==7) {
				Tile corner = game.getTile(8);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos+1; i<=7; i++) {
							if (game.getTile(i).getPiece()!=null) {
								return null;
							}
						}
					} 
				}
			} else if (endPos==3) {
				Tile corner = game.getTile(1);
				Piece cornerPiece = corner.getPiece();
				if (cornerPiece!=null && cornerPiece.getpieceType().equals("R")) { 
					rook = (Rook)cornerPiece;
					if (rook.firstMove) {
						for (int i=initPos-1; i<=2; i--) {
							if (game.getTile(i).getPiece()!=null) {
								return null;
							}
						}
					} 
				}
			} 
		}
		return rook;
	}
	
	
	/**
	 * Gets the move a Rook must make to complete a castling move.
	 * 
	 * @param initPos	The initial position of the tile where the King is located 
	 * @param endPos	The end position of the tile where the King is castling to 
	 * @return			An array of integers representing the move[]
	 */
	private int[] getRookMoveCastle(int initPos, int endPos) {
		int[] move=new int[2];
		if (initPos==5) {
			if (endPos==3) {
				move[0] = 1;
				move[1] = 4;
			} else if (endPos==7) {
				move[0] = 8;
				move[1] = 6;
			}
		} else if (initPos==61) {
			if (endPos==63) {
				move[0] = 64;
				move[1] = 62;
			} else if (endPos==59) {
				move[0] = 57;
				move[1] = 60;
			}
		}
		return move;
	}
	
	/**
	 * Checks if tile is Attacked, used to identify tiles a King cannot go to
	 * 
	 * @param game		chessboard instance
	 * @param tileNum	number of tile being checked out
	 * @return			If tile is attacked and the king cannot go there, return true, else return false
	 */
	private boolean tileIsAttacked(ChessBoard game, int tileNum, String color) {
		ArrayList<Integer> enemyPieces = new ArrayList<>();
		for (int i=1; i<=64; i++) {
			Piece p = game.getTile(i).getPiece();
			if (p!=null) {
				if (!p.getpieceColor().equals(color)) {
					enemyPieces.add(i);
				}
			}
		}
		for (int i=0; i<enemyPieces.size(); i++) {
			int temp = enemyPieces.get(i);
			String[] move = {game.getFileRank(temp), game.getFileRank(tileNum)};
			Piece p = game.getTile(temp).getPiece();
			if (p.isLegalMove(color, move, game)) return true;
		}
		return false;
	}
	
	/**
	 * Determines if a given move is legal.
	 * The abstract boolean method for a given Piece
	 * 
	 * @param move	The move to be made in FileRank format.
	 * @param game	chessboard instance
	 * @return		Returns true if the move is legal, or false if not.
	 */
	public abstract boolean isLegalMove(String currColor, String[] move, ChessBoard game);
}
