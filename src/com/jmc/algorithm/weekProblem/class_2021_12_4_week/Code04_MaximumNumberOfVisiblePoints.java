package com.jmc.algorithm.weekProblem.class_2021_12_4_week;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试链接 : https://leetcode.com/problems/maximum-number-of-visible-points/
 *
 * @Author jmc
 * @Description
 * @Date 2022/1/4 20:28
 **/
public class Code04_MaximumNumberOfVisiblePoints {
    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int ans = 0;
        if (points == null || points.size() == 0 || location == null || location.size() == 0) {
            return ans;
        }

        int N = points.size();
        double[] arr = new double[N << 1];
        int x = location.get(0);
        int y = location.get(1);
        int x1;
        int y1;
        int zero = 0;
        int count = 0;
        for (List<Integer> point : points) {
            x1 = point.get(0) - x;
            y1 = point.get(1) - y;
            if (x1 == 0 && y1 == 0) {
                zero++;
            } else {
                arr[count] = Math.toDegrees(Math.atan2(y1, x1));
                arr[count + 1] = arr[count] + 360;
                count += 2;
            }
        }

        Arrays.sort(arr, 0, count);
        int R = 0;
        for (int L = 0; L < count; L++) {
            while (R < count && arr[R] - arr[L] <= angle) {
                R++;
            }
            ans = Math.max(ans, R - L);
        }

        return ans + zero;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        Integer[][] points1 = new Integer[][]{{0, 0}, {0, 2}};
        for (Integer[] p : points1) {
            points.add(Arrays.asList(p));
        }
        int angle = 90;
        Integer[] location1 = new Integer[]{1, 1};
        List<Integer> location = Arrays.asList(location1);

        System.out.println(visiblePoints(points, angle, location));
    }
}
