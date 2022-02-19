package com.jmc.algorithm.jjb.class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈是什么？
 * 一种特别设计的栈结构，为了解决如下的问题：
 * <p>
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
 * <p>
 * 那么到底怎么设计呢？
 *
 * @author jmc
 * @version 1.0
 * @date 2021/2/1 10:06
 */
public class Code01_MonotonousStack {

    public static int[][] getNearLessNoRepeat1(int[] arr) {
        int N = arr.length;
        int[][] ans = new int[N][2];
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < N; index++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[index]) {
                Integer pop = stack.pop();
                ans[pop][1] = index;
                ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(index);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop][1] = -1;
            ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
        }

        return ans;
    }

    public static int[][] getNearLessRepeat1(int[] arr) {
        int N = arr.length;
        int[][] ans = new int[N][2];
        Stack<List<Integer>> stack = new Stack<>();

        for (int index = 0; index < N; index++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[index]) {
                List<Integer> popList = stack.pop();
                int lessMostIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer j : popList) {
                    ans[j][1] = index;
                    ans[j][0] = lessMostIndex;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[index]) {
                stack.peek().add(index);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int lessMostIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer j : popList) {
                ans[j][1] = -1;
                ans[j][0] = lessMostIndex;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 5, 1, 6};
        int[][] lessNoRepeat = getNearLessNoRepeat1(arr);
        for (int i = 0; i < lessNoRepeat.length; i++) {
            System.out.println(lessNoRepeat[i][0] + " " + lessNoRepeat[i][1]);
        }

        System.out.println("===========================");

        int[] arr1 = {1, 1, 1, 2, 2, 2};
        int[][] lessRepeat = getNearLessRepeat1(arr1);
        for (int i = 0; i < lessRepeat.length; i++) {
            System.out.println(lessRepeat[i][0] + " " + lessRepeat[i][1]);
        }
    }
}
