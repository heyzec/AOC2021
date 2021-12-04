import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Day3p1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n_lines = 1;
        String data = sc.nextLine();
        int len = data.length();
        int[] arr = new int[len];

        // Add each digit into array
        while (sc.hasNextLine()) {
            for (int i = 0; i < len; i++) {
                arr[i] += Character.getNumericValue(data.charAt(i));  // <- To convert char to int
            }
            data = sc.nextLine();
            n_lines++;
        }
        // One more time (fences and posts)
        for (int i = 0; i < len; i++) {
            arr[i] += Character.getNumericValue(data.charAt(i));
        }



        char[] gamma = new char[len];
        char[] epsilon = new char[len];
        for (int i = 0; i < len; i++) {
            // If there are more 1's than 0's added, then the sum must be more than half of n_lines, and vice versa
            if (arr[i] > n_lines / 2) {
                gamma[i] = '1';
                epsilon[i] = '0';
            } else {
                gamma[i] = '0';
                epsilon[i] = '1';
            }
        }
        System.out.println(
                Integer.parseInt(String.valueOf(gamma), 2)
                *
                Integer.parseInt(String.valueOf(epsilon), 2)
        );
    }
}
