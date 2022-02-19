package com.jmc.algorithm.jjb.class16;

import java.util.*;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/26 12:23
 */
public class Code03_TopologySort {

    public static List<Node> topologySort(Graph graph) {
        List<Node> ans = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Integer> inMap = new HashMap<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            ans.add(cur);
            for (Node node : cur.nexts) {
                inMap.put(node, inMap.get(node) - 1);
                if (inMap.get(node) == 0) {
                    queue.add(node);
                }
            }
        }

        return ans;
    }
}
