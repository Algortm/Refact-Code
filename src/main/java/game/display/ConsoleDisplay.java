package game.display;

public final class ConsoleDisplay implements Display {

    private static final String SEPARATOR = "-".repeat(11);

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printBoard(char[] board) {
        System.out.println("\n");
        for (int cell = 0; cell < board.length; cell += 3) {
            System.out.printf(" %c | %c | %c %n",
                    board[cell] != 'X' && board[cell] != 'O' ? ' ' : board[cell],
                    board[cell + 1] != 'X' && board[cell + 1] != 'O' ? ' ' : board[cell + 1],
                    board[cell + 2] != 'X' && board[cell + 2] != 'O' ? ' ' : board[cell + 2]
            );
            if (cell < board.length - 3) {
                System.out.println(SEPARATOR);
            }
        }
        System.out.println();
    }

    @Override
    public void printInitialBoard(char[] board) {
        System.out.println("\n");
        for (int cell = 0; cell < board.length; cell += 3) {
            System.out.printf(" %c | %c | %c %n", board[cell], board[cell + 1], board[cell + 2]);
            if (cell < board.length - 3) {
                System.out.println(SEPARATOR);
            }
        }
        System.out.println();
    }
}
