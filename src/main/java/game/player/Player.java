
package game.player;

import game.board.Board;

public interface Player {

    boolean makeMove(Board board);

    char getMarker();
}