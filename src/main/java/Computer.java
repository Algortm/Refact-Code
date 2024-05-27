public class Computer {
    public static void computerMove(Board board) {
        int rand;
        while (true) {
            rand = (int) (Math.random() * 9);
            if (board.getBox()[rand] != 'X' && board.getBox()[rand] != 'O') {
                board.setMove(rand, 'O');
                break;
            }
        }
    }
}