package com.jmc.algorithm.jjb.class24;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：
 * 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 *
 * @author jmc
 * @version 1.0
 * @date 2021/1/24 20:58
 */
public class Code04_MinCoinsOnePaper {
    public static int minWays1(int[] arr, int aim) {
        if (arr == null || aim <= 0) {
            return 0;
        }

        Info1 info = new Info1(arr);
        int[] values = info.values;
        int[] counts = info.counts;
        return process1(values, counts, 0, aim);
    }

    private static int process1(int[] values, int[] counts, int index, int rest) {
        if (index == values.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang <= counts[index]; zhang++) {
            int p1 = process1(values, counts, index + 1, rest);
            int p2 = Integer.MAX_VALUE;
            int next = process1(values, counts, index + 1, rest - zhang * values[index]);
            if (next != Integer.MAX_VALUE) {
                p2 = next + zhang;
            }
            ans = Math.min(ans, Math.min(p1, p2));
        }
        return ans;
    }

    public static int dpWays1(int[] arr, int aim) {
        if (arr == null || aim <= 0) {
            return 0;
        }
        Info1 info = new Info1(arr);
        int[] values = info.values;
        int[] counts = info.counts;
        int N = values.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int index = 0; index <= N; index++) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[index][rest] = Integer.MAX_VALUE;
            }
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang <= counts[index] && zhang * values[index] <= rest; zhang++) {
                    int p1 = dp[index + 1][rest];
                    int p2 = Integer.MAX_VALUE;
                    int next = dp[index + 1][rest - zhang * values[index]];
                    if (next != Integer.MAX_VALUE) {
                        p2 = next + zhang;
                    }
                    ans = Math.min(ans, Math.min(p1, p2));
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    public static class Info1 {
        public int[] values;
        public int[] counts;

        public Info1(int[] arr) {
            int N = arr.length;
            Map<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < N; i++) {
                if (!hashMap.containsKey(arr[i])) {
                    hashMap.put(arr[i], 1);
                } else {
                    hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
                }
            }
            int size = hashMap.size();
            values = new int[size];
            counts = new int[size];
            int index = 0;
            for (Integer key : hashMap.keySet()) {
                values[index] = key;
                counts[index] = hashMap.get(key);
                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 12, 1, 1, 12, 1, 12, 3, 4, 4, 3, 5, 6, 17};
        int aim1 = 69;
        System.out.println(minWays1(arr1, aim1));
        System.out.println(dpWays1(arr1, aim1));

    }
}
