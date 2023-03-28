package board;

import java.util.ArrayList;
import pieces.*;

/**
 * An implementation of Chessboard
 * 
 * @author    Harsh Sharma
 * @author    Ethan Nguyen
 */
public class ChessBoard {
	public Tile[] board;
	ArrayList<Integer> edges;
	ArrayList<Integer> corners;
	public boolean EnPassant; //if the game allows En Passant
	public int lastMove;
	
	/**
	 * A constructor for the Chessboard. It calls the createBoard() method, fillEdges() method and fillCorners() method.
	 */
	public ChessBoard() {
		this.board = createBoard();
		fillEdges();
		fillCorners();
		EnPassant = false;
		lastMove = 0;
	}
	
	/**
	 * A method to create all the tile objects and piece objects on the chessboard
	 * 
	 * @return
	 * 		An array of tiles that act as the "board"
	 */
	private Tile[] createBoard() {
		board = new Tile[64];
		board[0] = new Tile(1, new Rook("b"),"w");
		board[1] = new Tile(2, new Knight("b"),"b");
		board[2] = new Tile(3, new Bishop("b"),"w");
		board[3] = new Tile(4, new Queen("b"),"b");
		board[4] = new Tile(5, new King("b"),"w");
		board[5] = new Tile(6, new Bishop("b"),"b");
		board[6] = new Tile(7, new Knight("b"),"w");
		board[7] = new Tile(8, new Rook("b"),"b");
		board[8] = new Tile(9, new Pawn("b"),"b");
		board[9] = new Tile(10, new Pawn("b"),"w");
		board[10] = new Tile(11, new Pawn("b"),"b");
		board[11] = new Tile(12, new Pawn("b"),"w");
		board[12] = new Tile(13, new Pawn("b"),"b");
		board[13] = new Tile(14, new Pawn("b"),"w");
		board[14] = new Tile(15, new Pawn("b"),"b");
		board[15] = new Tile(16, new Pawn("b"),"w");
		board[16] = new Tile(17, null, "w");
		board[17] = new Tile(18, null,"b");
		board[18] = new Tile(19, null,"w");
		board[19] = new Tile(20, null,"b");
		board[20] = new Tile(21, null,"w");
		board[21] = new Tile(22, null,"b");
		board[22] = new Tile(23, null,"w");
		board[23] = new Tile(24, null,"b");
		board[24] = new Tile(25, null,"b");
		board[25] = new Tile(26, null,"w");
		board[26] = new Tile(27, null,"b");
		board[27] = new Tile(28, null,"w");
		board[28] = new Tile(29, null,"b");
		board[29] = new Tile(30, null,"w");
		board[30] = new Tile(31, null,"b");
		board[31] = new Tile(32, null,"w");
		board[32] = new Tile(33, null,"w");
		board[33] = new Tile(34, null,"b");
		board[34] = new Tile(35, null,"w");
		board[35] = new Tile(36, null,"b");
		board[36] = new Tile(37, null,"w");
		board[37] = new Tile(38, null,"b");
		board[38] = new Tile(39, null,"w");
		board[39] = new Tile(40, null,"b");
		board[40] = new Tile(41, null,"b");
		board[41] = new Tile(42, null,"w");
		board[42] = new Tile(43, null,"b");
		board[43] = new Tile(44, null,"w");
		board[44] = new Tile(45, null,"b");
		board[45] = new Tile(46, null,"w");
		board[46] = new Tile(47, null,"b");
		board[47] = new Tile(48, null,"w");
		board[48] = new Tile(49, new Pawn("w"),"w");
		board[49] = new Tile(50, new Pawn("w"),"b");
		board[50] = new Tile(51, new Pawn("w"),"w");
		board[51] = new Tile(52, new Pawn("w"),"b");
		board[52] = new Tile(53, new Pawn("w"),"w");
		board[53] = new Tile(54, new Pawn("w"),"b");
		board[54] = new Tile(55, new Pawn("w"),"w");
		board[55] = new Tile(56, new Pawn("w"),"b");
		board[56] = new Tile(57, new Rook("w"),"b");
		board[57] = new Tile(58, new Knight("w"),"w");
		board[58] = new Tile(59, new Bishop("w"),"b");
		board[59] = new Tile(60, new Queen("w"),"w");
		board[60] = new Tile(61, new King("w"),"b");
		board[61] = new Tile(62, new Bishop("w"),"w");
		board[62] = new Tile(63, new Knight("w"),"b");
		board[63] = new Tile(64, new Rook("w"),"w");
		return board;
	}
	
	/**
	 * A method to return the tile at the specific point in the array
	 * 
	 * @param tileNum
	 * 		The position on the "board" (the position in the array)
	 * @return
	 * 		The tile object at the space on the "board"
	 */
	public Tile getTile(int tileNum) {
		if (tileNum<1 || tileNum>64) {
			throw new IllegalArgumentException("Out of Bounds");
		}
		return board[tileNum-1];
	}
	
	/**
	 * A method to get the coordinates of a tile
	 * 
	 * @param tileNum
	 * 		The number tile on the board that is in question
	 * @return
	 * 		The coordinates of the tile
	 */
	public String getFileRank(int tileNum) {
		String res = "";
		switch(tileNum) {
		case 1: return "a8"; case 2: return "b8"; case 3: return "c8";
		case 4: return "d8"; case 5: return "e8"; case 6: return "f8"; 
		case 7: return "g8"; case 8: return "h8"; case 9: return "a7"; 
		case 10: return "b7"; case 11: return "c7"; case 12: return "d7"; 
		case 13: return "e7"; case 14: return "f7"; case 15: return "g7"; 
		case 16: return "h7"; case 17: return "a6"; case 18: return "b6"; 
		case 19: return "c6"; case 20: return "d6"; case 21: return "e6"; 
		case 22: return "f6"; case 23: return "g6"; case 24: return "h6";
		case 25: return "a5"; case 26: return "b5"; case 27: return "c5"; 
		case 28: return "d5"; case 29: return "e5"; case 30: return "f5"; 
		case 31: return "g5"; case 32: return "h5"; case 33: return "a4"; 
		case 34: return "b4"; case 35: return "c4"; case 36: return "d4"; 
		case 37: return "e4"; case 38: return "f4"; case 39: return "g4"; 
		case 40: return "h4"; case 41: return "a3"; case 42: return "b3";
		case 43: return "c3"; case 44: return "d3"; case 45: return "e3"; 
		case 46: return "f3"; case 47: return "g3"; case 48: return "h3";
		case 49: return "a2"; case 50: return "b2"; case 51: return "c2"; 
		case 52: return "d2"; case 53: return "e2"; case 54: return "f2";
		case 55: return "g2"; case 56: return "h2"; case 57: return "a1";
		case 58: return "b1"; case 59: return "c1"; case 60: return "d1"; 
		case 61: return "e1"; case 62: return "f1"; case 63: return "g1"; 
		case 64: return "h1"; 
		default: break;
		}
		return res;
	}
	
	/**
	 * A method to get the number of a tile given its coordinates
	 * 
	 * @param fileRank
	 * 		The cooridnates of a tile
	 * @return
	 * 		The number of the tile in question
	 */
	public int getTileNum(String fileRank) {
		int res=0;
		switch(fileRank) {
		case "a8": return res=1; case "b8": return res=2; case "c8": return res=3;
		case "d8": return res=4; case "e8": return res=5; case "f8": return res=6;
		case "g8": return res=7; case "h8": return res=8; case "a7": return res=9;
		case "b7": return res=10; case "c7": return res=11; case "d7": return res=12;
		case "e7": return res=13; case "f7": return res=14; case "g7": return res=15;
		case "h7": return res=16; case "a6": return res=17; case "b6": return res=18;
		case "c6": return res=19; case "d6": return res=20; case "e6": return res=21;
		case "f6": return res=22; case "g6": return res=23; case "h6": return res=24;
		case "a5": return res=25; case "b5": return res=26; case "c5": return res=27;
		case "d5": return res=28; case "e5": return res=29; case "f5": return res=30;
		case "g5": return res=31; case "h5": return res=32; case "a4": return res=33;
		case "b4": return res=34; case "c4": return res=35; case "d4": return res=36;
		case "e4": return res=37; case "f4": return res=38; case "g4": return res=39;
		case "h4": return res=40; case "a3": return res=41; case "b3": return res=42;
		case "c3": return res=43; case "d3": return res=44; case "e3": return res=45;
		case "f3": return res=46; case "g3": return res=47; case "h3": return res=48;
		case "a2": return res=49; case "b2": return res=50; case "c2": return res=51;
		case "d2": return res=52; case "e2": return res=53; case "f2": return res=54;
		case "g2": return res=55; case "h2": return res=56; case "a1": return res=57;
		case "b1": return res=58; case "c1": return res=59; case "d1": return res=60;
		case "e1": return res=61; case "f1": return res=62; case "g1": return res=63;
		case "h1": return res=64;
		default: break;
		}
		return res;
	}
	
	/**
	 * A method to print the chess board in a specific format
	 */
	public void printBoard() {
		int count = 8;
		for (int i =0; i < board.length; i++) {
			if(board[i].getPiece() == null && board[i].getColor().equals("w")) {
				System.out.print("   ");
			}
			else if (board[i].getPiece() == null && board[i].getColor().equals("b")) {
				System.out.print("## ");
			}
			else 
				System.out.print(board[i].getPiece() + " ");
			
			if((i + 1) % 8 == 0) {
				System.out.println(count);
				count--;
			} 
			
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();
	}

	/**
	 * A method to add the edges of the board to an arraylist
	 */
	void fillEdges() {
		edges=new ArrayList<>();
		for (int i=2; i<8; i++) edges.add(i);
		for (int i=9; i<57; i++) {
			if (i%8==1 || i%8==0) edges.add(i);
		}
		for (int i=58; i<64; i++) edges.add(i);
	}

	/**
	 * A method to check if a spot on the board is the edge or not
	 * 
	 * @param tileNum
	 * 		The tile of the board in question
	 * @return
	 * 		True or False depedning on if the edges arraylist contains the tileNum
	 */
	public boolean isEdge(int tileNum) {
		return edges.contains(tileNum);
	}
	
	/**
	 * A method to add the corner spots to an arraylist
	 */
	void fillCorners() {
		corners=new ArrayList<>();
		corners.add(1);
		corners.add(8);
		corners.add(57);
		corners.add(64);
	}
	
	/**
	 * A method to check is a spot on the board is a corner or not
	 * 
	 * @param tileNum
	 * 		The tile of the board in question
	 * @return
	 * 		True or Flase depending on if the corners arraylist contains the tileNum
	 */
	public boolean isCorner(int tileNum) {
		return corners.contains(tileNum);
	}
	
}
