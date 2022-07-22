package com.jmc.algorithm.dailyChallenge;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 测试链接：https://leetcode.cn/problems/shift-2d-grid/
 */
public class Problem_1260_Shift2dGrid {
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                deque.offerLast(grid[i][j]);
            }
        }

        for (int i = 0; i < k; i++) {
            Integer poll = deque.pollLast();
            deque.offerFirst(poll);
        }

        for (int i = 0; i < m; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                rowList.add(deque.pollFirst());
            }
            ans.add(rowList);
        }
        return ans;
    }
}
