package com.jmc.algorithm.jjb.class05;

import java.util.Arrays;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/2/23 11:37
 */
public class Code02_PartitionAndQuickSort {
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int pos = partition(arr, L, R);
        process1(arr, L, pos - 1);
        process1(arr, pos + 1, R);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        int[] pos = netherLandsFlag(arr, L, R);
        process1(arr, L, pos[0] - 1);
        process1(arr, pos[1] + 1, R);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);

        int[] pos = netherLandsFlag(arr, L, R);
        process1(arr, L, pos[0] - 1);
        process1(arr, pos[1] + 1, R);
    }

    private static int[] netherLandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, L};
        }

        int[] ans = new int[2];
        int leftLess = L - 1;
        int rightMore = R;
        int cur = L;
        while (cur < rightMore) {
            if (arr[cur] < arr[R]) {
                swap(arr, ++leftLess, cur++);
            } else if (arr[cur] == arr[R]) {
                cur++;
            } else {
                swap(arr, cur, --rightMore);
            }
        }

        swap(arr, rightMore, R);
        ans[0] = leftLess + 1;
        ans[1] = rightMore;
        return ans;
    }

    private static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }

        int leftLess = L - 1;
        int current = L;
        while (current < R) {
            if (arr[current] <= arr[R]) {
                swap(arr, current++, ++leftLess);
            } else {
                current++;
            }
        }
        swap(arr, ++leftLess, R);
        return leftLess;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int test = 1000000;
        int[] num = new int[10];
        for (int i = 0; i < test; i++) {
            for (int j = 0; j < 10; j++) {
                if ((int)(Math.random() * 10) == j) {
                    num[j]++;
                }
            }
        }
        System.out.println(Arrays.toString(num));

        int[] arr1 = {18, 9, 12, 400, 206, 13, 17, 10, 4};
        int[] arr2 = {18, 9, 12, 400, 206, 13, 17, 10, 4};
        int[] arr3 = {18, 9, 12, 400, 206, 13, 17, 10, 4};
        quickSort1(arr1);
        quickSort2(arr2);
        quickSort3(arr3);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }
}
