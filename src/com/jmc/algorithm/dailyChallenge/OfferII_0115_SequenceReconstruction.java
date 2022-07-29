package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/ur2n8P/
 */
public class OfferII_0115_SequenceReconstruction {
    public static class Node {
        public int value;
        public int in;
        public int out;
        public List<Node> next;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            next = new ArrayList<>();
        }
    }

    public static class Graph {
        public Map<Integer, Node> nodes;

        public Graph() {
            this.nodes = new HashMap<>();
        }
    }

    public static boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        Graph graph = createGraph(sequences);
        return sortedTopology(graph, nums.length);
    }

    private static boolean sortedTopology(Graph graph, int n) {
        Map<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
            if (zeroInQueue.size() > 1) {
                return false;
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.next) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
                if (zeroInQueue.size() > 1) {
                    return false;
                }
            }
        }

        return result.size() == n;
    }

    private static Graph createGraph(int[][] sequences) {
        Graph graph = new Graph();
        for (int i = 0; i < sequences.length; i++) {
            graph.nodes.putIfAbsent(sequences[i][0], new Node(sequences[i][0]));
            for (int j = 1; j < sequences[i].length; j++) {
                int pre = sequences[i][j - 1];
                int node = sequences[i][j];
                graph.nodes.putIfAbsent(node, new Node(node));
                graph.nodes.putIfAbsent(pre, new Node(pre));
                Node preNode = graph.nodes.get(pre);
                Node nextNode = graph.nodes.get(node);
                preNode.next.add(nextNode);
                preNode.out++;
                nextNode.in++;
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        // [1,2,3,4,5]
        // [[1,2,3,4,5],[1,2,3,4],[1,2,3],[1],[4],[5]]
        int[] nums = new int[]{1,2,3,4,5};
        int[][] sequences = new int[][]{{1,2,3,4,5},{1,2,3,4},{1,2,3},{1},{4},{5}};
        System.out.println(sequenceReconstruction(nums, sequences));
    }
}
