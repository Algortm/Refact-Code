package org.example;

import org.example.game.TicTacToeGame;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TicTacToeGame game = new TicTacToeGame(9, scanner);
            game.play();
        }
    }
}