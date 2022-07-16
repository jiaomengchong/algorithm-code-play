package com.jmc.algorithm.dailyChallenge;

/**
 * 测试链接：https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees/
 */
public class Problem_0558_LogicalOrOfTwoBinaryGridsRepresentedAsQuadTrees {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return new Node(true, true, null, null, null, null);
            }
            return new Node(quadTree2.val, quadTree2.isLeaf, quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
        }

        if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return new Node(true, true, null, null, null, null);
            }
            return new Node(quadTree1.val, quadTree1.isLeaf, quadTree1.topLeft, quadTree1.topRight, quadTree1.bottomLeft, quadTree1.bottomRight);

        }

        Node ans = new Node();
        ans.isLeaf = false;
        ans.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        ans.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        ans.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        ans.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        int isLeaf = operation(ans.topLeft.isLeaf, ans.topRight.isLeaf, ans.bottomLeft.isLeaf, ans.bottomRight.isLeaf);
        int val = operation(ans.topLeft.val, ans.topRight.val, ans.bottomLeft.val, ans.bottomRight.val);
        if (isLeaf == 4 && (val == 4 || val == 0)) {
            return new Node(val == 4, true, null, null, null, null);
        }

        return ans;
    }

    private int operation(boolean a, boolean b, boolean c, boolean d) {
        return (a ? 1 : 0) + (b ? 1 : 0) + (c ? 1 : 0) + (d ? 1 : 0);
    }
}
