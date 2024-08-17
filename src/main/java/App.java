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
            new TicTacToe(new Board(), new HumanPlayer(scanner),
                    new BotPlayer(new Random()), new ConsoleDisplay()).startGame();

        }
    }
}