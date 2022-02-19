package com.jmc.algorithm.greatOffer.class08;

/**
 * 测试链接：https://leetcode.com/problems/container-with-most-water/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/22 22:22
 */
public class Code02_ContainerWithMostWater {
    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        int L = 0;
        int R = height.length - 1;
        while (L < R) {
            if (height[L] < height[R]) {
                ans = Math.max(ans, height[L] * (R - L));
                L++;
            } else {
                ans = Math.max(ans, height[R] * (R - L));
                R--;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
    }
}
