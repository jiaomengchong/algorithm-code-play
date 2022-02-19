package com.jmc.algorithm.greatOffer.class38;

import java.util.*;

/**
 * 找到数组中所有消失的数字
 *
 * @Author jmc
 * @Description
 * @Date 2021/9/4 18:01
 **/
public class Problem_0448_FindAllNumbersDisappearedInAnArray {
    public static List<Integer> findDisappearedNumbers1(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int N = nums.length;
        int[] copy = Arrays.copyOf(nums, N);
        Arrays.sort(copy);
        System.out.println(Arrays.toString(copy));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(copy[i], i);
        }

        for (int i = 0; i < N; i++) {
            if (map.get(i + 1) == null) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    public static List<Integer> findDisappearedNumbers2(int[] nums) {
        if (nums == null) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            subscriptCycle(nums, i);
        }
        for (int i = 0; i < N; i++) {
            if (i + 1 != nums[i]) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    // 下标循环怼
    private static void subscriptCycle(int[] nums, int index) {
        while (index != nums[index] - 1) {
            int nextIndex = nums[index] - 1;
            if (nums[nextIndex] == nextIndex + 1) {
                break;
            }
            swap(nums, index, nextIndex);
        }
    }

    private static void swap(int[] nums, int index, int nextIndex) {
        int temp = nums[index];
        nums[index] = nums[nextIndex];
        nums[nextIndex] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers1(nums));
        System.out.println(findDisappearedNumbers2(nums));
    }
}
