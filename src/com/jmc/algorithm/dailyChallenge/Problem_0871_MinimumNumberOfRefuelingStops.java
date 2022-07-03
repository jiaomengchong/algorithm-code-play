package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/minimum-number-of-refueling-stops/
 */
public class Problem_0871_MinimumNumberOfRefuelingStops {
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations.length == 0 && startFuel < target) {
            return -1;
        }

        if (startFuel >= target) {
            return 0;
        }

        return process(target, startFuel, stations, 0);
    }

    private static int process(int target, int lastFuel, int[][] stations, int index) {
        if (index == stations.length) {
            return target > lastFuel ? -1 : 0;
        }

        if (target <= 0) {
            return 0;
        }

        if (index == 0) {
            if (lastFuel < stations[index][0]) {
                return -1;
            }
        } else {
            if (lastFuel < stations[index][0] - stations[index - 1][0]) {
                return -1;
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = index; i < stations.length; i++) {
            int next = process(target - stations[i][0], lastFuel - stations[i][0], stations, index + 1);
            if (next != -1) {
                ans = Math.min(ans, next);
            }
            int next1 = process(target - stations[i][0], lastFuel - stations[i][0] + stations[i][1], stations, index + 1);
            if (next1 != -1) {
                ans = Math.min(ans, next1 + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int target = 100;
        int startFuel = 25;
        int[][] stations = new int[][]{{25,25},{50,25},{75,25}};
        System.out.println(minRefuelStops(target, startFuel, stations));
    }
}
