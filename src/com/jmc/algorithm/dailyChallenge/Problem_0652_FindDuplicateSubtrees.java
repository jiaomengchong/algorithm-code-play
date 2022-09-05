package com.jmc.algorithm.dailyChallenge;

import java.util.*;

/**
 * 测试链接：https://leetcode.cn/problems/find-duplicate-subtrees/
 */
public class Problem_0652_FindDuplicateSubtrees {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    Map<String, TreeNode> appear = new HashMap<>();
    Set<TreeNode> sets = new HashSet<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(sets);
    }

    private String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        sb.append("(");
        sb.append(dfs(root.left));
        sb.append(")(");
        sb.append(dfs(root.right));
        sb.append(")");
        if (appear.containsKey(sb.toString())) {
            sets.add(appear.get(sb.toString()));
        } else {
            appear.put(sb.toString(), root);
        }
        return sb.toString();
    }
}
