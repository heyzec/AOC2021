import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


    // Part 1 was pretty short so I did it in a python console instead
    // import statistics
    // data = "INPUT"
    // data = list(map(int, data.split(',')))
    // sum(map(lambda x: abs(x - statistics.median(data)), data))

    // Note on median:
    // The median has the optimality property
    // Wikipedia: m is a median of X if and only if m is a minimizer of the mean absolute error with respect to X

    // Intuition:
    // Given a list [a, b, c, d, e, f, g, h, i] (sorted)
    //                     ^     ^
    //                  guess  middle
    // The value of e gives us the median. If we calculate cost = (total absolute error wrt guess), we can further decrease the
    // cost by moving the guess closer towards the middle. The increase in cost due to values a, b, c is outweighed by the decrease
    // in cost due to values d, e, f, g, h, i. We can repeat this process until the cost can no longer be opimised at the middle.

class Day7 {
    public static void main(String[] args) {
        Scanner sc;

        try {
            File file = new File("Day7.in");
            sc = new Scanner(file).useDelimiter(",|$");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            return;
        }

        ArrayList<Integer> crabs = new ArrayList<Integer>();
        int firstElem = sc.nextInt();
        int thisElem;
        Integer minElem = firstElem;
        Integer maxElem = firstElem;
        crabs.add(firstElem);

        while (sc.hasNextInt()) {
            thisElem = sc.nextInt();
            crabs.add(thisElem);
            minElem = Math.min(minElem, thisElem);
            maxElem = Math.max(maxElem, thisElem);
        }

        int diff;
        int thisCost = -1;
        int totalCost;
        int minCost = Integer.MAX_VALUE;  // Workaround
        int bestPos = -1;
        for (int pos = minElem; pos < maxElem; pos++) {
            totalCost = 0;
            for (int i = 0; i < crabs.size(); i++) {
                diff = Math.abs(crabs.get(i) - pos);
                // 1 + 2 + 3 + ... + n = n * (n+1) / 2
                thisCost =  diff * (diff + 1) / 2;
                totalCost += thisCost;
            }
            if (totalCost < minCost) {
                minCost = totalCost;
                bestPos = pos;
            }
        }
        System.out.println(minCost);
    }
}
