import java.util.Scanner;
import java.util.logging.Logger;

public final class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private App() {
        throw new UnsupportedOperationException("Do not instantiate utility");
    }

    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        byte winner;

        while (true) {
            printBoard(box);

            // Player
            byte input = getPlayerInput(scan, box);
            box[input - 1] = 'X';

            winner = checkWinner(box);
            if (winner != 0) {
                break;
            }

            // Computer
            computerTurn(box);

            winner = checkWinner(box);
            if (winner != 0) {
                break;
            }

            // Draw
            if (isBoardFull(box)) {
                winner = 3;
                break;
            }
        }

        displayResult(winner); // Result of the game
    }

    // Displaying the board on the screen
    private static void printBoard(final char[] board) {
        logger.info(() -> String.format(
                "%n%n %c | %c | %c %n"
                        + "-----------%n %c | %c | %c %n"
                        + "-----------%n %c | %c | %c %n",
                board[0], board[1], board[2],
                board[3], board[4], board[5],
                board[6], board[7], board[8]
        ));
    }

    private static byte getPlayerInput(final Scanner scan, final char[] box) {
        byte input;
        while (true) {
            logger.info("Enter box number (1-9): ");
            input = scan.nextByte();
            if (input > 0 && input < 10
                    && box[input - 1] != 'X'
                    && box[input - 1] != 'O') {
                return input;
            } else {
                logger.info("Invalid input. Please try again.");
            }
        }
    }

    private static void computerTurn(final char[] box) {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                box[i] = 'O';
                break;
            }
        }
    }

    // Method to check if there is a winner
    private static byte checkWinner(final char[] box) {

        int[][] winningCombinations = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int player = 0; player <= 1; player++) {
            char symbol = player == 0 ? 'X' : 'O';
            for (int[] combination : winningCombinations) {
                if (box[combination[0]] == symbol
                        && box[combination[1]] == symbol
                        && box[combination[2]] == symbol) {
                    return (byte) (player + 1);
                }
            }
        }

        return 0; // No winner
    }

    // Check if the board is full
    private static boolean isBoardFull(final char[] box) {
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                return false; // The board is not full
            }
        }
        return true;
    }

    // Display the result of the game
    private static void displayResult(final byte winner) {
        if (winner == 1) {
            logger.info("You won the game!");
        } else if (winner == 2) {
            logger.info("Computer won the game!");
        } else {
            logger.info("It's a draw!");
        }
    }
}