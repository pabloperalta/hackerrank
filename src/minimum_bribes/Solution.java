package minimum_bribes;

import java.util.Scanner;

public class Solution {

    // Complete the minimumBribes function below.

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

        for (int i = 0; i < q.length; i++) {
            q[i] = q[i] - 1;
        }

        try {
            int totalPermutations = 0;

            for (int i = 0; i < q.length; i++) {
                int value = q[i];
                if (value > i + 2) {
                    throw new RuntimeException("Too chaotic");
                }

                for (int j = i-1 ; j>=0 && j >=value-1; j--) {
                    if (q[j] > value) {
                        totalPermutations++;
                    }
                }
            }

            System.out.println(totalPermutations);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        minimumBribes(new int[]{2, 1, 5, 3, 4});
        minimumBribes(new int[]{5,1,2,3,7,8,6,4});
        minimumBribes(new int[]{1, 2, 5, 3, 7, 8, 6, 4});
    }
}
