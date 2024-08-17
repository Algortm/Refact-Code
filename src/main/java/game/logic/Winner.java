package game.logic;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
enum Winner {
    HUMAN("You won the game!"),
    BOT("You lost the game!"),
    DRAW("It's a draw!");

    private static final String CREATOR = "Created by Shreyas Saha. Thanks for playing!";
    private final String message;

    public String fullMessage() {
        return message + "\n" + CREATOR;
    }
}