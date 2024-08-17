package game.logic;

import game.board.Board;
import game.display.Display;
import game.player.HumanPlayer;
import game.player.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class TicTacToe {
    private final Board board;
    private final Player humanPlayer;
    private final Player botPlayer;
    private final Display display;


    public void startGame() {
        display.showMessage("Enter box number to select. Enjoy!\n");
        display.printInitialBoard(board.getBoard());
        Player currentPlayer = humanPlayer;
        Winner winner;

        while (true) {
            boolean validMove = currentPlayer.makeMove(board);
            if (validMove) {
                if (board.isWinningCombination(currentPlayer.getMarker())) {
                    display.printBoard(board.getBoard());
                    winner = currentPlayer instanceof HumanPlayer ? Winner.HUMAN : Winner.BOT;
                    break;
                }

                if (board.isDraw()) {
                    display.printBoard(board.getBoard());
                    winner = Winner.DRAW;
                    break;
                }
                currentPlayer = botPlayer;
            }
            validMove = currentPlayer.makeMove(board);
            if (validMove) {
                display.printBoard(board.getBoard());
                if (board.isWinningCombination(currentPlayer.getMarker())) {
                    winner = currentPlayer instanceof HumanPlayer ? Winner.HUMAN : Winner.BOT;
                    break;
                }
                if (board.isDraw()) {
                    winner = Winner.DRAW;
                    break;
                }

                currentPlayer = humanPlayer;
            }
        }
        display.showMessage(winner.fullMessage());
    }
}

