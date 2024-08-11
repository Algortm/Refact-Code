package org.example.game;

import java.util.Scanner;

public class TicTacToeGame {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        byte winner = 0;
        char[] board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        boolean boardEmpty = false;

        while (true) {
            printBoard(board);

            if (!boardEmpty) {
                for (byte i = 0; i < 9; i++)
                    board[i] = ' ';
                boardEmpty = true;
            }

            if (winner != 0) {
                displayResult(winner);
                break;
            }

            byte userMove = getUserMove(scanner, board);
            board[userMove - 1] = 'X';

            if (checkWin(board, 'X')) {
                winner = 1;
                continue;
            }

            if (checkDraw(board)) {
                winner = 3;
                continue;
            }

            byte computerMove = getComputerMove(board);
            board[computerMove - 1] = 'O';

            if (checkWin(board, 'O')) {
                winner = 2;
            }
        }
    }

    public void printBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    public void displayResult(byte winner) {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 3) {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }

    public byte getUserMove(Scanner scanner, char[] board) {
        while (true) {
            byte input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (board[input - 1] == 'X' || board[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    return input;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    public byte getComputerMove(char[] board) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (board[rand - 1] != 'X' && board[rand - 1] != 'O') {
                return rand;
            }
        }
    }

    public boolean checkWin(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    public boolean checkDraw(char[] board) {
        for (byte i = 0; i < 9; i++) {
            if (board[i] != 'X' && board[i] != 'O') {
                return false;
            }
        }
        return true;
    }
}
