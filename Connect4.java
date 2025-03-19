package gameplay;

import java.util.ArrayList;
import java.util.List;

public class Connect4 extends GameSearch {

    @Override
    public boolean drawnPosition(Position p) {
        Connect4Position pos = (Connect4Position) p;
        for (int row = 0; row < Connect4Position.R; row++) {
            for (int col = 0; col < Connect4Position.C; col++) {
                if (pos.board[row][col] == Connect4Position.BLANK) {
                    return false;
                }
            }
        }
        return !wonPosition(p, true) && !wonPosition(p, false);
    }

    @Override
    public boolean wonPosition(Position p, boolean player) {
        int sign = player ? Connect4Position.HUMAN : Connect4Position.PROGRAM;
        Connect4Position pos = (Connect4Position) p;

        // Vízszintes ellenőrzés
        for (int row = 0; row < Connect4Position.R; row++) {
            for (int col = 0; col <= Connect4Position.C - 4; col++) {
                if (pos.board[row][col] == sign && pos.board[row][col + 1] == sign &&
                        pos.board[row][col + 2] == sign && pos.board[row][col + 3] == sign) {
                    return true;
                }
            }
        }

        // Függőleges ellenőrzés
        for (int col = 0; col < Connect4Position.C; col++) {
            for (int row = 0; row <= Connect4Position.R - 4; row++) {
                if (pos.board[row][col] == sign && pos.board[row + 1][col] == sign &&
                        pos.board[row + 2][col] == sign && pos.board[row + 3][col] == sign) {
                    return true;
                }
            }
        }

        // Átlós (jobbra lefelé)
        for (int row = 0; row <= Connect4Position.R - 4; row++) {
            for (int col = 0; col <= Connect4Position.C - 4; col++) {
                if (pos.board[row][col] == sign && pos.board[row + 1][col + 1] == sign &&
                        pos.board[row + 2][col + 2] == sign && pos.board[row + 3][col + 3] == sign) {
                    return true;
                }
            }
        }

        // Átlós (balra lefelé)
        for (int row = 0; row <= Connect4Position.R - 4; row++) {
            for (int col = 3; col < Connect4Position.C; col++) {
                if (pos.board[row][col] == sign && pos.board[row + 1][col - 1] == sign &&
                        pos.board[row + 2][col - 2] == sign && pos.board[row + 3][col - 3] == sign) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public double positionEvaluation(Position p, boolean player) {
        if (wonPosition(p, player)) return 100;
        if (wonPosition(p, !player)) return -100;
        return 0;
    }

    @Override
    public void printPosition(Position p) {
        Connect4Position pos = (Connect4Position) p;
        for (int row = 0; row < Connect4Position.R; row++) {
            for (int col = 0; col < Connect4Position.C; col++) {
                char symbol = (pos.board[row][col] == Connect4Position.HUMAN) ? 'X' :
                        (pos.board[row][col] == Connect4Position.PROGRAM) ? 'O' : '.';
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Position[] possibleMoves(Position p, boolean player) {
        List<Position> moves = new ArrayList<>();
        Connect4Position pos = (Connect4Position) p;

        for (int col = 0; col < Connect4Position.C; col++) {
            for (int row = Connect4Position.R - 1; row >= 0; row--) {
                if (pos.board[row][col] == Connect4Position.BLANK) {
                    Connect4Position newPos = new Connect4Position(pos);
                    newPos.board[row][col] = player ? Connect4Position.HUMAN : Connect4Position.PROGRAM;
                    moves.add(newPos);
                    break;
                }
            }
        }

        return moves.toArray(new Position[0]);
    }

    @Override
    public Position makeMove(Position p, boolean player, Move move) {
        Connect4Position pos = (Connect4Position) p;
        int col = ((Connect4Move) move).column;

        for (int row = Connect4Position.R - 1; row >= 0; row--) {
            if (pos.board[row][col] == Connect4Position.BLANK) {
                Connect4Position newPos = new Connect4Position(pos);
                newPos.board[row][col] = player ? Connect4Position.HUMAN : Connect4Position.PROGRAM;
                return newPos;
            }
        }
        return p; // Ha nincs érvényes lépés
    }

    @Override
    public boolean reachedMaxDepth(Position p, int depth) {
        return depth >= 1; // Példa: mélységkorlát 6
    }

    @Override
    public Move createMove() {
        return new Connect4Move(0); // Alapértelmezett oszlop
    }
}
