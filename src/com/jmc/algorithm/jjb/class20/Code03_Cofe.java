package com.jmc.algorithm.jjb.class20;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/7 15:30
 */
public class Code03_Cofe {
    public static int minTime(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks.length * b;
        }

        return process(drinks, a, b, 0, 0);
    }

    private static int process(int[] drinks, int a, int b, int index, int washTime) {
        if (index == drinks.length) {
            return 0;
        }

        int selfWashTime = Math.max(drinks[index], washTime) + a;
        int restWashTime = process(drinks, a, b, index + 1, selfWashTime);
        int p1 = Math.max(selfWashTime, restWashTime);

        int selfTime = drinks[index] + b;
        int restTime = process(drinks, a, b, index + 1, washTime);
        int p2 = Math.max(selfTime, restTime);

        return Math.min(p1, p2);
    }

    public static int dp(int[] drinks, int a, int b) {
        if (a >= b) {
            return drinks.length * b;
        }

        int N = drinks.length;
        int washTime = 0;
        for (int i = 0; i < N; i++) {
            washTime += Math.max(drinks[i], washTime) + a;
        }
        int[][] dp = new int[N][washTime];
        for (int i = 0; i < washTime; i++) {
            dp[N - 1][i] = Math.min(Math.max(drinks[N - 1], i) + a, drinks[N - 1] + b);
        }
        for (int index = N - 2; index >= 0; index--) {
            for (int i = 0; i < washTime; i++) {
                int p1 = Integer.MAX_VALUE;
                int selfWashTime = Math.max(drinks[index], washTime) + a;
                if (selfWashTime < washTime) {
                    int restWashTime = dp[index + 1][selfWashTime];
                    p1 = Math.max(selfWashTime, restWashTime);
                }
                int selfTime = drinks[index] + b;
                int restTime = dp[index + 1][i];
                int p2 = Math.max(selfTime, restTime);

                dp[index][i] = Math.min(p1, p2);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] drinks = {1, 1, 5, 5, 7, 10, 12, 12, 12, 12, 12, 12, 15};
        int a1 = 3;
        int b1 = 10;
        System.out.println(minTime(drinks, a1, b1));
        System.out.println(dp(drinks, a1, b1));
    }
}
