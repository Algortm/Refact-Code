import java.util.Scanner;

public class GameDemo {
    private final Scanner scan = new Scanner(System.in);
    private byte winner = 0;
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean boxEmpty = false;

    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");
        while(true) {
            BoardOfGame.printBoard(box);
            boxEmpty = BoardOfGame.cleanBoard(box, boxEmpty);
            if(winner == 1 || winner == 2 || winner == 3) {
                CheckWinner.checkWin(winner);
                break;
            }
            InputNumbers.input(scan, box);
            winner = SetWinner.setWinner(box, winner);
            Computer.computerProgress(box);
        }
    }
}
