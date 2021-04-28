package max_subarray_sum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {

        List<Pair> prefixArray = makePrefixArray(a, m);

        prefixArray.stream().forEach(x -> System.out.println("v[" + x.getValue() + "] i[" + x.getIndex() + "]"));
        System.out.println();

        prefixArray.sort((o1, o2) -> {
            long subtraction = o1.getValue() - o2.getValue();

            if (subtraction != 0) {
                return Long.valueOf(subtraction / Math.abs(subtraction)).intValue();
            }

            return Long.valueOf(o1.getIndex() - o2.getIndex() / o1.getIndex() - o2.getIndex()).intValue();
        });

        prefixArray.stream().forEach(x -> System.out.println("v[" + x.getValue() + "] i[" + x.getIndex() + "]"));
        Long max = prefixArray.subList(prefixArray.size() - 1, prefixArray.size()).get(0).getValue();
        System.out.println("Max [" + max + "]");
//
        long minDiff = m - max;

        if (minDiff == 1L) {
            return m - minDiff;
        }

        for (int i = 0; i < prefixArray.size(); i++) {
            Pair current = prefixArray.get(i);

            for (int j = 0; j <= i; j++) {
                Pair previous = prefixArray.get(i - j);

                if (previous.getIndex() > current.getIndex()) {
                    long diff = current.getValue() - previous.getValue();
                    System.out.println("Testing multielement array module diff cv[" + current.getValue() + "] ci[" + current.getIndex() + "]" + " pv[" + previous.getValue() + "] pi[" + previous.getIndex() + "]");

                    if (diff < minDiff) {
                        if (diff == 1L) {
                            return m - diff;
                        }

                        minDiff = diff;
                        System.out.println("New min [" + minDiff + "]");
                    }
                    break;
                }
            }

        }

        return m - minDiff;
    }

    private static List<Pair> makePrefixArray(long[] a, long m) {
        List<Pair> prefixArray = new ArrayList<>();

        long acum = 0;
        long previous = 0;
        for (int i = 0; i < a.length; i++) {
            acum = ((previous % m) + acum) % m;
            previous = a[i];
            prefixArray.add(new Pair(i, acum));
        }
        return prefixArray;
    }

    public static class Pair {
        int index;
        long value;

        public Pair(int index, long value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\max_subarray_sum\\input18.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\max_subarray_sum\\output.txt"));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

