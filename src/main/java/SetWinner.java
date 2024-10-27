public class SetWinner {
    public byte setWinner(char[] box, byte winner) {
        boolean boxAvailable = false;
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
        if (createDependenceOfWin(box,'X')) {
            winner = 1;
        } else if (createDependenceOfWin(box,'O')) {
            winner = 2;
        }else if (!boxAvailable) {
            winner = 3;
        }
        return winner;
    }

    private boolean createDependenceOfWin(char[] box,char symbol){
        return ((box[0] == symbol && box[1] == symbol && box[2] == symbol) ||
                (box[3] == symbol && box[4] == symbol && box[5] == symbol) ||
                (box[6] == symbol && box[7] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[3] == symbol && box[6] == symbol) ||
                (box[1] == symbol && box[4] == symbol && box[7] == symbol) ||
                (box[2] == symbol && box[5] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[4] == symbol && box[8] == symbol) ||
                (box[2] == symbol && box[4] == symbol && box[6] == symbol));
    }
}