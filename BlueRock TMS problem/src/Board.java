import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int depth;
    private char[][] grid;
    private List<int[]> placed;

    public int getDepth() {
        return depth;
    }

    public Board(int depth, int m, int n) {
        this.depth = depth;
        this.grid = new char[m][n];
        this.placed = new ArrayList<>();
    }


    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(char[][] board) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = board[i][j];
            }
        }
    }

    public List<int[]> getPlacedCoordinates() {
        return placed;
    }

    public void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public boolean canPlace(Piece piece, int row, int col) {

        int m = piece.shape.length;
        int n = piece.shape[0].length;

        if (row + m > grid.length || col + n > grid[0].length) {
            return false;
        }

        return true;
    }

    public void placePiece(Piece piece, int row, int col, int depth) {
        int m = piece.shape.length;
        int n = piece.shape[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (piece.shape[i][j] == 'X') {
                    if (Character.getNumericValue(grid[row + i][col + j]) == depth - 1) {
                        grid[row + i][col + j] = '0';
                    } else {
                        char newVal = String.valueOf(Character.getNumericValue(grid[row + i][col + j]) + 1).charAt(0);
                        grid[row + i][col + j] = newVal;
                    }
                }
            }
        }

        placed.add(new int[]{row, col});
    }

    public void removePiece(Piece piece, int row, int col, int depth) {
        int m = piece.shape.length;
        int n = piece.shape[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (piece.shape[i][j] == 'X') {
                    if (grid[row + i][col + j] == '0') {
                        grid[row + i][col + j] = String.valueOf(depth - 1).charAt(0);
                    } else {
                        char newVal = String.valueOf(Character.getNumericValue(grid[row + i][col + j]) - 1).charAt(0);
                        grid[row + i][col + j] = newVal;
                    }
                }
            }
        }
        placed.removeLast();
    }
}