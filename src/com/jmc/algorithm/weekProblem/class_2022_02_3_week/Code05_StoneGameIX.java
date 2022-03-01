package com.jmc.algorithm.weekProblem.class_2022_02_3_week;

import java.util.Map;

/**
 * 测试链接：https://leetcode-cn.com/problems/stone-game-ix/
 *
 * @Author jmc
 * @Description
 * @Date 2022/3/1 17:57
 **/
public class Code05_StoneGameIX {
    public static boolean stoneGameIX(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }

        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }

        return count[0] % 2 == 0 ? (count[1] == 0 || count[2] == 0 ? false : true) : (Math.abs(count[1] - count[2]) <= 2 ? false : true);
    }

    public static void main(String[] args) {
        int[] stones = new int[]{5, 1, 2, 4, 3};
        System.out.println(stoneGameIX(stones));
    }
}
