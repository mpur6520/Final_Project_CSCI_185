# Anomaly / Horror Game !

A hallway exploration game developed in Java as a final project for CSCI 185, inspired by *Exit 8*.

Explore a mysterious hallway, observe your surroundings, and decide whether to move forward or turn back based on what you do or don't see.

## Gameplay

- **Observe the Hallway:** Look for unusual changes and hidden anomalies.
- **Make Decisions:** Move forward if the hallway looks normal, or turn back if something is wrong.
- **Progress Through Six Periods:** Navigate hallway and try to make it through all six periods.
- **Randomized Anomalies:** Different hallway variations and visual anomalies, including eyes, blood, spills, and other horror-inspired details.
- **Atmospheric Effects:** Animated character movement, screen transitions, and sound effects.

## Controls

- **Left Arrow** — Move left
- **Right Arrow** — Move right
- **Z** — Confirm a decision or continue through menus

**Objective:** Correctly identify anomalies and make it through all six periods; incorrect decisions reset progress.

## Technologies

- Java
- Java Swing and AWT
- Object-oriented programming
- Event handling, animation, and audio playback

## My Contributions

I developed the game's Java code, including gameplay logic, anomaly generation system, player movement, progression mechanics, user interface, and sound handling. The visual artwork was created by my project partner!

## Project Structure

- `Game.java` — Application entry point.
- `GamePanel.java` — Main gameplay, player movement, rendering, and progression.
- `AnomalyManager.java` — Generates hallway states and randomized anomalies.
- `Anomaly.java` — Abstract base class for visual anomalies.
- `BaseState.java` — Defines the different hallway states.
- `IntroScreen.java` and `EndScreen.java` — Introduction, instructions, and ending screens.
- `SoundPlayer.java` — Handles footsteps and door sound effects.
- Individual anomaly classes — Implement specific visual anomalies.

## Project Context

Created collaboratively as a final project for CSCI 185.
