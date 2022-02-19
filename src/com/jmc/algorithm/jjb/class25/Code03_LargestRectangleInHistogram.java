package com.jmc.algorithm.jjb.class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/1 15:44
 */
public class Code03_LargestRectangleInHistogram {
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        Stack<List<Integer>> stack = new Stack<>();
        for (int index = 0; index < heights.length; index++) {
            while (!stack.isEmpty() && heights[stack.peek().get(0)] > heights[index]) {
                List<Integer> popList = stack.pop();
                int subIndex = stack.isEmpty() ? 0 : stack.peek().get(stack.peek().size() - 1) + 1;
                ans = Math.max(ans, heights[popList.get(0)] * (index - subIndex));
            }

            if (!stack.isEmpty() && heights[stack.peek().get(0)] == heights[index]) {
                stack.peek().add(index);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            Integer subIndex = stack.isEmpty() ? 0 : stack.peek().get(stack.peek().size() - 1) + 1;
            ans = Math.max(ans, heights[popList.get(0)] * (heights.length - subIndex));
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] heights = {3, 6, 5, 7, 4, 8, 1, 0};
        System.out.println(largestRectangleArea(heights));
    }
}
