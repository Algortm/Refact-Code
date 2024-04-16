import java.util.Scanner;

public class TicTacToe {
    private static final char[] BOARD = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean boxEmpty;
    private static final Scanner scan = new Scanner(System.in);
    public void playGame() {

        System.out.println("Enter box number to select. Enjoy!\n");

        while (true) {
            printBoard();

            playerMove();
            if (checkForWinner('X')) {
                printBoard();
                System.out.println("You won the game!\nCreated by Volodymyr Podkorytov. Thanks for playing!\"");
                break;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!\nCreated by Volodymyr Podkorytov. Thanks for playing!");
                break;
            }

            computerMove();
            if (checkForWinner('O')) {
                printBoard();
                System.out.println("You lost the game!\nCreated by Volodymyr Podkorytov. Thanks for playing!");
                break;
            }
          }
        }

    private void playerMove() {
        byte inputNumber;
        while (true) {
            inputNumber = checkForCorrectInput();
            if (inputNumber > 0 && inputNumber < 10) {
                if (BOARD[inputNumber - 1] == 'X' || BOARD[inputNumber - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    BOARD[inputNumber - 1] = 'X';
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD.length; i++) {
            if (BOARD[i] != 'X' && BOARD[i] != 'O') {
                return false;
            }
        }
        return true;
    }

    private void computerMove() {
        byte randomNumber;
        while (true) {
            randomNumber = (byte) (Math.random() * BOARD.length);
            if (BOARD[randomNumber] != 'X' && BOARD[randomNumber] != 'O') {
                BOARD[randomNumber] = 'O';
                break;
            }
        }
    }

    private void printBoard() {
        byte i;
        System.out.println("\n " + BOARD[0] + " | " + BOARD[1] + " | " + BOARD[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + BOARD[3] + " | " + BOARD[4] + " | " + BOARD[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + BOARD[6] + " | " + BOARD[7] + " | " + BOARD[8] + " \n");
        if (!boxEmpty) {
            for (i = 0; i < BOARD.length; i++)
                BOARD[i] = ' ';
            boxEmpty = true;
        }
    }

    private byte checkForCorrectInput() {
        byte input;
        while (true) {
            try {
                input = Byte.parseByte(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return input;
    }

    private boolean checkForWinner(char symbol) {
        return (BOARD[0] == symbol && BOARD[1] == symbol && BOARD[2] == symbol) ||
                (BOARD[3] == symbol && BOARD[4] == symbol && BOARD[5] == symbol) ||
                (BOARD[6] == symbol && BOARD[7] == symbol && BOARD[8] == symbol) ||
                (BOARD[0] == symbol && BOARD[3] == symbol && BOARD[6] == symbol) ||
                (BOARD[1] == symbol && BOARD[4] == symbol && BOARD[7] == symbol) ||
                (BOARD[2] == symbol && BOARD[5] == symbol && BOARD[8] == symbol) ||
                (BOARD[0] == symbol && BOARD[4] == symbol && BOARD[8] == symbol) ||
                (BOARD[2] == symbol && BOARD[4] == symbol && BOARD[6] == symbol);
    }
}
