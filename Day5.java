import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

// Consider reading up on Bentley Ottmann Algorithm instead if complexity is concerned
public class Day5 {
    public static void main(String[] args) {
        Scanner sc;
        try {
            File file = new File("./Day5.in");
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            return;
        }

        int[][] grid = new int[1000][1000];
        while (sc.hasNextLine()) {
            String[] temp = sc.nextLine().split("\\s+->\\s+");
            String[] left = temp[0].split(",");
            String[] right = temp[1].split(",");
            int x1 = Integer.parseInt(left[0]);
            int y1 = Integer.parseInt(left[1]);
            int x2 = Integer.parseInt(right[0]);
            int y2 = Integer.parseInt(right[1]);
            if (x1 == x2) {
                for (int i = Math.min(y1, y2); i < Math.max(y1, y2) + 1; i++) {
                    grid[i][x1] += 1;
                }
            } else if (y1 == y2) {
                for (int i = Math.min(x1, x2); i < Math.max(x1, x2) + 1; i++) {
                    grid[y1][i] += 1;
                }
            } else {
                int i = 0;
                int j = 0;
                while (Math.abs(i) < Math.abs(x1 - x2) + 1) {
                    grid[y1 + j][x1 + i] += 1;
                    i += Integer.signum(x2 - x1);
                    j += Integer.signum(y2 - y1);
                }

            }

        }
        int nOverlap = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (grid[i][j] >= 2) {
                    nOverlap++;
                }
            }
        }

        // System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
        System.out.println(String.valueOf(nOverlap));

        sc.close();
    }
}
