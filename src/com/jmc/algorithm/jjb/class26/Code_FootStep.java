package com.jmc.algorithm.jjb.class26;

/**
 * 一个人可以一次往上迈1个台阶，也可以迈2个台阶
 * 返回这个人迈上N级台阶的方法数
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/10 21:02
 */
public class Code_FootStep {
    public static int f1(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        return process(f1(n - 1) + f1(n - 2));
    }

    public static int numWays1(int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <=3 ; i++) {
            dp[i] = i;
        }
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        double[][] base = {{1, 1}, {1, 0}};
        double[][] res = matrixPower(base, n - 2);
        return process((2 * res[0][0] + res[1][0]));
    }

    private static int process(double ans) {
        return (int) (ans % 1000000007);
    }

    private static double[][] matrixPower(double[][] base, int p) {
        double[][] res = new double[base.length][base[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        double[][] t = base;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, t);
            }
            t = multiMatrix(t, t);
        }

        return res;
    }

    private static double[][] multiMatrix(double[][] m1, double[][] m2) {
        double[][] res = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    res[i][j] += (m1[i][k] * m2[k][j]);
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int n = 78;
//        System.out.println(f1(n));
        System.out.println(numWays(n));
        System.out.println(numWays1(n));
    }
}
