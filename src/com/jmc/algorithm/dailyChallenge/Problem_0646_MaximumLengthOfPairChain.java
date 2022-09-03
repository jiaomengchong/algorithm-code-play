package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-length-of-pair-chain/
 */
public interface Problem_0646_MaximumLengthOfPairChain {
    public static int findLongestChain(int[][] pairs) {
        int ans = 1;
        int N = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int pre = pairs[0][1];
        for (int i = 1; i < N; i++) {
            if (pre < pairs[i][0]) {
                ans++;
                pre = pairs[i][1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] pairs = new int[][]{{1,2}, {2,3}, {3,4}};
        System.out.println(findLongestChain(pairs));
    }
}
