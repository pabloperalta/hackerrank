package common_child;

import java.io.*;
import java.util.*;

public class Solution {
    private static int max = 0;

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        max = 0;

        for (int i = 0; i < s1.length(); i++) {
            //printS1Position(s1, i);

            int indexOf = s2.indexOf(s1.charAt(i));
            if (indexOf >= 0) {
                Stack<Character> characters = new Stack<>();
                characters.push(s1.charAt(i));
                caminoMasLargo(s1, i, s2, indexOf + 1, characters);
            } else {
                //System.out.println("No esta la letra en s2. Descarto el nodo raiz.");
            }
        }

        //System.out.println(max);
        return max;
    }


    private static int caminoMasLargo(String s1, int i, String s2, int s2Offset, Stack<Character> stack) {
        Integer[] indicesEnS2 = transicionesPosibles(s1, i, s2, s2Offset);

        //printS2Position(s2, s2Offset, indicesEnS2);

        for (int indiceEnS2 : indicesEnS2) {
            //printCurrentPath(s2, stack, indiceEnS2);

            int siguienteNodo = darUnPaso(s1, i, s2, indiceEnS2, stack);

            if (siguienteNodo >= 0) {

                caminoMasLargo(s1, siguienteNodo, s2, indiceEnS2 + 1, stack);

                //printWordCompleted(stack);

                if (stack.size() > max) {
                    max = stack.size();
                }
            }

            stack.pop();
        }

        return 0;
    }

    private static void printWordCompleted(Stack<Character> stack) {
        System.out.print("Word Completed: ");
        printStack(stack);
        System.out.println();
    }

    private static void printCurrentPath(String s2, Stack<Character> stack, int indiceEnS2) {
        System.out.print("Word so far: ");
        printStack(stack);
        System.out.print("[" + s2.charAt(indiceEnS2) + "]");
        System.out.println();
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

    private static void printStack(Stack<Character> stack) {
        Character[] array = stack.toArray(new Character[stack.size()]);

        for (Character c : array) {
            System.out.print(c);
        }
    }

    private static Integer[] transicionesPosibles(String s1, int nodeIndex, String s2, int s2Offset) {
        String substring = s1.substring(nodeIndex + 1);
        String available = s2.substring(s2Offset);
        ArrayList<Integer> posibleTransitionsIndexes = new ArrayList<>();

        for (int i = 0; i < substring.length(); i++) {
            char letter = substring.charAt(i);
            for (int j = 0; j < available.length(); j++) {
                if (available.charAt(j) == letter) {
                    posibleTransitionsIndexes.add(j + s2Offset);
                }
            }
        }

        return posibleTransitionsIndexes.toArray(new Integer[posibleTransitionsIndexes.size()]);
    }

    private static int darUnPaso(String s1, int i, String s2, int indiceEnS2, Stack<Character> stack) {
        char charAt = s2.charAt(indiceEnS2);

        stack.push(charAt);

        int indexOf = s1.substring(i + 1).indexOf(charAt);
        if (indexOf >= 0) {
            return indexOf + i + 1;
        }

        return indexOf;
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

