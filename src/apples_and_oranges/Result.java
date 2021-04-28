package apples_and_oranges;

import java.util.List;

public class Result {

    /*
     * Complete the 'countApplesAndOranges' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER s
     *  2. INTEGER t
     *  3. INTEGER a
     *  4. INTEGER b
     *  5. INTEGER_ARRAY apples
     *  6. INTEGER_ARRAY oranges
     */

    public static void countApplesAndOranges(int samHouseLeft, int samHouseRight, int appleTree, int orangeTree, List<Integer> apples, List<Integer> oranges) {
        // Write your code here
        System.out.println(countFruits(samHouseLeft, samHouseRight, appleTree, apples));
        System.out.println(countFruits(samHouseLeft, samHouseRight, orangeTree, oranges));

    }

    private static int countFruits(int samHouseLeft, int samHouseRight, int tree, List<Integer> fruits) {
        return (int) fruits.stream().filter(fruit -> tree + fruit >= samHouseLeft && tree + fruit <= samHouseRight).count();
    }

}
