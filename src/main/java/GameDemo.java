import java.util.Scanner;

public class GameDemo {
    private final Scanner scan = new Scanner(System.in);
    private byte winner = 0;
    private final char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private boolean boxEmpty = false;

    public void startGame() {
        BoardOfGame board = new BoardOfGame();
        CheckWinner win = new CheckWinner();
        InputNumbers inputNumber = new InputNumbers();
        SetWinner statusWinner = new SetWinner();
        Computer computer = new Computer();
        System.out.println("Enter box number to select. Enjoy!\n");
        while(true) {
            board.printBoard(box);
            boxEmpty = board.cleanBoard(box, boxEmpty);
            if(winner == 1 || winner == 2 || winner == 3) {
                win.checkWin(winner);
                break;
            }
            inputNumber.input(scan, box);
            winner = statusWinner.setWinner(box, winner);
            computer.computerProgress(box);
        }
    }
}
