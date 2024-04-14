package out.example;

public class App {
    private static final byte MAX_FIELD = 9;
    public static void main(String[] args) {
        int numberMoves = 0;
        byte winner = 0;
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        byte maxMove = (MAX_FIELD + 1) / 2;

        GameUtils game = new GameUtils();

        game.start();
        game.drawBox(box);
        game.nullBox(box, MAX_FIELD);

        while (winner == 0) {

            game.inputBox(box, MAX_FIELD);
            numberMoves++;
            if (game.orBox(box, 'X')) {
                winner = 1;
            }

            if (numberMoves != maxMove) {
                game.rundomBox(box, MAX_FIELD);
                if (game.orBox(box, 'O')) {
                    winner = 2;
                }
            }

            game.drawBox(box);

            if (numberMoves == maxMove && winner == 0) {
                winner = 3;
            }

            game.finish(winner);

        }
    }
}
