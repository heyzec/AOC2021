import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

class Day9 {
    public static void main(String[] args) {
        Scanner sc;
        try {
            File file = new File("Day9.in");
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            return;
        }

        List<List<Integer>> heightmap = new ArrayList<>();  // Store our data in this heightmap
        String line;
        List<Integer> list;
        while (sc.hasNextLine()) {
            list = new ArrayList<>();
            line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                list.add(Character.getNumericValue(line.charAt(i)));
            }
            heightmap.add(list);
        }


        System.out.println(String.format("Part 1: %d", sumRiskLowPoints(heightmap)));

        int R = heightmap.size();
        int C = heightmap.get(0).size();
        int nFilled;

        List<Integer> basinSizes = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                nFilled = countingFloodFill(heightmap, R, C, r, c);
                if (nFilled != 0) {
                    basinSizes.add(nFilled);
                    System.out.println(String.format("r=%d c=%d - %d cells filled", r, c, nFilled));
                }
            }
        }

        // Sort the array to get the top k elements (a better way is using heap)
        basinSizes.sort(Comparator.reverseOrder());
        int output = 1;
        for (int i = 0; i < 3; i++) {
            output *= basinSizes.get(i);
        }

        System.out.println(String.format("Part 2: %d", output));
    }

    public static int countLowerNeighbours(List<List<Integer>> heightmap, int R, int C, int r, int c) {
        int output = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if ((dr == 0 && dc == 0) || r + dr < 0 || r + dr >= R || c + dc < 0 || c + dc >= C)
                    continue;
                if (heightmap.get(r + dr).get(c + dc) <= heightmap.get(r).get(c))
                    output += 1;
            }
        }
        return output;
    }

    public static int sumRiskLowPoints(List<List<Integer>> heightmap) {
        int output = 0;
        int R = heightmap.size();
        int C = heightmap.get(0).size();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (countLowerNeighbours(heightmap, R, C, r, c) == 0)
                    output += heightmap.get(r).get(c) + 1;
            }
        }
        return output;
    }

    public static int countingFloodFill(List<List<Integer>> heightmap, int R, int C, int r, int c) {
        // Use floodfill algorithm, recursively replacing explored cells with -1

        if (
            (r < 0 || c < 0 || r >= R || c  >= C) || // If out of bounds, return
            heightmap.get(r).get(c) == -1 ||         // If already explored, return
            heightmap.get(r).get(c) == 9             // Height 9 not counted as basin
        ) {
            return 0;
        }


        // We will now replace this cell
        int output = 1;
        heightmap.get(r).set(c, -1);

        // Now explore neighbouring cells
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (Math.abs(dr) == Math.abs(dc)) {
                    // Ignore diagonal cells and own cell
                    continue;
                }
                output += countingFloodFill(heightmap, R, C, r + dr, c + dc);
            }
        }
        return output;
    }
}
