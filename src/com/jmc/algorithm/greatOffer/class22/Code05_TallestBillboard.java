package com.jmc.algorithm.greatOffer.class22;

import java.util.HashMap;
import java.util.Map;

/**
 * 最高的广告牌
 * 测试链接：https://leetcode-cn.com/problems/tallest-billboard/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/19 19:31
 */
public class Code05_TallestBillboard {
    public static int tallestBillboard(int[] rods) {
        if (null == rods || rods.length == 0) {
            return 0;
        }

        int N = rods.length;
        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer, Integer> old;
        // 空集合对空集合，空集合差值为0，并没有办法安装广告牌，所以value为0
        dp.put(0, 0);
        for (int i = 0; i < N; i++) {
            if (rods[i] != 0) {
                // map(2, 13), 表示差值是2, 基值是13, 较大值是15
                old = new HashMap<>(dp);
                for (int key : old.keySet()) {
                    Integer value = old.get(key);
                    Integer maxValue = value + key;
                    dp.put(rods[i] + maxValue - value, Math.max(dp.getOrDefault(rods[i] + maxValue - value, 0), value));
                    dp.put(rods[i] + value - maxValue, Math.max(dp.getOrDefault(rods[i] + value - maxValue, 0), maxValue));
                }
            }
        }

        return dp.get(0);
    }

    public static void main(String[] args) {
        int[] rods = new int[]{1, 2};
        System.out.println(tallestBillboard(rods));
    }
}
