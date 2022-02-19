package com.jmc.algorithm.greatOffer.class18;

/**
 * 最短的桥
 * 测试链接:https://leetcode-cn.com/problems/shortest-bridge/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/25 16:22
 */
public class Code02_ShortestBridge {
    public static int shortestBridge(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rLen = grid.length;
        int cLen = grid[0].length;
        int all = rLen * cLen;
        int[] cur = new int[all];
        int[] nexts = new int[all];
        int[][] record = new int[2][all];
        int lands = 0;
        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (grid[i][j] == 1) {
                    int queueSize = inflect(grid, rLen, cLen, i, j, 0, cur, record[lands]);
                    int level = 1;
                    while (queueSize != 0) {
                        level++;
                        queueSize = bfs(level, queueSize, rLen, cLen, cur, nexts, record[lands]);
                        int[] tmp = new int[all];
                        cur = nexts;
                        nexts = tmp;
                    }
                    lands++;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < all; i++) {
            ans = Math.min(ans, record[0][i] + record[1][i]);
        }

        return ans - 3;
    }

    private static int bfs(int level, int queueSize, int rLen, int cLen, int[] cur, int[] nexts, int[] records) {
        int nextIndex = 0;
        int all = rLen * cLen;
        for (int k = 0; k < queueSize; k++) {
            int up = cur[k] - cLen < 0 ? -1 : cur[k] - cLen;
            int down = cur[k] + cLen >= all ? -1 : cur[k] + cLen;
            int left = cur[k] % cLen - 1 < 0 ? -1 : cur[k] - 1;
            int right = cur[k] % cLen + 1 >= cLen ? -1 : cur[k] + 1;
            nextIndex = getNextIndex(level, nexts, records, nextIndex, up);
            nextIndex = getNextIndex(level, nexts, records, nextIndex, down);
            nextIndex = getNextIndex(level, nexts, records, nextIndex, left);
            nextIndex = getNextIndex(level, nexts, records, nextIndex, right);
        }
        return nextIndex;
    }

    private static int getNextIndex(int level, int[] nexts, int[] records, int nextIndex, int position) {
        if (position != -1 && records[position] == 0) {
            records[position] = level;
            nexts[nextIndex++] = position;
        }
        return nextIndex;
    }

    private static int inflect(int[][] grid, int rLen, int cLen, int i, int j, int index, int[] cur, int[] record) {
        if (i < 0 || i >= rLen || j < 0 || j >= cLen || grid[i][j] != 1) {
            return index;
        }
        // 一维数组的下标
        int pos = i * cLen + j;
        grid[i][j] = 2;
        record[pos] = 1;
        cur[index++] = pos;
        index = inflect(grid, rLen, cLen, i - 1, j, index, cur, record);
        index = inflect(grid, rLen, cLen, i + 1, j, index, cur, record);
        index = inflect(grid, rLen, cLen, i, j - 1, index, cur, record);
        index = inflect(grid, rLen, cLen, i, j + 1, index, cur, record);

        return index;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}
        };
        System.out.println(shortestBridge(grid));
    }
}
