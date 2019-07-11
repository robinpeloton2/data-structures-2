 import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Testing {

    public static void main(String[] args) {
        int reps = 60;
//        ArrayHashTable hTable = new ArrayHashTable();
//
//        System.out.println("Adding Test Data");
//        // Add values 1 to reps to the hash table.
//        for (int i = 0; i < reps; i += 2) {
//            hTable.add(i);
//        }
//
//        System.out.println("-------------------------------------------------");
//
//        System.out.println("Check Test Data is Added");
//        // Check that all values were added correctly.
//        for (int j = 0; j < reps; j += 2) {
//            hTable.contains(j);
//        }
//
//        System.out.println("-------------------------------------------------");
//
//        System.out.println("Remove Test Data");
//        // Removes all values that were added.
//        for (int k = 0; k < reps; k += 2) {
//            hTable.remove(k);
//        }

         timingExperiment();
    }

    /**
     * An experiment that calculates the average time, variance and standard
     * deviation of adding and deleting in the hash table. The hash table is
     * used a specific number of times defined by reps with the size of the
     * matrix continually increasing.
     */
    public static void timingExperiment() {

        Random r = new Random();
        ArrayHashTable hTable = new ArrayHashTable();
        int reps = 100;
        int n = 1000; // Starting matrix size

        while (n <= 50000) {
            System.out.println("-------------------------------");
            System.out.println("Current Matrix Size: " + n);
            System.out.println("-------------------------------");
            int[] numbers = new int[n];


            for (int j = 0; j < n; j++) {
                numbers[j] = Math.abs(r.nextInt());
            }

            // Record mean and std deviation of performing an operation
            // reps times
            double sum = 0;
            double sumSquared = 0;

            for (int i = 0; i < reps; i++) {
                long t1 = System.nanoTime();

                for (int j = 0; j < n; j++) {
                    hTable.add(numbers[j]);
                }

                for (int j = 0; j < n; j++) {
                    hTable.remove(numbers[j]);
                }

                //Get runtime in nanoseconds
                long t2 = System.nanoTime() - t1;

                // Convert to milliseconds to make the result more readable
                sum += (double) t2 / 1000000.0;
                sumSquared += (t2 / 1000000.0) * (t2 / 1000000.0);
            }
            //Calculate the mean time taken for each rep
            double mean = sum / reps;

            //Calculate the variance to see the range of the set from its mean
            double variance = sumSquared / reps - (mean * mean);

            //Calculate standard deviation to see how the mean runtime
            //can variate
            double stdDev = Math.sqrt(variance);

            // Print results to console
            System.out.println("Mean: " + mean);
            System.out.println("Variance: " + variance);
            System.out.println("Standard Deviation: " + stdDev);
            System.out.println();

            if (n < 20000) {
                n += 1000;
            }

            else if (n < 50001) {
                n += 5000;
            }
        }
    }
}
