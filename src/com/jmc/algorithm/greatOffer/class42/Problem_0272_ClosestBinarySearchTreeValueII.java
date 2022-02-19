package com.jmc.algorithm.greatOffer.class42;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的 k 个值。web
 * <p>
 * 注意：
 * 给定的目标值 target 是一个浮点数
 * 你能够默认 k 值永远是有效的，即 k ≤ 总结点数
 * 题目保证该二叉搜索树中只会存在一种 k 个值集合最接近目标值网络
 * <p>
 * 示例：
 * 输入: root = [4,2,5,1,3]，目标值 = 3.714286，且 k = 2
 * <p>
 * 4
 * / \
 * 2   5
 * / \
 * 1   3
 * <p>
 * 输出: [4,3]
 * 拓展：
 * 假设该二叉搜索树是平衡的，请问您是否能在小于 O(n)（n 为总结点数）的时间复杂度内解决该问题呢？
 *
 * @Author jmc
 * @Description`
 * @Date 2021/10/21 15:51
 **/
public class Problem_0272_ClosestBinarySearchTreeValueII {
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null || k <= 0) {
            return ans;
        }

        //快速找前驱节点
        Stack<TreeNode> lessTops = new Stack<>();
        getLessTops(root, target, lessTops);
        //快速找后继节点
        Stack<TreeNode> moreTops = new Stack<>();
        getMoreTops(root, target, moreTops);

        if (!lessTops.isEmpty() && !moreTops.isEmpty() && lessTops.peek().val == moreTops.peek().val) {
            getPredecessor(lessTops);
        }

        while (k-- > 0) {
            if (moreTops.isEmpty()) {
                ans.add(getPredecessor(lessTops));
            } else if (lessTops.isEmpty()) {
                ans.add(getSuccessor(moreTops));
            } else {
                double diffSuccessor = Math.abs(moreTops.peek().val - target);
                double diffPredecessor = Math.abs(lessTops.peek().val - target);
                if (diffSuccessor < diffPredecessor) {
                    ans.add(getSuccessor(moreTops));
                } else {
                    ans.add(getPredecessor(lessTops));
                }
            }
        }

        return ans;
    }

    private static Integer getSuccessor(Stack<TreeNode> moreTops) {
        TreeNode cur = moreTops.pop();
        int ret = cur.val;
        cur = cur.right;
        while (cur != null) {
            moreTops.push(cur);
            cur = cur.left;
        }
        return ret;
    }

    private static int getPredecessor(Stack<TreeNode> lessTops) {
        TreeNode cur = lessTops.pop();
        int ret = cur.val;
        cur = cur.left;
        while (cur != null) {
            lessTops.push(cur);
            cur = cur.right;
        }
        return ret;
    }

    private static void getMoreTops(TreeNode root, double target, Stack<TreeNode> moreTops) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == target) {
                moreTops.push(cur);
            } else if (cur.val > target) {
                moreTops.push(cur);
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
    }

    private static void getLessTops(TreeNode root, double target, Stack<TreeNode> lessTops) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == target) {
                lessTops.push(cur);
            } else if (cur.val < target) {
                lessTops.push(cur);
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
    }

    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        // root = [4,2,5,1,3]，目标值 = 3.714286，且 k = 2
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        double target = 3.714286;
        int k = 2;
        System.out.println(closestKValues(root, target, k));
    }
}
