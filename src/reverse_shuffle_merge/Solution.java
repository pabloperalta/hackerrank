package reverse_shuffle_merge;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\reverse_shuffle_merge\\input102.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\reverse_shuffle_merge\\output.txt"));

        String s = scanner.nextLine();

        String result = new ShuffleMerger().reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static class ShuffleMerger {

        private Map<Character, Integer> maxOcurrences;
        private Map<Character, Integer> charCount;
        private List<Character> guaranteedResult;
        private List<Character> trailingResult;

        // Complete the reverseShuffleMerge function below.
        public String reverseShuffleMerge(String s) {

            maxOcurrences = getMaxOcurrences(s);

            charCount = new HashMap<>();

            guaranteedResult = new ArrayList<>();
            trailingResult = new ArrayList<>();

            for (int i = s.length() - 1; i >= 0; i--) {
                char currentChar = s.charAt(i);
                int remaining = remaining(currentChar);

                System.out.println("Index [" + i + "] Char [" + currentChar + "]");
                System.out.println("Max ocurrences of [" + currentChar + "] is [" + this.maxOcurrences.get(currentChar) + "]");
                System.out.println("So far we used [" + this.charCount.getOrDefault(currentChar, 0) + "]");
                System.out.println("Remaining [" + remaining + "]");

                if (remaining > maxNeeded(currentChar)) {
                    // I have spares
                    if (isLexicographycallyLower(currentChar)) {
                        //I should use it as the new head it anyway but it's not mandatory for it to end up in the final result so its the head of the trailingResult
                        System.out.println("I should use it as the new head it anyway but it's not mandatory for it to end up in the final result so its the head of the trailingResult");
                        trailingResult.clear();
                        trailingResult.add(currentChar);
                    } else {
                        // I'll use it in the result as long as there's room for more of it's kind but it wont be the head and since i have spares it will go in the trailing result
                        System.out.println("I'll use it in the result as long as there's room for more of it's kind but it wont be the head and since i have spares it will go in the trailing result");
                        if (getCurrentCount(currentChar) < maxNeeded(currentChar)) {
                            trailingResult.add(currentChar);
                        } else {
                            // This means it just won't fit so it's discarded
                            System.out.println("This means it just won't fit so it's discarded");
                        }
                    }
                } else {
                    // This means it might be running out of this chars. Need to check wether it's mandatory to use it or not
                    System.out.println("This means it might be running out of this chars. Need to check wether it's mandatory to use it or not");
                    if (remaining <= maxNeeded(currentChar) - getCurrentCount(currentChar)) {
                        // This means that if I don't use this char then i won't be able to form a valid result.
                        System.out.println("This means that if I don't use this char then i won't be able to form a valid result.");
                        if (isLexicographycallyLower(currentChar)) {
                            // I must use it and it must be the head of the trail and then guaranteed
                            System.out.println("I must use it and it must be the head of the trail and then guaranteed");
                            trailingResult.clear();
                            trailingResult.add(currentChar);
                            guaranteedResult.add(currentChar);
                            trailingResult.clear();
                        } else {
                            if (getCurrentCount(currentChar) <= maxNeeded(currentChar)) {
                                // This means it goes as the tail of the trail making everything on the trail mandatory
                                System.out.println("This means it goes as the tail of the trail making everything on the trail mandatory");
                                trailingResult.add(currentChar);
                                guaranteedResult.addAll(trailingResult);
                                trailingResult.clear();
                            } else {
                                // This means it goes as the tail of the trail making everything on the trail mandatory
                                System.out.println("This means it is not necesary to add the char because there are enough but i must make sure that if the are in teh trail they are not removed");
                                guaranteedResult.addAll(trailingResult);
                                trailingResult.clear();
                            }

                        }
                    } else {
                        // This means that given that I already have some instances of this char in the result it is not mandatory for me to add it
                        System.out.println("This means that given that I already have some instances of this char in the result it is not mandatory for me to add it");
                        if (getCurrentCount(currentChar) < maxNeeded(currentChar)) {
                            System.out.println("This means it is mandatory to use this char");
                            if (isLexicographycallyLower(currentChar)) {
                                System.out.println("It should be the head of the trail and then guaranteed");
                                trailingResult.clear();
                                trailingResult.add(currentChar);
                                guaranteedResult.addAll(trailingResult);
                                trailingResult.clear();
                            } else {
                                System.out.println("It should be the tail of the trail making everything on the trail mandatory");
                                trailingResult.add(currentChar);
                                guaranteedResult.addAll(trailingResult);
                                trailingResult.clear();
                            }
                        } else {
                            // I dont need any more of this char
                            System.out.println("I dont need any more of this char but i must make sure that if the are in teh trail they are not removed");
                            guaranteedResult.addAll(trailingResult);
                            trailingResult.clear();
                        }

                    }
                }

                // Count char ocurrences
                charCount.merge(currentChar, 1, (a, b) -> a + b);

                System.out.println("currentString [" + charListToString(guaranteedResult) + charListToString(trailingResult) + "]");
                System.out.println();
            }

            System.out.println("currentString [" + charListToString(guaranteedResult) + charListToString(trailingResult) + "]");
            System.out.println();

            guaranteedResult.addAll(trailingResult);

            return

                    charListToString(guaranteedResult);
        }

        private int maxNeeded(char currentChar) {
            return this.maxOcurrences.get(currentChar) / 2;
        }

        private Integer getCurrentCount(char currentChar) {
            return currentResultCount().getOrDefault(currentChar, 0);
        }

        public int remaining(char currentChar) {
            return this.maxOcurrences.get(currentChar) - (this.charCount.getOrDefault(currentChar, 0));
        }

        public boolean isLexicographycallyLower(char currentChar) {
            return trailingResult.isEmpty() || currentChar < trailingResult.get(0);
        }

        public Map<Character, Integer> currentResultCount() {
            Map<Character, Integer> count = new HashMap<>();

            for (Character c : guaranteedResult) {
                count.merge(c, 1, (a, b) -> a + b);
            }

            for (Character c : trailingResult) {
                count.merge(c, 1, (a, b) -> a + b);
            }

            return count;
        }

        public String charListToString(List<Character> result) {
            StringBuilder stringBuffer = new StringBuilder();

            for (Character c : result) {
                stringBuffer.append(c);
            }

            return stringBuffer.toString();
        }

        public Map<Character, Integer> getMaxOcurrences(String s) {
            Map<Character, Integer> charSet = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                charSet.merge(s.charAt(i), 1, (a, b) -> a + b);
            }

            return charSet;
        }
    }
}
