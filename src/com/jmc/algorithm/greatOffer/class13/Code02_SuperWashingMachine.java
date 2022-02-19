package com.jmc.algorithm.greatOffer.class13;

/**
 * 本题测试链接 : https://leetcode.com/problems/super-washing-machines/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/6/13 19:47
 */
public class Code02_SuperWashingMachine {
    public static int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) {
            return 0;
        }
        int sum = 0;
        int size = machines.length;
        for (int i = 0; i < machines.length; i++) {
            sum += machines[i];
        }
        if (sum % size != 0) {
            return -1;
        }

        int avg = sum / size;
        int ans = 0;
        int leftSum = 0;
        int leftRest = 0;
        int rightRest = 0;

        for (int i = 0; i < size; i++) {
            leftRest = leftSum - avg * i;
            rightRest = (sum - leftSum - machines[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += machines[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] machines = new int[]{0, 3, 0};
        System.out.println(findMinMoves(machines));
    }
}
