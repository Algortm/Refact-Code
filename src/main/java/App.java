import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // Constant changes for easier code writing and future changes.
    private static final char EMPTY = ' ';
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private static final int SIZE = 9;




    // The main method that calls all other methods to perform the game.
    public static void runGame() {
        Scanner scan = new Scanner(System.in);
        List<Character> box = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            box.add((char) (i + '1'));
        }
        printBoard(box);

        GameState gameState = GameState.CONTINUE;
        while (gameState == GameState.CONTINUE) {
            playerTurn(scan, box);
            gameState = checkGameState(box, PLAYER);
            if (gameState != GameState.CONTINUE) {
                break;
            }

            computerTurn(box);
            gameState = checkGameState(box, COMPUTER);
        }

        printResult(gameState);
    }


    // Methods for displaying the game board, player's move, computer's move, checking the game state, and displaying the result.
    private static void printBoard(List<Character> box) {
        System.out.println("\n\n " + box.get(0) + " | " + box.get(1) + " | " + box.get(2) + " ");
        System.out.println("-----------");
        System.out.println(" " + box.get(3) + " | " + box.get(4) + " | " + box.get(5) + " ");
        System.out.println("-----------");
        System.out.println(" " + box.get(6) + " | " + box.get(7) + " | " + box.get(8) + " \n");
    }


    // Methods for the player's and computer's moves.
    private static void playerTurn(Scanner scan, List<Character> box) {
        while (true) {
            int input = scan.nextInt();
            if (input > 0 && input <= SIZE && box.get(input - 1) != PLAYER && box.get(input - 1) != COMPUTER) {
                box.set(input - 1, PLAYER);
                break;
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }


    // Method for the computer's move.
    private static void computerTurn(List<Character> box) {
        while (true) {
            int rand = (int) (Math.random() * SIZE);
            if (box.get(rand) != PLAYER && box.get(rand) != COMPUTER) {
                box.set(rand, COMPUTER);
                break;
            }
        }
    }


    // Method for checking the game state.
    private static GameState checkGameState(List<Character> box, char player) {
        if (isWinning(box, player)) {
            return player == PLAYER ? GameState.WIN : GameState.LOSE;
        } else if (!box.contains((char) ('1')) && !box.contains((char) ('2')) && !box.contains((char) ('3')) &&
                !box.contains((char) ('4')) && !box.contains((char) ('5')) && !box.contains((char) ('6')) &&
                !box.contains((char) ('7')) && !box.contains((char) ('8')) && !box.contains((char) ('9'))) {
            return GameState.DRAW;
        } else {
            return GameState.CONTINUE;
        }
    }


    // Method for checking winning combinations.
    private static boolean isWinning(List<Character> box, char player) {
        return (box.get(0) == player && box.get(1) == player && box.get(2) == player) ||
                (box.get(3) == player && box.get(4) == player && box.get(5) == player) ||
                (box.get(6) == player && box.get(7) == player && box.get(8) == player) ||
                (box.get(0) == player && box.get(3) == player && box.get(6) == player) ||
                (box.get(1) == player && box.get(4) == player && box.get(7) == player) ||
                (box.get(2) == player && box.get(5) == player && box.get(8) == player) ||
                (box.get(0) == player && box.get(4) == player && box.get(8) == player) ||
                (box.get(2) == player && box.get(4) == player && box.get(6) == player);
    }


    // Method for displaying the game result.
    private static void printResult(GameState gameState) {
        switch (gameState) {
            case WIN:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case LOSE:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case DRAW:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
        }
    }
}