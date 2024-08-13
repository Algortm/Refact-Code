# Tic Tac Toe Game

This is the solution for the GoIT Academy Module 2.2 [Code Quality] homework. While working on this task, the educational project was analyzed with a focus on clean code principles. The SonarLint plugin was used to identify issues related to clean code standards, and the code was subsequently refactored.

## Refactoring Description

#### **1. Renamed Variables**

- In the original code, variables such as `box`, `i` and `rand` were ambiguously named.
- **Refactored Code:** Variables were renamed to more descriptive names like `gameBoard`, `currentPlayer`, and `move`.

#### **2. Extracted Methods**

- In the original code, logic for checking the game winner, handling user input, and printing the game board was embedded within the `main` method.
- **Refactored Code:** The logic was extracted into separate methods (`makeMove`, `makeUserMove`, `makeComputerMove`, `checkWinner`, `printBoard`, etc.) for better organisation.

#### **3. Extracted Classes**

- In the original code, the `App` class was responsible for all tasks, including user interaction, game logic, and board management.
- **Refactored Code:** Responsibilities were distributed among multiple classes:

  - `TicTacToeGame` manages the game flow.
  - `Board` handles board management.
  - `Player` manages player-related logic.
  - `Result` deals with game outcomes.
  - `Message` handles user interactions and console output.

  Specifics:
  1. The `App` class serves as the entry point, creating an instance of `TicTacToeGame` and starting the game by calling the `play` method.
  2. The `TicTacToeGame` class manages the game loop, controls the flow, and handles player and board interactions, including checking win conditions and displaying results.
  3. The `Board` class encapsulates the game board's state and includes methods for resetting the board, printing it, checking for a winner or draw, and updating cell values.
  4. The `Player` enum represents the two players, USER and COMPUTER, and contains their respective marks ('X' and 'O'). It also includes a `next()` method for switching between players.
  5. The `Result` enum defines possible game outcomes: USER_WON, COMPUTER_WON, and DRAW.
  6. The `Message` utility class centralises all user interaction logic, handling console output and formatted text.

#### **4. Moved Methods**

- **Refactored Code:** Methods related to board management (e.g., `initializeNumberedBoard`, `resetBoard`, `printBoard`, `checkRows`, `checkColumns`, `checkDiagonals`, `checkDraw`, etc.) were moved to the `Board` class, where they logically belong. This class now encapsulates all board-related operations.
- The `TicTacToeGame` class now focuses on the game flow, including taking turns, making moves, and determining the game outcome. It uses the `Board` class to interact with the board.

#### **5. Removed Duplication**

- The original code had duplicated logic for checking if a player had won for both players.
- **Refactored Code:** This logic was generalised and encapsulated within methods like `checkWinner`, `checkRows`, `checkColumns`, and `checkDiagonals` in the `Board` class.

#### **6. Replaced Conditionals with Polymorphism**

- The original code relied on conditionals to check the winner, switch players, and handle moves.
- **Refactored Code:** Polymorphism was introduced through the `Player` and `Result` enums to represent players and game outcomes, reducing the need for conditionals. This change simplifies the code structure, making it more extensible and easier to modify.

#### **7. Consolidated Methods**

- **Refactored Code:** Similar operations were consolidated into methods like `makeMove`, which delegates tasks to `makeUserMove` or `makeComputerMove` based on the player. This consolidation reduces code duplication and centralises similar operations, leading to a more maintainable codebase.

## Project Description

The result of the refactoring is a simple, console-based implementation of the classic Tic Tac Toe game in Java. It allows a user to play against the computer, with the game alternating turns between the user and the computer until there is a winner or the game ends in a draw.

## Structure

The project is organised into several packages and classes, each responsible for different aspects of the game:

- **`org.example`**

  - **`App`**: The main entry point of the application. This class initiates the game by creating an instance of `TicTacToeGame` and calling its `play()` method.
- **`org.example.game`**

  - **`TicTacToeGame`**: Manages the game flow, including player turns, checking for a winner or a draw, and handling user input.
  - **`Board`**: Represents the game board, manages its state, prints it to the console, and checks for winning or drawing conditions.
  - **`Player`**: An enum representing the two players (USER and COMPUTER) and their respective marks (X and O).
  - **`Result`**: An enum representing possible game outcomes (USER_WON, COMPUTER_WON, DRAW).
- **`org.example.util`**

  - **`Message`**: A utility class for handling console output, providing methods for printing messages to the user.

## Gameplay

- The game starts with an empty 3x3 board. At the beginning of the game, the board displays the numbers of cells that the user can select if they are empty.
```
1 | 2 | 3 
----------
4 | 5 | 6 
----------
7 | 8 | 9
```
- The user is always assigned the 'X' mark, while the computer uses the 'O' mark.
- The user makes the first move by entering a number between 1 and 9 corresponding to the position on the board.
- The computer then makes its move by randomly selecting an available spot.
- The game continues until either the user or the computer wins, or the board is full (draw).
- After the game ends, the result is displayed, and the program terminates.

## Customisation

- **Board Size**: The current implementation uses a fixed 3x3 board (9 cells). To modify this, you would need to adjust the `BOARD_SIZE` value and associated logic in the `Board` class.
- **Computer AI**: The current computer opponent uses a simple random move strategy. This can be enhanced by implementing more advanced algorithms like Minimax.