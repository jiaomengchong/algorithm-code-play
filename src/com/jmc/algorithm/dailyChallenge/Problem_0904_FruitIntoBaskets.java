package com.jmc.algorithm.dailyChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试链接：https://leetcode.cn/problems/fruit-into-baskets/
 */
public class Problem_0904_FruitIntoBaskets {
    public static int totalFruit(int[] fruits) {
            int ans = 1;
        // 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
        // 输出：5
        int N = fruits.length;
        int left = 0, right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (left <= right && right < N) {
            if (map.containsKey(fruits[right])) {
                map.put(fruits[right], map.get(fruits[right]) + 1);
                right++;
            } else {
                while (map.size() > 1 && left <= right) {
                    map.put(fruits[left], map.get(fruits[left]) - 1);
                    if (map.getOrDefault(fruits[left], 0) == 0) {
                        map.remove(fruits[left++]);
                    } else {
                        left++;
                    }
                }
                map.put(fruits[right++], 1);
            }
            if (map.size() <= 2) {
                ans = Math.max(ans, right - left);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits = new int[]{3};
        System.out.println(totalFruit(fruits));
    }
}
