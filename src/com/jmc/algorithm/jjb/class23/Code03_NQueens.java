package com.jmc.algorithm.jjb.class23;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/20 16:14
 */
public class Code03_NQueens {
    public static int ways1(int n) {
        if (n == 1) {
            return 1;
        }

        int[] record = new int[n];
        for (int i = 0; i < n; i++) {
            record[i] = -1;
        }
        return process1(n, 0, record);
    }

    private static int process1(int n, int index, int[] record) {
        if (n == index) {
            return 1;
        } else {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (isValid(index, i, record)) {
                    record[index] = i;
                    ans += process1(n, index + 1, record);
                }
            }
            return ans;
        }
    }

    private static boolean isValid(int row, int col, int[] record) {
        for (int i = 0; i < row; i++) {
            if ((col == record[i] || Math.abs(row - i) == Math.abs(record[i] - col))) {
                return false;
            }
        }
        return true;
    }

    public static int ways2(int n) {
        if (n == 1) {
            return 1;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;

        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int colLimit, int leftDiamLimit, int rightDiamLimit) {
        if (limit == colLimit) {
            return 1;
        }
        int pos = limit & (~(colLimit | leftDiamLimit | rightDiamLimit));
        int ans = 0;
        while (pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos -= mostRightOne;
            ans += process2(limit, colLimit | mostRightOne, (leftDiamLimit | mostRightOne) << 1, (rightDiamLimit | mostRightOne) >> 1);
        }
        return ans;
    }

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 15;
        long start = System.currentTimeMillis();
        System.out.println(ways2(n));
        long end = System.currentTimeMillis();
        System.out.println(String.format("%s花费时间:%s ms", "ways2", end - start));
        start = System.currentTimeMillis();
        System.out.println(ways1(n));
        end = System.currentTimeMillis();
        System.out.println(String.format("%s花费时间:%s ms", "_ways1", end - start));
    }
}
