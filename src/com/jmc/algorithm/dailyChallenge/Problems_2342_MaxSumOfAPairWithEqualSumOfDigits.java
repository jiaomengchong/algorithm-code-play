package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 测试链接：https://leetcode.cn/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
 *
 * @Author jmc
 * @Description
 * @Date 2022/8/18 17:41
 **/
public class Problems_2342_MaxSumOfAPairWithEqualSumOfDigits {
    public static int maximumSum(int[] nums) {
        int N = nums.length;
        int ans = -1;
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Integer sum = getSum(nums[i]);
            if (map.containsKey(sum)) {
                map.get(sum).offer(nums[i]);
            } else {
                PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
                pq.offer(nums[i]);
                map.put(sum, pq);
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                ans = Math.max(ans, entry.getValue().poll() + entry.getValue().poll());
            }
        }
        return ans;
    }

    private static Integer getSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{18, 43, 36, 13, 7};
        maximumSum(arr);
    }
}
