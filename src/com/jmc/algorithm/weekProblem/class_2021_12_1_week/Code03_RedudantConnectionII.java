package com.jmc.algorithm.weekProblem.class_2021_12_1_week;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 测试链接 : https://leetcode.com/problems/redundant-connection-ii/
 *
 * @Author jmc
 * @Description
 * @Date 2021/12/8 19:09
 **/
public class Code03_RedudantConnectionII {
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return new int[]{};
        }

        int n = edges.length;
        UnionFind unionFind = new UnionFind(n);
        // pre[i] = 1 表示第一次来到1
        // pre[i] = 6 表示不是第一次来到i，这次从6来到的i
        int[] pre = new int[n + 1];
        int[] first = null;
        int[] second = null;
        int[] circle = null;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (pre[to] != 0) {
                // 不是第一次来到to
                first = new int[]{pre[to], to};
                second = edge;
            } else {
                // 第一次来到to
                pre[to] = from;
                if (unionFind.isSame(from, to)) {
                    circle = edge;
                } else {
                    unionFind.union(from, to);
                }
            }
        }
        return first != null ? (circle != null ? first : second) : circle;
    }

    public static class UnionFind {
        private Map<Integer, Node<Integer>> nodesMap;
        private Map<Node<Integer>, Node<Integer>> parentMap;
        private Map<Node<Integer>, Integer> sizeMap;

        public UnionFind(int n) {
            nodesMap = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                Node node = new Node(i);
                nodesMap.put(i, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSame(int i, int j) {
            return findFather(nodesMap.get(i)) == findFather(nodesMap.get(j));
        }

        private Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            while (parentMap.get(cur) != cur) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }

            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }

            return cur;
        }

        public void union(int i, int j) {
            Node fi = findFather(nodesMap.get(i));
            Node fj = findFather(nodesMap.get(j));
            if (fi != fj) {
                Integer size1 = sizeMap.get(fi);
                Integer size2 = sizeMap.get(fj);
                Node big = size1 < size2 ? fj : fi;
                Node small = big == fi ? fj : fi;
                parentMap.put(small, big);
                sizeMap.put(big, size1 + size2);
                sizeMap.remove(small);
            }
        }
    }

    public static class Node<V> {
        private V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static int[] findRedundantDirectedConnection1(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return new int[]{};
        }

        int n = edges.length;
        UnionFind1 unionFind = new UnionFind1(n);
        // pre[i] = 0 表示之前没有点来到i
        // pre[i] = 6 表示之前有点来到i，是从6来的
        int[] pre = new int[n + 1];
        int[] first = null;
        int[] second = null;
        int[] circle = null;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (pre[to] != 0) {
                first = new int[]{pre[to], to};
                second = edge;
            } else {
                pre[to] = from;
                if (unionFind.isSameSet(from, to)) {
                    circle = edge;
                } else {
                    unionFind.union(from, to);
                }
            }
        }

        return first != null ? (circle != null ? first : second) : circle;
    }

    // 并查集数组实现
    public static class UnionFind1 {
        private int[] help;
        private int[] parents;
        private int[] sizes;

        public UnionFind1(int n) {
            help = new int[n + 1];
            parents = new int[n + 1];
            sizes = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                help[i] = i;
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        private boolean isSameSet(int i, int j) {
            return findFather(i) == findFather(j);
        }

        private int findFather(int i) {
            int cur = i;
            int size = 0;
            while (parents[cur] != cur) {
                help[size++] = parents[cur];
                cur = parents[cur];
            }

            while (size > 0) {
                parents[help[--size]] = cur;
            }
            return cur;
        }

        public void union(int i, int j) {
            int f1 = findFather(i);
            int f2 = findFather(j);
            if (f1 != f2) {
                int big = sizes[f1] < sizes[f2] ? f2 : f1;
                int small = big == f1 ? f2 : f1;
                parents[small] = big;
                sizes[big] = sizes[f1] + sizes[f2];
            }
        }

        public static void main(String[] args) {
            int[][] edges = new int[][]{{1,2},{2,3},{3,4},{4,1},{1,5}};
            System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
            System.out.println(Arrays.toString(findRedundantDirectedConnection1(edges)));
        }
    }
}