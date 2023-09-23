# Snake Game

![Snake Game Demo](https://github.com/Yashpaliwal391/Snake.AI/blob/main/Captures/ezgif.com-optimize.gif)



This is a classic Snake game implemented in Java using the Swing library. In this game, you control a snake and try to eat apples to grow longer. Be careful not to run into walls or collide with yourself!

## How to Play

- Use the arrow keys (Up, Down, Left, Right) to control the snake's direction.
- The snake will initially start moving to the right.
- Your goal is to eat as many apples as possible without colliding with walls or the snake's own body.
- When the snake eats an apple, it will grow longer.
- The game ends if the snake collides with the walls or itself.
- Your score is determined by how many apples you eat (each apple eaten adds one point to your score).

## Gameplay Controls

- **Up Arrow Key**: Move the snake upward.
- **Down Arrow Key**: Move the snake downward.
- **Left Arrow Key**: Move the snake to the left.
- **Right Arrow Key**: Move the snake to the right.

## Code Overview

### Dependencies

This game utilizes the Java Swing library for creating the graphical user interface (GUI) and handling user input.

### Class Structure

- `SnakeGame`: The main class that extends `JPanel`. It handles the game's logic, rendering, and user input.
- `TAdapter`: An inner class extending `KeyAdapter` to capture keyboard input for controlling the snake's direction.
- `main`: The entry point of the game, where a `JFrame` is created to host the `SnakeGame` panel.

### Game Elements

- `x` and `y` arrays store the coordinates of the snake's body segments.
- `dots` keeps track of the number of body segments.
- `appleX` and `appleY` store the coordinates of the apple.
- Directional boolean variables (`leftDirection`, `rightDirection`, `upDirection`, `downDirection`) control the snake's movement.
- `inGame` tracks whether the game is still in progress.
- `isEatingApple` is set to `true` when the snake eats an apple.
- A `Timer` is used to update the game at regular intervals.

### Game Initialization

- The game is initialized in the `initGame` method, where the GUI is set up, the snake is initialized, and the first apple is placed.
- The `timer` starts to update the game periodically.

### Game Logic

- The `actionPerformed` method is called by the timer and handles game logic.
- `move` method updates the snake's position.
- `checkApple` method checks if the snake has eaten an apple and increases the score.
- `checkCollision` method checks for collisions with walls or itself and ends the game if necessary.

### Rendering

- The `paintComponent` method is responsible for rendering the game.
- `doDrawing` method draws the apple and the snake.
- `gameOver` method displays the game-over message and score when the game ends.

## Getting Started

To run the Snake game, follow these instructions:

1. Make sure you have Java installed on your system.

2. Compile the source code using your preferred Java compiler.

3. Run the compiled `SnakeGame` class.

4. Use the arrow keys to control the snake and enjoy the game!

## Have fun playing!
