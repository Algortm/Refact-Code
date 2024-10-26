class BoardOfGame {
    private BoardOfGame() {
    }
    public static void printBoard(char[] box) {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }
    public static boolean cleanBoard(char[] box, boolean boxEmpty){
        if (!boxEmpty) {
            for (byte i = 0; i < 9; i++)
                box[i] = ' ';
        }
        return true;
    }
}
