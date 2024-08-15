import java.util.Scanner;

public class App {

    private static final char PLAYER_MARK = 'X';
    private static final char AI_MARK = 'O';
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY = ' ';

    public static void main(String[] args) {
        char[] board = initializeBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (gameRunning) {
            printBoard(board);
            playerMove(board, scanner);

            if (checkWin(board, PLAYER_MARK)) {
                printBoard(board);
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }

            if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }

            aiMove(board);

            if (checkWin(board, AI_MARK)) {
                printBoard(board);
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                gameRunning = false;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                gameRunning = false;
            }
        }

    }

    private static char[] initializeBoard() {
        char[] board = new char[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY;
        }
        return board;
    }

    private static void printBoard(char[] board) {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private static void playerMove(char[] board, Scanner scanner) {
        int move;
        while (true) {
            System.out.print("Enter your move (1-9): ");
            move = scanner.nextInt() - 1;
            if (move >= 0 && move < BOARD_SIZE && board[move] == EMPTY) {
                board[move] = PLAYER_MARK;
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    private static void aiMove(char[] board) {
        int move;
        while (true) {
            move = (int) (Math.random() * BOARD_SIZE);
            if (board[move] == EMPTY) {
                board[move] = AI_MARK;
                break;
            }
        }
    }

    private static boolean checkWin(char[] board, char mark) {
        return (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[0] == mark && board[3] == mark && board[6] == mark) ||
                (board[1] == mark && board[4] == mark && board[7] == mark) ||
                (board[2] == mark && board[5] == mark && board[8] == mark) ||
                (board[0] == mark && board[4] == mark && board[8] == mark) ||
                (board[2] == mark && board[4] == mark && board[6] == mark);
    }

    private static boolean isBoardFull(char[] board) {
        for (char c : board) {
            if (c == EMPTY) {
                return false;
            }
        }
        return true;
    }
}
