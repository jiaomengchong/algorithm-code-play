package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/find-players-with-zero-or-one-losses/
 */
public class Problem_2225_FindPlayersWithZeroOrOneLosses {
    public static List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = matches.length;
        Map<Integer, Integer> winMap = new HashMap<>();
        Map<Integer, Integer> lossMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            winMap.put(matches[i][0], winMap.getOrDefault(matches[i][0], 0) + 1);
            lossMap.put(matches[i][1], lossMap.getOrDefault(matches[i][1], 0) + 1);
        }

        List<Integer> winList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : winMap.entrySet()) {
            if (!lossMap.containsKey(entry.getKey())) {
                winList.add(entry.getKey());
            }
        }
        List<Integer> lossList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : lossMap.entrySet()) {
            if (entry.getValue() == 1) {
                lossList.add(entry.getKey());
            }
        }
        Collections.sort(winList);
        Collections.sort(lossList);
        ans.add(winList);
        ans.add(lossList);
        return ans;
    }

    public static void main(String[] args) {
        int[][] matches = new int[][]{{2,3},{1,3},{5,4},{6,4}};
        System.out.println(findWinners(matches));
    }
}
