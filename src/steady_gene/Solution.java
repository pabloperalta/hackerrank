package steady_gene;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        int geneLength = gene.length();
        int expectedCount = geneLength / 4;
        Map<Character, Integer> counters = new HashMap<>();
        Map<Character, Integer> countOcurrences = new HashMap<>();
        Map<Character, Integer> countOcurrencesReversed = new HashMap<>();

        countOcurrences.put('A', 0);
        countOcurrences.put('C', 0);
        countOcurrences.put('G', 0);
        countOcurrences.put('T', 0);
        countOcurrencesReversed.put('A', 0);
        countOcurrencesReversed.put('C', 0);
        countOcurrencesReversed.put('G', 0);
        countOcurrencesReversed.put('T', 0);

        int maxI = -1;
        int minJ = geneLength;

        for (int incrementalIndex = 0; incrementalIndex < gene.length(); incrementalIndex++) {
            char key = gene.charAt(incrementalIndex);
            countOcurrences.merge(key, 1, (a, b) -> a + b);

            if (countOcurrences.get(key) > expectedCount && maxI == -1) {
                maxI = incrementalIndex;
            }
            char antiKey = gene.charAt((geneLength - 1) - incrementalIndex);
            countOcurrencesReversed.merge(antiKey, 1, (a, b) -> a + b);

            if (countOcurrencesReversed.get(antiKey) > expectedCount && minJ == geneLength) {
                minJ = (geneLength - 1) - incrementalIndex;
            }
        }

        if (countOcurrences.values().stream().allMatch(x -> x == expectedCount)) {
            return 0;
        }

        int minFoundLength = Integer.MAX_VALUE;

        for (int i = 0; i < maxI; i++) {
            initMatrixes(counters);
            for (int j = i; j < geneLength; j++) {

                int currentStringLength = (j + 1) - i;

                if (currentStringLength < minFoundLength) {

                    char charAtJ = gene.charAt(j);

                    if (j == i) {
                        counters.put(charAtJ, 1);
                    } else {
                        counters.put(charAtJ, counters.get(charAtJ) + 1);
                    }

                    if (j >= minJ && isCandidateSubstring(counters, countOcurrences, expectedCount)) {
                        minFoundLength = currentStringLength;
                    }
                }
            }
        }

        return minFoundLength;
    }

    private static boolean isCandidateSubstring(Map<Character, Integer> countOcurrencesSoFar, Map<Character, Integer> countTotalOcurrences, int expectedCount) {
        return countOcurrencesSoFar.get('A') >= getRequiredCount(countTotalOcurrences, expectedCount, 'A') &&
                countOcurrencesSoFar.get('C') >= getRequiredCount(countTotalOcurrences, expectedCount, 'C') &&
                countOcurrencesSoFar.get('G') >= getRequiredCount(countTotalOcurrences, expectedCount, 'G') &&
                countOcurrencesSoFar.get('T') >= getRequiredCount(countTotalOcurrences, expectedCount, 'T');
    }

    private static int getRequiredCount(Map<Character, Integer> countTotalOcurrences, int expectedCount, Character genome) {
        return countTotalOcurrences.get(genome) - expectedCount;
    }

    private static void initMatrixes(Map<Character, Integer> counters) {
        counters.put('A', 0);
        counters.put('C', 0);
        counters.put('G', 0);
        counters.put('T', 0);
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
