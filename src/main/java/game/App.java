package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class App {
    private static final char[] BOX = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Scanner SCANNER = new Scanner(in);
    private static final Random RANDOM = new Random();
    private static final byte[][] WINNING_COMBINATIONS = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    static {
        out.print("Enter box number to select. Enjoy!");
        printBox();
        Arrays.fill(BOX, ' ');
    }

    private static void botMove() {
        while (true) {
            int rand = RANDOM.nextInt(0, 10);
            if (BOX[rand] == ' ') {
                BOX[rand] = 'O';
                break;
            }
        }
    }

    private static boolean canWeContinuePlaying() {
        if (hasPlayerWon('O')) {
            out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (hasPlayerWon('X')) {
            out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (isBoardFull()) {
            out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        }

        return true;
    }

    private static boolean hasPlayerWon(char playerSymbol) {
        for (byte[] combination : WINNING_COMBINATIONS) {
            if (BOX[combination[0]] == playerSymbol &&
                    BOX[combination[1]] == playerSymbol &&
                    BOX[combination[2]] == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (byte i = 0; i < 9; i++) {
            if (BOX[i] != 'X' && BOX[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    public static void startGame() {
        while (true) {
            inputScanner();
            botMove();
            printBox();
            if (!canWeContinuePlaying()) {
                break;
            }
        }
    }

    public static void printBox() {
        out.print("\n\n");
        for (byte i = 0; i < BOX.length; i += 3) {
            out.printf(" %s | %s | %s %n", BOX[i], BOX[i + 1], BOX[i + 2]);
            if (i < 6) {
                out.println("-----------");
            }
        }
    }

    public static void inputScanner() {
        while (true) {
            out.print("\nPick the number(1 to 10): ");
            byte input = SCANNER.nextByte();
            int index = input - 1;

            if (input >= 1 && input <= 9) {

                if (BOX[index] == 'X' || BOX[index] == 'O')
                    out.println("That one is already in use. Enter another.");
                else {
                    BOX[index] = 'X';
                    break;
                }
            } else {
                out.println("Invalid input. Enter again.");
            }
        }
    }

    public static void main(String[] args) {
        startGame();
    }
}