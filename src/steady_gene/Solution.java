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

        System.out.println("Expected count: " + expectedCount);

        int maxLeftLimit = getMaxLeftLimit(gene, expectedCount, countOcurrences);
        int minRightLimit = getMinRightLimit(gene, geneLength, expectedCount, countOcurrencesReversed);

        printCountMap(expectedCount, countOcurrences);

        if (countOcurrences.values().stream().allMatch(x -> x == expectedCount)) {
            return 0;
        }

        int minFoundLength = Integer.MAX_VALUE;

        System.out.println("[" + minRightLimit + "][" + maxLeftLimit + "]");
        System.out.println("Minimo string a explorar: " + gene.substring(minRightLimit, maxLeftLimit));

        for (int i = 0; i < maxLeftLimit; i++) {
            initMatrixes(counters, posibleLetters);
            int start = (i < minRightLimit ? minRightLimit : i);
            for (int j = start; j < geneLength; j++) {

                int currentStringLength = (j + 1) - i;

                if (currentStringLength < minFoundLength) {

                    char charAtJ = gene.charAt(j);

                    if (j == start) {
                        counters.put(charAtJ, 1);
                    } else {
                        Integer counter1 = counters.get(charAtJ);
                        counter1 += 1;
                        counters.put(charAtJ, counter1);
                    }

                    if (isCandidateSubstring(counters, countOcurrences, posibleLetters, expectedCount)) {
                        System.out.println("[" + i + "][" + j + "] Es la menor hasta ahora con " + currentStringLength + " letras: " + gene.substring(i, j + 1));
                        minFoundLength = currentStringLength;
                    }
                }
            }
        }

        return minFoundLength;
    }

    private static boolean isCandidateSubstring(Map<Character, Integer> countOcurrencesSoFar, HashMap<Character, Integer> countTotalOcurrences, Set<Character> posibleLetters, int expectedCount) {
        for (Character genome : posibleLetters) {
            if (countOcurrencesSoFar.get(genome) < countTotalOcurrences.get(genome) - expectedCount) {
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

    private static void printCountMap(int expectedCount, HashMap<Character, Integer> countOcurrences) {
        for (Character key : countOcurrences.keySet()) {
            System.out.println("La letra [" + key + "] debe aparecer [" + (countOcurrences.get(key) - expectedCount) + "] veces");
        }
    }

    private static int getMinRightLimit(String gene, int geneLength, int expectedCount, HashMap<Character, Integer> countOcurrencesReversed) {
        int minRightLimit = geneLength;
        for (int decrementalIndex = geneLength - 1; decrementalIndex >= 0; decrementalIndex--) {
            char key = gene.charAt(decrementalIndex);
            countOcurrencesReversed.merge(key, 1, (a, b) -> a + b);

            if (countOcurrencesReversed.get(key) > expectedCount && minRightLimit == geneLength) {
                System.out.println("Min right limit found [" + (decrementalIndex + 1) + "]");
                minRightLimit = decrementalIndex + 1;
            }
        }
        return minRightLimit;
    }

    private static int getMaxLeftLimit(String gene, int expectedCount, HashMap<Character, Integer> countOcurrences) {
        int maxLeftLimit = -1;
        for (int incrementalIndex = 0; incrementalIndex < gene.length(); incrementalIndex++) {
            char key = gene.charAt(incrementalIndex);
            countOcurrences.merge(key, 1, (a, b) -> a + b);

            if (countOcurrences.get(key) > expectedCount && maxLeftLimit == -1) {
                System.out.println("Max left limit found [" + (incrementalIndex) + "]");
                maxLeftLimit = incrementalIndex;
            }
        }
        return maxLeftLimit;
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
