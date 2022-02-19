package com.jmc.algorithm.jjb.class06;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/30 10:13
 */
public class Code02_Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private int heapSize;
        private int limit;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heapSize = 0;
            heap = new int[limit];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("Heap is Full!");
            }

            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Heap is Empty!");
            }

            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0);
            return ans;
        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] arr, int index) {
            int left;
            while (index * 2 + 1 < heapSize) {
                left = index * 2 + 1;
                int largest = (left + 1) < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(arr, index, largest);
                index = largest;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 8, 6, 12, 11};
        int N = arr.length;
        MyMaxHeap heap = new MyMaxHeap(N);
        for (int i = 0; i < N; i++) {
            heap.push(arr[i]);
        }
        for (int i = 0; i < heap.heapSize; i++) {
            System.out.println(heap.heap[i]);
        }
        System.out.println(heap.pop());
    }
}
