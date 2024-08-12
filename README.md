# Tic Tac Toe Game

This is the solution for the GoIT Academy Module 2.2 [Code Quality] homework.
While working on this task, the educational project was analyzed with a focus on clean code principles. The SonarLint plugin was used to identify issues related to clean code standards, and the code was refactored accordingly.

## Refactoring description

#### **1. Renamed Variables**

- In original code variables such as `box`, `i`, `rand`, `box` were named ambiguously.
- **Refactored Code:** Variables were renamed to more descriptive names like `gameBoard`, `currentPlayer`, `move`, etc.
#### **2. Extracted Methods**

- Original code for checking the game winner, handling user input, and the game board printing logic were all embedded within the `main` method.
- **Refactored Code:** The logic for handling different functionalities has been extracted into separate methods (`makeMove`, `makeUserMove`, `makeComputerMove`, `checkWinner`, `printBoard`, etc.).
#### **3. Extracted Classes**

- In original code the `App` class handled all responsibilities, including user interaction, game logic, and board management.
-  **Refactored Code:** The responsibilities were distributed among multiple classes: `TicTacToeGame` for game flow, `Board` for board management, `Player` for player-related logic, `Result` for game outcomes, and `Message` for user interactions and printing text in the console.
1. The `App` class serves as the entry point for the application. It creates an instance of the TicTacToeGame class and initiates the game by calling the play method.
2. `TicTacToeGame` Class - his class manages the game loop, controls the flow of the game, and handles the interaction between the players and the board. It checks for game-winning conditions and handles the display of results.
3. The `Board` class encapsulates the game board's state, including methods for resetting the board, printing it, checking for a winner or a draw, and updating cell values.
4. `Player` Enum - this enum represents the two players in the game, USER and COMPUTER, and contains their respective marks ('X' and 'O'). Player enum encapsulates the user and computer players along with their respective marks (USER_MARK and COMPUTER_MARK).  The `next()` method in the `Player` enum to switch between the user and computer.
5. `Result` Enum - this enum defines the possible outcomes of the game: USER_WON, COMPUTER_WON, and DRAW.
6. `Message` Utility Class - this utility class is responsible for printing messages to the user, including formatted text. It centralises all user interaction-related logic.
#### **4. Moved Method**

- **Refactored Code:** As the `Board` class is responsible for managing the game board, including initializing, updating, and checking the state of the board. This class will encapsulate all board-related operations. Methods related to the board itself, such as `initializeNumberedBoard`, `resetBoard`, `printBoard`, and the various methods to check the board state (`checkRows`, `checkColumns`, `checkDiagonals`, `checkDraw`, etc.), were moved to the `Board` class where they conceptually belong.
- The `TicTacToeGame` class focuses on the game flow, such as taking turns, making moves, and determining the game result. So all the methods responsible for this logic will be in class `TicTacToeGame`: `play`, `displayResult`, `makeMove`, `makeUserMove`, `getUserMove`, `makeComputerMove`. `TicTacToeGame` class will use the `Board` class to interact with the board.`
#### **5. Removed Duplication**

- The original code for checking if a player has won was duplicated for both players.
- **Refactored Code:** This logic was generalised and encapsulated within the `Board` class’s methods like `checkWinner`, `checkRows`, `checkColumns`, and `checkDiagonals`.
#### **6. Replaced Conditional with Polymorphism**

- The original code used conditionals to check the winner, switch players, and handle moves.
- **Refactored Code:** Polymorphism was introduced through the `Player` enum and `Result` enum to represent the players and game outcomes, reducing the need for conditionals. Replacing conditionals with polymorphism simplifies the code structure, making it more extensible and easier to modify when new cases arise.

#### **7. Consolidate Methods**

- **Refactored Code:** Similar operations were consolidated into methods like `makeMove` which delegates to `makeUserMove` or `makeComputerMove` based on the player. Consolidating methods reduces code duplication and centralises similar operations, leading to a more maintainable codebase.

## Description of the project
As result of refactoring was made a simple console-based implementation of the classic Tic Tac Toe game in Java. It allows a user to play against the computer, with the game alternating turns between the user and the computer until there is a winner or the game ends in a draw.

## Structure

The project is organised into several packages and classes, each responsible for different aspects of the game:

- **`org.example`**
    - **`App`**: The main entry point of the application. This class initiates the game by creating an instance of `TicTacToeGame` and calling its `play()` method.

- **`org.example.game`**
    - **`TicTacToeGame`**: Manages the game flow, including player turns, checking for a winner or a draw, and handling user input.
    - **`Board`**: Represents the game board. It manages the state of the board, printing it to the console, and checking for winning or drawing conditions.
    - **`Player`**: An enum representing the two players (USER and COMPUTER) and their respective marks (X and O).
    - **`Result`**: An enum representing the possible outcomes of the game (USER_WON, COMPUTER_WON, DRAW).

- **`org.example.util`**
    - **`Message`**: A utility class for handling console output, providing methods for printing messages to the user.
## Gameplay

- The game starts with an empty 3x3 board. At the beginning of the game user will see the board with numbers of cells which they can use if it's empty.
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

- **Board Size**: The current implementation uses a fixed 3x3 board (9 cells). To modify this, you would need to adjust the `BOARD_SIZE` value and associated logic in the `Board` class:
- **Computer AI**: The current computer opponent uses a simple random move strategy. This can be enhanced to a smarter AI by implementing algorithms like Minimax.