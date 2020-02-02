package common_child;

import java.io.*;
import java.util.*;

public class Solution {
    private static int max = 0;

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        max = 0;
        Stack<Character> stack = new Stack<>();
        findLongestPath(s1, 0, s2, 0, stack);
        return max;
    }


    private static void findLongestPath(String s1, int s1Offset, String s2, int s2Offset, Stack<Character> stack) {
        for (int i = s1Offset; i < s1.length(); i++) {
            for (int j = s2Offset; j < s2.length(); j++) {
                if (s2.charAt(j) == s1.charAt(i)) {
                    //printCurrentPath(s2, stack, j);

                    char charAt = s2.charAt(j);

                    stack.push(charAt);

                    int nextCharRelativePosition = nextNodeIndex(s1, s1Offset, charAt);

                    if (nextCharRelativePosition >= 0) {

                        findLongestPath(s1, nextCharRelativePosition + 1, s2, j + 1, stack);

                        //printWordCompleted(stack);

                        if (stack.size() > max) {
                            max = stack.size();
                        }
                    }

                    stack.pop();
                }
            }
        }
    }

    private static int nextNodeIndex(String s1, int s1Offset, char charAt) {
        for (int k = s1Offset; k < s1.length(); k++) {
            if (s1.charAt(k) == charAt) {
                return k;
            }
        }
        return -1;
    }

    private static void printWordCompleted(Stack<Character> stack) {
        printStack("Word Completed: ", stack, "");
    }

    private static void printCurrentPath(String s2, Stack<Character> stack, int indiceEnS2) {
        printStack("Word so far: ", stack, "[" + s2.charAt(indiceEnS2) + "]");
    }


    private static void printS1Position(String s1, int i) {
        System.out.println(s1.substring(0, i) + "[" + s1.charAt(i) + "]" + s1.substring(i + 1));
    }

    private static void printS2Position(String s2, int s2Offset, Integer[] indicesEnS2) {
        if (indicesEnS2.length > 0) {
            System.out.print("Posible paths " + s2.substring(s2Offset) + ": ");
        } else {
            System.out.print("No more letter for this path");
        }

        System.out.println();

        for (int k = s2Offset; k < s2.length(); k++) {
            if (Arrays.asList(indicesEnS2).contains(k)) {
                System.out.print("[" + s2.charAt(k) + "]");
            } else {
                System.out.print(s2.charAt(k));
            }
        }
        System.out.println();
    }

    private static void printStack(String prefix, Stack<Character> stack, String suffix) {
        System.out.print(prefix);
        Character[] array = stack.toArray(new Character[stack.size()]);

        for (Character c : array) {
            System.out.print(c);
        }
        System.out.println(suffix);
    }


    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\common_child\\input14.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\common_child\\output.txt"));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

