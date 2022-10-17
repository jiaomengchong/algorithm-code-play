package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/build-an-array-with-stack-operations/
 */
public class Problems_1441_BuildAnArrayWithStackOperations {
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int N = target.length, cycle = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            if (target[i] == pre + 1) {
                ans.add("Push");
            } else {
                cycle = target[i] - pre;
                while (cycle > 1) {
                    ans.add("Push");
                    ans.add("Pop");
                    cycle--;
                }
                ans.add("Push");
            }
            pre = target[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        // [2,3,4]
        // 4
        int[] target = new int[]{2, 3, 4};
        int n = 4;
        System.out.println(buildArray(target, n));
    }
}
