package com.jmc.algorithm.weekProblem.class_2022_04_03_week;

/**
 * 小红书
 * 3.13 笔试
 * 数组里有0和1，一定要翻转一个区间，翻转：0变1，1变0
 * 请问翻转后可以使得1的个数最多是多少？
 *
 * @Author jmc
 * @Description
 * @Date 2022/4/24 16:21
 **/
public class Code01_MaxOneNumbers {
    public static int ways1(int[] arr) {
        int ans = 0;
        // 10100011000
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : -1;
        }

        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }

        return ans + max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 0};
        System.out.println(ways1(arr));
    }
}
