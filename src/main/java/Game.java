import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scan;
    private boolean boxEmpty;
    private int winner;

    public Game(Scanner scan) {
        this.board = new Board();
        this.scan = scan;
        this.boxEmpty = false;
        this.winner = 0;
    }

    public void start() {
        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            board.printBoard();

            if (!boxEmpty) {
                board.clearBoard();
                boxEmpty = true;
            }

            if (winner != 0) {
                displayWinner(winner);
                break;
            }

            Player.playerMove(scan, board);

            if (board.checkWin('X')) {
                winner = 1;
                continue;
            }

            if (!board.isMoveAvailable()) {
                winner = 3;
                continue;
            }

            Computer.computerMove(board);

            if (board.checkWin('O')) {
                winner = 2;
                continue;
            }
        }
    }

    private void displayWinner(int winner) {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }
}