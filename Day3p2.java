import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.spi.CalendarDataProvider;

class Day3p2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read input into an ArrayList
        List<String> list = new ArrayList<String>();

        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        // System.out.println(list.toString());  // <- This is the way to convert ArrayList to String repr

        String gamma = processList(list, true);
        String epsilon = processList(list, false);

        System.out.println(
                Integer.parseInt(String.valueOf(gamma), 2)
                *
                Integer.parseInt(String.valueOf(epsilon), 2)
        );
    }





    public static String processList(List<String> list, Boolean useMostCommon) {

        int width = list.get(0).length();
        int n_lines = list.size();

        String prefix = "";     // This acts as a filter
        String candidate = "";
        String elem;

        int n_candidates = 0;
        int n_0;
        int n_1;

        int i = 0;
        while (n_candidates != 1) {

            n_0 = 0;
            n_1 = 0;
            n_candidates = 0;
            for (int j = 0; j < n_lines; j++) {
                elem = list.get(j);
                if (elem.startsWith(prefix)) {
                    candidate = elem;
                    n_candidates += 1;
                    if (elem.charAt(i) == '0') {
                        n_0 += 1;
                    } else {
                        n_1 += 1;
                    }
                }
            }
            if (useMostCommon && n_0 > n_1 || !useMostCommon && n_0 <= n_1) {
                prefix += "0";
            } else {
                prefix += "1";
            }
            i += 1;
            if (i == width) {
                candidate = prefix;
                break;
            }
        }
        return candidate;
    }
}
