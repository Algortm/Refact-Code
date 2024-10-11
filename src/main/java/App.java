import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] gameBoard = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        boolean isGameRunning = true;
        boolean isBoardEmpty = false;
        int winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        while (isGameRunning) {
            printBoard(gameBoard);

            if (!isBoardEmpty) {
                clearBoard(gameBoard);
                isBoardEmpty = true;
            }

            if (winner != 0) {
                displayWinner(winner);
                break;
            }

            // Хід гравця
            playerMove(scanner, gameBoard);

            // Перевірка перемоги гравця
            if (checkWinner(gameBoard, 'X')) {
                winner = 1;
                continue;
            }

            // Перевірка на нічию
            if (!isBoardAvailable(gameBoard)) {
                winner = 3;
                continue;
            }

            // Хід комп'ютера
            makeComputerMove(gameBoard);

            // Перевірка перемоги комп'ютера
            if (checkWinner(gameBoard, 'O')) {
                winner = 2;
            }
        }
    }

    // Виведення ігрової дошки
    private static void printBoard(char[] board) {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    // Очищення дошки
    private static void clearBoard(char[] board) {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    // Хід гравця
    private static void playerMove(Scanner scanner, char[] board) {
        while (true) {
            byte input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (board[input - 1] == 'X' || board[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    board[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    // Перевірка переможця
    private static boolean checkWinner(char[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
                (board[3] == player && board[4] == player && board[5] == player) ||
                (board[6] == player && board[7] == player && board[8] == player) ||
                (board[0] == player && board[3] == player && board[6] == player) ||
                (board[1] == player && board[4] == player && board[7] == player) ||
                (board[2] == player && board[5] == player && board[8] == player) ||
                (board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player);
    }

    // Перевірка наявності вільних місць
    private static boolean isBoardAvailable(char[] board) {
        for (char cell : board) {
            if (cell != 'X' && cell != 'O') {
                return true;
            }
        }
        return false;
    }

    // Хід комп'ютера
    private static void makeComputerMove(char[] board) {
        while (true) {
            int rand = (int) (Math.random() * 9);
            if (board[rand] != 'X' && board[rand] != 'O') {
                board[rand] = 'O';
                break;
            }
        }
    }

    // Виведення переможця
    private static void displayWinner(int winner) {
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
