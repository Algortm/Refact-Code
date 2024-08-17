package game.player;

import game.board.Board;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public final class HumanPlayer implements Player {
    private final Scanner scanner;
    private final char marker = 'X';

    @Override
    public boolean makeMove(Board board) {
        byte input;
        while (true) {
            input = scanner.nextByte();
            if (isValidInput(input)) {
                if (board.isCellInUse(input)) {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    board.makeMove(input, marker);
                    return true;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    @Override
    public char getMarker() {
        return marker;
    }

    private static boolean isValidInput(byte input) {
        return input > 0 && input < 10;
    }
}