package org.example.game;

public enum Player {
    USER('X'),
    COMPUTER('O');

    private final char mark;

    Player(char mark) {
        this.mark = mark;
    }

    public char getMark(){
        return mark;
    }

    public Player next(){
        return this == USER ? COMPUTER : USER;
    }
}
