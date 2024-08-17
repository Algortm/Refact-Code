import game.board.Board;
import game.display.ConsoleDisplay;
import game.logic.TicTacToe;
import game.player.BotPlayer;
import game.player.HumanPlayer;

import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Board board = new Board();
            HumanPlayer humanPlayer = new HumanPlayer(scanner);
            BotPlayer botPlayer = new BotPlayer(new Random());
            ConsoleDisplay display = new ConsoleDisplay();

            TicTacToe game = new TicTacToe(board, humanPlayer, botPlayer, display);
            game.startGame();

        }
    }
}