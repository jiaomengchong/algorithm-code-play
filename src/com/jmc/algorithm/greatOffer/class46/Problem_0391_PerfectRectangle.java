package com.jmc.algorithm.greatOffer.class46;

import java.util.HashMap;
import java.util.Map;

/**
 * 完美矩形
 *
 * @Author jmc
 * @Description
 * @Date 2021/11/25 22:55
 **/
public class Problem_0391_PerfectRectangle {
    public static boolean isRectangleCover(int[][] nums) {
        if (nums == null || nums.length == 0 || nums[0].length == 0) {
            return false;
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int L = Integer.MAX_VALUE;
        int D = Integer.MAX_VALUE;
        int U = Integer.MIN_VALUE;
        int R = Integer.MIN_VALUE;
        int area = 0;
        for (int[] arr : nums) {
            add(map, arr[0], arr[1]);
            add(map, arr[0], arr[3]);
            add(map, arr[2], arr[1]);
            add(map, arr[2], arr[3]);
            area += (arr[2] - arr[0]) * (arr[3] - arr[1]);
            L = Math.min(L, arr[0]);
            D = Math.min(D, arr[1]);
            U = Math.max(U, arr[3]);
            R = Math.max(R, arr[2]);
        }

        return check(map, L, D, U, R) && area == (R - L) * (U - D);
    }

    private static boolean check(Map<Integer, Map<Integer, Integer>> map, int l, int d, int u, int r) {
        if (map.get(l).getOrDefault(d, 0) != 1 ||
                map.get(l).getOrDefault(u, 0) != 1 ||
                map.get(r).getOrDefault(d, 0) != 1 ||
                map.get(r).getOrDefault(u, 0) != 1
        ) {
            return false;
        }
        map.get(l).remove(d);
        map.get(l).remove(u);
        map.get(r).remove(d);
        map.get(r).remove(u);

        for (int row : map.keySet()) {
            for (int col : map.get(row).keySet()) {
                if ((map.get(row).getOrDefault(col, 0 ) & 1) != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void add(Map<Integer, Map<Integer, Integer>> map, int row, int col) {
        if (!map.containsKey(row)) {
            map.put(row, new HashMap<>());
        }
        map.get(row).put(col, map.get(row).getOrDefault(col, 0) + 1);
    }

    public static void main(String[] args) {
        int[][] rectangles = {{1,1,4,4},{1,3,4,5},{1,6,4,7}};
        System.out.println(isRectangleCover(rectangles));
    }
}
