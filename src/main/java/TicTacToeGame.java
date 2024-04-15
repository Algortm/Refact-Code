import java.util.Scanner;

class TicTacToeGame {
    private byte winner = 0;
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final Scanner scanner;

    public TicTacToeGame(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startGame() {
        while (true) {
            displayBoard();
            if (winner != 0) {
                printEndMessage();
                break;
            }

            playerMove();
            checkWinner('X');

            if (winner != 0) continue;

            computerMove();
            checkWinner('O');

            if (!isBoxAvailable()) winner = 3;
        }
    }

    private void displayBoard() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    private void printEndMessage() {
        if (winner == 1) System.out.println("Congratulations! You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        else if (winner == 2) System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        else if (winner == 3) System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
    }

    private void playerMove() {
        byte input;
        while (true) {
            input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else System.out.println("Invalid input. Enter again.");
        }
    }

    private void computerMove() {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private void checkWinner(char symbol) {
        if ((box[0] == symbol && box[1] == symbol && box[2] == symbol) ||
                (box[3] == symbol && box[4] == symbol && box[5] == symbol) ||
                (box[6] == symbol && box[7] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[3] == symbol && box[6] == symbol) ||
                (box[1] == symbol && box[4] == symbol && box[7] == symbol) ||
                (box[2] == symbol && box[5] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[4] == symbol && box[8] == symbol) ||
                (box[2] == symbol && box[4] == symbol && box[6] == symbol)) {
            if (symbol == 'X') winner = 1;
            else winner = 2;
        }
    }

    private boolean isBoxAvailable() {
        for (char c : box) if (c != 'X' && c != 'O') return true;
        return false;
    }
}


