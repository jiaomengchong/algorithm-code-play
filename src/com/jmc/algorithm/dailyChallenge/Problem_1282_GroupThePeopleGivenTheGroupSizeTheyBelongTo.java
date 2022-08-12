package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
 */
public class Problem_1282_GroupThePeopleGivenTheGroupSizeTheyBelongTo {
    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        int N = groupSizes.length;
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(groupSizes[i])) {
                List<Integer> index = new ArrayList<>();
                index.add(i);
                map.put(groupSizes[i], index);
            } else {
                List<Integer> index = map.get(groupSizes[i]);
                index.add(i);
                map.put(groupSizes[i], index);
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            int size = entry.getValue().size();
            int times = size / key;
            for (int i = 0; i < times; i++) {
                List<Integer> ret = new ArrayList<>();
                for (int j = 0; j < key; j++) {
                    // 0 1 2 3 4 6
                    ret.add(entry.getValue().get(i * key + j));
                }
                ans.add(ret);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] groupSizes = new int[]{2,1,3,3,3,2};
        System.out.println(groupThePeople(groupSizes));
    }
}
