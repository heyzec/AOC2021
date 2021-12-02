import java.util.Scanner;

class Day2p2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        String command;
        int value;

        while (sc.hasNextLine()) {
            command = sc.nextLine();
            value = Integer.parseInt(command.substring(command.indexOf(" ") + 1));

            if (command.startsWith("forward")) {
                horizontal += value;
                depth += aim * value;
            } else if (command.startsWith("down")) {
                aim += value;
            } else if (command.startsWith("up")) {
                aim -= value;
            }
        }  
        
        System.out.println(String.valueOf(horizontal * depth));
                
    }
}