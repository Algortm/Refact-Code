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
    private Winner winner;


    public void startGame() {
        display.showMessage("Enter box number to select. Enjoy!\n");
        display.printInitialBoard(board.getBoard());
        Player currentPlayer = humanPlayer;

        while (true) {
            if (processMove(currentPlayer)) {
                break;
            }
            currentPlayer = currentPlayer instanceof HumanPlayer ? botPlayer : humanPlayer;
        }
        display.showMessage(winner.fullMessage());


    }

    private boolean processMove(Player player) {
        if (player.makeMove(board)) {
            display.printBoard(board.getBoard());
            if (board.isWinningCombination(player.getMarker())) {
                winner = player instanceof HumanPlayer ? Winner.HUMAN : Winner.BOT;
                return true;
            }
            if (board.isDraw()) {
                winner = Winner.DRAW;
                return true;
            }
        }
        return false;
    }
}
