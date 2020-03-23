package ice_cream_parlor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        List<Pair> costPairs = new ArrayList<>();

        for (int i = 0; i < cost.length; i++) {
            costPairs.add(new Pair(i + 1, cost[i]));
        }

        costPairs.sort(Comparator.comparingInt(x -> x.value));

        for (Pair pair : costPairs) {
            int complement = Collections.binarySearch(costPairs, new Pair(-1, money - pair.getValue()), (o1, o2) -> {
                if (o1.getIndex() == o2.getIndex()) {
                    return -1;
                }
                return o1.getValue() - o2.getValue();
            });

            if (complement > 0) {
                Pair found = costPairs.get(complement);
                if (found.getIndex() != pair.getIndex()) {
                    if (pair.getIndex() < found.getIndex()) {
                        System.out.println(pair.getIndex() + " " + found.getIndex());
                        return;
                    } else {
                        System.out.println(found.getIndex() + " " + pair.getIndex());
                        return;
                    }
                }
            }
        }
    }

    public static class Pair {
        int index;
        int value;

        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\ice_cream_parlor\\input14.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}

