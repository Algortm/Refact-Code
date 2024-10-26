import java.util.Scanner;
import java.util.logging.Logger;

public class GameMethods {

    private static final Logger logger = Logger.getLogger(GameMethods.class.getName());
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final Scanner scan;

    public GameMethods(Scanner scan) {
        this.scan = scan;
    }

    public void playGame() {
        byte winner;

        while (true) {
            printBoard();

            // Player
            byte input = getPlayerInput();
            box[input - 1] = 'X';

            winner = checkWinner();
            if (winner != 0) {
                break;
            }

            // Computer
            computerTurn();

            winner = checkWinner();
            if (winner != 0) {
                break;
            }

            // Draw
            if (isBoardFull()) {
                winner = 3;
                break;
            }
        }

        displayResult(winner);
    }

    // Displaying the board on the screen
    private void printBoard() {
        logger.info(() -> String.format(
                "%n%n %c | %c | %c %n"
                        + "-----------%n %c | %c | %c %n"
                        + "-----------%n %c | %c | %c %n",
                box[0], box[1], box[2],
                box[3], box[4], box[5],
                box[6], box[7], box[8]
        ));
    }

    private byte getPlayerInput() {
        byte input;
        while (true) {
            logger.info("Enter box number (1-9): ");
            input = scan.nextByte();
            if (input > 0 && input < 10 && box[input - 1] != 'X' && box[input - 1] != 'O') {
                return input;
            } else {
                logger.info("Invalid input. Please try again.");
            }
        }
    }

    private void computerTurn() {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                box[i] = 'O';
                break;
            }
        }
    }

    // Method to check if there is a winner
    private byte checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int player = 0; player <= 1; player++) {
            char symbol = player == 0 ? 'X' : 'O';
            for (int[] combination : winningCombinations) {
                if (box[combination[0]] == symbol &&
                        box[combination[1]] == symbol &&
                        box[combination[2]] == symbol) {
                    return (byte) (player + 1);
                }
            }
        }
        return 0; // Немає переможця
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }

    // Display the result of the game
    private void displayResult(byte winner) {
        if (winner == 1) {
            logger.info("You won the game!");
        } else if (winner == 2) {
            logger.info("Computer won the game!");
        } else {
            logger.info("It's a draw!");
        }
    }
}