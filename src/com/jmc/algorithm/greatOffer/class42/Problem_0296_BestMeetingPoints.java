package com.jmc.algorithm.greatOffer.class42;

/**
 * 最佳碰头地点
 * <p>
 * 有一队人(两个或以上)想要在一个一个地方碰面，他们希望最小化他们的总行走距离。
 * 给你一个2D网格，其中格子的值要么是1要么是0.
 * 1表示某个人家所处的位置。这里我们将用曼哈顿距离来计算，其中，distance(p1,p2)=
 * |p2.x-p1.x| + |p2.y-p1.y|。
 * <p>
 * 示例
 *
 * @Author jmc
 * @Description
 * @Date 2021/10/31 8:53
 **/
public class Problem_0296_BestMeetingPoints {
    public static int minDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;

        // 统计出每一行1的个数
        int[] rowTotal = new int[N];
        int[] colTotal = new int[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    rowTotal[i]++;
                    colTotal[j]++;
                }
            }
        }

        // 行找最佳碰头点
        int ans = 0;
        ans = getAns(N, rowTotal, ans);

        // 列找最佳碰头点
        ans = getAns(M, colTotal, ans);

        return ans;
    }

    private static int getAns(int m, int[] total, int ans) {
        int start = 0;
        int startRest = 0;
        int end = m - 1;
        int endRest = 0;
        while (start < end) {
            if (total[start] + startRest >= total[end] + endRest) {
                endRest += total[end--];
                ans += endRest;
            } else {
                startRest += total[start++];
                ans += startRest;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0}
        };
        System.out.println(minDistance(grid));
    }
}