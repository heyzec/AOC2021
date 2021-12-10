import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.Comparator;


// It's a standard valid parenthesis problem
class Day10 {
    public static void main(String[] args) {
        Scanner sc;
        try {
            File file = new File("Day10.in");
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            return;
        }

        int syntaxErrorScoreTotal = 0;

        // Need to use long instead of int :(
        List<Long> completionScoreTotalList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] result = checkBrackets(line);
            String type = result[0];
            String output = result[1];
            if (type == "corrupt") {
                syntaxErrorScoreTotal += charToSyntaxErrorScore(output.charAt(0));
                continue;
            }
            long completionScore = 0;
            for (int i = 0; i < output.length(); i++) {
                completionScore = completionScore * 5 + charToCompletionScore(output.charAt(i));
            }
            System.out.printf("%s %s \n", output, completionScore);
            completionScoreTotalList.add(completionScore);
        }
        // Part 1 answer
        System.out.println(syntaxErrorScoreTotal);


        // Sort the array to get middle value (a better way is using heap)
        completionScoreTotalList.sort(Comparator.reverseOrder());
        int middleIndex = (completionScoreTotalList.size() - 1) / 2;
        long middleCompletionScore = completionScoreTotalList.get(middleIndex);
        // Part 2 answer
        System.out.println(middleCompletionScore);
    }

    static String[] checkBrackets(String string) {
        // Return array, first char is the expected bracket, second one is the bracket found

        Map<Character, Character> BRACKETS = Map.of(
                '(', ')',
                '[', ']',
                '{', '}',
                '<', '>'
        );
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            char thisChar = string.charAt(i);
            if (BRACKETS.containsKey(thisChar)) {
                stack.push(thisChar);
            } else {
                Character expectedChar = BRACKETS.get(stack.pop());

                if (expectedChar != thisChar) {
                    return new String[] {"corrupt", Character.toString(thisChar)};
                }
            }
        }
        String missingBrackets = "";
        while (!stack.isEmpty()) {
            missingBrackets = missingBrackets + BRACKETS.get(stack.pop());
        }
        return new String[] {"incomplete", missingBrackets};
    }

    static int charToSyntaxErrorScore(char ch) {
        Map<Character, Integer> SCORES = Map.of(
                ')', 3,
                ']', 57,
                '}', 1197,
                '>', 25137
        );
        return SCORES.get(ch);
    }

    static int charToCompletionScore(char ch) {
        Map<Character, Integer> SCORES = Map.of(
                ')', 1,
                ']', 2,
                '}', 3,
                '>', 4
        );
        return SCORES.get(ch);
    }
}
