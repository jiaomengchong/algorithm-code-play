package com.jmc.algorithm.jjb.class15;

import java.util.*;

/**
 * 岛屿数量II
 * <p>
 * https://leetcode.com/problems/number-of-islands-ii/
 *
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 10:52
 */
public class Code03_NumIslandsII {

    public static class UnionFind {
        private int[] parents;
        private int[] help;
        private int[] sizes;
        private int sets;
        private int rowSize;
        private int colSize;

        public UnionFind(int m, int n) {
            rowSize = m;
            colSize = n;
            // 有可能超出整数范围
            int length = rowSize * colSize;
            parents = new int[length];
            sizes = new int[length];
            help = new int[length];
            sets = 0;
        }

        public int index(int row, int col) {
            return row * colSize + col;
        }

        public int find(int cur) {
            int hi = 0;
            if (cur != parents[cur]) {
                help[hi++] = cur;
                cur = parents[cur];
            }

            for (int i = 0; i < hi; i++) {
                parents[help[i]] = cur;
            }

            return cur;
        }

        public void union(int row1, int col1, int row2, int col2) {
            if (row1 < 0 || row2 < 0 || row1 >= rowSize || row2 >= rowSize || col1 < 0 || col2 < 0 || col1 >= colSize || col2 >= colSize) {
                return;
            }

            int index1 = index(row1, col1);
            int index2 = index(row2, col2);
            int f1 = find(index1);
            int f2 = find(index2);
            int size1 = sizes[index1];
            int size2 = sizes[index2];
            if (size1 < 1 || size2 < 1) {
                return;
            }

            if (f1 != f2) {
                if (size1 >= size2) {
                    parents[f2] = f1;
                    sizes[index1] += sizes[index2];
                } else {
                    parents[f1] = f2;
                    sizes[index2] += sizes[index1];
                }
                sets--;
            }
        }

        public void connect(int row, int col) {
            int index = index(row, col);
            parents[index] = index;
            sizes[index] = 1;
            sets++;

            union(row, col, row - 1, col);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            union(row, col, row, col + 1);
        }
    }

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind unionFind = new UnionFind(m, n);
        for (int[] position : positions) {
            unionFind.connect(position[0], position[1]);
            ans.add(unionFind.sets);
        }

        return ans;
    }

    public static class UnionFind2 {
        private HashMap<String, String> parents;
        private HashMap<String, Integer> sizeMap;
        private HashMap<String, String> help;
        private int sets;

        public UnionFind2() {
            parents = new HashMap<>();
            help = new HashMap<>();
            sizeMap = new HashMap<>();
            sets = 0;
        }

        public String find(String cur) {
            Stack<String> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }

            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }

            return cur;
        }

        public void union(String key1, String key2) {
            if (!parents.containsKey(key1) || !parents.containsKey(key2)) {
                return;
            }

            String value1 = find(key1);
            String value2 = find(key2);
            if (!value1.equals(value2)) {
                Integer size1 = sizeMap.get(key1);
                Integer size2 = sizeMap.get(key2);
                String big = size1 >= size2 ? value1 : value2;
                String small = big.equals(value1) ? value2 : value1;

                parents.put(small, big);
                sizeMap.put(big, size1 + size2);
                sizeMap.remove(small);
                sets--;
            }
        }

        public void connect(int row, int col) {
            String key = String.format("%s_%s", row, col);
            if (!parents.containsKey(key)) {
                parents.put(key, key);
                sizeMap.put(key, 1);
                sets++;

                String left = String.format("%s_%s", row - 1, col);
                String right = String.format("%s_%s", row + 1, col);
                String up = String.format("%s_%s", row, col - 1);
                String down = String.format("%s_%s", row, col + 1);
                union(key, left);
                union(key, right);
                union(key, up);
                union(key, down);
            }
        }
    }

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind2 unionFind2 = new UnionFind2();
        for (int[] position : positions) {
            unionFind2.connect(position[0], position[1]);
            ans.add(unionFind2.sets);
        }

        return ans;
    }

    public static void main(String[] args) {
        int m = 13, n = 13;
        int[][] positions = {
                {0, 0}, {0, 1}, {1, 2}, {2, 1}
        };

        List<Integer> ans = numIslands2(m, n, positions);
        System.out.println(ans);

        List<Integer> ans1 = numIslands21(m, n, positions);
        System.out.println(ans1);
    }
}
