package org.example.game;

import java.util.Scanner;

public class TicTacToeGame {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        byte winner = 0;
        char[] board = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        System.out.println("Enter board number to select. Enjoy!\n");

        boolean boardEmpty = false;
        while (true) {
            System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
            System.out.println("-----------");
            System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
            System.out.println("-----------");
            System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
            if (!boardEmpty) {
                for (byte i = 0; i < 9; i++)
                    board[i] = ' ';
                boardEmpty = true;
            }

            if (winner == 1) {
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (winner == 2) {
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (winner == 3) {
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }

            while (true) {
                byte input = scanner.nextByte();
                if (input > 0 && input < 10) {
                    if (board[input - 1] == 'X' || board[input - 1] == 'O')
                        System.out.println("That one is already in use. Enter another.");
                    else {
                        board[input - 1] = 'X';
                        break;
                    }
                } else
                    System.out.println("Invalid input. Enter again.");
            }

            if ((board[0] == 'X' && board[1] == 'X' && board[2] == 'X') || (board[3] == 'X' && board[4] == 'X' && board[5] == 'X') || (board[6] == 'X' && board[7] == 'X' && board[8] == 'X') ||
                    (board[0] == 'X' && board[3] == 'X' && board[6] == 'X') || (board[1] == 'X' && board[4] == 'X' && board[7] == 'X') || (board[2] == 'X' && board[5] == 'X' && board[8] == 'X') ||
                    (board[0] == 'X' && board[4] == 'X' && board[8] == 'X') || (board[2] == 'X' && board[4] == 'X' && board[6] == 'X')) {
                winner = 1;
                continue;
            }

            boolean boardAvailable = false;
            for (byte i = 0; i < 9; i++) {
                if (board[i] != 'X' && board[i] != 'O') {
                    boardAvailable = true;
                    break;
                }
            }

            if (!boardAvailable) {
                winner = 3;
                continue;
            }

            byte rand;
            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (board[rand - 1] != 'X' && board[rand - 1] != 'O') {
                    board[rand - 1] = 'O';
                    break;
                }
            }

            if ((board[0] == 'O' && board[1] == 'O' && board[2] == 'O') || (board[3] == 'O' && board[4] == 'O' && board[5] == 'O') || (board[6] == 'O' && board[7] == 'O' && board[8] == 'O') ||
                    (board[0] == 'O' && board[3] == 'O' && board[6] == 'O') || (board[1] == 'O' && board[4] == 'O' && board[7] == 'O') || (board[2] == 'O' && board[5] == 'O' && board[8] == 'O') ||
                    (board[0] == 'O' && board[4] == 'O' && board[8] == 'O') || (board[2] == 'O' && board[4] == 'O' && board[6] == 'O')) {
                winner = 2;
            }
        }
    }
}
