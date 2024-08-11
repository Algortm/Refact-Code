package org.example.game;

import java.util.Scanner;
import java.util.stream.IntStream;

public class TicTacToeGame {
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';
    private static final int BOARD_SIZE = 16;
    private static final int ROW_SIZE = (int) Math.sqrt(BOARD_SIZE);
    private static final String EMPTY_CELL = " ";

    private final String[] board = new String[BOARD_SIZE];
    private final Scanner scanner = new Scanner(System.in);
    private final char[] players = {USER_MARK, COMPUTER_MARK};

    public TicTacToeGame() {
        initializeNumberedBoard();
        printBoard();
        resetBoard();
    }

    public static void printMessageToUser(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message); //NOSONAR
        } else {
            System.out.print(message); //NOSONAR
        }
    }

    public static void printFormattedMessageToUser(String format, Object... args) {
        System.out.printf(format, args); //NOSONAR
    }

    private void initializeNumberedBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> board[i] = String.valueOf(i + 1));
    }

    public void play() {
        int currentPlayerIndex = 0;

        while (true) {
            makeMove(players[currentPlayerIndex]);

            if (checkWinner(players[currentPlayerIndex])) {
                printBoard();
                displayResult(players[currentPlayerIndex] == USER_MARK ? Result.USER_WON : Result.COMPUTER_WON);
                return;
            }

            if (checkDraw()) {
                printBoard();
                displayResult(Result.DRAW);
                return;
            }

            printBoard();
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }

    private void resetBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> board[i] = EMPTY_CELL);
    }

    public void printBoard() {
        printMessageToUser("", true);
        for (int i = 0; i < BOARD_SIZE; i++) {
            printFormattedMessageToUser("%2s", board[i]);
            if ((i + 1) % ROW_SIZE == 0) {
                printMessageToUser("", true);
                if (i < BOARD_SIZE - 1) {
                    printMessageToUser("----".repeat(ROW_SIZE), true);
                }
            } else {
                printMessageToUser(" | ", false);
            }
        }
        printMessageToUser("", true);
    }

    private void displayResult(Result result) {
        String message = switch (result) {
            case USER_WON -> "You won the game!";
            case COMPUTER_WON -> "You lost the game!";
            case DRAW -> "It's a draw!";
        };
        printMessageToUser(message + "\nCreated by Shreyas Saha. Thanks for playing!", true);
    }

    private void makeMove(char player) {
        if (player == USER_MARK) {
            makeUserMove();
        } else makeComputerMove();
    }

    private void makeUserMove() {
        byte move;
        do {
            move = getUserMove();
        } while (board[move - 1].equals(String.valueOf(USER_MARK)) || board[move - 1].equals(String.valueOf(COMPUTER_MARK)));
        board[move - 1] = String.valueOf(USER_MARK);
    }

    private byte getUserMove() {
        byte input;
        while (true) {
            printMessageToUser("Enter your move: ", true);
            input = scanner.nextByte();
            if (input >= 1 && input <= BOARD_SIZE) {
                return input;
            }
            printMessageToUser("Invalid input. Enter again.", true);
        }
    }

    private void makeComputerMove() {
        byte move;
        do {
            move = (byte) ((Math.random() * BOARD_SIZE) + 1);
        } while (board[move - 1].equals(String.valueOf(USER_MARK)) || board[move - 1].equals(String.valueOf(COMPUTER_MARK)));
        board[move - 1] = String.valueOf(COMPUTER_MARK);
    }

    private boolean checkWinner(char player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    private boolean checkRows(char player) {
        for (int i = 0; i < BOARD_SIZE; i += ROW_SIZE) {
            if (IntStream.range(i, i + ROW_SIZE).allMatch(j -> board[j].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char player) {
        for (int i = 0; i < ROW_SIZE; i++) {
            int finalI = i;
            if (IntStream.range(0, ROW_SIZE).allMatch(j -> board[finalI + j * ROW_SIZE].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char player) {
        return IntStream.range(0, ROW_SIZE).allMatch(i -> board[i * (ROW_SIZE + 1)].equals(String.valueOf(player))) ||
                IntStream.range(1, ROW_SIZE + 1).allMatch(i -> board[i * (ROW_SIZE - 1)].equals(String.valueOf(player)));
    }

    private boolean checkDraw() {
        return IntStream.range(0, BOARD_SIZE).noneMatch(i -> board[i].equals(EMPTY_CELL));
    }
}