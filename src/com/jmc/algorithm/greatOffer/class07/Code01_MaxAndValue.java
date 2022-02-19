package com.jmc.algorithm.greatOffer.class07;

/**
 * 给定一个正数组成的数组，长度一定大于1
 * 想知道数组中哪两个数&的结果最大
 * 返回这个最大结果
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/17 17:46
 */
public class Code01_MaxAndValue {
    public static int _maxAndValue(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int M = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int temp = M;
            int i = 0;
            while (i < M) {
                if ((arr[i] & (1 << bit)) == 0) {
                    _swap(arr, i, --M);
                } else {
                    i++;
                }
            }
            if (M == 2) {
                return arr[0] & arr[1];
            } else if (M < 2) {
                M = temp;
            } else {
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    private static void _swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 1, 8};
        System.out.println(_maxAndValue(arr));
    }
}
