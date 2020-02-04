package steady_gene;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        Map<Character, Integer> ocurrences = ocurrencesMap(gene);

        Map<Character, Integer[][]> matrixes = new HashMap<>();

        Set<Character> posibleLetters = posibleLettersSet(gene);

        for (Character key : ocurrences.keySet()) {
            System.out.println("La letra [" + key + "] debe aparecer [" + (ocurrences.get(key) - (gene.length() / 4)) + "] veces");
        }

        if(ocurrences.values().stream().allMatch(x -> x - (gene.length() /4) == 0)){
            return 0;
        }

        for (Character letter : posibleLetters) {
            matrixes.put(letter, new Integer[gene.length()][gene.length()]);
        }

        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < gene.length(); i++) {
            for (int j = i; j < gene.length(); j++) {
                boolean isCandidateSubstring = true;

                System.out.println("[" + i + "][" + j + "] Evaluando: " + gene.substring(i, j + 1));

                for (Character letter : posibleLetters) {
                    Integer[][] matrix = matrixes.get(letter);
                    matrix[i][j] = 0;

                    if (j == i) {
                        if (gene.charAt(i) == letter) {
                            matrix[i][j] = 1;
                        }
                    } else {
                        if (letter == gene.charAt(j)) {
                            matrix[i][j] = matrix[i][j - 1] + 1;
                        } else {
                            matrix[i][j] = matrix[i][j - 1];
                        }
                    }

                    int minimunTimes = ocurrences.get(letter) - (gene.length() / 4);

                    System.out.println("[" + i + "][" + j + "] - Tiene [" + matrix[i][j] + "] letras [" + letter + "], necesita [" + minimunTimes + "]");
                    if (matrix[i][j] < minimunTimes) {
                        isCandidateSubstring = false;
                    }
                }

                if (isCandidateSubstring) {
                    System.out.println("[" + i + "][" + j + "] Se encontro ------------------------------------------------------> " + gene.substring(i, j + 1));
                    if (((j + 1) - i) < minLength) {
                        System.out.println("[" + i + "][" + j + "] Es la menor hasta ahora !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + gene.substring(i, j + 1));
                        minLength = ((j + 1) - i);
                    }
                }

            }
        }

        return minLength;
    }

    private static Set<Character> posibleLettersSet(String gene) {
        HashSet<Character> characters = new HashSet<>();

        for (int i = 0; i < gene.length(); i++) {
            characters.add(gene.charAt(i));
        }

        return characters;
    }

    private static Map<Character, Integer> ocurrencesMap(String gene) {
        HashMap<Character, Integer> countOcurrences = new HashMap<>();

        for (int i = 0; i < gene.length(); i++) {
            countOcurrences.merge(gene.charAt(i), 1, (a, b) -> a + b);
        }

        return countOcurrences;
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\steady_gene\\input04.txt");
        } catch (FileNotFoundException e) {
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Pablo\\projects\\code-quality-game\\hackerrank\\src\\steady_gene\\output.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
