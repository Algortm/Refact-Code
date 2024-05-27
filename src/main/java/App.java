import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] box = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        boolean boxEmpty = false;
        int winner = 0;

        System.out.println("Enter box number to select. Enjoy!\n");

        // Основний цикл гри
        while (true) {
            printBoard(box);

            if (!boxEmpty) {
                clearBoard(box);
                boxEmpty = true;
            }

            if (winner != 0) {
                displayWinner(winner);
                break;
            }

            // Хід гравця
            playerMove(scan, box);

            // Перевірка на перемогу гравця
            if (checkWin(box, 'X')) {
                winner = 1;
                continue;
            }

            // Перевірка на нічию
            if (!isMoveAvailable(box)) {
                winner = 3;
                continue;
            }

            // Хід комп'ютера
            computerMove(box);

            // Перевірка на перемогу комп'ютера
            if (checkWin(box, 'O')) {
                winner = 2;
                continue;
            }
        }
    }

    // Метод для виведення ігрового поля
    private static void printBoard(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    // Метод для очищення ігрового поля
    private static void clearBoard(char[] box) {
        for (int i = 0; i < 9; i++) {
            box[i] = ' ';
        }
    }

    // Метод для виведення повідомлення про переможця
    private static void displayWinner(int winner) {
        if (winner == 1) {
            System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else {
            System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
    }

    // Метод для ходу гравця
    private static void playerMove(Scanner scan, char[] box) {
        int input;
        while (true) {
            input = scan.nextInt();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }

    // Метод для перевірки на перемогу
    private static boolean checkWin(char[] box, char player) {
        return (box[0] == player && box[1] == player && box[2] == player) ||
                (box[3] == player && box[4] == player && box[5] == player) ||
                (box[6] == player && box[7] == player && box[8] == player) ||
                (box[0] == player && box[3] == player && box[6] == player) ||
                (box[1] == player && box[4] == player && box[7] == player) ||
                (box[2] == player && box[5] == player && box[8] == player) ||
                (box[0] == player && box[4] == player && box[8] == player) ||
                (box[2] == player && box[4] == player && box[6] == player);
    }

    // Метод для перевірки наявності доступних ходів
    private static boolean isMoveAvailable(char[] box) {
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                return true;
            }
        }
        return false;
    }

    // Метод для ходу комп'ютера
    private static void computerMove(char[] box) {
        int rand;
        while (true) {
            rand = (int) (Math.random() * 9);
            if (box[rand] != 'X' && box[rand] != 'O') {
                box[rand] = 'O';
                break;
            }
        }
    }
}
