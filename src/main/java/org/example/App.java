package org.example;

import org.example.game.TicTacToeGame;

public class App {

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(9);
        game.play();
    }
}