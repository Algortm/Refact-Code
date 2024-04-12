package com.exemple.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Scanner;

public class Game {
    public static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    public static final String CREATOR = "Created by Shreyas Saha. Thanks for playing!";
    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_O = 'O';
    public static final String PATTERN_MESSAGE = "\n\n {0} | {1} | {2} \n-----------\n {3} | {4} | {5} \n-----------\n {6} | {7} | {8} \n";

    public void play() {
        final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        byte winner = 0;
       LOGGER.info("Enter box number to select. Enjoy!\n");
        drawBoard(box);
        makeEmptyBoard(box);
        while (true) {
            readInput(box);
            drawBoard(box);
            if (isWinner(box, SYMBOL_X)) {
                winner = 1;
            } else if (hasMove(box)) {
                makeMoveO(box);
                if (isWinner(box, SYMBOL_O)) {
                    winner = 2;
                }
            } else {
                winner = 3;
            }
            if (checkWinner(winner)) {
                return;
            }
        }
    }

    private boolean hasMove(char[] box) {
        for (int i = 0; i < 9; i++) {
            if (box[i] != SYMBOL_X && box[i] != SYMBOL_O) {
                return true;
            }
        }
        return false;
    }

    private void makeEmptyBoard(char[] box) {
        for (int i = 0; i < 9; i++)
            box[i] = ' ';
    }

    private boolean isWinner(char[] box, char symbol) {
        return (box[0] == symbol && box[1] == symbol && box[2] == symbol) || (box[3] == symbol && box[4] == symbol && box[5] == symbol)
                || (box[6] == symbol && box[7] == symbol && box[8] == symbol) || (box[0] == symbol && box[3] == symbol && box[6] == symbol)
                || (box[1] == symbol && box[4] == symbol && box[7] == symbol) || (box[2] == symbol && box[5] == symbol && box[8] == symbol)
                || (box[0] == symbol && box[4] == symbol && box[8] == symbol) || (box[2] == symbol && box[4] == symbol && box[6] == symbol);
    }

    private void makeMoveO(char[] box) {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != SYMBOL_X && box[rand - 1] != SYMBOL_O) {
                box[rand - 1] = SYMBOL_O;
                break;
            }
        }
    }

    private void readInput(char[] box) {
        byte input;

        Scanner scan = new Scanner(System.in);
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == SYMBOL_X || box[input - 1] == SYMBOL_O)
                    LOGGER.info("That one is already in use. Enter another." );
                else {
                    box[input - 1] = SYMBOL_X;
                    break;
                }
            } else
                LOGGER.info("Invalid input. Enter again.");
        }
    }

    private boolean checkWinner(byte winner) {
        if (winner == 1) {
            LOGGER.info("You won the game!\n" + CREATOR);
            return true;
        } else if (winner == 2) {
            LOGGER.info("You lost the game!\n" + CREATOR);
            return true;
        } else if (winner == 3) {
            LOGGER.info("It's a draw!\n" + CREATOR);
            return true;
        }
        return false;
    }

    private void drawBoard(char[] box) {
        String result = MessageFormat.format(PATTERN_MESSAGE, box[0], box[1], box[2], box[3], box[4], box[5],
                box[6], box[7], box[8]);
        LOGGER.info(result);
    }
}
