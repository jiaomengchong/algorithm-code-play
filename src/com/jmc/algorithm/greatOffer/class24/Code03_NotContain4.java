package com.jmc.algorithm.greatOffer.class24;

/**
 * 正常的里程表会依次显示自然数表示里程
 * 吉祥的里程表会忽略含有4的数字而跳到下一个完全不含有4的数
 * 正常：1 2 3 4 5 6 7 8   9 10 11 12 13 14 15
 * 吉祥：1 2 3 5 6 7 8 9 10 11 12 13 15 16 17 ... 38 39 50 51 52 53 55
 * 给定一个吉祥里程表的数字num，返回这个数字代表的真实里程
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/26 16:22
 */
public class Code03_NotContain4 {
    public static final long[] notContain4Map = new long[]{0L, 1L, 9L, 81L, 729L, 6561L, 59049L, 531441L,
            4782969L, 43046721L, 387420489L, 3486784401L, 31381059609L, 282429536481L, 2541865828329L,
            22876792454961L, 205891132094649L, 1853020188851841L, 16677181699666569L, 150094635296999121L, 1350851717672992089L};

    public static int getDecimalLength(long num) {
        int ans = 0;
        while (num != 0) {
            ans++;
            num /= 10;
        }
        return ans;
    }

    public static long offset1(int length) {
        long offset = 1;
        for (int i = 1; i < length; i++) {
            offset *= 10;
        }
        return offset;
    }

    public static long notContain4Ways1(long num) {
        if (num <= 0) {
            return 0;
        }

        // 6579
        // 1000
        // 一部分：三位数的即arr[arr.length-1]-1，注意2点：1、不包含0，所以减1；2、因为我们在notContain4Map补了0，所以arr[arr.length]-1=arr[4]-1
        // 二部分：最高位6，所以比6小的高位有1、2、3、5，个数=(6-2)*arr[4]
        // 三部分：最高位6，后三位579，处理比579小的，则调用递归处理，注意：因为最高位有数，所以递归可以包含0
        int length = getDecimalLength(num);
        long offset = offset1(length);
        long high = num / offset;
        return notContain4Map[length] - 1 + (high - (high > 4 ? 2 : 1)) * notContain4Map[length] + processWays1(num % offset, offset / 10, length - 1);
    }

    private static long processWays1(long num, long offset, int length) {
        if (length == 0) {
            return 1;
        }

        long high = num / offset;
        return (high > 4 ? high - 1 : high) * notContain4Map[length] + processWays1(num % offset, offset / 10, length - 1);
    }

    public static void main(String[] args) {
        long num = 8173528638135L;
        System.out.println(getDecimalLength(num));
        System.out.println(num);
        System.out.println(offset1(getDecimalLength(num)));
        System.out.println(6);
        System.out.println(6 & (~6 + 1));
        System.out.println(num % offset1(getDecimalLength(num)));

        long ans = 1;
        System.out.print("public static final long[] notContain4Map = new long[]{0L");
        for (int i = 0; i <= 19; i++) {
            System.out.print(", " + ans + "L");
            ans *= 9;
        }
        System.out.println("}");
        System.out.println(notContain4Ways1(6579L));
    }
}
