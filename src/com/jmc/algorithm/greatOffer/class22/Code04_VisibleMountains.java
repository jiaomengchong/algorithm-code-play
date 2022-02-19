package com.jmc.algorithm.greatOffer.class22;

import java.util.Stack;

/**
 * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度
 * 比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山
 * 山峰A和山峰B能够相互看见的条件为:
 * 1.如果A和B是同一座山，认为不能相互看见
 * 2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见
 * 3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min。
 * 1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 两个方向只要有一个能看见，就算A和B可以相互看见
 * 给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见。
 * <p>
 * 进阶问题
 * 给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见。
 *
 * @author jmc
 * @version 1.0
 * @date 2021/7/15 16:16
 */
public class Code04_VisibleMountains {
    public static class Record {
        private int value;
        private int times;

        public Record(int value) {
            this.value = value;
            times = 1;
        }
    }

    public static int visibleMountains(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int N = arr.length;
        int ans = 0;
        int maxIndex = 0;
        Stack<Record> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        stack.push(new Record(arr[maxIndex]));
        int nextIndex = getNextIndex(maxIndex, N);
        while (nextIndex != maxIndex) {
            while (stack.peek().value < arr[nextIndex]) {
                int times = stack.pop().times;
                ans += getInnerSum(times) + 2 * times;
            }
            if (stack.peek().value == arr[nextIndex]) {
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[nextIndex]));
            }
            nextIndex = getNextIndex(nextIndex, N);
        }

        while (!stack.isEmpty()) {
            if (stack.size() > 2) {
                int times = stack.pop().times;
                ans += getInnerSum(times) + 2 * times;
            }
            if (stack.size() == 2) {
                int times = stack.pop().times;
                ans += getInnerSum(times) + (stack.peek().times > 1 ? 2 * times : times);
            }
            if (stack.size() == 1) {
                ans += getInnerSum(stack.pop().times);
            }
        }

        return ans;
    }

    private static int getInnerSum(int times) {
        return times == 1 ? 0 : (times * (times - 1) / 2);
    }

    private static int getNextIndex(int curIndex, int N) {
        return curIndex + 1 == N ? 0 : curIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 7, 1, 3, 2, 0, 6};
        System.out.println(visibleMountains(arr));
    }
}
