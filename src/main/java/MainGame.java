import GameClasses.Controllers;
import GameClasses.Game;

import java.util.Scanner;

public class MainGame {
    public static void  main(String[] args) {
        Controllers controllers = new Controllers();
        Scanner input = new Scanner(System.in);
        System.out.println("Give Size of the Board");
        int sizeOfBoard = input.nextInt();
        System.out.println("Give total value to win");
        int totalValueToWin = input.nextInt();
        controllers.startGame(new Game(sizeOfBoard, totalValueToWin));
    }
}
