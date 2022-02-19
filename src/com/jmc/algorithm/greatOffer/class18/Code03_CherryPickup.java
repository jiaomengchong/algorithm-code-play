package com.jmc.algorithm.greatOffer.class18;

/**
 * 摘樱桃
 * 测试链接：https://leetcode-cn.com/problems/cherry-pickup/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/28 20:15
 */
public class Code03_CherryPickup {
    public static void main(String[] args) {
        /*Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int ans = cherryPickup(matrix);
        System.out.println(ans);
        sc.close();*/
        int[][] grid = new int[][]{{1, 1, 1, 1, -1, -1, -1, 1, 0, 0}, {1, 0, 0, 0, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0, -1, 1, 1}, {0, 0, 0, 0, 1, -1, 0, 0, 1, -1}, {1, 0, 1, 1, 1, 0, 0, -1, 1, 0}, {1, 1, 0, 1, 0, 0, 1, 0, 1, -1}, {1, -1, 0, 1, 0, 0, 0, 1, -1, 1}, {1, 0, -1, 0, -1, 0, 0, 1, 0, 0}, {0, 0, -1, 0, 1, 0, 1, 0, 0, 1}};
        System.out.println(cherryPickup1(grid));
        System.out.println(cherryPickup(grid));
    }

    public static int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int ans = process(grid, 0, 0, 0, 0);
        return ans < 0 ? 0 : ans;
    }

    private static int process(int[][] grid, int a, int b, int c, int d) {
        int N = grid.length - 1;
        int M = grid[0].length - 1;
        if (a + b == N + M) {
            return grid[a][b] == 1 ? 1 : 0;
        }

        // 最好的后序
        int best = Integer.MIN_VALUE;
        if (a < N) {
            if (c < N) {
                // A下B下
                best = Math.max(best, process(grid, a + 1, b, c + 1, d));
            }
            if (d < M) {
                // A下B右
                best = Math.max(best, process(grid, a + 1, b, c, d + 1));
            }
        }
        if (b < M) {
            if (c < N) {
                // A右B下
                best = Math.max(best, process(grid, a, b + 1, c + 1, d));
            }
            // A右B右
            if (d < M) {
                // A右B下
                best = Math.max(best, process(grid, a, b + 1, c, d + 1));
            }
        }

        if (grid[a][b] == -1 || grid[c][d] == -1 || best == -1) {
            return -1;
        }

        // 当前位置
        int cur;
        if (a == c && b == d) {
            cur = grid[a][b];
        } else {
            cur = grid[a][b] + grid[c][d];
        }
        return cur + best;
    }

    public static int cherryPickup1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N][M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        int ans = processDp(grid, N, M, 0, 0, 0, dp);
        return ans < 0 ? 0 : ans;
    }

    private static int processDp(int[][] grid, int N, int M, int x, int y, int z, int[][][] dp) {
        if (x == N || y == M || z == N || x + y - z == M) {
            return -1;
        }
        if (dp[x][y][z] != Integer.MIN_VALUE) {
            return dp[x][y][z];
        }
        if (x == N - 1 && y == M - 1) {
            dp[x][y][z] = grid[x][y];
            return grid[x][y];
        }
        int next = Integer.MIN_VALUE;
        next = Math.max(next, processDp(grid, N, M, x + 1, y, z + 1, dp));
        next = Math.max(next, processDp(grid, N, M, x + 1, y, z, dp));
        next = Math.max(next, processDp(grid, N, M, x, y + 1, z + 1, dp));
        next = Math.max(next, processDp(grid, N, M, x, y + 1, z, dp));
        if (next == -1 || grid[x][y] == -1 || grid[z][x + y - z] == -1) {
            dp[x][y][z] = -1;
            return -1;
        }

        int cur = 0;
        if (x == z) {
            dp[x][y][z] = grid[x][y];
            cur = grid[x][y];
        } else {
            cur = grid[x][y] + grid[z][x + y - z];
        }
        dp[x][y][z] = cur + next;
        return dp[x][y][z];
    }
}
