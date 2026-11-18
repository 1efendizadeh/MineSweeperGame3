import java.util.Random;
import java.util.Scanner;

/**
 * Core game engine for the console-based Minesweeper game.
 * Manages the hidden mine map, the visible player board, mine placement,
 * neighbor cell inspection, input validation, and win/loss conditions.
 */
public class Minesweeper {
    private final int rowCount;
    private final int colCount;
    private final int totalCells;
    private final int mineCount;
    private final int[][] mineMap;    // Hidden board storing mine locations (-1 = mine, 0 = safe)
    private final int[][] playerBoard; // Visible board displayed to the user

    private boolean isGameActive = true;
    private int safeCellsRevealed = 0;

    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Initializes the game boards and calculates the total number of mines (25% of the grid).
     *
     * @param rowCount Number of rows in the grid
     * @param colCount Number of columns in the grid
     */
    public Minesweeper(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.mineMap = new int[rowCount][colCount];
        this.playerBoard = new int[rowCount][colCount];
        this.totalCells = rowCount * colCount;
        this.mineCount = totalCells / 4;
    }

    /**
     * Prepares the minefield and initiates the main gameplay loop.
     */
    public void start() {
        placeMines();
        // printBoard(mineMap); // Uncomment for debugging to see mine locations
        System.out.println("\nGame Started! Good luck.\n");
        playTurn();
    }

    /**
     * Randomly distributes mines (-1) across the hidden map without overlapping.
     */
    private void placeMines() {
        int placedMines = 0;
        while (placedMines < mineCount) {
            int randRow = random.nextInt(rowCount);
            int randCol = random.nextInt(colCount);

            if (mineMap[randRow][randCol] != -1) {
                mineMap[randRow][randCol] = -1;
                placedMines++;
            }
        }
    }

    /**
     * Prints a 2D game grid with aligned spacing for single-digit and negative numbers.
     *
     * @param grid The 2D integer array to display
     */
    private void printBoard(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Counts adjacent mines (up, down, left, right) around the selected safe cell.
     * Marks the cell as -2 if no adjacent mines are found.
     *
     * @param row Selected row index
     * @param col Selected column index
     */
    private void revealAdjacentMines(int row, int col) {
        if (mineMap[row][col] == 0) {
            if ((row > 0) && (mineMap[row - 1][col] == -1)) {
                playerBoard[row][col]++;
            }
            if ((col < colCount - 1) && (mineMap[row][col + 1] == -1)) {
                playerBoard[row][col]++;
            }
            if ((row < rowCount - 1) && (mineMap[row + 1][col] == -1)) {
                playerBoard[row][col]++;
            }
            if ((col > 0) && (mineMap[row][col - 1] == -1)) {
                playerBoard[row][col]++;
            }

            if (playerBoard[row][col] == 0) {
                playerBoard[row][col] = -2;
            }
        }
    }

    /**
     * Runs the main turn-based loop: reads coordinates, validates moves,
     * checks win/loss states, and updates the board.
     */
    private void playTurn() {
        while (isGameActive) {
            printBoard(playerBoard);

            System.out.print("Select row (0 - " + (rowCount - 1) + "): ");
            int selectedRow = scanner.nextInt();

            System.out.print("Select column (0 - " + (colCount - 1) + "): ");
            int selectedCol = scanner.nextInt();

            // Bounds validation
            if (selectedRow >= rowCount || selectedCol >= colCount || selectedRow < 0 || selectedCol < 0) {
                System.out.println("Invalid coordinates! Please stay within the board limits.\n");
                continue;
            }

            // Duplicate move guard
            if (playerBoard[selectedRow][selectedCol] != 0) {
                System.out.println("Cell already revealed! Please choose a different coordinate.\n");
                continue;
            }

            // Mine check
            if (mineMap[selectedRow][selectedCol] != -1) {
                revealAdjacentMines(selectedRow, selectedCol);
                safeCellsRevealed++;
                System.out.println("Safe cell! Total safe cells uncovered: " + safeCellsRevealed + "\n");

                if (safeCellsRevealed == totalCells - mineCount) {
                    printBoard(playerBoard);
                    System.out.println("CONGRATULATIONS! You cleared the minefield and won the game!");
                    break;
                }
            } else {
                isGameActive = false;
                System.out.println("\nBOOM! You hit a mine. GAME OVER!");
                printBoard(mineMap);
            }
        }
    }
}