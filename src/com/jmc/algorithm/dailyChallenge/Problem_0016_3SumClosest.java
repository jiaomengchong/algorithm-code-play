package com.jmc.algorithm.dailyChallenge;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 测试链接：https://leetcode-cn.com/problems/3sum-closest/
 */
public class Problem_0016_3SumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) {
            return Integer.MAX_VALUE;
        }

        Arrays.sort(nums);

        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = nums.length - 1;
            int second = first + 1;
            while (second < third) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    second++;
                    continue;
                }
                int sum = nums[first] + nums[second] + nums[third];
                if (sum == target) {
                    return target;
                }
                treeSet.add(sum);
                if (sum > target) {
                    int third1 = third - 1;
                    while (second < third1 && nums[third] == nums[third1]) {
                        third1--;
                    }
                    third = third1;
                    if (second == third) {
                        continue;
                    }
                    sum = nums[first] + nums[second] + nums[third];
                    if (sum == target) {
                        return sum;
                    }
                    treeSet.add(sum);
                } else {
                    int second2 = second + 1;
                    while (second2 < third && nums[second2] == nums[second]) {
                        second2++;
                    }
                    second = second2;
                    if (second == third) {
                        continue;
                    }
                    sum = nums[first] + nums[second] + nums[third];
                    if (sum == target) {
                        return sum;
                    }
                    treeSet.add(sum);
                }
            }
        }
        int ans;
        Integer floor = treeSet.floor(target);
        Integer ceiling = treeSet.ceiling(target);
        if (floor == null) {
            ans = ceiling;
        } else if (ceiling == null) {
            ans = floor;
        } else {
            int tmp1 = Math.abs(floor - target);
            int tmp2 = Math.abs(ceiling - target);
            ans = tmp1 > tmp2 ? ceiling : floor;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,-3,3,5,4,1};
        int[] nums = new int[]{1, 1, 1, 1};
        int target = 4;
        System.out.println(threeSumClosest(nums, target));
    }
}
