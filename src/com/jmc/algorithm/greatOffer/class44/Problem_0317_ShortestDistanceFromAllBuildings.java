package com.jmc.algorithm.greatOffer.class44;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 离建筑距离最近的距离
 * 你是个房地产开发商，想要选择一片空地 建一栋大楼。
 * 你想把这栋大楼够造在一个距离周边设施都比较方便的地方，
 * 通过调研，你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。
 * 请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。
 * 注意：
 * 你只能通过向上、下、左、右四个方向上移动。
 * 给你一个由 0、1 和 2 组成的二维网格，其中：
 * 0 代表你可以自由通过和选择建造的空地
 * 1 代表你无非通行的建筑物
 * 2 代表你无非通行的障碍物
 * <p>
 * 示例：
 * 输入: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * <p>
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * <p>
 * 输出: 7
 * <p>
 * 解析:
 * 给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
 * 由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
 * 注意：
 * 你会保证有至少一栋建筑物，如果无法按照上述规则返回建房地点，则请你返回 -1。
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/10 20:53
 **/
public class Problem_0317_ShortestDistanceFromAllBuildings {
    public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int step = Integer.MAX_VALUE;
        int N = grid.length;
        int M = grid[0].length;
        int[][] dist = new int[N][M];
        int pass = 0;
        int[] trans = new int[]{0, 1, 0, -1, 0};
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    step = bfs(grid, dist, pass--, i, j, trans);
                    if (step == Integer.MAX_VALUE) {
                        return -1;
                    }
                }
            }
        }

        return step == Integer.MAX_VALUE ? -1 : step;
    }

    private static int bfs(int[][] grid, int[][] dist, int pass, int row, int col, int[] trans) {
        int ans = Integer.MAX_VALUE;
        LinkedList<int[]> queue = new LinkedList<>();
        // 从尾部加
        queue.offer(new int[]{row, col});
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                // 从头部出
                int[] node = queue.poll();
                // 上下左右4个方向
                for (int j = 1; j < trans.length; j++) {
                    int nextRow = node[0] + trans[j - 1];
                    int nextCol = node[1] + trans[j];
                    if (nextRow >= 0 && nextRow < grid.length && nextCol >= 0 && nextCol < grid[0].length && grid[nextRow][nextCol] == pass) {
                        queue.offer(new int[]{nextRow, nextCol});
                        dist[nextRow][nextCol] += level;
                        ans = Math.min(ans, dist[nextRow][nextCol]);
                        grid[nextRow][nextCol]--;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0}
        };
        System.out.println(shortestDistance(grid));
        Calendar ca = Calendar.getInstance();//创建一个日期实例

        ca.setTime(new Date());//实例化一个日期

        System.out.println(ca.get(Calendar.DAY_OF_YEAR));//获取是第多少天
    }
}
