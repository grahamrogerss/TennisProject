# Tennis

A 2-player Java tennis game built with Java Swing. Players choose characters with different stats and compete in a tennis match with real ball physics.

## Gameplay

- 2-player local multiplayer
- Each player has unique stats: speed, strength, and serve speed
- Ball physics with slope-based directional movement
- Custom sprite graphics

## How to Run
1. Requires Java 8+
2. Compile: javac -d out src/*.java
3. Run: java -cp out TennisProject.TennisProject
4. The TennisImages folder must be in the same directory as where you run the commands

## Project Structure

- Ball.java — ball physics and rendering
- Player.java — base player class with stats
- Player1.java / Player2.java — player-specific controls
- TennisProject.java — main game loop and window

## Built With

- Java
- Java Swing (GUI)

Built in high school (2023) as a final school project.