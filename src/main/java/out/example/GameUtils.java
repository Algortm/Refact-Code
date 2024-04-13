package out.example;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameUtils {
    private static final int MAX_NUMBER = 9;
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = '0';
    private static final Logger logger = LogManager.getLogger(GameUtils.class);

    private void doSomething(String string) {
        logger.info(string);
    }

    private void start() {
        doSomething("Enter box number to select. Enjoy!");
    }

    private void nullBox(char[] box) {
        for (int i = 0; i < MAX_NUMBER; i++)
            box[i] = ' ';
    }

    private void inputBox(char[] box) {
        Scanner scan = new Scanner(System.in);
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input <= MAX_NUMBER) {
                if (box[input - 1] == PLAYER_X || box[input - 1] == PLAYER_O)
                    doSomething("That one is already in use. Enter another.");
                else {
                    box[input - 1] = PLAYER_X;
                    break;
                }
            } else
                doSomething("Invalid input. Enter again.");
        }
    }

    private void rundomBox(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (MAX_NUMBER - 1 + 1) + 1);
            if (box[rand - 1] != PLAYER_X && box[rand - 1] != PLAYER_O) {
                box[rand - 1] = PLAYER_O;
                break;
            }
        }
    }

    private void drawBox(char[] box) {
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

    private boolean orBox(char[] box, char player) {
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

    private void finish(byte winner) {
        if (winner == 1) {
            doSomething("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 2) {
            doSomething("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");

        } else if (winner == 3) {
            doSomething("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");

        }
    }

    public void logicGame() {
        int numberMoves = 0;
        byte winner = 0;
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

        start();

        drawBox(box);
        nullBox(box);

        while (winner == 0) {

            inputBox(box);
            numberMoves++;

            if (orBox(box, PLAYER_X)) {
                winner = 1;
            }

            if (numberMoves != 5) {
                rundomBox(box);

                if (orBox(box, PLAYER_O)) {
                    winner = 2;
                }
            }

            drawBox(box);

            if (numberMoves == 5 && winner == 0) {
                winner = 3;
            }

            finish(winner);

        }
    }
}
