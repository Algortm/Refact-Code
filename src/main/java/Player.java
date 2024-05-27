import java.util.Scanner;

public class Player {
    public static void playerMove(Scanner scan, Board board) {
        int input;
        while (true) {
            input = scan.nextInt();
            if (input > 0 && input < 10) {
                if (board.getBox()[input - 1] == 'X' || board.getBox()[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    board.setMove(input - 1, 'X');
                    break;
                }
            } else {
                System.out.println("Invalid input. Enter again.");
            }
        }
    }
}