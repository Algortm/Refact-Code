import java.util.Scanner;

public class App {
    protected static final char[] BOX = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static final byte NUM_OF_BOXES = (byte) BOX.length;

    public static void main(String[] args) {
        boolean boxAvailable;
        byte winner = 0;
        boolean boxEmpty = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            redrawField();

            if (!boxEmpty) {
                emptyAllBoxes();
                boxEmpty = true;
            }

            if (finishGame(winner)) {
                break;
            }

            getUserInput(scan);

            boxAvailable = isBoxAvailable();

            getRandomInput();

            winner = getWinner(boxAvailable, winner);
        }
    }

    private static void redrawField() {
        System.out.println("\n\n " + App.BOX[0] + " | " + App.BOX[1] + " | " + App.BOX[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + App.BOX[3] + " | " + App.BOX[4] + " | " + App.BOX[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + App.BOX[6] + " | " + App.BOX[7] + " | " + App.BOX[8] + " \n");
    }

    private static void emptyAllBoxes() {
        byte i;
        for (i = 0; i < 9; i++) {
            App.BOX[i] = ' ';
        }
    }

    private static void getUserInput(Scanner scan) {
        byte input;

        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= NUM_OF_BOXES) {
                if (App.BOX[input - 1] == 'X' || App.BOX[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    App.BOX[input - 1] = 'X';
                    break;
                }
            } else System.out.println("Invalid input. Enter again.");
        }
    }

    private static void getRandomInput() {
        byte rand;

        while (true) {
            rand = (byte) (Math.random() * (NUM_OF_BOXES - 1 + 1) + 1);
            if (App.BOX[rand - 1] != 'X' && App.BOX[rand - 1] != 'O') {
                App.BOX[rand - 1] = 'O';
                break;
            }
        }
    }

    private static boolean isBoxAvailable() {
        boolean boxAvailable;
        boxAvailable = false;

        for (byte i = 0; i < NUM_OF_BOXES; i++) {
            if (App.BOX[i] != 'X' && App.BOX[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        return boxAvailable;
    }

    private static byte getWinner(boolean boxAvailable, byte winner) {

        if (!boxAvailable) {
            winner = 3;
        } else if (matchesHorizontal('X') || matchesVertical('X') || matchesDiagonal('X')) {
            winner = 1;
        } else if (matchesHorizontal('O') || matchesVertical('O') || matchesDiagonal('O')) {
            winner = 2;
        }
        return winner;
    }

    private static boolean matchesDiagonal(char c) {
        return (App.BOX[0] == c && App.BOX[4] == c && App.BOX[8] == c) ||
                (App.BOX[2] == c && App.BOX[4] == c && App.BOX[6] == c);
    }

    private static boolean matchesVertical(char c) {
        return (App.BOX[0] == c && App.BOX[3] == c && App.BOX[6] == c) ||
                (App.BOX[1] == c && App.BOX[4] == c && App.BOX[7] == c) ||
                (App.BOX[2] == c && App.BOX[5] == c && App.BOX[8] == c);
    }

    private static boolean matchesHorizontal(char c) {
        return (App.BOX[0] == c && App.BOX[1] == c && App.BOX[2] == c) ||
                (App.BOX[3] == c && App.BOX[4] == c && App.BOX[5] == c) ||
                (App.BOX[6] == c && App.BOX[7] == c && App.BOX[8] == c);
    }

    private static boolean finishGame(byte winner) {
        String author = "\nCreated by Shreyas Saha. Thanks for playing!";

        if (winner == 1) {
            System.out.println("You won the game!" + author);
            return true;
        } else if (winner == 2) {
            System.out.println("You lost the game!" + author);
            return true;
        } else if (winner == 3) {
            System.out.println("It's a draw!" + author);
            return true;
        }
        return false;
    }
}
