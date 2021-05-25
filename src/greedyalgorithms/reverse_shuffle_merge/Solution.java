package greedyalgorithms.reverse_shuffle_merge;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\greedyalgorithms\\reverse_shuffle_merge\\input102.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\greedyalgorithms\\reverse_shuffle_merge\\output.txt"));

        String s = scanner.nextLine();

        String result = new ShuffleMerger().reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static class ShuffleMerger {

        private Map<Character, Integer> maxOcurrences;
        private Map<Character, Integer> charCount = new HashMap<>();
        private List<Character> guaranteedResult = new ArrayList<>();
        private List<Character> trailingResult = new ArrayList<>();

        // Complete the reverseShuffleMerge function below.
        String reverseShuffleMerge(String s) {

            Map<Character, Integer> charSet = new HashMap<>();

            for (int i1 = 0; i1 < s.length(); i1++) {
                charSet.merge(s.charAt(i1), 1, (a1, b1) -> a1 + b1);
            }

            maxOcurrences = charSet;

            for (int i = s.length() - 1; i >= 0; i--) {
                char currentChar = s.charAt(i);
                int remaining = remaining(currentChar);

                System.out.println("Index [" + i + "] Char [" + currentChar + "] There is a total of [" + this.maxOcurrences.get(currentChar) + "] Meaning we need [" + maxNeeded(currentChar) + "] So far our string has [" + getCurrentCount(currentChar, guaranteedResult, trailingResult) + "], Remaining in the input [" + remaining + "] Without the ones in the trail there would be [" + (remaining + getCurrentCount(currentChar, guaranteedResult, trailingResult) - trailingResult.stream().filter(x -> x.equals(Character.valueOf(currentChar))).count()) + "]");

                if (remaining > maxNeeded(currentChar)) {
                    System.out.println("I have spares. Will try to insert but there is no guarantee of this char being in the final result.");
                    tryToInsertInTrail(currentChar, false);
                } else if (remaining <= currentlyNeeded(currentChar)) {
                    System.out.println("Have no spares. If I don't use this char then i won't be able to form a valid result.");
                    tryToInsertInTrail(currentChar, true);
                } else if (remaining + getCurrentCount(currentChar, guaranteedResult, trailingResult) - trailingResult.stream().filter(x -> x.equals(Character.valueOf(currentChar))).count() <= maxNeeded(currentChar)) {
                    System.out.println("Im running out of this char. Must protect the instances of it that are still in the trail");
                    System.out.println("Moving part of the trail to guaranteed: [" + charListToString(trailingResult.subList(0, trailingResult.indexOf(currentChar) + 1)) + "]");
                    guaranteedResult.addAll(trailingResult.subList(0, trailingResult.indexOf(currentChar) + 1));
                    trailingResult = trailingResult.subList(trailingResult.indexOf(currentChar) + 1, trailingResult.size());
                    tryToInsertInTrail(currentChar, false);
                } else if (getCurrentCount(currentChar, guaranteedResult, trailingResult) < maxNeeded(currentChar)) {
                    System.out.println("I could use more of this char but i could drop it too");
                    tryToInsertInTrail(currentChar, false);
                }

                charCount.merge(currentChar, 1, (a, b) -> a + b);

                System.out.println("currentString [" + charListToString(guaranteedResult) + charListToString(trailingResult) + "]");
                System.out.println("guaranteed [" + charListToString(guaranteedResult) + "]");
                System.out.println("Trail so far  [" + charListToString(trailingResult) + "]");
                System.out.println();
            }

            System.out.println("currentString [" + charListToString(guaranteedResult) + charListToString(trailingResult) + "]");
            System.out.println();

            guaranteedResult.addAll(trailingResult);

            return charListToString(guaranteedResult);
        }

        private int currentlyNeeded(char currentChar) {
            return maxNeeded(currentChar) - getCurrentCount(currentChar, guaranteedResult, trailingResult);
        }

        private void tryToInsertInTrail(char currentChar, boolean makeGuaranteed) {
            if (trailingResult.isEmpty()) {
                this.addAtEndOfTrail(currentChar, makeGuaranteed);
            } else {
                this.tryToInsertInTheCurrentTrail(currentChar, makeGuaranteed);
            }
        }

        private void tryToInsertInTheCurrentTrail(char currentChar, boolean makeGuaranteed) {
            boolean inserted = false;

            for (int j = 0; j < trailingResult.size(); j++) {
                if (isLexicographicallyLower(currentChar, j)) {
                    if (getCurrentCount(currentChar, guaranteedResult, trailingResult.subList(0, j)) < maxNeeded(currentChar)) {
                        System.out.println("Found a lexicographically higher char in the trail. Taking its place. Discarding [" + charListToString(trailingResult.subList(j, trailingResult.size())) + "]");
                        setNewTrailFromPos(currentChar, j);
                        inserted = true;

                        if (makeGuaranteed) {
                            makeTrailGuaranteed();
                        }
                    } else {
                        System.out.println("There were too many [" + currentChar + "] already");
                        if (makeGuaranteed) {
                            makeTrailGuaranteed();
                        }
                    }
                    break;
                }
            }

            if (!inserted) {
                if (getCurrentCount(currentChar, guaranteedResult, trailingResult) < maxNeeded(currentChar)) {
                    addAtEndOfTrail(currentChar, makeGuaranteed);
                } else {
                    System.out.println("There were too many [" + currentChar + "] already");
                    if (makeGuaranteed) {
                        makeTrailGuaranteed();
                    }
                }
            }
        }

        private void addAtEndOfTrail(char currentChar, boolean makeGuaranteed) {
            if (getCurrentCount(currentChar, guaranteedResult, trailingResult) < maxNeeded(currentChar)) {
                System.out.println("Adding [" + currentChar + "] at the end of the trail");
                trailingResult.add(currentChar);
            }

            if (makeGuaranteed) {
                makeTrailGuaranteed();
            }
        }

        private void setNewTrailFromPos(char currentChar, int j) {
            trailingResult = trailingResult.subList(0, j);
            trailingResult.add(currentChar);
        }

        private void makeTrailGuaranteed() {
            System.out.print("Adding trail as guaranteed: ");

            for (Character c : trailingResult) {
                System.out.print("[" + c + "]");
            }
            System.out.println();

            guaranteedResult.addAll(trailingResult);
            trailingResult.clear();
        }

        private int maxNeeded(char currentChar) {
            return this.maxOcurrences.get(currentChar) / 2;
        }

        private Integer getCurrentCount(char currentChar, List<Character> guaranteedResult, List<Character> trailingResult) {
            Map<Character, Integer> count = new HashMap<>();

            for (Character c : guaranteedResult) {
                count.merge(c, 1, (a, b) -> a + b);
            }

            for (Character c : trailingResult) {
                count.merge(c, 1, (a, b) -> a + b);
            }

            return count.getOrDefault(currentChar, 0);
        }

        int remaining(char currentChar) {
            return this.maxOcurrences.get(currentChar) - (this.charCount.getOrDefault(currentChar, 0));
        }

        boolean isLexicographicallyLower(char currentChar, int j) {
            return trailingResult.isEmpty() || currentChar < trailingResult.get(j);
        }

        String charListToString(List<Character> result) {
            StringBuilder stringBuffer = new StringBuilder();

            for (Character c : result) {
                stringBuffer.append(c);
            }

            return stringBuffer.toString();
        }

    }
}
