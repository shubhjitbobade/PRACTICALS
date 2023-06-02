import java.util.*;

public class BerkeleyAlgorithm {
    private int numProcesses;
    private int[] processClocks;

    public BerkeleyAlgorithm(int numProcesses) {
        this.numProcesses = numProcesses;
        this.processClocks = new int[numProcesses];
        Arrays.fill(processClocks, 0);
    }

    public void synchronizeClocks() {
        int[] timeOffsets = new int[numProcesses];

        // Calculate the average clock time
        int sum = 0;
        for (int i = 0; i < numProcesses; i++) {
            sum += processClocks[i];
        }
        int averageTime = sum / numProcesses;

        // Calculate the time offsets for each process
        for (int i = 0; i < numProcesses; i++) {
            timeOffsets[i] = averageTime - processClocks[i];
        }

        // Adjust the clocks of each process based on the time offsets
        for (int i = 0; i < numProcesses; i++) {
            processClocks[i] += timeOffsets[i];
        }
    }

    public void displayClocks() {
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Process " + (i + 1) + " clock: " + processClocks[i]);
        }
    }

    public void setClock(int processId, int time) {
        if (processId >= 1 && processId <= numProcesses) {
            processClocks[processId - 1] = time;
        } else {
            System.out.println("Invalid process ID.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many processes you want -");
        int numProcesses = scanner.nextInt();
        BerkeleyAlgorithm berkeley = new BerkeleyAlgorithm(numProcesses);

        // Set initial clock times for each process
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter the clock time for process " + (i + 1) + ": ");
            int time = scanner.nextInt();
            berkeley.setClock(i + 1, time);
        }

        // Synchronize the clocks
        berkeley.synchronizeClocks();

        // Display the synchronized clocks
        berkeley.displayClocks();
    }
}
