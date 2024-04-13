package com.example.tictactoe.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class TicTacToe {
    private static final Logger logger = LoggerFactory.getLogger(TicTacToe.class);
    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[] board;
    private Scanner scanner;

    public TicTacToe() {
        board = new char[BOARD_SIZE];
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    private void printBoard() {
        logger.info("\n");
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            logger.info(" {} | {} | {} ", board[i], board[i + 1], board[i + 2]);
            if (i < BOARD_SIZE - 3) {
                logger.info("-----------");
            }
        }
        logger.info("\n");
    }

    private boolean isInputValid(int input) {
        return input > 0 && input <= BOARD_SIZE && board[input - 1] == EMPTY_CELL;
    }

    private void playerMove(char player) {
        int input;
        do {
            logger.info("Enter box number to select:");
            input = Integer.parseInt(scanner.nextLine());
            if (!isInputValid(input)) {
                logger.info("Invalid input. Enter again.");
            }
        } while (!isInputValid(input));
        board[input - 1] = player;
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == player && board[i + 1] == player && board[i + 2] == player) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] == player && board[i + 3] == player && board[i + 6] == player) {
                return true;
            }
        }

        return (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    private void printWinMessage(char winner) {
        if (winner == PLAYER_X) {
            logger.info("Player X wins!");
            logger.info("Player O loses.");
        } else {
            logger.info("Player O wins!");
            logger.info("Player X loses.");
        }
    }

    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell == EMPTY_CELL) {
                return false;
            }
        }
        return true;
    }

    public void playGame() {
        logger.info("Welcome to Tic Tac Toe!");
        printBoard();
        boolean xTurn = true;
        while (true) {
            char currentPlayer = xTurn ? PLAYER_X : PLAYER_O;
            playerMove(currentPlayer);
            printBoard();
            if (checkWin(currentPlayer)) {
                printWinMessage(currentPlayer);
                return;
            } else if (isBoardFull()) {
                logger.info("It's a draw!");
                return;
            }
            xTurn = !xTurn;
        }
    }
}