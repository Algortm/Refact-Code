
/**
 * The Engine interface defines the contract for starting a game.
 * Any class implementing this interface should provide its own implementation
 * of the startGame method, which initializes and runs the game.
 */
public interface Engine {

    /**
     * Starts the game and handles the main game loop, including player input,
     * game state updates, and determining the outcome of the game.
     */
    void startGame();
}
