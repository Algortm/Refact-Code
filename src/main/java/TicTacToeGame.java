import java.util.Scanner;

public class TicTacToeGame {
    private Scanner scan = new Scanner(System.in);
    private static char[] box = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    private byte winner = 0;
    private boolean isGameOn = true;
    public void runGame(){
        printBox();
        System.out.println("Enjoy!\n");
        cleanBox();
        while (isGameOn){
            if(!isBoxFull()){
                makePlayerTurn();
            }
            if(!isBoxFull()){
                makeComputerTurn();
            }
            printBox();
            if(checkWinner('X')){
                winner = 1;
                isGameOn = false;
                break;
            }
            if(checkWinner('O')){
                winner = 2;
                isGameOn = false;
                break;
            }
            if(isBoxFull()){
                winner = 3;
                isGameOn = false;
                break;
            }
        }
        printWinner();
    }
    private void printBox(){
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }
    private void cleanBox(){
        for(int i = 0; i < 9; i++)
            box[i] = ' ';
    }
    private void printWinner(){
        switch (winner) {
            case 1 -> {
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
            }
            case 2 -> {
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
            }
            case 3 -> {
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
            }
        }
    }
    private boolean checkWinner(char symbol){
        return ((box[0]== symbol && box[1]== symbol && box[2]== symbol)
                || (box[3]== symbol && box[4]== symbol && box[5]== symbol)
                || (box[6]== symbol && box[7]== symbol && box[8]== symbol)
                || (box[0]== symbol && box[3]== symbol && box[6]== symbol)
                || (box[1]== symbol && box[4]== symbol && box[7]== symbol)
                || (box[2]== symbol && box[5]== symbol && box[8]== symbol)
                || (box[0]== symbol && box[4]== symbol && box[8]== symbol)
                || (box[2]== symbol && box[4]== symbol && box[6]== symbol));
    }
    private void makePlayerTurn(){
        System.out.println("Enter box number to select:");
        byte input;
        input = scan.nextByte();
        if (input > 0 && input < 10) {
            if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                System.out.println("That one is already in use. Enter another.");
                makePlayerTurn();
            }
            else {
                box[input - 1] = 'X';
            }
        } else{
            System.out.println("Invalid input. Enter again.");
            makePlayerTurn();
        }
    }
    private void makeComputerTurn(){
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * 9);
            if (box[rand] != 'X' && box[rand] != 'O') {
                box[rand] = 'O';
                break;
            }
        }
    }
    private boolean isBoxFull() {
        for (char value : box) {
            if (value != 'X' && value != 'O') {
                return false;
            }
        }
        return true;
    }
}