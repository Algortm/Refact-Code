import java.util.Scanner;


class AppGame {
    private char[] board;
    private final int BOARD_SIZE = 9;
    private final char EMPTY_CELL = ' ';
    private final char PLAYER_SYMBOL = 'X';
    private final char COMPUTER_SYMBOL = 'O';
    private Scanner scanner;

    public AppGame() {
        board = new char[BOARD_SIZE];
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    public void playGame() {
        boolean isPlayerTurn = true;
        char winner = '\0';

        System.out.println("Enter box number to select. Enjoy!\n");

        while (winner == '\0') {
            printBoard();

            int move = isPlayerTurn ? getPlayerMove() : getComputerMove();
            board[move] = isPlayerTurn ? PLAYER_SYMBOL : COMPUTER_SYMBOL;
            isPlayerTurn = !isPlayerTurn;

            winner = checkWinner();
        }

        printBoard();
        printResult(winner);
    }

    private void printBoard() {
        System.out.println("\n");
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " ");
            if (i < 6) System.out.println("-----------");
        }
        System.out.println("\n");
    }

    private int getPlayerMove() {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < BOARD_SIZE && board[move] == EMPTY_CELL) {
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
        return move;
    }

    private int getComputerMove() {
        int move;
        do {
            move = (int) (Math.random() * BOARD_SIZE);
        } while (board[move] != EMPTY_CELL);
        return move;
    }

    private char checkWinner() {

        return 0;
    }

    private void printResult(char winner) {

    }
}
