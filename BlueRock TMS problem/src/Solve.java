public class Solve {
    private final int depth;
    private Board board;
    private char[][] grid;
    public Solve(Board board) {
        this.board = board;
        this.depth = this.board.getDepth();
        this.grid = this.board.getGrid();
    }

    private boolean isValid() {
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve(Board board, Piece[] pieces, int index) {
        if (index == pieces.length) {
            return this.isValid();
        }

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[0].length; col++) {
                if (board.canPlace(pieces[index], row, col)) {
                    board.placePiece(pieces[index], row, col, depth);
                    if (solve(board, pieces, index + 1)) {
                        return true;
                    }
                    board.removePiece(pieces[index], row, col, depth);
                }
            }
        }
        return false;
    }
}
