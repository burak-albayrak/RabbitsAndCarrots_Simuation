# Rabbit and Carrot Simulation

## Overview

The Rabbit and Carrot Simulation is a Java-based multi-threaded application that simulates a scenario where rabbits move between boxes to check for and produce carrots. The project demonstrates the use of multi-threading, synchronization, and basic object-oriented programming concepts.

## Features

### Rabbit Movement

Each rabbit is implemented as a separate thread and moves between boxes at specified intervals. The movement is controlled by a sleep time parameter, which determines how long a rabbit waits before moving to the next box.

### Carrot Production

Carrots are randomly added to boxes with a certain probability. This probability is defined by the carrot production rate, which is a percentage value. If the random number generated falls below this rate, a carrot is added to the current box.

### Carrot Control

When a rabbit reaches a box, it checks for the presence of a carrot. If a carrot is found, the rabbit consumes it, increasing its score. The consumed carrot is then removed from the box.

### Carrot Timeout

Carrots have a lifespan defined by a timeout value. If a carrot is not consumed within this timeout period, it disappears from the box. This feature adds a time-sensitive aspect to the simulation.

## Technologies Used

- **Java**: The core programming language used for implementing the simulation.
  
- **Multi-threading**: Utilized to run each rabbit as a separate thread, allowing for concurrent execution.

- **Synchronized Blocks**: Used to ensure thread safety when accessing shared resources like carrot boxes and scores.

## Usage

Upon running the application, the user is prompted to enter the simulation parameters:

- **Number of Rabbits**: Total number of rabbits participating in the simulation.
  
- **Number of Boxes**: Total number of boxes that rabbits will visit.

- **Carrot Production Rate**: Probability of adding a carrot to a box, expressed as a percentage.

- **Carrot Timeout**: Duration after which a carrot will disappear from a box, in milliseconds.

- **Rabbit Sleep Time**: Interval at which a rabbit will move to the next box, in milliseconds.

### Example Input

```
Number of Rabbits: 2
Number of Boxes: 5
Carrot Production Rate (%): 30
Carrot Timeout (ms): 2000
Rabbit Sleep Time (ms): 1000
```
### Example Output

```
Number of Rabbits: 2
Number of Boxes: 5
Carrot Production Rate (%): 30
Carrot Timeout (ms): 2000
Rabbit Sleep Time (ms): 1000

Rabbit 1 jumped to box 1
Carrot added to box 1
Rabbit 2 jumped to box 1
Rabbit 1 ate the carrot in box 1
Carrot in the box 1 has timed out
Rabbit 2 ate the carrot in box 1
Rabbit 1 jumped to box 2
Carrot added to box 2
Rabbit 2 jumped to box 2
Rabbit 1 jumped to box 3
Rabbit 2 jumped to box 3
Carrot added to box 3
Rabbit 1 jumped to box 4
Carrot added to box 4
Rabbit 2 jumped to box 4
Rabbit 1 jumped to box 5
Rabbit 2 jumped to box 5
Carrot added to box 5
Rabbit 1 earned a total of 4 points.
Rabbit 2 earned a total of 4 points.
```

## Acknowledgements

- Java's built-in `Thread` and `Random` classes for multi-threading and random number generation.
- Stack Overflow and various Java documentation sources for troubleshooting and learning.
