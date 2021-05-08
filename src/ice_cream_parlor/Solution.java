package ice_cream_parlor;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {
    // Complete the whatFlavors function below.
    static void whatFlavors(List<Integer> cost, int money) {
        // I create a list of pairs so I can sort by value without loosing the ID of the cost, previously represented
        // as the position in the original array.
        List<Pair> costPairs = IntStream.range(0, cost.size())
                .mapToObj(i -> new Pair(i + 1, cost.get(i)))
                .sorted(Comparator.comparingInt(Pair::getValue))
                .collect(toList());

        for (Pair pair : costPairs) {
            int complementaryCostForThisPair = money - pair.getValue();

            // I want to find a pair with the complementary cost as it's value
            Pair key = new Pair(-1, complementaryCostForThisPair);

            // I use binary search because it is O(log n)
            int indexOfComplementaryPrice = Collections.binarySearch(costPairs, key, Result::compare);

            // If I did not find a complementary value or it is myself, keep looking for a solution
            if (indexOfComplementaryPrice <= 0 || indexOfComplementaryPrice == pair.getIndex()) {
                continue;
            }

            printSolutionInOrder(pair, costPairs.get(indexOfComplementaryPrice));
            return;
        }
    }

    private static void printSolutionInOrder(Pair pair, Pair complement) {
        // Print the lower index first
        if (pair.getIndex() < complement.getIndex()) {
            printSolution(pair, complement);
            return;
        }

        printSolution(complement, pair);
    }

    private static void printSolution(Pair pair, Pair found) {
        System.out.println(pair.getIndex() + " " + found.getIndex());
    }

    private static int compare(Pair o1, Pair o2) {
        if (o1.getIndex() == o2.getIndex()) {
            return -1;
        }

        // I am comparing by value
        return o1.getValue() - o2.getValue();
    }

}

class Pair {
    private int index;
    private int value;

    Pair(int index, int value) {
        this.index = index;
        this.value = value;
    }

    int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

}

public class Solution {

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\ice_cream_parlor\\input14.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

