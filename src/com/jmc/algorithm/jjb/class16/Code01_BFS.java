package com.jmc.algorithm.jjb.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 17:16
 */
public class Code01_BFS {
    public static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node k = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        a.nexts.add(b);
        a.nexts.add(c);

        b.nexts.add(c);
        b.nexts.add(k);

        c.nexts.add(e);
        c.nexts.add(f);

        k.nexts.add(e);
        k.nexts.add(a);

        e.nexts.add(f);

        bfs(a);

    }
}
