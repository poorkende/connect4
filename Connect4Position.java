package gameplay;

public class Connect4Position extends Position {
    static final int C = 7, R = 6;
    public static final int BLANK = 0;
    public static final int HUMAN = 1;
    public static final int PROGRAM = -1;
    int[][] board;

    // Alapértelmezett konstruktor (üres játéktábla)
    public Connect4Position() {
        board = new int[R][C];
    }

    // Másoló konstruktor
    public Connect4Position(Connect4Position other) {
        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            System.arraycopy(other.board[i], 0, this.board[i], 0, C);
        }
    }
}
