package com.jmc.algorithm.jjb.class06;

import java.util.Arrays;

/**
 * @author jmc
 * @version 1.0
 * @date 2021/3/7 21:05
 */
public class Code03_HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        int heapSize = arr.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 6, 0, 3};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
