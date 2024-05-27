public class Board {
    private char[] box;

    public Board() {
        this.box = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    public void printBoard() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            box[i] = ' ';
        }
    }

    public boolean checkWin(char player) {
        return (box[0] == player && box[1] == player && box[2] == player) ||
                (box[3] == player && box[4] == player && box[5] == player) ||
                (box[6] == player && box[7] == player && box[8] == player) ||
                (box[0] == player && box[3] == player && box[6] == player) ||
                (box[1] == player && box[4] == player && box[7] == player) ||
                (box[2] == player && box[5] == player && box[8] == player) ||
                (box[0] == player && box[4] == player && box[8] == player) ||
                (box[2] == player && box[4] == player && box[6] == player);
    }

    public boolean isMoveAvailable() {
        for (char c : box) {
            if (c != 'X' && c != 'O') {
                return true;
            }
        }
        return false;
    }

    public char[] getBox() {
        return box;
    }

    public void setMove(int index, char player) {
        box[index] = player;
    }
}