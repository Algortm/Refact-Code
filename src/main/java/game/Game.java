package game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private static final Logger log = LoggerFactory.getLogger(Game.class);
    private static final String LINE = "{} | {} | {} ";
    private final Scanner scan = new Scanner(System.in);
    private byte winner = 0;
    char[] box = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    public void startGame() {

        boolean boxEmpty = false;
        log.info("Enter box number to select. Enjoy!");

        while (true) {

            printBoard(box);

            if (!boxEmpty) {
                Arrays.fill(box, ' ');
                boxEmpty = true;
            }

            if (printGameResults(winner)) break;

            playerMove(box);

            if (isWinner(box, 'X')) {
                winner = 1;
                continue;
            }

            boolean boxAvailable = checkBoard(box);

            if (!boxAvailable) {
                winner = 3;
                continue;
            }

            computerMove(box);

            if (isWinner(box, 'O')) {
                winner = 2;
            }

        }
    }

    private boolean printGameResults(byte winner) {
        if (winner == 1) {
            log.info("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 2) {
            log.info("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        } else if (winner == 3) {
            log.info("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            return true;
        }
        return false;
    }

    private boolean isWinner(char[] box, char sign) {
        return (box[0] == sign && box[1] == sign && box[2] == sign) || (box[3] == sign && box[4] == sign && box[5] == sign) || (box[6] == sign && box[7] == sign && box[8] == sign) ||
                (box[0] == sign && box[3] == sign && box[6] == sign) || (box[1] == sign && box[4] == sign && box[7] == sign) || (box[2] == sign && box[5] == sign && box[8] == sign) ||
                (box[0] == sign && box[4] == sign && box[8] == sign) || (box[2] == sign && box[4] == sign && box[6] == sign);
    }

    private void playerMove(char[] box) {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    log.info("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                log.info("Invalid input. Enter again.");
        }
    }

    private void computerMove(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private boolean checkBoard(char[] box) {
        boolean boxAvailable;
        boxAvailable = false;
        for (int i = 0; i < box.length; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        return boxAvailable;
    }

    private void printBoard(char[] box) {
        log.info("\n");
        log.info(LINE, box[0], box[1], box[2]);
        log.info("-----------");
        log.info(LINE, box[3], box[4], box[5]);
        log.info("-----------");
        log.info(LINE, box[6], box[7], box[8]);
        log.info("\n");
    }
}
