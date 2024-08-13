package org.example.game;

import org.example.util.Message;

import java.util.stream.IntStream;

public class Board {
    private static final String EMPTY_CELL = " ";
    private final int boardSize;
    private final int rowSize;
    private final String[] gameBoard;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.rowSize = (int) Math.sqrt(boardSize);
        this.gameBoard = new String[boardSize];
        initializeNumberedBoard();
    }

    private void initializeNumberedBoard() {
        IntStream.range(0, boardSize).forEach(i -> gameBoard[i] = String.valueOf(i + 1));
    }

    public void resetBoard() {
        IntStream.range(0, boardSize).forEach(i -> gameBoard[i] = EMPTY_CELL);
    }

    public void printBoard() {
        Message.printMessageToUser("", true);
        for (int i = 0; i < boardSize; i++) {
            Message.printFormattedMessageToUser("%2s", gameBoard[i]);
            if ((i + 1) % rowSize == 0) {
                Message.printMessageToUser("", true);
                if (i < boardSize - 1) {
                    Message.printMessageToUser("----".repeat(rowSize), true);
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
        for (int i = 0; i < boardSize; i += rowSize) {
            if (IntStream.range(i, i + rowSize).allMatch(j -> gameBoard[j].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(char player) {
        for (int i = 0; i < rowSize; i++) {
            int finalI = i;
            if (IntStream.range(0, rowSize).allMatch(j -> gameBoard[finalI + j * rowSize].equals(String.valueOf(player)))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(char player) {
        return IntStream.range(0, rowSize).allMatch(i -> gameBoard[i * (rowSize + 1)].equals(String.valueOf(player))) ||
                IntStream.range(1, rowSize + 1).allMatch(i -> gameBoard[i * (rowSize - 1)].equals(String.valueOf(player)));
    }

    public boolean checkDraw() {
        return IntStream.range(0, boardSize).noneMatch(i -> gameBoard[i].equals(EMPTY_CELL));
    }

    public boolean isCellEmpty(int index) {
        return gameBoard[index].equals(EMPTY_CELL);
    }

    public void updateCell(int index, char player) {
        gameBoard[index] = String.valueOf(player);
    }

    public int getBoardSize() {
        return boardSize;
    }
}
