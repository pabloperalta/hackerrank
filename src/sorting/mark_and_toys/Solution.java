package sorting.mark_and_toys;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        System.out.println(prices.length);

        List<Integer> toys = new ArrayList<>();
        Integer max = Integer.MIN_VALUE;
        Integer sum = 0;

        for (Integer current : prices) {
            if (sum + current <= k) {
                toys.add(current);
                sum += current;
                if (current > max) {
                    max = current;
                }
            } else if (current < max) {
                toys.remove(max);
                toys.add(current);
                sum = sum - max + current;
                max = toys.stream().max(Comparator.naturalOrder()).get();
            }
        }

        return toys.size();
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\sorting\\mark_and_toys\\input12.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\sorting\\mark_and_toys\\output.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] prices = new int[n];

        String[] pricesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pricesItem = Integer.parseInt(pricesItems[i]);
            prices[i] = pricesItem;
        }

        int result = maximumToys(prices, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

