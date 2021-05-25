package problemsolving.reverse_array;

public class Solution {
    static int[] reverseArray(int[] a) {
        int length = a.length;

        int[] reverse = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            reverse[(length - 1) - i] = a[i];
        }

        return reverse;
    }
}
