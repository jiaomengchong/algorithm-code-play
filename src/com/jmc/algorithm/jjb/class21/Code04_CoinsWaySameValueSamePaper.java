package com.jmc.algorithm.jjb.class21;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/15 20:57
 */
public class Code04_CoinsWaySameValueSamePaper {
    static class Info {
        int[] arrValue;
        int[] arrCount;

        public Info(int[] arr) {
            int N = arr.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                if (map.containsKey(arr[i])) {
                    map.put(arr[i], map.get(arr[i]) + 1);
                } else {
                    map.put(arr[i], 1);
                }
            }

            int size = map.size();
            arrValue = new int[size];
            arrCount = new int[size];
            int index = 0;
            for (Integer key : map.keySet()) {
                arrValue[index] = key;
                arrCount[index] = map.get(key);
                index++;
            }
        }
    }

    public static int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = new Info(arr);
        int ans = process1(info.arrValue, info.arrCount, 0, aim);

        return ans;
    }

    private static int process1(int[] arrValue, int[] arrCount, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arrValue.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0; zhang <= arrCount[index]; zhang++) {
            ways += process1(arrValue, arrCount, index + 1, rest - zhang * arrValue[index]);
        }

        return ways;
    }

    public static int dpWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = new Info(arr);
        int[] arrValue = info.arrValue;
        int[] arrCount = info.arrCount;
        int N = arrValue.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang <= arrCount[index]; zhang++) {
                    ways += (rest - zhang * arrValue[index]) >= 0 ? dp[index + 1][rest - zhang * arrValue[index]] : 0;
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dpWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Info info = new Info(arr);
        int[] arrValue = info.arrValue;
        int[] arrCount = info.arrCount;
        int N = arrValue.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arrValue[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arrValue[index]];
                    if (rest - (arrCount[index] + 1) * arrValue[index] >= 0) {
                        dp[index][rest] -= dp[index + 1][rest - (arrCount[index] + 1) * arrValue[index]];
                    }
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 1, 2, 1, 2, 3, 4, 4, 3, 5, 6, 7};
        int aim = 23;
        System.out.println(coinsWay1(arr, aim));
        System.out.println(dpWays1(arr, aim));
        System.out.println(dpWays2(arr, aim));
    }
}
