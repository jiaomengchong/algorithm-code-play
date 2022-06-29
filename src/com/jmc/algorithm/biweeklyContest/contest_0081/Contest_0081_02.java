package com.jmc.algorithm.biweeklyContest.contest_0081;

/**
 * @Author jmc
 * @Description
 * @Date 2022/6/29 16:48
 **/
public class Contest_0081_02 {
    public static int maximumXOR(int[] nums) {
        // 输入：nums = [3,2,4,6]
        // 输出：7
        // 0011
        // 0010
        // 0100
        // 0110
        int max = 0;
        for (int num : nums) {
            max |= num;
        }
        return max;
    }
}
