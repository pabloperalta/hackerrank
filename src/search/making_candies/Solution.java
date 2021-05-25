package search.making_candies;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Result {

    /*
     * Complete the 'minimumPasses' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER m
     *  2. LONG_INTEGER w
     *  3. LONG_INTEGER p
     *  4. LONG_INTEGER n
     */

    public static long minimumPasses(long machines, long workers, long purchasingCost, long goal) {
        System.out.println("Begin");

        if (purchasingCost == 0) {
            return 1;
        }

        long currentCandy = 0;
        long countPasses = 0;

        System.out.println("Beginning state: machines[" + machines + "], workers[" + workers + "], purchasingCost[" + purchasingCost + "], current candy[" + currentCandy + "], goal{" + goal + "}");

        // While I have not reached my goal
        while (currentCandy < goal) {
            System.out.println("Current state: machines[" + machines + "], workers[" + workers + "], current candy[" + currentCandy + "], goal{" + goal + "}");

            // I should know all my alternatives
            List<Purchase> purchases = generatePurchasingAlternatives(machines, workers, currentCandy, purchasingCost);
            System.out.println("Generated [" + purchases.size() + "] alternatives");

            // I have to find the one that gets me closer to the goal. Meaning it minimizes the amount of days left at this rate
            // current candy + N times this production (m * w) = goal
            // This means N = (goal - current candy)/current production
            Purchase minimizingPurchase = getMinimizingPurchase(machines, workers, goal, currentCandy, purchases);

            // Once I find this minimizing choice y take it
            System.out.println("Minimizing purchase: machines[" + minimizingPurchase.getMachines() + "], workers[" + minimizingPurchase.getWorkers() + "], cost[" + minimizingPurchase.getCost() + "]");
            currentCandy -= minimizingPurchase.getCost();
            machines += minimizingPurchase.getMachines();
            workers += minimizingPurchase.getWorkers();
            currentCandy += machines * workers;
            countPasses++;

            System.out.println();
        }

        System.out.println("Final state: machines[" + machines + "], workers[" + workers + "], current candy[" + currentCandy + "], goal{" + goal + "}");
        return countPasses;
    }

    private static Purchase getMinimizingPurchase(long machines, long workers, long goal, long currentCandy, List<Purchase> purchases) {
        double min = ((double) goal - currentCandy) / (double) (workers * machines);
        System.out.println("Remainig passes if no purchase is done[" + min + "]");

        Purchase minimizingPurchase = new Purchase(0, 0, 0);

        for (Purchase purchase : purchases) {
            long currentProduction = (machines + purchase.getMachines()) * (workers + purchase.getWorkers());
            if (currentProduction > 0) {
                long candyAfterPurchase = currentCandy - purchase.getCost();
                double n = ((double) goal - candyAfterPurchase) / (double) currentProduction;
                System.out.println("Evaluating purchase: machines[" + purchase.getMachines() + "], workers[" + purchase.getWorkers() + "], cost[" + purchase.getCost() + "], currentCandy[" + currentCandy + "]");
                System.out.println("If do this purchase, i would start at [" + candyAfterPurchase + "] and produce [" + currentProduction + "] dynamicprogramming.candies per turn. Reaching [" + goal + "] would take [" + n + "] passes");
                if (n < min) {
                    min = n;
                    minimizingPurchase = purchase;
                }
            }
        }

        System.out.println("Decided purchase: machines[" + minimizingPurchase.getMachines() + "], workers[" + minimizingPurchase.getWorkers() + "], cost[" + minimizingPurchase.getCost() + "], currentCandy[" + (currentCandy - minimizingPurchase.getCost()) + "]");

        return minimizingPurchase;
    }

    private static List<Purchase> generatePurchasingAlternatives(long machines, long workers, long currentCandy, long purchasingCost) {

        ArrayList<Purchase> purchases = new ArrayList<>();

        long purchasedMachines = 0;
        long hiredWorkers = 0;
        long cost = 0;

        while (currentCandy - (cost + purchasingCost) >= 0) {

            if (machines <= workers) {
                purchasedMachines++;
            } else {
                hiredWorkers++;
            }

            cost += purchasingCost;

            purchases.add(new Purchase(purchasedMachines, hiredWorkers, cost));
        }

        return purchases;
    }

}

class Purchase {
    private long machines;
    private long workers;
    private long cost;

    //I could just calculate the cost instead of keeping it in an attribute
    public Purchase(long machines, long workers, long cost) {

        this.machines = machines;
        this.workers = workers;
        this.cost = cost;
    }

    public long getMachines() {
        return machines;
    }

    public void setMachines(long machines) {
        this.machines = machines;
    }

    public long getWorkers() {
        return workers;
    }

    public void setWorkers(long workers) {
        this.workers = workers;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}

public class Solution {
    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\search\\making_candies\\input\\input02.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getSource()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\search\\making_candies\\output.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long m = Long.parseLong(firstMultipleInput[0]);

        long w = Long.parseLong(firstMultipleInput[1]);

        long p = Long.parseLong(firstMultipleInput[2]);

        long n = Long.parseLong(firstMultipleInput[3]);

        long result = Result.minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
