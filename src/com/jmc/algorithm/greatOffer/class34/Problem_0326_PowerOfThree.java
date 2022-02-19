package com.jmc.algorithm.greatOffer.class34;

/**
 * 3的幂
 *
 * @author jmc
 * @version 1.0
 * @date 2021/8/25 14:24
 */
public class Problem_0326_PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1162261467);
        System.out.println(3486784401L);
        System.out.println(Math.pow(3, 20));
        System.out.println(isPowerOfThree(9));
    }
}
