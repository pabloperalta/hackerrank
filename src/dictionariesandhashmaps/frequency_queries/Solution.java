package dictionariesandhashmaps.frequency_queries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(int[][] queries) {
        List<Integer> results = new ArrayList<>();
        Map<Integer, Integer> index = new HashMap<>(queries.length);
        Map<Integer, Integer> invertedIndex = new HashMap<>(queries.length);

        for (int[] query : queries) {
            Integer operation = query[0];
            Integer argument = query[1];

            System.out.println();
            System.out.println("Operation[" + operation + "] - Argument[" + argument + "]");

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
                    System.out.println("FrequencySearch arg: " + argument + " [" + (invertedIndex.get(argument) == null ? "Not found" : "Found") + "]");
                    results.add(result);
                    break;
                default:
                    break;
            }

            describeMap("Index", index);
            describeMap("Inverted Index", invertedIndex);
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
        };

        List<Integer> ans = freqQuery(queries);

        System.out.println();
        System.out.println("Answers:");
        for (Integer n : ans) {
            System.out.println(n);
        }

    }
}
