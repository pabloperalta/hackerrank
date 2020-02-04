package steady_gene;

import java.io.*;
import java.util.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        Map<Character, Integer> ocurrences = ocurrencesMap(gene);
        Map<Character, Integer> counters = new HashMap<>();
        Set<Character> posibleLetters = posibleLettersSet(gene);
        int geneLength = gene.length();

        for (Character key : ocurrences.keySet()) {
            System.out.println("La letra [" + key + "] debe aparecer [" + (ocurrences.get(key) - (geneLength / 4)) + "] veces");
        }

        if (ocurrences.values().stream().allMatch(x -> x - (geneLength / 4) == 0)) {
            return 0;
        }

        int minTeoricalLength = 0;
        for(Integer ocurr : ocurrences.values()){
            if(ocurr - (geneLength / 4) > 0){
                minTeoricalLength += ocurr - (geneLength / 4);
            }
        }

        int minFoundLength = Integer.MAX_VALUE;

        for (int i = 0; i < geneLength; i++) {
            initMatrixes(counters, posibleLetters);
            for (int j = i; j < geneLength; j++) {

                int currentStringLength = (j + 1) - i;

                if (currentStringLength < minFoundLength) {
                    boolean isCandidateSubstring = true;

                    System.out.println("[" + i + "][" + j + "] Evaluando: " + gene.substring(i, j + 1));

                    for (Character letter : posibleLetters) {
                        int minimunTimes = ocurrences.get(letter) - (geneLength / 4);
                        Integer counter = counters.get(letter);
                        char charAtJ = gene.charAt(j);

                        if (letter == charAtJ) {
                            if (j == i) {
                                counter = 1;
                                counters.put(letter, counter);
                            } else {
                                counter += 1;
                                counters.put(letter, counter);
                            }
                        }


                        System.out.println("[" + i + "][" + j + "] - Tiene [" + counter + "] letras [" + letter + "], necesita [" + minimunTimes + "]");
                        if (counter < minimunTimes) {
                            isCandidateSubstring = false;
                        }
                    }

                    if (isCandidateSubstring) {
                        System.out.println("[" + i + "][" + j + "] Se encontro ------------------------------------------------------> " + gene.substring(i, j + 1));
                        System.out.println("[" + i + "][" + j + "] Es la menor hasta ahora !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + gene.substring(i, j + 1));
                        minFoundLength = currentStringLength;

                        if(minFoundLength == minTeoricalLength){
                            return minFoundLength;
                        }
                    }
                }
            }
        }

        return minFoundLength;
    }

    private static void initMatrixes(Map<Character, Integer> counters, Set<Character> posibleLetters) {
        for (Character letter : posibleLetters) {
            counters.put(letter, 0);
        }
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
