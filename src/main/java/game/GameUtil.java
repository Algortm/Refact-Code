package game;

import java.util.Scanner;

public class GameUtil {
    private static final int BOARD_SIZE = 9;
    private char board[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean boxEmpty = false;
    private Winner winner;

    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");
        while (isAvailable() && winner == null) {
            printBoard();
            fillArray();
            makeMove();
        }
        printWinner();
    }

    private void printBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void fillArray() {
        if (!boxEmpty) {
            for (int i = 0; i < BOARD_SIZE; i++)
                board[i] = ' ';
            boxEmpty = true;
        }
    }

    private void makeMove() {
        makeUserMove();
        if (winner == null && isAvailable()) {
            makeBotMove();
        }
        determineWinner();
    }

    private void makeBotMove() {
        int randomBox;
        do {
            randomBox = (int) (Math.random() * BOARD_SIZE);
        } while (board[randomBox] == 'X' || board[randomBox] == 'O');
        board[randomBox] = 'O';
    }


    private void makeUserMove() {
        Scanner scanner = new Scanner(System.in);
        byte input = scanner.nextByte();
        if (input > 0 && input < 10) {
            if (board[input - 1] == 'X' || board[input - 1] == 'O')
                System.out.println("That one is already in use. Enter another.");
            else {
                board[input - 1] = 'X';
            }
        } else {
            System.out.println("Invalid input. Enter again.");
        }
    }

    private void determineWinner() {
         if (isBotWin()){
             winner = Winner.USER;
         }
         if (isUserWin()){
             winner = Winner.BOT;
         }
         if (!isAvailable()){
             winner = Winner.DRAW;
         }
    }

    public boolean isBotWin() {
        if ((board[0] == 'X' && board[1] == 'X' && board[2] == 'X') || (board[3] == 'X' && board[4] == 'X' && board[5] == 'X') || (board[6] == 'X' && board[7] == 'X' && board[8] == 'X') ||
                (board[0] == 'X' && board[3] == 'X' && board[6] == 'X') || (board[1] == 'X' && board[4] == 'X' && board[7] == 'X') || (board[2] == 'X' && board[5] == 'X' && board[8] == 'X') ||
                (board[0] == 'X' && board[4] == 'X' && board[8] == 'X') || (board[2] == 'X' && board[4] == 'X' && board[6] == 'X')) {
            return true;
        }
        return false;
    }

    public boolean isUserWin() {
        if ((board[0] == 'O' && board[1] == 'O' && board[2] == 'O') || (board[3] == 'O' && board[4] == 'O' && board[5] == 'O') || (board[6] == 'O' && board[7] == 'O' && board[8] == 'O') ||
                (board[0] == 'O' && board[3] == 'O' && board[6] == 'O') || (board[1] == 'O' && board[4] == 'O' && board[7] == 'O') || (board[2] == 'O' && board[5] == 'O' && board[8] == 'O') ||
                (board[0] == 'O' && board[4] == 'O' && board[8] == 'O') || (board[2] == 'O' && board[4] == 'O' && board[6] == 'O')) {
            return true;
        }
        return false;
    }

    private boolean isAvailable() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return true;
            }
        }
        return false;
    }


    private void printWinner() {
        if (winner.compareTo(Winner.USER) == 0) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner.compareTo(Winner.BOT) == 0) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner.compareTo(Winner.DRAW) == 0) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }

}
