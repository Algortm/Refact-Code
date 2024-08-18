import java.util.Scanner;

public class TikTakToe {
    private static final char PLAYER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';
    private static final int BOARD_SIZE = 9;
    private static Scanner scanner = new Scanner(System.in);
    public void startPoint(){
        char[] board = initializeBoard();
        int winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        while(winner ==0){
        printBoard(board);
        playerMove(scanner, board);
        winner = checkWinner(board, PLAYER_MARK, COMPUTER_MARK);

        if (winner != 0){
            break;
        }

        if (isBoardFull(board)) {
            winner = 3;
            break;
        }

        computerMove(board);
        winner = checkWinner(board, PLAYER_MARK, COMPUTER_MARK);
    }

    printBoard(board);

    printGameResult(winner);
}
    private char[] initializeBoard() {
        char[] board = new char[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = ' ';
        }
        return board;
    }

    private void printBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void playerMove(Scanner scanner, char[] board) {
        while (true) {
            System.out.print("Enter your move (1-9): ");
            int input = scanner.nextInt();
            if (isValidMove(input, board)) {
                board[input - 1] = PLAYER_MARK;
                break;
            } else {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private boolean isValidMove(int input, char[] board) {
        return input > 0 && input <= BOARD_SIZE && board[input - 1] == ' ';
    }

    private boolean isBoardFull(char[] board) {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    private void computerMove(char[] board) {
        while (true) {
            int randomMove = (int) (Math.random() * BOARD_SIZE);
            if (board[randomMove] == ' ') {
                board[randomMove] = COMPUTER_MARK;
                break;
            }
        }
    }

    private int checkWinner(char[] board, char playerMark, char computerMark) {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] winPos : winPositions) {
            if (board[winPos[0]] == playerMark && board[winPos[1]] == playerMark && board[winPos[2]] == playerMark) {
                return 1;
            }
            if (board[winPos[0]] == computerMark && board[winPos[1]] == computerMark && board[winPos[2]] == computerMark) {
                return 2;
            }
        }

        return 0;
    }

    private void printGameResult(int winner) {
        switch (winner) {
            case 1:
                System.out.println("You won the game!");
                break;
            case 2:
                System.out.println("You lost the game!");
                break;
            case 3:
                System.out.println("It's a draw!");
                break;
        }
        System.out.println("Created by Shreyas Saha. Thanks for playing!");
    }
}

