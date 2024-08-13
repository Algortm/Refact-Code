package org.example.game;

public enum Result {
    USER_WON("You won the game!"),
    COMPUTER_WON("You lost the game!"),
    DRAW("It's a draw!");

    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
