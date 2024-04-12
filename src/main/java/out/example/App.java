package out.example;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final int MAX_NUMBER = 9;
    private static void doSomething(String string) {
        logger.info(string);
    }

    private static void nullBox(char[] box) {
        for (int i = 0; i < MAX_NUMBER; i++)
            box[i] = ' ';
    }

    private static void inputBox(char[] box) {
        Scanner scan = new Scanner(System.in);
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= MAX_NUMBER) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    doSomething("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                doSomething("Invalid input. Enter again.");
        }
    }

    private static void rundomBox(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (MAX_NUMBER - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private static void drawBox(char[] box) {
        doSomething("\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        doSomething("-----------");
        doSomething(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        doSomething("-----------");
        doSomething(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private static boolean andBox(char box1, char box2, char box3, char priz) {
        boolean booleanAnd = false;
        if (box1 == priz && box2 == priz && box3 == priz) {
            booleanAnd = true;
        }
        return booleanAnd;
    }

    private static boolean orBox(char[] box, char priz) {
        boolean booleanOr = false;
        if (andBox(box[0], box[1], box[2], priz) ||
                andBox(box[0], box[3], box[6], priz) ||
                andBox(box[0], box[4], box[8], priz) ||
                andBox(box[3], box[4], box[5], priz) ||
                andBox(box[1], box[4], box[7], priz) ||
                andBox(box[2], box[4], box[6], priz) ||
                andBox(box[6], box[7], box[8], priz) ||
                andBox(box[2], box[5], box[8], priz)
        ) {
            booleanOr = true;
        }
        return booleanOr;
    }

    private static void finish(byte winner) {
        if (winner == 1) {
            doSomething("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 2) {
            doSomething("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 3) {
            doSomething("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

        }
    }

    public static void main(String[] args) {
        int count = 0;
        byte winner = 0;
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        doSomething("Enter box number to select. Enjoy!");

        drawBox(box);
        nullBox(box);

        while (winner == 0) {

            inputBox(box);
            count++;

            if (orBox(box, 'X')) {
                winner = 1;
            }

            if (count != 5) {
                rundomBox(box);

                if (orBox(box, 'O')) {
                    winner = 2;
                }
            }

            drawBox(box);

            if (count == 5 && winner == 0) {
                winner = 3;
            }

            finish(winner);

        }
    }

}
