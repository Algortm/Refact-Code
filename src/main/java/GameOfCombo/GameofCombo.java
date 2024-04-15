package GameOfCombo;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GameofCombo {

    private static Logger logger;
    private final Random random = new Random();

    static {
        try (FileInputStream ins = new FileInputStream("src/main/java/config/log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getLogger(GameofCombo.class.getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    private final Scanner scanner = new Scanner(System.in);
    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private final char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void startGame() {
        boolean boardPrepared = false;
        boolean gameConcluded = false;

        while (!gameConcluded) {
            showBoard(board);

            if (!boardPrepared) {
                sweepBoard();
                boardPrepared = true;
            }

            if (checkWinner(PLAYER_SYMBOL)) {
                gameConcluded = true;
                showResults("Player won!");
            } else if (checkWinner(COMPUTER_SYMBOL)) {
                gameConcluded = true;
                showResults("Computer won!");
            } else if (isBoardFull()) {
                gameConcluded = true;
                showResults("It's a stalemate!");
            }

            if (!gameConcluded) {
                playerMove();
                computerMove();
            }
        }
    }

    private void showBoard(char[] box) {
        String boardString = String.format(
                "%n %c | %c | %c %n-----------%n %c | %c | %c %n-----------%n %c | %c | %c %n",
                box[0], box[1], box[2], box[3], box[4], box[5], box[6], box[7], box[8]);
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, boardString);
        }
    }

    private void sweepBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    private void showResults(String message) {
        logger.log(Level.INFO, String.format("%s%nCreated by Korysh. Thanks for playing!", message));
    }

    private boolean checkWinner(char playerSymbol) {
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            if (board[i] == playerSymbol && board[i + 1] == playerSymbol && board[i + 2] == playerSymbol) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] == playerSymbol && board[i + 3] == playerSymbol && board[i + 6] == playerSymbol) {
                return true;
            }
        }

        if (board[0] == playerSymbol && board[4] == playerSymbol && board[8] == playerSymbol) {
            return true;
        }

        return board[2] == playerSymbol && board[4] == playerSymbol && board[6] == playerSymbol;
    }

    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell != PLAYER_SYMBOL && cell != COMPUTER_SYMBOL) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidMove(int move) {
        return move > 0 && move <= BOARD_SIZE && board[move - 1] == EMPTY_CELL;
    }

    private void playerMove() {
        int input;
        while (true) {
            logger.log(Level.INFO, "Enter box number to select.");
            input = scanner.nextInt();
            if (isValidMove(input)) {
                board[input - 1] = PLAYER_SYMBOL;
                break;
            } else {
                logger.log(Level.INFO, "Invalid input. Enter again.");
            }
        }
    }

    private void computerMove() {
        int randomMove;
        while (true) {
            randomMove = random.nextInt(BOARD_SIZE) + 1;
            if (isValidMove(randomMove)) {
                board[randomMove - 1] = COMPUTER_SYMBOL;
                break;
            }
        }
    }
}

