package mainpackage;


public class solver {
	public static int tries = 0;
	private static final int SIZE = 9;  							//standard board size
	
	public static void main(String[] args) {
		int [][] board = {											//replace the board to solve your own sudoku problem
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0}
				};
		
		if (solve(board)) {
			printBoard(board);
		}else {
			System.out.println("Unsolvable");
		}
		
	}
	
	private static void printBoard(int[][] board) {
		for (int row = 0; row < SIZE; row ++) {
			for (int column = 0; column < SIZE; column ++) {
				System.out.print(board[row][column]);
				if (column%3 - 2 == 0 && column < SIZE-1) {System.out.print("|");}
			}
			System.out.print("\n");
			if (row%3 - 2 == 0 && row < SIZE-1) {System.out.print("\n");}
		}
		System.out.println("Took "+tries+" tries.");
	}

	private static boolean isValid(int [][] board, int number, int row, int column) {
		return !inRow(board, number, row) && !inColumn(board, number, column) && !inGridlet(board, number, row, column);
	}
	
	private static boolean inRow(int [][] board, int number, int row) {
		for (int i = 0; i < SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}return false;
	}
	
	private static boolean inColumn(int [][] board, int number, int column) {
		for (int i = 0; i < SIZE; i++) {
			if (board[i][column] == number) {return true;}
		}
		return false;
	}
	
	private static boolean inGridlet(int [][] board, int number, int row, int column) {
		int gridletrow = row - row % 3;								//this is how we locate the gridlet
		int gridletcol = column - column % 3;
		
		for (int i = gridletrow; i < gridletrow + 3; i++) {
			for (int j = gridletcol; j < gridletcol + 3; j++) {
				if (board[i][j] == number) {return true;}	
			}
		}
		return false;
	}
	
	
	private static boolean solve(int[][]board) {
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				if(board[row][column]==0) {
					for (int x = 1; x <= SIZE; x++) {				//x is the number we are inputing in every repetition
						if (isValid(board, x, row, column)){
							board[row][column] = x;					//we try x. then we try to solve the rest of the grid.
							tries++;
							if (solve(board)) {return true;}  		//we backtrack in the scenario that we end up in an unsolvable position
							else {board[row][column] = 0;}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
}
