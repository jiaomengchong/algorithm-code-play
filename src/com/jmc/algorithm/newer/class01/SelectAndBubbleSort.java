package com.jmc.algorithm.newer.class01;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/11 9:12
 */
public class SelectAndBubbleSort {

    private static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                min = arr[min] > arr[j] ? j : min;
            }
            swap(arr, i, min);
        }
    }

    private static void bubbleSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int cur = i;
            while (cur - 1 >= 0 && arr[cur - 1] > arr[cur]) {
                swap(arr, cur - 1, cur);
                cur--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 6, 3, 5, 1, 0, 10, 11};
        printArray(arr);
        insertSort(arr);
        printArray(arr);
    }
}
