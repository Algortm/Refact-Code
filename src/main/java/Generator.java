
/**
 * The Generator class is responsible for creating and initializing
 * a game board for a tic-tac-toe game.
 */
public class Generator {
    /**
     * The total number of elements (boxes) on the game board.
     */
    public static final int COUNT_ELEMENTS = 9;


    /**
     * Generates and initializes a new game board for tic-tac-toe.
     * The board is represented as a character array where each element
     * corresponds to a box on the board, numbered from '1' to '9'.
     *
     * @return a char array representing the initialized game board.
     */
    public char[] generateBoard() {
        char[] box = new char[COUNT_ELEMENTS];

        for (int i = 0; i < COUNT_ELEMENTS; i++) {
            box[i] = (char) ('0' + (i + 1));
        }

        return box;
    }
}
