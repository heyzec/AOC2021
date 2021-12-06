import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Day6 {
    public static void main(String[] args) {
        Scanner sc;
        try {
            File file = new File("Day6.in");
            sc = new Scanner(file);

        } catch (FileNotFoundException ex) {
            return;
        }
        String fishInput = sc.nextLine();


        // PART 1

        // long startTime1 = System.currentTimeMillis();
        // int result1 = fishAfterDaysNaive(80, fishInput);
        // long endTime1 = System.currentTimeMillis();

        // long startTime2 = System.currentTimeMillis();
        // int result2 = fishAfterDaysCounter(80, fishInput);
        // long endTime2 = System.currentTimeMillis();

        // System.out.println(String.format("Naive method gave result of %d within %d ms", result1, endTime1- startTime1));
        // System.out.println(String.format("Counter method gave result of %d within %d ms", result2, endTime2 - startTime2));


        // PART 2 (had to change ints to long)

        long startTime2 = System.currentTimeMillis();
        long result2 = fishAfterDaysCounter(256, fishInput);
        long endTime2 = System.currentTimeMillis();

        System.out.println(String.format("Counter method gave result of %d within %d ms", result2, endTime2 - startTime2));
    }


    public static long fishAfterDaysNaive(int nDays, String fishInput) {
        // Initialise the array representing the fishes
        List<Integer> fishes = new ArrayList<Integer>();
        String[] arr = fishInput.split(",");
        for (int i = 0; i < arr.length; i++) {
            fishes.add(Integer.valueOf(arr[i]));
        }

        // Simulate
        for (int day = 0; day < nDays; day++) {
            int length = fishes.size();
            for (int i = 0; i < length; i++) {
                if (fishes.get(Integer.valueOf(i)) == 0) {
                    fishes.set(i, Integer.valueOf(6));
                    fishes.add(Integer.valueOf(8));
                } else {
                    fishes.set(i, fishes.get(i) - 1);
                }
            }
            System.out.println(String.format("After %d days: %s", day + 1, fishes.size()));
        }
        return fishes.size();
    }


    // public static int fishAfterDaysCounter(int nDays, String fishInput) {
    public static long fishAfterDaysCounter(int nDays, String fishInput) {
        // Initialise the map representing the fishes
        HashMap<Integer,Long> fishes = new HashMap<Integer,Long>();
        String[] arr = fishInput.split(",");
        for (int i = 0; i < 9; i++) {
            fishes.put(i, Long.valueOf(0));
        }
        int fishTimer;
        for (int i = 0; i < arr.length; i++) {
            fishTimer = Integer.parseInt(arr[i]);
            fishes.put(fishTimer, fishes.get(fishTimer) + 1);
        }

        // Simulate
        for (int day = 0; day < nDays; day++) {
            long fishBreeding = fishes.get(Integer.valueOf(0));
            for (int i = 0; i < 8; i++) {
                fishes.put(i, fishes.get(Integer.valueOf(i + 1)));
            }
            fishes.put(6, fishes.get(Integer.valueOf(6)) + fishBreeding);
            fishes.put(8, fishBreeding);
            System.out.println(String.format("After %d days: %s", day + 1, Arrays.asList(fishes)));
        }

        // Tally up fishes
        long total = 0;
        for (int i = 0; i < 9; i++) {
            total += fishes.get(Integer.valueOf(i));
        }
        return total;
    }
}

