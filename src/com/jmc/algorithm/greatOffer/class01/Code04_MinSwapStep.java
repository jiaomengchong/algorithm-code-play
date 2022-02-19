package com.jmc.algorithm.greatOffer.class01;

/**
 * 一个数组中只有两种字符'G'和'B’，
 * 想让所有的G都放在左侧，所有的B都放在右侧
 * 或者想让所有的G都放在右侧，所有的B都放在左侧
 * 但是只能在相邻字符之间进行交换操作，
 * 返回至少需要交换几次
 *
 * @author jmc
 * @version 1.0
 * @date 2021/4/24 22:28
 */
public class Code04_MinSwapStep {
    public static int minStep(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        int gi = 0;
        int bi = 0;
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                ans1 += i - (gi++);
            } else {
                ans2 += i - (bi++);
            }
        }
        return Math.min(ans1, ans2);
    }

    public static void main(String[] args) {
        String s = "BBBBBBGB";
        System.out.println(minStep(s));
    }
}
