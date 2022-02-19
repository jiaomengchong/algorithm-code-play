package com.jmc.algorithm.jjb.class22;

/**
 * 给定一个正数，裂开的方法有一种，(1)给定一个正数2，裂开的方法有两种，(1和1)、(2)
 * 给定一个正数3，裂开的方法有3种，(1、1、1)、(1、2)、(3)
 * 给定一个正数4，裂开的方法有5种，(1、1、1、1)、(1、1、2)、(2、2)、(1、3)、(4)
 * 给定一个正数n，求裂开的方法数，动态规划优化状态依赖的技巧
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/14 16:57
 */
public class Code03_SpitNum {

    public static int ways1(int n) {
        if (n < 0) {
            return 0;
        }

        return process1(1, n);
    }

    private static int process1(int pre, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }

        int ways = 0;
        for (int index = pre; index <= rest; index++) {
            ways += process1(index, rest - index);
        }

        return ways;
    }

    public static int dpWays1(int n) {
        if (n < 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int pre = n; pre >= 0; pre--) {
                int ways = 0;
                for (int index = pre; index <= rest; index++) {
                    ways += dp[index][rest - index];
                }
                dp[pre][rest] = ways;
            }
        }

        return dp[1][n];
    }

    public static int dpWays2(int n) {
        if (n < 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][n +1];
        for (int pre = 0; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int rest = 1; rest <= n; rest++) {
            for (int pre = n - 1; pre >= 0; pre--) {
                if (pre < rest) {
                    dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
                }
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(ways1(49));
        System.out.println(dpWays1(49));
        System.out.println(dpWays2(49));
    }
}
