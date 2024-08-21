
/**
 * The Printer class is responsible for displaying the game board and
 * the game results to the user.
 */
public class Printer {

    /**
     * Prints the current state of the game board to the console.
     * The board is displayed as a 3x3 grid, with each box separated by a pipe character ('|').
     *
     * @param board a char array representing the current state of the game board.
     */
    public void printBoard(char[] board) {
        int count = 0;

        for (char c : board) {
            if (count > 0 && count % 3 == 0) {
                System.out.println();
            }
            System.out.print(c + " | ");
            count++;
        }

        System.out.println();
        System.out.println();
    }


    /**
     * Prints the result of the game based on the winner's status.
     * The message is displayed to the console and varies depending on whether
     * the player won, lost, or if the game ended in a draw.
     *
     * @param winner an integer representing the game's outcome:
     *               1 for a player win, 2 for a player loss, and 3 for a draw.
     */
    public void printResult(int winner) {
        switch (winner) {
            case GameEngine.WIN_VALUE -> System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case GameEngine.LOSE_VALUE -> System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            case GameEngine.DRAW_VALUE -> System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }
}
