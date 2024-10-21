import java.util.Scanner;

public final class App {

    private App() {
        throw new UnsupportedOperationException("Do not instantiate utility");
    }

    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        GameMethods game = new GameMethods(scan);
        game.playGame();
    }
}