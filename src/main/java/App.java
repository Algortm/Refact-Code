
/**
 * The App class is the entry point of the application.
 * It initializes and starts the game by creating an instance of the GameEngine
 * and invoking the startGame method.
 */
public class App {

    /**
     * The main method is the entry point of the application.
     * It creates an instance of the GameEngine with the necessary components,
     * including a Generator, a Printer, and an InputValidator.
     * Then, it starts the game by calling the startGame method on the GameEngine instance.
     *
     * @param args the command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Engine gameEngine = new GameEngine(new Generator(), new Printer(), new InputValidator());
        gameEngine.startGame();
    }
}
