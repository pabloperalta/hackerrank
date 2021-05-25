package search.swap_nodes;

import java.io.*;
import java.util.*;

public class Solution {

    /*
     * Complete the swapNodes function below.
     */
    static int[][] swapNodes(int[][] indexes, int[] queries) {
        RootNode rootNode = new RootNode();

        for (int[] currentNodeValues : indexes) {
            rootNode.addNode(currentNodeValues[0], currentNodeValues[1]);
        }

        int[][] result = new int[queries.length][rootNode.totalNodesInTree()];

        for (int i = 0; i < queries.length; i++) {
            int query = queries[i];
            rootNode.swap(query);
            int[] traverse = rootNode.traverse();
            result[i] = traverse;
        }

        return result;
    }

    private static class RootNode extends Node {
        private List<Node> queue;
        private Map<Integer, Node> nodeIndex;
        private Map<Integer, List<Node>> nodesByDepth;

        public RootNode() {
            this.nodeIndex = new HashMap<>();
            this.nodesByDepth = new HashMap<>();
            this.nodeIndex.put(1, this);
            this.queue = new ArrayList<>();
            this.queue.add(this);
            this.setValue(1);
            this.setDepth(1);

            this.indexNodeByDepth(this);
        }

        private void indexNodeByDepth(Node node) {
            List<Node> nodeList = this.nodesByDepth.computeIfAbsent(node.getDepth(), k -> new ArrayList<>());

            nodeList.add(node);
        }

        public void addNode(int leftValue, int rightValue) {
            Node node = this.takeFromQueue();

            node.setLeft(new Node(leftValue));
            node.getLeft().setDepth(node.getDepth() + 1);
            indexNodeByDepth(node.getLeft());

            if (leftValue != -1) {
                this.addToQueue(node.getLeft());
                this.nodeIndex.put(leftValue, node.getLeft());
            }

            node.setRight(new Node(rightValue));
            node.getRight().setDepth(node.getDepth() + 1);
            indexNodeByDepth(node.getRight());

            if (rightValue != -1) {
                this.nodeIndex.put(rightValue, node.getRight());
                this.addToQueue(node.getRight());
            }
        }

        private Node takeFromQueue() {
            Node node = this.queue.get(0);
            this.queue.remove(0);
            return node;
        }

        private void addToQueue(Node node) {
            this.queue.add(node);
        }

        private void swap(int index) {
            int multiplier = 1;
            int currentDepth = multiplier * index;
            List<Node> nodes = this.nodesByDepth.get(currentDepth);
            while (nodes != null) {

                for (Node node : nodes) {
                    node.swap();
                }

                multiplier++;
                currentDepth = multiplier * index;
                nodes = this.nodesByDepth.get(currentDepth);
            }

        }

        public int totalNodesInTree() {
            return this.nodeIndex.size();
        }

        public int[] traverse() {
            List<Integer> visitedNodes = new ArrayList<>();

            this.visit(visitedNodes);

            return mapListToArray(visitedNodes);
        }

        private int[] mapListToArray(List<Integer> visitedNodes) {
            int[] traverseArray = new int[visitedNodes.size()];

            for (int i = 0; i < visitedNodes.size(); i++) {
                traverseArray[i] = visitedNodes.get(i);
            }

            return traverseArray;
        }
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;
        private int depth;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public void swap() {
            Node currentLeft = this.getLeft();
            this.setLeft(this.getRight());
            this.setRight(currentLeft);
        }

        public void visit(List<Integer> visitedNodes) {
            if (this.getLeft() != null && this.getLeft().getValue() != -1) {
                this.getLeft().visit(visitedNodes);
            }

            visitedNodes.add(this.getValue());

            if (this.getRight() != null && this.getRight().getValue() != -1) {
                this.getRight().visit(visitedNodes);
            }
        }
    }

    private static final Scanner scanner = new Scanner(getSource());

    private static InputStream getSource() {
        try {
            return new FileInputStream(".\\src\\search\\swap_nodes\\input02.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return System.in;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\src\\search\\swap_nodes\\output.txt"));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }

}

