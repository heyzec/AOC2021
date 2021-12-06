import java.util.Scanner;

class Day1p1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int counter = 0;
        int thisDepth;
        int prevDepth1 = sc.nextInt();
        int prevDepth2 = sc.nextInt();
        int prevDepth3 = sc.nextInt();
         
        while (sc.hasNextInt()) {
            thisDepth = sc.nextInt();
            // if (prevDepth2 + prevDepth3 + thisDepth > prevDepth1 + prevDepth2 + prevDepth3) {
            if (thisDepth > prevDepth1) {
                counter++;
            }
            prevDepth1 = prevDepth2;
            prevDepth2 = prevDepth3;
            prevDepth3 = thisDepth;
        }
        System.out.println(String.valueOf(counter));
    }
}





