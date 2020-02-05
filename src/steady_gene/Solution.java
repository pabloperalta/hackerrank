package steady_gene;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        int geneLength = gene.length();
        int expectedCount = geneLength / 4;
        Map<Character, Integer> counters = new HashMap<>();
        Set<Character> posibleLetters = posibleLettersSet();
        HashMap<Character, Integer> countOcurrences = new HashMap<>();
        HashMap<Character, Integer> countOcurrencesReversed = new HashMap<>();

        initCountMaps(posibleLetters, countOcurrences, countOcurrencesReversed);

        int maxI = maxI(gene, expectedCount, countOcurrences);
        int minJ = minJ(gene, geneLength, expectedCount, countOcurrencesReversed);

        if (countOcurrences.values().stream().allMatch(x -> x == expectedCount)) {
            return 0;
        }

        int minFoundLength = Integer.MAX_VALUE;

        for (int i = 0; i < maxI; i++) {
            initMatrixes(counters, posibleLetters);
            for (int j = i; j < geneLength; j++) {

                int currentStringLength = (j + 1) - i;

                if (currentStringLength < minFoundLength) {

                    char charAtJ = gene.charAt(j);

                    if (j == i) {
                        counters.put(charAtJ, 1);
                    } else {
                        counters.put(charAtJ, counters.get(charAtJ) + 1);
                    }

                    if (j >= minJ && isCandidateSubstring(counters, countOcurrences, posibleLetters, expectedCount)) {
                        minFoundLength = currentStringLength;
                    }
                }
            }
        }

        return minFoundLength;
    }

    private static boolean isCandidateSubstring(Map<Character, Integer> countOcurrencesSoFar, HashMap<Character, Integer> countTotalOcurrences, Set<Character> posibleLetters, int expectedCount) {
        for (Character genome : posibleLetters) {

            Integer currentCount = countOcurrencesSoFar.get(genome);
            int requiredCount = countTotalOcurrences.get(genome) - expectedCount;
//            System.out.println("[" + genome + "][" + currentCount + "][" + requiredCount + "]");

            if (currentCount < requiredCount) {
                return false;
            }
        }
        return true;
    }

    private static void initCountMaps(Set<Character> posibleLetters, HashMap<Character, Integer> countOcurrences, HashMap<Character, Integer> countOcurrencesReversed) {
        for (Character genome : posibleLetters) {
            countOcurrences.put(genome, 0);
            countOcurrencesReversed.put(genome, 0);
        }
    }

    private static int minJ(String gene, int geneLength, int expectedCount, HashMap<Character, Integer> countOcurrencesReversed) {
        int minJ = geneLength;
        for (int decrementalIndex = geneLength - 1; decrementalIndex >= 0; decrementalIndex--) {
            char key = gene.charAt(decrementalIndex);
            countOcurrencesReversed.merge(key, 1, (a, b) -> a + b);

            if (countOcurrencesReversed.get(key) > expectedCount && minJ == geneLength) {
                minJ = decrementalIndex;
            }
        }
        return minJ;
    }

    private static int maxI(String gene, int expectedCount, HashMap<Character, Integer> countOcurrences) {
        int maxI = -1;
        for (int incrementalIndex = 0; incrementalIndex < gene.length(); incrementalIndex++) {
            char key = gene.charAt(incrementalIndex);
            countOcurrences.merge(key, 1, (a, b) -> a + b);

            if (countOcurrences.get(key) > expectedCount && maxI == -1) {
                maxI = incrementalIndex;
            }
        }
        return maxI;
    }

    private static void initMatrixes(Map<Character, Integer> counters, Set<Character> posibleLetters) {
        for (Character letter : posibleLetters) {
            counters.put(letter, 0);
        }
    }

    private static Set<Character> posibleLettersSet() {
        HashSet<Character> characters = new HashSet<>();
        characters.add('A');
        characters.add('C');
        characters.add('G');
        characters.add('T');
        return characters;
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
