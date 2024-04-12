package game;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean boxAvailable;
        byte winner = 0;
        char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        System.out.println("Enter box number to select. Enjoy!\n");

        AppUtils appUtils = new AppUtils();

        while (true) {

            appUtils.boxCreate(box);

            if (appUtils.defineWinner(winner)) break;

            appUtils.inputX(scan, box);

            boolean winn = appUtils.isWinner(box);
            if (winn) {
                winner = 1;
                continue;
            }

            boxAvailable = appUtils.isBoxAvailable(box);

            if (!boxAvailable) {
                winner = 3;
                continue;
            }

            AppUtils.inputO(box);

            boolean lose = appUtils.isLoser(box);
            if (lose) {
                winner = 2;
            }

        }

    }
}