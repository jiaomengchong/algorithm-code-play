package com.jmc.algorithm.greatOffer.class05;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/7 17:50
 */
public class Code01_ConstructBinarySearchTreePreOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        return process1(preorder, 0, preorder.length - 1);
    }

    private static TreeNode process1(int[] preorder, int L, int R) {
        if (L > R) {
            return null;
        }

        int firstBig = L + 1;
        for (; firstBig <= R; firstBig++) {
            if (preorder[firstBig] > preorder[L]) {
                break;
            }
        }

        TreeNode head = new TreeNode(preorder[L]);
        head.left = process1(preorder, L + 1, firstBig - 1);
        head.right = process1(preorder, firstBig, R);
        return head;
    }

    public static TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        int N = preorder.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        // [8,5,1,7,10,12]
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && (preorder[stack.peek()] < preorder[i])) {
                Integer pop = stack.pop();
                nearBig[pop] = i;
            }
            stack.push(i);
        }

        return process2(preorder, 0, N - 1, nearBig);
    }

    private static TreeNode process2(int[] preorder, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }

        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(preorder[L]);
        head.left = process2(preorder, L + 1, firstBig - 1, nearBig);
        head.right = process2(preorder, firstBig, R, nearBig);
        return head;
    }

    public static TreeNode bstFromPreorder3(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }

        int N = preorder.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        int[] stack = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && preorder[stack[size - 1]] < preorder[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }

        return process3(preorder, 0, N - 1, nearBig);
    }

    private static TreeNode process3(int[] preorder, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(preorder[L]);
        head.left = process3(preorder, L + 1, firstBig - 1, nearBig);
        head.right = process3(preorder, firstBig, R, nearBig);
        return head;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        System.out.println(bstFromPreorder2(preorder).val);
        System.out.println(bstFromPreorder3(preorder).val);
    }
}
