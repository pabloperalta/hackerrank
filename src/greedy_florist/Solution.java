package greedy_florist;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int clients, int[] originalCost) {

        Arrays.sort(originalCost);

        int[] purchasesPerClient = new int[clients];

        int cost = 0;

        for (int i = originalCost.length - 1; i >= 0; i--) {
            int minPurchases = Integer.MAX_VALUE;
            int indexOfMin = 0;

            for (int j = 0; j < purchasesPerClient.length; j++) {
                int current = purchasesPerClient[j];

                if (current < minPurchases) {
                    minPurchases = current;
                    indexOfMin = j;
                }
            }

            int price = originalCost[i] * (minPurchases + 1);
            cost += price;
            purchasesPerClient[indexOfMin] += 1;
//            System.out.println("Client number [" + indexOfMin + "] bought flower [" + i + "] that costed [" + price + "]. So far the clients spent [" + cost + "]");
//            System.out.println("Purchases per client " + Arrays.toString(purchasesPerClient));
        }

        return cost;

    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\greedy_florist\\input11.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\greedy_florist\\output.txt"));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

