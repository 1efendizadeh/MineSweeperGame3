/**
 * Handles the introductory UI and instructions displayed to the player
 * before the game board is initialized.
 */
public class GameIntro {

    /**
     * Displays the welcome banner and basic rules of the game.
     */
    public void displayWelcomeMessage() {
        System.out.println("=========================================");
        System.out.println("       WELCOME TO MINESWEEPER!           ");
        System.out.println("=========================================");
        System.out.println("Uncover all safe cells without hitting a mine to win.");
        System.out.println("Please define the grid dimensions to begin.\n");
    }
}