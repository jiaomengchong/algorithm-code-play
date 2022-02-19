package com.jmc.algorithm.jjb.class16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 17:47
 */
public class Code02_DFS {
    public static void dfs(Node start) {
        Set<Node> set = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.push(start);
        set.add(start);
        System.out.println(start.value);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node node : cur.nexts) {
                if (!set.contains(node)) {
                    stack.push(cur);
                    stack.push(node);
                    set.add(node);
                    System.out.println(node.value);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node k = new Node(6);
        a.nexts.add(b);
        a.nexts.add(c);
        a.nexts.add(d);
        a.nexts.add(k);

        b.nexts.add(c);
        b.nexts.add(e);

        c.nexts.add(d);

        e.nexts.add(c);

        dfs(a);
    }
}
