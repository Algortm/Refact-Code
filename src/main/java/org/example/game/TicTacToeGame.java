package org.example.game;

import org.example.util.Message;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private final Board board;
    private final Scanner scanner;
    private final Random random = new Random();

    public TicTacToeGame(int boardSize, Scanner scanner) {
        this.board = new Board(boardSize);
        this.scanner = scanner;
        board.printBoard();
        board.resetBoard();
    }

    public void play() {
        Player currentPlayer = Player.USER;

        while (true) {
            makeMove(currentPlayer.getMark());

            if (board.checkWinner(currentPlayer.getMark())) {
                board.printBoard();
                displayResult(currentPlayer == Player.USER ? Result.USER_WON : Result.COMPUTER_WON);
                return;
            }

            if (board.checkDraw()) {
                board.printBoard();
                displayResult(Result.DRAW);
                return;
            }

            board.printBoard();
            currentPlayer = currentPlayer.next();
        }
    }

    private void displayResult(Result result) {
        Message.printMessageToUser(result.getMessage() + "\nCreated by Shreyas Saha. Thanks for playing!", true);
    }

    private void makeMove(char player) {
        if (player == Player.USER.getMark()) {
            makeUserMove();
        } else makeComputerMove();
    }

    private void makeUserMove() {
        byte move;
        do {
            move = getUserMove();
        } while (!board.isCellEmpty(move - 1));
        board.updateCell(move - 1, Player.USER.getMark());
    }

    private byte getUserMove() {
        while (true) {
            try {
                Message.printMessageToUser("Enter your move: ", true);
                String inputString = scanner.next();
                int input = Integer.parseInt(inputString);
                if (input >= 1 && input < (board.getBoardSize() + 1)) {
                    return (byte) input;
                } else {
                    Message.printMessageToUser("Invalid input. Enter again.", true);
                }
            } catch (NumberFormatException e) {
                Message.printMessageToUser("Invalid input. Enter again.", true);
            }
        }
    }

    private void makeComputerMove() {
        byte move;
        do {
            move = (byte) (random.nextInt(board.getBoardSize()) + 1);
        } while (!board.isCellEmpty(move - 1));
        board.updateCell(move - 1, Player.COMPUTER.getMark());
    }
}