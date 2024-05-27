import java.util.Scanner;

public class GameHelper {
    private char[] box = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private byte numOfBoxes = (byte) box.length;

    protected void play() {
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

    private void redrawField() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private void emptyAllBoxes() {
        byte i;
        for (i = 0; i < 9; i++) {
            box[i] = ' ';
        }
    }

    private void getUserInput(Scanner scan) {
        byte input;

        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= numOfBoxes) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else System.out.println("Invalid input. Enter again.");
        }
    }

    private void getRandomInput() {
        byte rand;

        while (true) {
            rand = (byte) (Math.random() * (numOfBoxes - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private boolean isBoxAvailable() {
        boolean boxAvailable;
        boxAvailable = false;

        for (byte i = 0; i < numOfBoxes; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        return boxAvailable;
    }

    private byte getWinner(boolean boxAvailable, byte winner) {

        if (!boxAvailable) {
            winner = 3;
        } else if (matchesHorizontal('X') || matchesVertical('X') || matchesDiagonal('X')) {
            winner = 1;
        } else if (matchesHorizontal('O') || matchesVertical('O') || matchesDiagonal('O')) {
            winner = 2;
        }
        return winner;
    }

    private boolean matchesDiagonal(char c) {
        return (box[0] == c && box[4] == c && box[8] == c) ||
                (box[2] == c && box[4] == c && box[6] == c);
    }

    private boolean matchesVertical(char c) {
        return (box[0] == c && box[3] == c && box[6] == c) ||
                (box[1] == c && box[4] == c && box[7] == c) ||
                (box[2] == c && box[5] == c && box[8] == c);
    }

    private boolean matchesHorizontal(char c) {
        return (box[0] == c && box[1] == c && box[2] == c) ||
                (box[3] == c && box[4] == c && box[5] == c) ||
                (box[6] == c && box[7] == c && box[8] == c);
    }

    private boolean finishGame(byte winner) {
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
