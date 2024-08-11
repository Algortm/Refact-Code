import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class App {
    private static char box[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Scanner SCANNER = new Scanner(in);
    private static final Random RANDOM = new Random();

    static {
        out.print("Enter box number to select. Enjoy!");
        printBox();
        for (int i = 0; i < box.length; i++) {
            box[i] = ' ';
        }
    }

    private static void botMove() {
        while (true) {
            int rand = RANDOM.nextInt(0, 10);
            if (box[rand] == ' ') {
                box[rand] = 'O';
                break;
            }
        }
    }

    private static boolean canWeContinuePlaying() {
        if (s('O')) {
            out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (s('X')) {
            out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        } else if (k()) {
            out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return false;
        }

        return true;
    }

    private static boolean s(char c) {
        if ((box[0] == c && box[1] == c && box[2] == c) || (box[3] == c && box[4] == c && box[5] == c) || (box[6] == c && box[7] == c && box[8] == c) ||
                (box[0] == c && box[3] == c && box[6] == c) || (box[1] == c && box[4] == c && box[7] == c) || (box[2] == c && box[5] == c && box[8] == c) ||
                (box[0] == c && box[4] == c && box[8] == c) || (box[2] == c && box[4] == c && box[6] == c)) {
            return true;
        }
        return false;
    }

    private static boolean k() {
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    public static void startGame() {
        while (true) {
            inputScaner();
            botMove();
            printBox();
            if (!canWeContinuePlaying()) {
                break;
            }
        }
    }

    public static void printBox() {
        out.print("\n\n");
        for (int i = 0; i < box.length; i += 3) {
            out.printf(" %s | %s | %s %n", box[i], box[i + 1], box[i + 2]);
            if (i < 6) {
                out.println("-----------");
            }
        }
    }

    public static void inputScaner() {
        while (true) {
            out.print("\nPick the number(1 to 10): ");
            byte input = SCANNER.nextByte();
            if (input > 0 && input < 10) {
                int index = input - 1;
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

    public static void main(String[] args) {
        startGame();
    }
}