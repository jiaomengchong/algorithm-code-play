package com.jmc.algorithm.dailyChallenge;


import java.util.HashMap;
import java.util.Map;

public class Problem_0873_LengthOfLongestFibonacciSubsenquence {
    public static int lenLongestFibSubseq(int[] arr) {
        // 输入 arr = [1,2,3,4,5,6,7,8]
        // 输出 5
        // [1,2,3,5,8]
        int N = arr.length;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < N; i++) {
            map.put(arr[i], i);
        }

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                dp[i][j] = 2;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int tmp = arr[j] - arr[i];
                if (map.containsKey(tmp)) {
                    Integer index = map.get(tmp);
                    if (index < i) {
                        dp[i][j] = Math.max(dp[i][j], dp[index][i] + 1);
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans > 2 ? ans : 0;
    }
}
