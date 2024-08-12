package org.example.game;

import org.example.util.Message;

import java.util.stream.IntStream;

public class Board {
    private static final int BOARD_SIZE = 9;
    private static final int ROW_SIZE = (int) Math.sqrt(BOARD_SIZE);
    private static final String EMPTY_CELL = " ";
    private final String[] gameBoard = new String[BOARD_SIZE];

    public Board() {
        initializeNumberedBoard();
    }

    private void initializeNumberedBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> gameBoard[i] = String.valueOf(i + 1));
    }

    public void resetBoard() {
        IntStream.range(0, BOARD_SIZE).forEach(i -> gameBoard[i] = EMPTY_CELL);
    }

    public void printBoard() {
        Message.printMessageToUser("", true);
        for (int i = 0; i < BOARD_SIZE; i++) {
            Message.printFormattedMessageToUser("%2s", gameBoard[i]);
            if ((i + 1) % ROW_SIZE == 0) {
                Message.printMessageToUser("", true);
                if (i < BOARD_SIZE - 1) {
                    Message.printMessageToUser("----".repeat(ROW_SIZE), true);
                }
            } else {
                Message.printMessageToUser(" | ", false);
            }
        }
        Message.printMessageToUser("", true);
    }

    public boolean checkWinner(char player) {
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    private boolean checkRows(char player) {
        for (int i = 0; i < BOARD_SIZE; i += ROW_SIZE) {
            if (IntStream.range(i, i + ROW_SIZE).allMatch(j -> gameBoard[j].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char player) {
        for (int i = 0; i < ROW_SIZE; i++) {
            int finalI = i;
            if (IntStream.range(0, ROW_SIZE).allMatch(j -> gameBoard[finalI + j * ROW_SIZE].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char player) {
        return IntStream.range(0, ROW_SIZE).allMatch(i -> gameBoard[i * (ROW_SIZE + 1)].equals(String.valueOf(player))) ||
                IntStream.range(1, ROW_SIZE + 1).allMatch(i -> gameBoard[i * (ROW_SIZE - 1)].equals(String.valueOf(player)));
    }

    public boolean checkDraw() {
        return IntStream.range(0, BOARD_SIZE).noneMatch(i -> gameBoard[i].equals(EMPTY_CELL));
    }

    public boolean isCellEmpty(int index) {
        return gameBoard[index].equals(EMPTY_CELL);
    }

    public void updateCell(int index, char player) {
        gameBoard[index] = String.valueOf(player);
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }
}
