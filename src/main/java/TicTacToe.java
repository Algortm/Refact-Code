import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(scanner);
        game.startGame();
    }
}