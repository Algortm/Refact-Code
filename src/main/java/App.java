import java.util.Scanner;

public class App {
    private char[] gameBoard = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private boolean isGameRunning = true;
    private boolean isBoardEmpty = false;
    private int winner = 0;

    public static void main(String[] args) {
        App app = new App();
        app.startGame();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter box number to select. Enjoy!\n");

        while (isGameRunning) {
            printBoard();

            if (!isBoardEmpty) {
                clearBoard();
                isBoardEmpty = true;
            }

            if (winner != 0) {
                displayWinner(winner);
                break;
            }

            // Хід гравця
            playerMove(scanner);

            // Перевірка перемоги гравця
            if (checkWinner('X')) {
                winner = 1;
                continue;
            }

            // Перевірка на нічию
            if (!isBoardAvailable()) {
                winner = 3;
                continue;
            }

            // Хід комп'ютера
            makeComputerMove();

            // Перевірка перемоги комп'ютера
            if (checkWinner('O')) {
                winner = 2;
            }
        }

        scanner.close();
    }

    // Виведення ігрової дошки
    private void printBoard() {
        System.out.println("\n\n " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " \n");
    }

    // Очищення дошки
    private void clearBoard() {
        for (int i = 0; i < 9; i++) {
            gameBoard[i] = ' ';
        }
    }

    // Хід гравця
    private void playerMove(Scanner scanner) {
        while (true) {
            byte input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (gameBoard[input - 1] == 'X' || gameBoard[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    gameBoard[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    // Перевірка переможця
    private boolean checkWinner(char player) {
        return (gameBoard[0] == player && gameBoard[1] == player && gameBoard[2] == player) ||
                (gameBoard[3] == player && gameBoard[4] == player && gameBoard[5] == player) ||
                (gameBoard[6] == player && gameBoard[7] == player && gameBoard[8] == player) ||
                (gameBoard[0] == player && gameBoard[3] == player && gameBoard[6] == player) ||
                (gameBoard[1] == player && gameBoard[4] == player && gameBoard[7] == player) ||
                (gameBoard[2] == player && gameBoard[5] == player && gameBoard[8] == player) ||
                (gameBoard[0] == player && gameBoard[4] == player && gameBoard[8] == player) ||
                (gameBoard[2] == player && gameBoard[4] == player && gameBoard[6] == player);
    }

    // Перевірка наявності вільних місць
    private boolean isBoardAvailable() {
        for (char cell : gameBoard) {
            if (cell != 'X' && cell != 'O') {
                return true;
            }
        }
        return false;
    }

    // Хід комп'ютера
    private void makeComputerMove() {
        while (true) {
            int rand = (int) (Math.random() * 9);
            if (gameBoard[rand] != 'X' && gameBoard[rand] != 'O') {
                gameBoard[rand] = 'O';
                break;
            }
        }
    }

    // Виведення переможця
    private void displayWinner(int winner) {
        switch (winner) {
            case 1:
                System.out.println("You won the game! Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game! Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw! Thanks for playing!");
                break;
        }
    }
}

