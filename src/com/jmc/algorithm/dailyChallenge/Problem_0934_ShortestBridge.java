package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 测试链接：https://leetcode.cn/problems/shortest-bridge/
 */
public class Problem_0934_ShortestBridge {
    public static int shortestBridge(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<>();
                    dfs(i, j, grid, queue);
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] pos = queue.poll();
                            int row = pos[0];
                            int col = pos[1];
                            for (int d = 1; d < 5; d++) {
                                int nextRow = row + dir[d - 1];
                                int nextCol = col + dir[d];
                                if (nextRow >= 0 && nextCol >= 0 & nextRow < N && nextCol < M) {
                                    if (grid[nextRow][nextCol] == 0) {
                                        queue.offer(new int[]{nextRow, nextCol});
                                        grid[nextRow][nextCol] = -1;
                                    } else if (grid[nextRow][nextCol] == 1) {
                                        return step;
                                    }
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        return 0;
    }

    private static void dfs(int row, int col, int[][] grid, Queue<int[]> queue) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1) {
            return;
        }
        queue.offer(new int[]{row, col});
        grid[row][col] = -1;
        dfs(row - 1, col, grid, queue);
        dfs(row + 1, col, grid, queue);
        dfs(row, col - 1, grid, queue);
        dfs(row, col + 1, grid, queue);
    }
}
