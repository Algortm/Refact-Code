package ua.example.game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class TicTacToe {
    private final char[] box;
    private final Scanner scanner;
    private final Random random;
    private final byte[][] winningCombinations = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    public TicTacToe(){
        out.print("Enter box number to select. Enjoy!");
        box = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        printBox();
        Arrays.fill(box, ' ');
        scanner = new Scanner(in);
        random = new Random();
    }

    private void botMove() {
        while (true) {
            int rand = random.nextInt(0, 10);
            if (box[rand] == ' ') {
                box[rand] = 'O';
                break;
            }
        }
    }

    private boolean canWeContinuePlaying() {
        if (hasPlayerWon('O')) {
            out.println("You lost the ua.example.game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (hasPlayerWon('X')) {
            out.println("You won the ua.example.game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (isBoardFull()) {
            out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        }

        return true;
    }

    private boolean hasPlayerWon(char playerSymbol) {
        for (byte[] combination : winningCombinations) {
            if (box[combination[0]] == playerSymbol &&
                    box[combination[1]] == playerSymbol &&
                    box[combination[2]] == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    public void printBox() {
        out.print("\n\n");
        for (byte i = 0; i < box.length; i += 3) {
            out.printf(" %s | %s | %s %n", box[i], box[i + 1], box[i + 2]);
            if (i < 6) {
                out.println("-----------");
            }
        }
    }

    public void inputScanner() {
        while (true) {
            out.print("\nPick the number(1 to 10): ");
            byte input = scanner.nextByte();
            int index = input - 1;

            if (input >= 1 && input <= 9) {

                if (box[index] == 'X' || box[index] == 'O')
                    out.println("That one is already in use. Enter another.");
                else {
                    box[index] = 'X';
                    break;
                }
            } else {
                out.println("Invalid input. Enter again.");
            }
        }
    }

    public void startGame() {
        while (true) {
            inputScanner();
            botMove();
            printBox();
            if (!canWeContinuePlaying()) {
                break;
            }
        }
    }
}
