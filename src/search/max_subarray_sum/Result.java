package search.max_subarray_sum;

import java.util.List;
import java.util.TreeSet;

class Result {

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */

    public static long maximumSum(List<Long> a, long m) {
        // This would be a prefix array. The sum of a subarray from i to j can be expressed as the (sum from 0 to j) - (sum from 0 to i). The array precalculates sums from 0 so it would be prefix[j] - prefix[i]
        // With module it would work almost the same (prefix[j] - prefix[i])%m except the corner case where prefix[j] - prefix[i] < 0. We can compensate by adding m.
        // A module is maximized when the very module of the prefix array is highest or there is a module higher in a lower index of the previous array that take the cycle barely backwards enough to get higher.
        // We use a tree set to find the lowest strictly higher value in the prefix array in relation to the current prefix sum module.
        TreeSet<Long> modOfSumFrom0 = new TreeSet<>();

        Long soFar = 0L;
        long maxModule = 0l;

        for (int i = 0; i < a.size(); i++) {
            soFar = (soFar + a.get(i)) % m;
            modOfSumFrom0.add(soFar);

            // If the subarray 0 to i has the highest submodule, so be it
            System.out.println("Module so far at [" + i + "] = [" + soFar + "]");
            if (maxModule < soFar) {
                System.out.println("It is maximun [" + soFar + "]");
                maxModule = soFar;
            }

            Long strictlyHigher = modOfSumFrom0.higher(soFar);

            // If my "m" is say, 6. If I have a module here of 3 and previously I had a 4, it means that between that and here the module of the sum would be 5 because the remainder of the division increased by 5.
            if (strictlyHigher != null) {
                long maxModuleOfASubarrayEndingAtThisIndex = (soFar - strictlyHigher + m) % m;
                System.out.println("Closest higher module would be [" + strictlyHigher + "] which makes max module for any subarray at this index [" + maxModuleOfASubarrayEndingAtThisIndex + "]");
                if (maxModuleOfASubarrayEndingAtThisIndex > maxModule) {
                    System.out.println("It is maximun [" + maxModuleOfASubarrayEndingAtThisIndex + "]");
                    maxModule = maxModuleOfASubarrayEndingAtThisIndex;
                }
            }

        }

        return maxModule;

    }

}
