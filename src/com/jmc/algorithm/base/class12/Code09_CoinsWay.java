package com.jmc.algorithm.base.class12;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/8 10:13
 * <p>
 * 给定数组arr，arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 再给定一个整数 aim，代表要找的钱数
 * 求组成 aim 的方法数
 */
public class Code09_CoinsWay {

    public static int ways1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        int ways = 0;

        for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
            ways += process1(arr, index + 1, aim - (zhang * arr[index]));
        }

        return ways;
    }

    public static int ways2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int[][] dp = new int[arr.length + 1][aim + 1];
        //初始化
        for (int i = 0; i <= arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(arr, 0, aim, dp);
    }

    private static int process2(int[] arr, int index, int aim, int[][] dp) {
        if (dp[index][aim] != -1) {
            return dp[index][aim];
        }

        if (index == arr.length) {
            dp[index][aim] = aim == 0 ? 1 : 0;
            return dp[index][aim];
        }

        int ways = 0;

        for (int zhang = 0; zhang * arr[index] <= aim; zhang++) {
            ways += process2(arr, index + 1, aim - (zhang * arr[index]), dp);
        }

        dp[index][aim] = ways;

        return ways;

    }

    public static int ways3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int col = 0; col <= aim; col++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= col; zhang++) {
                    ways += dp[index + 1][col - (zhang * arr[index])];
                }
                dp[index][col] = ways;
            }
        }


        return dp[0][aim];
    }

    public static int ways4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(ways1(arr, 6));
        System.out.println(ways2(arr, 6));
        System.out.println(ways3(arr, 6));
        System.out.println(ways4(arr, 6));
    }
}
