import java.util.Scanner;

/**
 * Entry point for the Minesweeper console application.
 * Handles initial user input for board dimensions and launches the game loop.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameIntro intro = new GameIntro();
        intro.displayWelcomeMessage();

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter number of columns: ");
        int columns = scanner.nextInt();

        Minesweeper game = new Minesweeper(rows, columns);
        game.start();

        scanner.close();
    }
}