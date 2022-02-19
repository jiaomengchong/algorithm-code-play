package com.jmc.algorithm.jjb.class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/14 16:23
 */
public class Code01_KillMonster {

    public static double ways1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        double all = Math.pow(M + 1, K);
        double p = process(N, M, K);

        return p / all;
    }

    private static double process(int help, int M, int rest) {
        if (rest == 0) {
            return help <= 0 ? 1 : 0;
        }

        if (help <= 0) {
            return Math.pow(M + 1, rest);
        }

        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(help - i, M, rest - 1);
        }

        return ways;
    }

    public static double dpWays1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        double all = Math.pow(M + 1, K);
        double[][] dp = new double[N + 1][K + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[0][rest] = Math.pow(M + 1, rest);
            for (int help = 1; help <= N; help++) {
                int ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (help - i <= 0) {
                        ways += Math.pow(M + 1, rest - 1);
                    } else {
                        ways += dp[help - i][rest - 1];
                    }
                }
                dp[help][rest] = ways;
            }
        }

        return dp[N][K] / all;
    }

    public static double dpWays2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        double[][] dp = new double[N + 1][K + 1];
        double all = Math.pow(M + 1, K);
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[0][rest] = Math.pow(M + 1, rest);
            for (int help = 1; help <= N; help++) {
                dp[help][rest] = dp[help][rest - 1] + dp[help - 1][rest];
                if (help - 1 - M >= 0) {
                    dp[help][rest] -= dp[help - 1 - M][rest - 1];
                } else {
                    dp[help][rest] -= Math.pow(M + 1, rest - 1);
                }
            }
        }

        return dp[N][K] / all;
    }

    public static void main(String[] args) {
        //0.975830078125
        System.out.println(ways1(8, 7, 5));
        System.out.println(dpWays1(8, 7, 5));
        System.out.println(dpWays2(8, 7, 5));
    }
}
