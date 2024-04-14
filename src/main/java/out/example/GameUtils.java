package out.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class GameUtils {
     private static final Logger logger = LogManager.getLogger(GameUtils.class);

    private void doSomething(String string) {
        logger.info(string);
    }

    public void start() {
        doSomething("Enter box number to select. Enjoy!");
    }

    public void nullBox(char[] box, byte maxField) {
        for (int i = 0; i < maxField; i++)
            box[i] = ' ';
    }

    public void inputBox(char[] box, byte maxField) {
        Scanner scan = new Scanner(System.in);
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= maxField) {
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

    public void rundomBox(char[] box, byte maxField) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (maxField - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    public void drawBox(char[] box) {
        doSomething("\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        doSomething("-----------");
        doSomething(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        doSomething("-----------");
        doSomething(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private boolean andBox(char box1, char box2, char box3, char player) {
        boolean booleanAnd = false;
        if (box1 == player && box2 == player && box3 == player) {
            booleanAnd = true;
        }
        return booleanAnd;
    }

    public boolean orBox(char[] box, char player) {
        boolean booleanOr = false;
        if (andBox(box[0], box[1], box[2], player) ||
                andBox(box[0], box[3], box[6], player) ||
                andBox(box[0], box[4], box[8], player) ||
                andBox(box[3], box[4], box[5], player) ||
                andBox(box[1], box[4], box[7], player) ||
                andBox(box[2], box[4], box[6], player) ||
                andBox(box[6], box[7], box[8], player) ||
                andBox(box[2], box[5], box[8], player)
        ) {
            booleanOr = true;
        }
        return booleanOr;
    }

    public void finish(byte winner) {
        if (winner == 1) {
            doSomething("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 2) {
            doSomething("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 3) {
            doSomething("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

        }
    }
}
