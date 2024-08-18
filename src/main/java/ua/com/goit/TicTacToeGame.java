package ua.com.goit;

import java.util.Scanner;

public class TicTacToeGame {

    private static final String MESSAGE_FOOTER = "Created by Shreyas Saha. Thanks for playing!";
    private static final Scanner scan = new Scanner(System.in);

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
        scan.close();
    }

    private byte resultOfStep(char[] box) {
        takeStepByX(box);
        if (isWinnerFound(box, 'X')) {
            return 1;
        }
        if (!boxAvailable(box)) {
            return 3;
        }
        takeStepByO(box);
        if (isWinnerFound(box, 'O')) {
            return 2;
        }
        return 0;
    }

    private boolean boxAvailable(char[] box) {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
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
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private void takeStepByX(char[] box) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
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
        for (byte i = 0; i < 9; i++) {
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
