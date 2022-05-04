package com.jmc.algorithm.dailyChallenge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 测试链接：https://leetcode-cn.com/problems/3sum/
 */
public class Problem_0015_3Sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int a = nums[i];
            for (int j = i + 1; j < N; j++) {
                int b = nums[j];
                for (int k = j + 1; k < N; k++) {
                    int c = nums[k];
                    if (a + b + c == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(a);
                        list.add(b);
                        list.add(c);
                        list = list.stream().sorted().collect(Collectors.toList());
                        String key = String.format("%s%s%s", list.get(0), list.get(1), list.get(2));
                        if (!map.containsKey(key)) {
                            map.put(key, 1);
                            ans.add(list);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = nums.length - 1;
            for (int second = first + 1; second < third; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[first] + nums[second] + nums[third] > 0) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        System.out.println(threeSum(nums));
        System.out.println(threeSum1(nums));
    }
}
