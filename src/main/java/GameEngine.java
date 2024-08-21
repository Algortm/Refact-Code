
/**
 * The GameEngine class implements the main game logic for a simple Tic-Tac-Toe game.
 * It handles the initialization of the game board, player and computer moves,
 * checking for a winner, and determining when the game ends.
 */
public class GameEngine implements Engine {
    private final Generator generator;
    private final Printer printer;
    private final InputValidator inputValidator;
    private char[] board;
    private int winner;
    public static final int WIN_VALUE = 1;
    public static final int LOSE_VALUE = 2;
    public static final int DRAW_VALUE = 3;
    private static final int BOARD_LENGTH = 9;
    private static final int ROW_LENGTH =  3;


    /**
     * Constructs a GameEngine with the specified board generator, printer, and scanner.
     *
     * @param generator the board generator used to create the initial game board
     * @param printer   the printer used to display the game board and results
     */
    public GameEngine(Generator generator, Printer printer, InputValidator validator) {
        this.generator = generator;
        this.printer = printer;
        this.inputValidator = validator;
        this.winner = 0;
    }


    /**
     * Starts the game, handles the game loop, and determines the winner.
     * The game continues until there is a winner or the board is full.
     */
    public void startGame() {
        board = generator.generateBoard();
        boolean boardEmpty = false;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (board.length != 0) {
            printer.printBoard(board);

            if (!boardEmpty) {
                for (int i = 0; i < BOARD_LENGTH; i++)
                    board[i] = ' ';
                boardEmpty = true;
            }

            printer.printResult(winner);

            playerMove();

            if (checkWin('X')) {
                winner = WIN_VALUE;
                continue;
            }

            if (isBoardFull()) {
                winner = DRAW_VALUE;
                continue;
            }

            computerMove();

            if (checkWin('O')) {
                winner = LOSE_VALUE;
            }
        }
    }


    /**
     * Handles the player's move, validating the input and updating the game board.
     * If the move is invalid, the player is prompted to enter a new move.
     */
    private void playerMove() {
        byte input = inputValidator.getValidNumber();
        if (isValidMove(input)) {
            board[input - 1] = 'X';
        } else {
            System.out.println("That one is already in use. Enter another.");
            playerMove();
        }
    }


    /**
     * Checks if the player's input corresponds to a valid move on the board.
     *
     * @param input the player's input
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(byte input) {
        return board[input - 1] != 'X' && board[input - 1] != 'O';
    }


    /**
     * Handles the computer's move by randomly selecting an available position on the board.
     * The move is automatically made without any user input.
     */
    private void computerMove() {
        while (true) {
            byte rand = (byte) (Math.random() * BOARD_LENGTH);
            if (board[rand] != 'X' && board[rand] != 'O') {
                board[rand] = 'O';
                break;
            }
        }
    }


    /**
     * Checks if the specified player has won the game by forming a winning combination.
     *
     * @param player the player's character ('X' or 'O')
     * @return true if the player has won, false otherwise
     */
    private boolean checkWin(char player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }


    /**
     * Checks if the specified player has won by forming a winning combination in any row.
     *
     * @param player the player's character ('X' or 'O')
     * @return true if the player has won in any row, false otherwise
     */
    private boolean checkRows(char player) {
        for (int i = 0; i < ROW_LENGTH; i++) {
            if (board[i * ROW_LENGTH] == player && board[i * ROW_LENGTH + 1] == player && board[i * ROW_LENGTH + 2] == player) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the specified player has won by forming a winning combination in any column.
     *
     * @param player the player's character ('X' or 'O')
     * @return true if the player has won in any column, false otherwise
     */
    private boolean checkColumns(char player) {
        for (int i = 0; i < ROW_LENGTH; i++) {
            if (board[i] == player && board[i + ROW_LENGTH] == player && board[i + ROW_LENGTH * 2] == player) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if the specified player has won by forming a winning combination in either diagonal.
     *
     * @param player the player's character ('X' or 'O')
     * @return true if the player has won in any diagonal, false otherwise
     */
    private boolean checkDiagonals(char player) {
        return (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }


    /**
     * Checks if the game board is completely filled with no empty spaces left.
     *
     * @return true if the board is full, false otherwise
     */
    private boolean isBoardFull() {
        for (char c : board) {
            if (c != 'X' && c != 'O') {
                return false;
            }
        }
        return true;
    }
}
