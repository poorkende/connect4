package gameplay;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connect4 game = new Connect4();
        Connect4Position position = new Connect4Position();
        boolean playerTurn = true; // Kezdés: emberi játékos

        while (!game.wonPosition(position, true) && !game.wonPosition(position, false) && !game.drawnPosition(position)) {
            game.printPosition(position);

            // Játékos választ oszlopot
            if (playerTurn) {
                System.out.println("Válassz egy oszlopot (0-6): ");
                int column = scanner.nextInt();
                // Ellenőrizzük, hogy a választott oszlop érvényes-e
                while (column < 0 || column >= Connect4Position.C || position.board[0][column] != Connect4Position.BLANK) {
                    System.out.println("Érvénytelen oszlop, próbálj meg újra (0-6): ");
                    column = scanner.nextInt();
                }
                Connect4Move move = new Connect4Move(column);
                position = (Connect4Position) game.makeMove(position, playerTurn, move);
                playerTurn = !playerTurn; // Játékosváltás
            } else {
                // A gép lépése
                System.out.println("A gép lépése...");
                Vector v = game.alphaBeta(0, position, false);
                Connect4Position newPosition = (Connect4Position) v.elementAt(1);
                position = newPosition;
                playerTurn = !playerTurn; // Játékosváltás
            }
        }

        game.printPosition(position);
        if (game.wonPosition(position, true)) {
            System.out.println("Az ember nyert!");
        } else if (game.wonPosition(position, false)) {
            System.out.println("A gép nyert!");
        } else {
            System.out.println("Döntetlen!");
        }

        scanner.close();
    }
}
