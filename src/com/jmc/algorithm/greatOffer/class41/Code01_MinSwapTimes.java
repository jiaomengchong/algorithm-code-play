package com.jmc.algorithm.greatOffer.class41;

/**
 * @Author jmc
 * @Description
 * @Date 2021/10/10 10:22
 **/
public class Code01_MinSwapTimes {
    public static int minSwapTimes1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // arr=[3,2,1,0]
        // 交换的次数=n-环的个数
        int N = arr.length;
        int ans = 0;
        for (int i = 0; i < N; ) {
            if (i == arr[i]) {
                i++;
            } else {
                swap(arr, i, arr[i]);
                ans++;
            }
        }
        return ans;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 4, 2, 0, 1};
        System.out.println(minSwapTimes1(arr));
    }
}
