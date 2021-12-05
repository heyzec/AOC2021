import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;


public class Day4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] temp = sc.nextLine().split(",");
        List<Integer> draws = new ArrayList<Integer>();

        for (int i = 0; i < temp.length; i++) {
            draws.add(Integer.parseInt(temp[i]));
        }

        List<BingoBoard> allBoards = new ArrayList<BingoBoard>();
        while (sc.hasNextLine()) {
            sc.nextLine();

            String[] input = new String[5];

            for (int i = 0; i < 5; i++) {
                input[i] = sc.nextLine();
            }
            allBoards.add(new BingoBoard(input));
        }

        BingoBoard board = allBoards.get(0);

        int[] pair = {-1, -1};
        int won = 0;
        String output = "";
        for (int i = 0; i < draws.size(); i++) {
            for (int j = 0; j < allBoards.size(); j++) {
                board = allBoards.get(j);
                pair = board.fillCell(draws.get(i));
                if (pair[0] != -1 && board.checkWin(pair)) {
                    board.showLayout();
                    won++;
                }
                if (won == 1 && output == "") {
                    output += "First board: " + String.valueOf(board.calcPoints(pair)) + "\n";
                } else if (won == draws.size()) {
                    output += "Last board: " + String.valueOf(board.calcPoints(pair));
                    System.out.println(output);
                    return;
                }
            }
        }
    }
}

class BingoBoard {
    private int[][] boardLayout = new int[5][5];
    private int[][] markedLayout = new int[5][5];
    private boolean won = false;


    public BingoBoard(String[] input) {
        for (int i = 0; i < 5; i++) {
            String[] temp = input[i].trim().split("\\s+");

            for (int j = 0; j < 5; j++) {
                boardLayout[i][j] = Integer.parseInt(temp[j]);
            }
        }
    }

    // For debugging
    public void showLayout() {
        System.out.println("Board: " + Arrays.deepToString(boardLayout));
        System.out.println("Marked: " + Arrays.deepToString(markedLayout));
    }

    // Mark a cell based on which number is drawn
    public int[] fillCell(int draw) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (boardLayout[i][j] == draw) {
                    markedLayout[i][j] = 1;
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    // Check if the current row and col will lead to the first win for this board
    public boolean checkWin(int[] pair) {
        // Modify it such that draws after the first win are not considered a win
        if (won) {
            return false;
        }

        int row = pair[0];
        int col = pair[1];
        boolean win = true;

        for (int i = 0; i < 5; i++) {
            if (markedLayout[row][i] != 1) {
                win = false;
                break;
            }
        }
        if (win) {
            won = true;
            return true;
        }
        for (int i = 0; i < 5; i++) {
            if (markedLayout[i][col] != 1) {
                return false;
            }
        }
        won = true;
        return true;
    }

    // Calculate the points of this board, treating row and col as the cell which led to this win
    public int calcPoints(int[] pair) {
        int row = pair[0];
        int col = pair[1];
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (markedLayout[i][j] == 0) {
                    sum += boardLayout[i][j];
                }
            }
        }
        return sum * boardLayout[row][col];
    }
}
