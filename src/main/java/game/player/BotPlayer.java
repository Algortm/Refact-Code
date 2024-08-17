package game.player;

import game.board.Board;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public final class BotPlayer implements Player {
    private final Random random;
    private final char marker = 'O';

    @Override
    public boolean makeMove(Board board) {
        byte rand;
        while (true) {
            rand = (byte) (random.nextInt(9) + 1);
            if (!board.isCellInUse(rand)) {
                board.makeMove(rand, marker);
                return true;
            }
        }
    }

    @Override
    public char getMarker() {
        return marker;
    }
}