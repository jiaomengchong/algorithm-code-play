package com.jmc.algorithm.greatOffer.class13;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.com/problems/bricks-falling-when-hit/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/15 9:41
 */
public class Code04_BricksFallingWhenHit {
    public static int[] hitBricks(int[][] grid, int[][] hits) {
        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 1) {
                grid[hits[i][0]][hits[i][1]] = 2;
            }
        }
        UnionFind unionFind = new UnionFind(grid);
        int[] ans = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            if (grid[hits[i][0]][hits[i][1]] == 2) {
                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
            }
        }
        return ans;
    }

    public static class UnionFind {
        private int N;
        private int M;
        private int cellingAll;
        private int[][] grid;
        private boolean[] cellingSet;
        private int[] parentMap;
        private int[] sizeMap;
        private int[] stack;

        public UnionFind(int[][] matrix) {
            init(matrix);
            connect();
        }

        private void init(int[][] matrix) {
            grid = matrix;
            N = grid.length;
            M = grid[0].length;
            int all = N * M;
            cellingAll = 0;
            cellingSet = new boolean[all];
            parentMap = new int[all];
            sizeMap = new int[all];
            stack = new int[all];
            int index = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1) {
                        index = i * M + j;
                        parentMap[index] = index;
                        sizeMap[index] = 1;
                        if (i == 0) {
                            cellingSet[index] = true;
                            cellingAll++;
                        }
                    }
                }
            }
        }

        private void connect() {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    union(row, col, row - 1, col);
                    union(row, col, row + 1, col);
                    union(row, col, row, col - 1);
                    union(row, col, row, col + 1);
                }
            }
        }

        private void union(int r1, int c1, int r2, int c2) {
            if (valid(r1, c1) && valid(r2, c2)) {
                int father1 = findFather(r1, c1);
                int father2 = findFather(r2, c2);
                if (father1 != father2) {
                    int size1 = sizeMap[father1];
                    int size2 = sizeMap[father2];
                    boolean status1 = cellingSet[father1];
                    boolean status2 = cellingSet[father2];
                    if (size1 <= size2) {
                        parentMap[father1] = father2;
                        sizeMap[father2] = size1 + size2;
                        if (status1 ^ status2) {
                            cellingSet[father2] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    } else {
                        parentMap[father2] = father1;
                        sizeMap[father1] = size1 + size2;
                        if (status1 ^ status2) {
                            cellingSet[father1] = true;
                            cellingAll += status1 ? size2 : size1;
                        }
                    }
                }
            }
        }

        private int findFather(int row, int col) {
            int index = row * M + col;
            int stackSize = 0;
            while (index != parentMap[index]) {
                stack[stackSize++] = index;
                index = parentMap[index];
            }
            while (stackSize != 0) {
                parentMap[stack[--stackSize]] = index;
            }
            return index;
        }

        private boolean valid(int row, int col) {
            return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
        }

        public int finger(int row, int col) {
            grid[row][col] = 1;
            int cur = row * M + col;
            if (row == 0) {
                cellingSet[cur] = true;
                cellingAll++;
            }
            parentMap[cur] = cur;
            sizeMap[cur] = 1;
            int pre = cellingAll;
            union(row, col, row - 1, col);
            union(row, col, row + 1, col);
            union(row, col, row, col - 1);
            union(row, col, row, col + 1);
            int now = cellingAll;
            if (row == 0) {
                return now - pre;
            } else {
                return now == pre ? 0 : now - pre - 1;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}};
        int[][] hits = new int[][]{{1, 0}, {0, 1}};
        System.out.println(Arrays.toString(hitBricks(grid, hits)));
    }
}
