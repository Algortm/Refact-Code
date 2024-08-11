package org.example.game;

import java.util.Scanner;
import java.util.stream.IntStream;

public class TicTacToeGame {
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';

    private final char[] board = new char[BOARD_SIZE];
    private final Scanner scanner = new Scanner(System.in);

    public TicTacToeGame() {
        initializeNumberedBoard();
        printBoard();
        resetBoard();
    }

    private void initializeNumberedBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> board[i] = (char) ('1' + i));
    }

    public void play() {
        while (true) {
            if (checkWinner(USER_MARK)) {
                printBoard();
                displayResult(Result.USER_WON);
                return;
            }

            if (checkDraw()) {
                printBoard();
                displayResult(Result.DRAW);
                return;
            }

            makeUserMove();

            if (checkWinner(USER_MARK)) {
                printBoard();
                displayResult(Result.USER_WON);
                return;
            }

            if (checkDraw()) {
                printBoard();
                displayResult(Result.DRAW);
                return;
            }

            makeComputerMove();
            if (checkWinner(COMPUTER_MARK)) {
                printBoard();
                displayResult(Result.COMPUTER_WON);
                return;
            }

            printBoard();
        }
    }

    private void resetBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> board[i] = EMPTY_CELL);
    }

    public void printBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void displayResult(Result result) {
        switch (result) {
            case USER_WON:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case COMPUTER_WON:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case DRAW:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    private void makeUserMove() {
        byte move;
        do {
            move = getUserMove();
        } while (board[move - 1] == USER_MARK || board[move - 1] == COMPUTER_MARK);
        board[move - 1] = USER_MARK;
    }

    private byte getUserMove() {
        byte input;
        while (true) {
            System.out.print("Enter your move: ");
            input = scanner.nextByte();
            if (input >= 1 && input <= 9) {
                return input;
            }
            System.out.println("Invalid input. Enter again.");
        }
    }

    private void makeComputerMove() {
        byte move;
        do {
            move = (byte) ((Math.random() * BOARD_SIZE) + 1);
        } while (board[move - 1] == USER_MARK || board[move - 1] == COMPUTER_MARK);
        board[move - 1] = COMPUTER_MARK;
    }

    private boolean checkWinner(char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private boolean checkDraw() {
        return IntStream.range(0, BOARD_SIZE).noneMatch(i -> board[i] == EMPTY_CELL);
    }
}