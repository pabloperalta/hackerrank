package frequency_queries;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(int[][] queries) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> index = new HashMap<>(queries.length);
        Map<Integer, Integer> invertedIndex = new HashMap<>(queries.length);


        for (int[] query : queries) {
            Integer operation = query[0];
            Integer argument = query[1];

//            System.out.println();
//            System.out.println("Op [" + operation + "] - Arg[" + argument + "]");

            switch (operation) {
                case 1:
                    Integer freq = increaseFrequency(index, argument);
                    increaseFrequencyCount(invertedIndex, freq);
                    break;
                case 2:
                    Integer currentFrequency = index.get(argument);

                    if (currentFrequency != null && currentFrequency > 0) {
                        int frequencyToReduce = decreaseFrequency(index, argument, currentFrequency);
                        decreaseFrequencyCount(invertedIndex, frequencyToReduce);
                    }

                    break;
                case 3:
                    int result = invertedIndex.get(argument) != null ? 1 : 0;
//                    System.out.println("FrequencySearch arg: " + argument + " [" + (invertedIndex.get(argument) == null ? "Not found" : "Found") + "]");
                    results.add(result);
                    break;
                default:
                    break;
            }

//            describeMap("Index", index);
//            describeMap("Inverted Index", invertedIndex);
        }

        return results;
    }

    private static void describeMap(String title, Map<Integer, Integer> index) {
        System.out.println(title);
        for (int key : index.keySet()) {
            System.out.println("    Key [" + key + "] - Freq[" + index.get(key) + "]");
        }
    }

    private static Integer increaseFrequency(Map<Integer, Integer> index, Integer argument) {
        Integer freq = index.get(argument);

        if (freq == null) {
            index.put(argument, 1);
            return 1;
        }

        index.put(argument, freq + 1);
        return freq + 1;
    }

    private static int decreaseFrequency(Map<Integer, Integer> index, Integer argument, Integer integer) {
        if (integer == 1) {
            index.remove(argument);
        } else {
            index.put(argument, integer - 1);
        }

        return integer;
    }

    private static void increaseFrequencyCount(Map<Integer, Integer> invertedIndex, Integer freq) {
        invertedIndex.merge(freq, 1, (a, b) -> a + b);

        Integer count = invertedIndex.get(freq - 1);

        if (count != null) {
            if (count == 1) {
                invertedIndex.remove(freq - 1);
            } else {
                invertedIndex.merge(freq - 1, 1, (a, b) -> a - b);
            }
        }
    }

    private static void decreaseFrequencyCount(Map<Integer, Integer> invertedIndex, Integer frequencyToReduce) {
        Integer count = invertedIndex.get(frequencyToReduce);

        if (count != null) {
            if (count == 1) {
                invertedIndex.remove(frequencyToReduce);
                invertedIndex.merge(frequencyToReduce - 1, 1, (a, b) -> a + b);
                return;
            }

            invertedIndex.put(frequencyToReduce, count - 1);
            invertedIndex.merge(frequencyToReduce - 1, 1, (a, b) -> a + b);
        }

    }

//    public static void main(String[] args) throws IOException {
//        try (BufferedReader bufferedReader = new BufferedReader(
//                new InputStreamReader(new FileInputStream(".\\src\\frequency_queries\\input07.txt")))) {
//
//            int q = Integer.parseInt(bufferedReader.readLine().trim());
//            int[][] queries = new int[q][2];
//
//            for (int i = 0; i < q; i++) {
//                String[] query = bufferedReader.readLine().split(" ");
//                queries[i][0] = Integer.parseInt(query[0]);
//                queries[i][1] = Integer.parseInt(query[1]);
//            }
//
//            List<Integer> ans = freqQuery(queries);
//
//            try (BufferedWriter bufferedWriter = new BufferedWriter(
//                    new FileWriter(".\\src\\frequency_queries\\outpt.txt"))) {
//
//                bufferedWriter.write(ans.stream().map(Object::toString)
//                        .collect(joining("\n")) + "\n");
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {

        int[][] queries = new int[][]{
                new int[]{1, 10},
                new int[]{3, 1},
                new int[]{2, 10},
                new int[]{3, 1},
                new int[]{1, 10},
                new int[]{1, 10},
                new int[]{3, 1},
                new int[]{3, 2},
                new int[]{2, 10},
                new int[]{3, 1},
                new int[]{2, 10},
                new int[]{3, 1},
//                new int[]{1, 4},
//                new int[]{1, 5},
//                new int[]{1, 5},
//                new int[]{1, 4},
//                new int[]{3, 2},
//                new int[]{2, 4},
//                new int[]{3, 2},
//                new int[]{2, 3},
//                new int[]{2, 4},
//                new int[]{2, 4},
//                new int[]{2, 5},
//                new int[]{2, 5},
//                new int[]{3, 1},
        };

        List<Integer> ans = freqQuery(queries);

        for (Integer n : ans) {
            System.out.println(n);
        }


    }
}
