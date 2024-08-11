package org.example.game;

import java.util.Scanner;

public class TicTacToeGame {
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';

    private final char[] board = new char[BOARD_SIZE];
    private final Scanner scanner = new Scanner(System.in);

    public void play() {
        resetBoard();
        while (true) {
            printBoard();

            if (checkWinner(USER_MARK)) {
                displayResult(1);
                return;
            }

            if (checkDraw()) {
                displayResult(3);
                return;
            }

            makeUserMove();
            if (checkWinner(USER_MARK)) {
                printBoard();
                displayResult(1);
                return;
            }

            if (checkDraw()) {
                printBoard();
                displayResult(3);
                return;
            }

            makeComputerMove();
            if (checkWinner(COMPUTER_MARK)) {
                displayResult(2);
                return;
            }
        }
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    public void printBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }


    private void displayResult(int result) {
        switch (result) {
            case 1:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
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
            System.out.print("Enter your move (1-9): ");
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
            move = (byte) (Math.random() * BOARD_SIZE + 1);
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
        for (char cell : board) {
            if (cell != USER_MARK && cell != COMPUTER_MARK) {
                return false;
            }
        }
        return true;
    }
}