import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BoardGame {

    public static void main(String[] args) {

        String filePath = "src/samples/00.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line1 = bufferedReader.readLine();
            String line2 = bufferedReader.readLine();
            String line3 = bufferedReader.readLine();

            String[] rows = line2.split(",");
            char[][] board = new char[rows.length][rows[0].length()];

            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows[0].length(); j++) {
                    board[i][j] = rows[i].charAt(j);
                }
            }

            int depth = Integer.parseInt(line1);

            String[] pieceStrings = line3.split(" ");
            int num_pieces = pieceStrings.length;
            Piece[] pieces = new Piece[num_pieces];

            for (int i = 0; i < num_pieces; i++) {
                String[] n_rows = pieceStrings[i].split(",");
                char[][] piece = new char[n_rows.length][n_rows[0].length()];
                for (int row = 0; row < n_rows.length; row++) {
                    for (int col = 0; col < n_rows[0].length(); col++) {
                        piece[row][col] = n_rows[row].charAt(col);
                    }
                }
                pieces[i] = new Piece(piece);
            }

            int m = board.length;
            int n = board[0].length;

            Board gameBoard = new Board(depth, m, n);
            gameBoard.setGrid(board);
            final Solve solver = new Solve(gameBoard);

            if (solver.solve(gameBoard, pieces, 0)) {
                for (int[] coordinates : gameBoard.getPlacedCoordinates()) {
                    System.out.print(coordinates[1] + "," + coordinates[0]);
                    System.out.print(" ");
                }
            } else {
                System.out.println("No solution found.");
            }

            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
