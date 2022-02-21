package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

import java.util.*;

/**
 * 测试链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/
 * @Author jmc
 * @Description
 * @Date 2022/2/20 11:53
 **/
public class Code01_CheapestFlightsWithInKStops {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (flights == null || flights.length == 0 || flights[0].length == 0) {
            return -1;
        }

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(i, new ArrayList<>());
        }
        for (int[] line : flights) {
            graph.get(line[0]).add(new int[]{line[1], line[2]});
        }

        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cost, Integer.MAX_VALUE);
        }
        Map<Integer, Integer> curMap = new HashMap<>();
        curMap.put(src, 0);
        for (int i = 0; i <= k; i++) {
            Map<Integer, Integer> nextMap = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : curMap.entrySet()) {
                Integer from = entry.getKey();
                Integer preCost = entry.getValue();
                for (int[] line : graph.get(from)) {
                    int to = line[0];
                    int curCost = line[1];
                    cost[to] = Math.min(cost[to], preCost + curCost);
                    nextMap.put(to, Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE), preCost + curCost));
                }
            }
            curMap = nextMap;
        }

        return cost[dst] != Integer.MAX_VALUE ? cost[dst] : -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] flights = new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };
        int src = 0;
        int dst = 2;
        int k = 0;

        System.out.println("test start...");
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
        System.out.println("test end...");
    }
}
