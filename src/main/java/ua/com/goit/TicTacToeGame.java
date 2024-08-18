package ua.com.goit;

import java.util.Scanner;

public class TicTacToeGame {

    private static final String MESSAGE_FOOTER = "Created by Shreyas Saha. Thanks for playing!";
    private static final Scanner SCAN = new Scanner(System.in);
    private static final char PLAYER_X_CHAR = 'X';
    private static final char PLAYER_O_CHAR = 'O';

    public void startGame() {

        byte winner;
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        System.out.println("Enter box number to select. Enjoy!\n");

        printGameBoard(box);
        clearBoard(box);
        do {
            winner = resultOfStep(box);
            printGameBoard(box);
        } while (winner == 0);
        printGameResult(winner);
        SCAN.close();
    }

    private byte resultOfStep(char[] box) {
        takeStepByX(box);
        if (isWinnerFound(box, PLAYER_X_CHAR)) {
            return 1;
        }
        if (!boxAvailable(box)) {
            return 3;
        }
        takeStepByO(box);
        if (isWinnerFound(box, PLAYER_O_CHAR)) {
            return 2;
        }
        return 0;
    }

    private boolean boxAvailable(char[] box) {
        for (byte i = 0; i < box.length; i++) {
            if (box[i] != PLAYER_X_CHAR && box[i] != PLAYER_O_CHAR) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinnerFound(char[] box, char ch) {
        return (box[0] == ch && box[1] == ch && box[2] == ch) || (box[3] == ch && box[4] == ch && box[5] == ch) || (box[6] == ch && box[7] == ch && box[8] == ch) ||
                (box[0] == ch && box[3] == ch && box[6] == ch) || (box[1] == ch && box[4] == ch && box[7] == ch) || (box[2] == ch && box[5] == ch && box[8] == ch) ||
                (box[0] == ch && box[4] == ch && box[8] == ch) || (box[2] == ch && box[4] == ch && box[6] == ch);
    }

    private void takeStepByO(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (box.length) + 1);
            if (box[rand - 1] != PLAYER_X_CHAR && box[rand - 1] != PLAYER_O_CHAR) {
                box[rand - 1] = PLAYER_O_CHAR;
                break;
            }
        }
    }

    private void takeStepByX(char[] box) {
        byte input;
        while (true) {
            input = SCAN.nextByte();
            if (input > 0 && input <= box.length) {
                if (box[input - 1] == PLAYER_X_CHAR || box[input - 1] == PLAYER_O_CHAR)
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = PLAYER_X_CHAR;
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    private void printGameResult(byte winner) {
        String result = switch (winner) {
            case 1 -> "You won the game!\n";
            case 2 -> "You lost the game!\n";
            case 3 -> "It's a draw!\n";
            default -> "Unknown result\n";
        };
        System.out.println(result + MESSAGE_FOOTER);
    }

    private void clearBoard(char[] box) {
        for (byte i = 0; i < box.length; i++) {
            box[i] = ' ';
        }
    }

    private void printGameBoard(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }
}
