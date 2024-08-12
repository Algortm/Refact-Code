package org.example.game;

import org.example.util.Message;

import java.util.Scanner;

public class TicTacToeGame {
    private static final char USER_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';

    private final Board board = new Board();
    private final Scanner scanner = new Scanner(System.in);
    private final char[] players = {USER_MARK, COMPUTER_MARK};

    public TicTacToeGame() {
        board.printBoard();
        board.resetBoard();
    }

    public void play() {
        int currentPlayerIndex = 0;

        while (true) {
            makeMove(players[currentPlayerIndex]);

            if (board.checkWinner(players[currentPlayerIndex])) {
                board.printBoard();
                displayResult(players[currentPlayerIndex] == USER_MARK ? Result.USER_WON : Result.COMPUTER_WON);
                return;
            }

            if (board.checkDraw()) {
                board.printBoard();
                displayResult(Result.DRAW);
                return;
            }

            board.printBoard();
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }

    private void displayResult(Result result) {
        String message = switch (result) {
            case USER_WON -> "You won the game!";
            case COMPUTER_WON -> "You lost the game!";
            case DRAW -> "It's a draw!";
        };
        Message.printMessageToUser(message + "\nCreated by Shreyas Saha. Thanks for playing!", true);
    }

    private void makeMove(char player) {
        if (player == USER_MARK) {
            makeUserMove();
        } else makeComputerMove();
    }

    private void makeUserMove() {
        byte move;
        do {
            move = getUserMove();
        } while (!board.isCellEmpty(move - 1));
        board.updateCell(move - 1, USER_MARK);
    }

    private byte getUserMove() {
        byte input;
        while (true) {
            Message.printMessageToUser("Enter your move: ", true);
            input = scanner.nextByte();
            if (input >= 1 && input <= board.getBoardSize()) {
                return input;
            }
            Message.printMessageToUser("Invalid input. Enter again.", true);
        }
    }

    private void makeComputerMove() {
        byte move;
        do {
            move = (byte) ((Math.random() * board.getBoardSize()) + 1);
        } while (!board.isCellEmpty(move - 1));
        board.updateCell(move - 1, COMPUTER_MARK);
    }
}