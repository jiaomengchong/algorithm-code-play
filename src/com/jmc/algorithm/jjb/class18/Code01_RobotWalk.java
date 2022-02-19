package com.jmc.algorithm.jjb.class18;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/2 19:06
 */
public class Code01_RobotWalk {
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }

        return process1(N, start, aim, K);
    }

    public static int process1(int N, int start, int aim, int K) {
        if (K == 0) {
            return start == aim ? 1 : 0;
        }

        if (start == 1) {
            return process1(N, 2, aim, K - 1);
        }
        if (start == N) {
            return process1(N, N - 1, aim, K - 1);
        }
        return process1(N, start - 1, aim, K - 1) + process1(N, start + 1, aim, K - 1);
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return -1;
        }

        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                dp[N][K] = -1;
            }
        }

        return process2(N, start, aim, K, dp);

    }

    public static int process2(int N, int start, int aim, int K, int[][] dp) {
        if (dp[N][K] != -1) {
            return dp[N][K];
        }

        int ans;
        if (K == 0) {
            ans = start == aim ? 1 : 0;
        } else if (start == 1) {
            ans = process1(N, 2, aim, K - 1);
        } else if (start == N) {
            ans = process1(N, N - 1, aim, K - 1);
        } else {
            ans = process1(N, start - 1, aim, K - 1) + process1(N, start + 1, aim, K - 1);
        }

        dp[N][K] = ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return -1;
        }

        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;

        for (int col = 1; col <= K; col++) {
            for (int row = 1; row <= N; row++) {
                if (row == 1) {
                    dp[row][col] = dp[2][col - 1];
                } else if (row == N) {
                    dp[row][col] = dp[N - 1][col - 1];
                } else {
                    dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
                }
            }
        }


        return dp[start][K];
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long mid;
        System.out.println(ways1(4, 2, 4, 45) + " " + (System.currentTimeMillis() - start));
        mid = System.currentTimeMillis();
        System.out.println(ways2(4, 2, 4, 45) + " " + (System.currentTimeMillis() - mid));
        mid = System.currentTimeMillis();
        System.out.println(ways3(4, 2, 4, 45) + " " + (System.currentTimeMillis() - mid));
    }
}
