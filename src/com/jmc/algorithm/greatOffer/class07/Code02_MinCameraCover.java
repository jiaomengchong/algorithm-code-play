package com.jmc.algorithm.greatOffer.class07;

/**
 * 测试链接：https://leetcode.com/problems/binary-tree-cameras/
 *
 * @author jmc
 * @version 1.0
 * @date 2021/5/19 9:50
 */
public class Code02_MinCameraCover {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Info {
        private long unCovered;
        private long noCameraCovered;
        private long hasCameraCovered;

        public Info(long unCovered, long noCameraCovered, long hasCameraCovered) {
            this.unCovered = unCovered;
            this.noCameraCovered = noCameraCovered;
            this.hasCameraCovered = hasCameraCovered;
        }
    }

    public static int minCameraCover1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Info info = process1(root);
        return (int) Math.min(info.unCovered + 1, Math.min(info.noCameraCovered, info.hasCameraCovered));
    }

    private static Info process1(TreeNode head) {
        if (head == null) {
            return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        }
        Info left = process1(head.left);
        Info right = process1(head.right);
        long noCovered = left.noCameraCovered + right.noCameraCovered;
        long noCameraCovered = Math.min(
                left.hasCameraCovered + right.noCameraCovered,
                Math.min(
                        left.hasCameraCovered + right.hasCameraCovered,
                        left.noCameraCovered + right.hasCameraCovered));
        long hasCameraCovered = Math.min(Math.min(left.unCovered, left.noCameraCovered), left.hasCameraCovered) +
                Math.min(Math.min(right.unCovered, right.noCameraCovered), right.hasCameraCovered) + 1;
        return new Info(noCovered, noCameraCovered, hasCameraCovered);
    }

    public static enum Status {
        UNCOVERED,
        COVERED_NO_CAMERA,
        COVERED_HAS_CAMERA
    }

    public static class Data {
        private int camera;
        private Status status;

        public Data(int camera, Status status) {
            this.camera = camera;
            this.status = status;
        }
    }

    public static int minCameraCover2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Data data = process2(root);
        return data.status == Status.UNCOVERED ? 1 + data.camera : data.camera;
    }

    private static Data process2(TreeNode head) {
        if (head == null) {
            return new Data(0, Status.COVERED_NO_CAMERA);
        }

        Data left = process2(head.left);
        Data right = process2(head.right);
        int cameras = left.camera + right.camera;
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(1 + cameras, Status.COVERED_HAS_CAMERA);
        }
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(cameras, Status.COVERED_NO_CAMERA);
        }
        return new Data(cameras, Status.UNCOVERED);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        System.out.println(minCameraCover1(root));
        System.out.println(minCameraCover2(root));
    }
}
