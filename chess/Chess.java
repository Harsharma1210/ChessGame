package chess;

import java.util.Scanner;
import player.Player;
import board.ChessBoard;
import pieces.*;

/**
 * An implementation of the Main Chess Class.
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class Chess {
	/**
	 * The main method for the chess project
	 * 
	 * @param args
	 * 		An array of arguments passed into the program at runtime
	 */
	public static void main(String[] args) {
		Player whitePlayer = new Player("white");
		Player blackPlayer = new Player("black");
		Player curr = whitePlayer;
		String currColor = "";
		String nextColor = "";
		King enemyKing = null;
		boolean isDraw = false;
		
		ChessBoard game = new ChessBoard();

		game.printBoard();
		
		//Starts The Game
		while(true) {
            //turnCounter++;
			Scanner sc = new Scanner(System.in);
		
			
            if (curr.equals(whitePlayer)) {
                currColor = "White"; nextColor = "Black";
            } else {
                currColor = "Black"; nextColor = "White";
            }
                      
            enemyKing = findKing(game, currColor);
			//System.out.println(enemyKing);

			//Starts The Turn
			while(true)
			{
				//System.out.println("It is turn: " + turnCounter);
            	System.out.print(currColor+"'s move: ");
            	String move[] = sc.nextLine().trim().split("\s+");

				//Resign
				if (move[0].equals("resign")) {
					System.out.print(nextColor+" wins");
					sc.close();
					return;
				} 
            
				//Complete Draw
				if (isDraw) {
					if (move[0].equals("draw")) {
						sc.close();
						return;
					} 
					else {
						System.out.println("You Are Obligated to Draw!");
						continue;
					}
				}


				//Get's the Piece at the Tile Listed
    			Piece piece = game.getTile(game.getTileNum(move[0])).getPiece();
    		
				//Check To See If A Piece Exists At The Tile
    			if(piece != null && piece.getpieceColor().equals(curr.toString())){
    				//Check Which Piece Is At The Tile
					if(piece.getpieceType().equals("P")){ //Pawn
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move,game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
					
					if(piece.getpieceType().equals("R")){ //Rook
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move, game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
					
					if(piece.getpieceType().equals("B")){ //Bishop
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move, game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
					
					if(piece.getpieceType().equals("K")){ //King
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move, game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
					
					if(piece.getpieceType().equals("N")){ //Knight
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move, game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
					
					if(piece.getpieceType().equals("Q")){ //Queen
						if(piece.isLegalMove(currColor, move, game)) {
							piece.move(move, game);
						}
						else {
							System.out.println("Illegal Move. Try Again.");
							continue;
						}
					}
    			}
				else {
					System.out.println("Illegal Move. Try Again.");
					continue;
				}	

				if (move.length>2 && move[2].equals("draw?")) {
					isDraw=true;
				}

				break;
			}
			
			//check if player checks other player's king
			if (enemyKing.inCheck) {
				System.out.println("Check");
				enemyKing.inCheck=false;
			}
			
			if(enemyKing.inCheckMate) {
				System.out.println("Checkmate");
				System.out.println(currColor+" wins");
				sc.close();
				return;
			}

			//Print Game Board
			System.out.println();
			game.printBoard();

            curr = (curr == whitePlayer) ? blackPlayer : whitePlayer;
        }
	}

	/**
	 * A method to find the king on a chessboard given its color
	 * 
	 * @param game
	 * 		A variable that give you access to the rest of the game
	 * @param color
	 * 		The color of the king you want to find
	 * @return
	 * 		A king object
	 */
	private static King findKing(ChessBoard game, String color) {
		King king=null;
		for (int i=1; i<=64; i++) {
			Piece p = game.getTile(i).getPiece();

			if (p!=null) {
				if (!p.getpieceColor().equals(color) && p.getpieceType().equals("K")) {
					king = (King)p;
					break;
				}
			}
		}
		return king;
	}
}
