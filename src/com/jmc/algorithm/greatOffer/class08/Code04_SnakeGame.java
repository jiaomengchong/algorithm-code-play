package com.jmc.algorithm.greatOffer.class08;

/**
 * 给定一个矩阵matrix，值有正、负、0
 * 蛇可以空降到最左列的任何一个位置，初始增长值是0
 * 蛇每一步可以选择右上、右、右下三个方向的任何一个前进
 * 沿途的数字累加起来，作为增长值；但是蛇一旦增长值为负数，就会死去
 * 蛇有一种能力，可以使用一次：把某个格子里的数变成相反数
 * 蛇可以走到任何格子的时候停止
 * 返回蛇能获得的最大增长值
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/23 7:53
 */
public class Code04_SnakeGame {
    public static class Data {
        private int yes;
        private int no;

        public Data(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int walkWays1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Data data = processWays1(matrix, i, j);
                ans = Math.max(ans, Math.max(data.no, data.yes));
            }
        }
        return ans;
    }

    private static Data processWays1(int[][] matrix, int i, int j) {
        // 第一列
        if (j == 0) {
            int yes = Math.max(-1, -matrix[i][0]);
            int no = Math.max(-1, matrix[i][0]);
            return new Data(yes, no);
        }

        // 不是第一列
        int preNo = -1;
        int preYes = -1;
        Data data = processWays1(matrix, i, j - 1);
        preNo = Math.max(preNo, data.no);
        preYes = Math.max(preYes, data.yes);
        // i > 0，肯定有左上
        if (i > 0) {
            data = processWays1(matrix, i - 1, j - 1);
            preNo = Math.max(preNo, data.no);
            preYes = Math.max(preYes, data.yes);
        }
        // i < length - 1， 肯定有左下
        if (i < matrix.length - 1) {
            data = processWays1(matrix, i + 1, j - 1);
            preNo = Math.max(preNo, data.no);
            preYes = Math.max(preYes, data.yes);
        }
        int no = preNo == -1 ? -1 : (Math.max(-1, preNo + matrix[i][j]));
        // 之前没用能力，这次用
        int p1 = preNo == -1 ? -1 : (Math.max(-1, preNo - matrix[i][j]));
        // 之前用能力，这次没得用
        int p2 = preYes == - 1 ? -1 : (Math.max(-1, preYes + matrix[i][j]));
        int yes = Math.max(-1, (Math.max(p1, p2)));

        return new Data(yes, no);
    }

    public static int walkWays2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int N = matrix.length;
        int M = matrix[0].length;
        int[][][] dp = new int[N][M][2];
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            dp[i][0][0] = Math.max(-1, matrix[i][0]);
            dp[i][0][1] = Math.max(-1, -matrix[i][0]);
            ans = Math.max(ans, Math.max(dp[i][0][0], dp[i][0][1]));
        }

        for (int j = 1; j < M; j++) {
            for (int i = 0; i< N; i++) {
                int preNo = Math.max(-1, dp[i][j - 1][0]);
                int preYes = Math.max(-1, dp[i][j - 1][1]);
                if (i > 0) {
                    preNo = Math.max(preNo, dp[i - 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i - 1][j - 1][1]);
                }
                if (i < N - 1) {
                    preNo = Math.max(preNo, dp[i + 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i + 1][j - 1][1]);
                }
                int no = preNo == -1 ? -1 : Math.max(-1, preNo + matrix[i][j]);
                int p1 = preNo == -1 ? -1 : Math.max(-1, preNo - matrix[i][j]);
                int p2 = preYes == -1 ? -1 : Math.max(-1, preYes + matrix[i][j]);
                int yes = Math.max(-1, Math.max(p1, p2));
                dp[i][j][0] = no;
                dp[i][j][1] = yes;
                ans = Math.max(ans, Math.max(no, yes));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 1, 1, 1},{1, 100, -200, 1},{1, 1, 1, 1}};
        int[][] matrix = new int[][]{{9, 2, -9, -4, -2},{7, 3, 1, -8, -9},{-8, -1, -8, 0, -1}};
        System.out.println(walkWays1(matrix));
        System.out.println(walkWays2(matrix));
    }
}
