package sorting.counting_inversions;

/* Java program for Merge Sort */
class MergeSort {
    // Merges two subarrays of inputArray[].
    // First subarray is inputArray[leftIndex..middleIndex]
    // Second subarray is inputArray[middleIndex+1..rightIndex]
    void merge(int inputArray[], int leftIndex, int middleIndex, int rightIndex) {
        // Find sizes of two subarrays to be merged
        int leftArraySize = middleIndex - leftIndex + 1;
        int rightArraySize = rightIndex - middleIndex;

        /* Create temp arrays */
        int leftArray[] = new int[leftArraySize];
        int rightArray[] = new int[rightArraySize];

        /*Copy data to temp arrays*/
        for (int i = 0; i < leftArraySize; ++i) {
            leftArray[i] = inputArray[leftIndex + i];
        }

        for (int j = 0; j < rightArraySize; ++j) {
            rightArray[j] = inputArray[middleIndex + 1 + j];
        }


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = leftIndex;
        while (i < leftArraySize && j < rightArraySize) {
            if (leftArray[i] <= rightArray[j]) {
                inputArray[k] = leftArray[i];
                i++;
            } else {
                inputArray[k] = rightArray[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of leftArray[] if any */
        while (i < leftArraySize) {
            inputArray[k] = leftArray[i];
            i++;
            k++;
        }

        /* Copy remaining elements of rightArray[] if any */
        while (j < rightArraySize) {
            inputArray[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Main function that sorts inputArray[leftIndex..rightIndex] using
    // merge()
    void sort(int inputArray[], int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            // Find the middle point
            int middleIndex = (leftIndex + rightIndex) / 2;

            // Sort first and second halves
            sort(inputArray, leftIndex, middleIndex);
            sort(inputArray, middleIndex + 1, rightIndex);

            // Merge the sorted halves
            merge(inputArray, leftIndex, middleIndex, rightIndex);
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
