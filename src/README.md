# Java Console Minesweeper

A clean, object-oriented console implementation of the classic Minesweeper game built in Java.

## Features
- **Dynamic Grid Sizing:** Players define custom row and column dimensions at startup.
- **Dual-Matrix Architecture:** Separates hidden mine logic (`mineMap`) from the user-facing display (`playerBoard`).
- **Adjacent Mine Detection:** Inspects neighboring cells (up, down, left, right) and displays mine proximity counts (marking isolated safe cells with `-2`).
- **Input & State Validation:**
    - Prevents `ArrayIndexOutOfBoundsException` via strict coordinate boundary checks.
    - Guards against duplicate moves on already revealed cells.
- **Formatted Console UI:** Aligned grid rendering for consistent readability across single-digit and negative indicators.

## Project Structure
- `Main.java` — Application entry point and user dimension setup.
- `GameIntro.java` — Displays the welcome screen and game rules.
- `Minesweeper.java` — Core game engine handling board generation, mine placement, input validation, and turn progression.

## How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/java-console-minesweeper.git](https://github.com/your-username/java-console-minesweeper.git)