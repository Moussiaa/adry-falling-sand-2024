package adry.fallingsand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sand sand = new Sand(50, 10);
        sand.randomSand(50);
        System.out.println(sand.toString());
        Scanner scanner = new Scanner(System.in);
        String kb = scanner.nextLine();
        while (kb.isEmpty()) {
            sand.fall();
            System.out.println(sand.toString());
            kb = scanner.nextLine();
        }

    }
}
