package com.jmc.algorithm.jjb.class26;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 * 返回有多少达标的字符串
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/10 19:06
 */
public class Code03_ZeroLeftOneStringNumber {
    public static int f1(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int p) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }

        int[][] t = base;
        for (; p != 0 ; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, t);
            }
            t = multiMatrix(t, t);
        }
        return res;
    }

    private static int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 18;
        System.out.println(f1(n));
        System.out.println(f2(n));
    }
}
