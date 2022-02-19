package com.jmc.algorithm.greatOffer.class22;

/**
 * 接雨水
 * <p>
 * 测试链接：https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/14 20:18
 */
public class Code02_TrappingRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int N = height.length;
        int lMax = height[0];
        int rMax = height[N - 1];
        int L = 1;
        int R = N - 2;
        int ans = 0;
        while (L <= R) {
            if (lMax <= rMax) {
                ans += Math.max(0, lMax - height[L]);
                lMax = Math.max(lMax, height[L++]);
            } else {
                ans += Math.max(0, rMax - height[R]);
                rMax = Math.max(rMax, height[R--]);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
