package com.jmc.algorithm.dailyChallenge;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试链接：https://leetcode.cn/problems/jewels-and-stones/
 *
 * @Author jmc
 * @Description
 * @Date 2022/7/15 16:44
 **/
public class problem_0771_JewelsAndStones {
    public static int numJewelsInStones(String jewels, String stones) {
        int M = jewels.length();
        int N = stones.length();
        int ans = 0;
        Set<Character> sets = new HashSet<>();
        for (int i = 0; i < M; i++) {
            sets.add(jewels.charAt(i));
        }

        for (int i = 0; i < N; i++) {
            if (sets.contains(stones.charAt(i))) {
                ans++;
            }
        }
        return ans;
    }
}
