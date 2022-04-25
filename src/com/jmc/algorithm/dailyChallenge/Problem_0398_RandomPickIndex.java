package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode-cn.com/problems/random-pick-index/
 *
 * @Author jmc
 * @Description
 * @Date 2022/4/25 10:40
 **/
public class Problem_0398_RandomPickIndex {
    public static class Node {
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Solution {
        private Map<Integer, List<Node>> indexMap;

        public Solution(int[] nums) {
            indexMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                Node node = new Node(i);
                if (indexMap.containsKey(nums[i])) {
                    indexMap.get(nums[i]).add(node);
                } else {
                    List<Node> nodes = new ArrayList<>();
                    nodes.add(node);
                    indexMap.put(nums[i], nodes);
                }
            }
        }

        public int pick(int target) {
            List<Node> nodes = indexMap.get(target);
            int index = (int) (Math.random() * nodes.size());
            return nodes.get(index).value;
        }
    }

    // hashMap代码精简版本
    public static class Solution1 {
        private Map<Integer, List<Integer>> position;
        private Random random;

        public Solution1(int[] nums) {
            position = new HashMap<>();
            random = new Random();
            for (int i = 0; i < nums.length; i++) {
                position.putIfAbsent(nums[i], new ArrayList<>());
                position.get(nums[i]).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> indicates = position.get(target);
            return indicates.get(random.nextInt(indicates.size()));
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 3};
        // pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
        Solution solution = new Solution(nums);
        System.out.println(solution.pick(3));

        Solution1 solution1 = new Solution1(nums);
        System.out.println(solution1.pick(3));
    }
}
