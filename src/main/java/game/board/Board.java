package game.board;

import lombok.Getter;

@Getter
public final class Board {
    private final char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public boolean isWinningCombination(char icon) {
        return isWinningLine(icon, 0, 1, 2) || (isWinningLine(icon, 3, 4, 5))
                || (isWinningLine(icon, 6, 7, 8)) || (isWinningLine(icon, 0, 3, 6))
                || (isWinningLine(icon, 1, 4, 7)) || (isWinningLine(icon, 2, 5, 8))
                || (isWinningLine(icon, 0, 4, 8)) || (isWinningLine(icon, 2, 4, 6));
    }

    private boolean isWinningLine(char icon, int cell1, int cell2, int cell3) {
        return board[cell1] == icon && board[cell2] == icon && board[cell3] == icon;
    }

    public boolean isDraw() {
        for (char cell : board) {
            if (cell != 'X' && cell != 'O') {
                return false;
            }
        }
        return true;
    }

    public boolean isCellInUse(byte input) {
        return board[input - 1] == 'X' || board[input - 1] == 'O';
    }

    public void makeMove(byte input, char marker) {
        board[input - 1] = marker;
    }
}