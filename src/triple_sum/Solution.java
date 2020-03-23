package triple_sum;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        a = sortAndRemoveDuplicates(a);
        b = sortAndRemoveDuplicates(b);
        c = sortAndRemoveDuplicates(c);

        System.out.println("A");
        System.out.println(Arrays.toString(a));
        System.out.println();
        System.out.println("B");
        System.out.println(Arrays.toString(b));
        System.out.println();
        System.out.println("C");
        System.out.println(Arrays.toString(c));
        System.out.println();

        int aIndex = 0;
        int cIndex = 0;
        long count = 0;

        for (int value : b) {
            while (aIndex < a.length && a[aIndex] <= value) {
//                System.out.println(a[aIndex]);
                aIndex++;
            }

            while (cIndex < c.length && c[cIndex] <= value) {
//                System.out.println(c[cIndex]);
                cIndex++;
            }

            count += (long) aIndex * cIndex;
        }

        return count;
    }

    private static int[] sortAndRemoveDuplicates(int[] a) {
        Arrays.sort(a);

        int j = 0;//for next element
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1]) {
                a[j++] = a[i];
            }
        }

        a[j++] = a[a.length - 1];

        return Arrays.copyOf(a, j);
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\triple_sum\\input04.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\triple_sum\\output.txt"));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

