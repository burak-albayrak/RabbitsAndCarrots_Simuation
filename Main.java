import java.util.Random;
import java.util.Scanner;

// The Rabbit class implements the Runnable interface and defines the behavior of each rabbit
class Rabbit implements Runnable {
    private static final Object rabbitLock = new Object();
    private static final Object carrotLock = new Object();

    private String name;
    private int score = 0;
    private int currentBox = 0;
    private boolean[] carrotBoxes;
    private int boxCount;
    private int carrotProductionRate;
    private int carrotTimeout;
    private int sleepTime;
    private Random random = new Random();

    // The constructor of the Rabbit class, initialized with input parameters
    public Rabbit(String name, int boxCount, int carrotProductionRate, int carrotTimeout, int sleepTime) {
        this.name = name;
        this.boxCount = boxCount;
        this.carrotProductionRate = carrotProductionRate;
        this.carrotTimeout = carrotTimeout;
        this.sleepTime = sleepTime;
        this.carrotBoxes = new boolean[boxCount];
    }

    // Method that controls the carrot, adds or doesn't add a carrot based on a randomly generated number
    private void carrotControl() {
        int box = currentBox;

        // Check if the box already has a carrot
        synchronized (carrotLock) {
            if (carrotBoxes[box]) {
                System.out.println(name + " ate the carrot in box " + box);
                score++;
                carrotBoxes[box] = false; // Remove the carrot
            }
        }

        // Add a carrot to the box with a probability based on carrotProductionRate
        if (random.nextInt(100) < carrotProductionRate) {
            synchronized (carrotLock) {
                carrotBoxes[box] = true;
                System.out.println("Carrot added to box " + box);
            }

            // Wait for carrotTimeout
            try {
                Thread.sleep(carrotTimeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Remove the carrot after timeout
            synchronized (carrotLock) {
                if (carrotBoxes[box]) {
                    System.out.println("Carrot in the box " + box + " has timed out");
                    carrotBoxes[box] = false; // Remove the carrot
                }
            }
        }
    }


    // Method simulating the rabbit's movement, sleeps for a certain time and then moves to a box
    private void move() {
        int nextBox = currentBox + 1; // Calculate the next box

        if (nextBox < boxCount) { // Check if the next box is within bounds
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Move to the next box
            synchronized (rabbitLock) {
                System.out.println(name + " jumped to box " + nextBox);
                currentBox = nextBox;
                carrotControl();
            }
        }
    }

    // Implements the run method from the Runnable interface
    @Override
    public void run() {
        // The rabbit continues to move until it reaches the specified number of boxes
        while (currentBox < boxCount) {
            move();
        }
        synchronized (rabbitLock) {
            // After finishing the run, print the total score to the screen
            System.out.println(name + " earned a total of " + score + " points.");
        }
    }
}

// Main class that takes input from the user to create and run rabbits
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input from the user
        System.out.print("Number of Rabbits: ");
        int rabbitCount = scanner.nextInt();

        System.out.print("Number of Boxes: ");
        int boxCount = scanner.nextInt();

        System.out.print("Carrot Production Rate (%): ");
        int carrotProductionRate = scanner.nextInt();

        System.out.print("Carrot Timeout (ms): ");
        int carrotTimeout = scanner.nextInt();

        System.out.print("Rabbit Sleep Time (ms): ");
        int sleepTime = scanner.nextInt();

        // Close the scanner
        scanner.close();

        // Create and run each rabbit in a separate thread
        for (int i = 1; i <= rabbitCount; i++) {
            Rabbit rabbit = new Rabbit("Rabbit " + i, boxCount, carrotProductionRate, carrotTimeout, sleepTime);
            Thread thread = new Thread(rabbit);
            thread.start();
        }

        // Wait for a while to ensure all rabbits finish running
        try {
            Thread.sleep(sleepTime * boxCount * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
