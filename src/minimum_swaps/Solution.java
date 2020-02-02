package minimum_swaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    private static Boolean[] visited;
    private static ArrayList<Pair<Integer, Integer>> arrpos;

    private static class Pair<T, U> {
        private T key;
        private U value;

        public Pair(U value, T key) {
            this.value = value;
            this.key = key;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public U getValue() {
            return value;
        }

        public void setValue(U value) {
            this.value = value;
        }
    }

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        // Function returns the minimum number of swaps
        // required to sort the array

        // To keep track of visited elements. Initialize all elements as not visited or false.
        visited = newVisitedTrackingArray(arr);
        arrpos = elementToPositionPairsList(arr);

        // Sort the array by array element values to get right position of every element as the elements of second array.
        arrpos.sort((o1, o2) -> {
            if (o1.getKey() < o2.getKey()) {
                return -1;
            } else if (o1.getKey().equals(o2.getKey())) {
                return 0;
            } else {
                return 1;
            }
        });

        return IntStream.range(0, arr.length) // Traverse array elements
                .filter(i -> !nodeAlreadyVisited(i) && !nodeAlreadyInCorrectPosition(i))  // already swapped and corrected or already present at correct pos
                .map(Solution::calculateCycleSize) // find out the number of  node in this cycle and add in ans
                .filter(cycle_size -> cycle_size > 0) // Update answer by adding current cycle.
                .map(cycle_size -> (cycle_size - 1))
                .sum();
    }

    private static int calculateCycleSize(int j) {
        System.out.print("Cycle[ ");

        int cycle_size = 0;

        while (!nodeAlreadyVisited(j)) {
            System.out.print(j + " ");
            visited[j] = true; // Mark as visited
            j = arrpos.get(j).getValue();  // move to next node
            cycle_size++;
        }


        System.out.println("] Size = " + (cycle_size > 0 ? (cycle_size - 1) : 0));
        return cycle_size;
    }

    private static boolean nodeAlreadyInCorrectPosition(int i) {
        return arrpos.get(i).getValue() == i;
    }

    private static Boolean nodeAlreadyVisited(int i) {
        return visited[i];
    }

    private static Boolean[] newVisitedTrackingArray(int[] arr) {
        Boolean[] visited = new Boolean[arr.length];
        Arrays.fill(visited, false);
        return visited;
    }

    private static ArrayList<Pair<Integer, Integer>> elementToPositionPairsList(int[] arr) {
        // Create two arrays and use as pairs where first array is element and second array is position of first element
        return IntStream.range(0, arr.length).mapToObj(i -> makeValueToPositionPair(i, arr)).collect(Collectors.toCollection(ArrayList::new));
    }

    private static Pair<Integer, Integer> makeValueToPositionPair(int i, int[] arr) {
        return new Pair<>(arr[i] - 1, i);
    }

    public static void main(String[] args) {
        System.out.println(minimumSwaps(new int[]{1, 3, 5, 2, 4, 6, 7}));
    }
}
