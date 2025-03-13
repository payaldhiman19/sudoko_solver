import java.util.*;
public class sudokusolver {

    static final int N = 9;
    // Check if it's safe to place a number at the given row and column
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if num is already present in the row, column, or 3x3 grid
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num || board[i][col] == num ){
                return false;
            }
        }
            int nrow=row - row % 3 ;
            int ncol=col - col % 3 ;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                if(board[i+nrow][j+ncol] == num) {
                return false;
            }
        }
        }
        return true;
    }
    // Print the Sudoku board
    public static void printBoard(int[][] grid) {
        System.out.println("--------------WELCOME TO SUDOKU Game!-----------------");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (col == 3 || col == 6)
                    System.out.print(" | ");
                System.out.print(grid[row][col] + " ");
            }
            if (row == 2 || row == 5) {
                System.out.println();
                for (int i = 0; i < N; i++)
                    System.out.print("---");
            }
            System.out.println();
        }
    }
    // Solve the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] board, int row, int col) {
        // If all cells are filled, the puzzle is solved
        if (row == N - 1 && col == N)
            return true;
        //if we reahed the end in column we need to move to next row and again we will be from first column
        if (col == N) {
            row++;
            col = 0;
        }
        //if current cell is empty then only solve the soduko
        if (board[row][col] != 0)
            return solveSudoku(board, row, col + 1);
        // solving the cell number from 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, row, col + 1))
                    return true;
                board[row][col] = 0; // Backtrack if invalid move 
            }
        }
        return false;
    }
    // Check if the Sudoku puzzle is completely solved
    public static boolean isSolvedCompletely(int[][] grid) {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                if (grid[row][col] == 0)
                    return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0},
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0}
        };
        printBoard(board);
        while (true) {
            System.out.print("Enter row (1-9), column (1-9), and number to input separated by spaces (e.g., '3 4 5'): ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            int num = sc.nextInt();

            // Validate input
            if (row < 1 || row > 9 || col < 1 || col > 9 || num < 1 || num > 9) {
                System.out.println("Invalid input! Please enter valid row, column, and number (1-9).");
                continue;
            }
            row--;
            col--;
            if (!isSafe(board, row, col, num)) {
                System.out.println("Invalid move. Number conflicts with existing numbers in the row, column, or 3x3 box.");
                continue;
            }
            board[row][col] = num;
            if (isSolvedCompletely(board)) {
                System.out.println("Congratulations! You have solved the puzzle.");
                printBoard(board);
                break;
            }
            printBoard(board);
        }
        sc.close();
    }
}
