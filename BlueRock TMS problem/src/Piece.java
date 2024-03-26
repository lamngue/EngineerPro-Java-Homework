public class Piece {
    char[][] shape;

    Piece(char[][] shape) {
        this.shape = shape;
    }

    public void print() {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                System.out.print(shape[i][j]);
            }
            System.out.println();
        }
    }
}
