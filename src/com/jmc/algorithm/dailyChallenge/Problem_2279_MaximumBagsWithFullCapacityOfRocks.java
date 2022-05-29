package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;

/**
 * 测试链接：https://leetcode.cn/problems/maximum-bags-with-full-capacity-of-rocks/
 */
public class Problem_2279_MaximumBagsWithFullCapacityOfRocks {
    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        // 输入：capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
        // 输出：3
        int N = capacity.length;
        int[] rest = new int[N];
        for (int i = 0; i < N; i++) {
            rest[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(rest);
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (rest[i] != 0 && additionalRocks != 0) {
                int reduce = rest[i] <= additionalRocks ? rest[i] : additionalRocks;
                rest[i] -= reduce;
                additionalRocks -= reduce;
            }
            ans += rest[i] == 0 ? 1 : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] capacity = new int[]{2, 3, 4, 5};
        int[] rocks = new int[]{1, 2, 4, 4};
        int additionalRocks = 2;
        System.out.println(maximumBags(capacity, rocks, additionalRocks));
    }
}
