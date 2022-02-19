package com.jmc.algorithm.jjb.class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/26 22:53
 */
public class Code05_Prim {
    private static class MyEdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        Set<Node> setNodes = new HashSet<>();
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(new MyEdgeComparator());
        for (Node node : graph.nodes.values()) {
            if (!setNodes.contains(node)) {
                setNodes.add(node);
                for (Edge edge : node.edges) {
                    edgePriorityQueue.add(edge);
                }
                while (!edgePriorityQueue.isEmpty()) {
                    Edge pollEdge = edgePriorityQueue.poll();
                    if (!setNodes.contains(pollEdge.to)) {
                        setNodes.add(pollEdge.to);
                        ans.add(pollEdge);
                        for (Edge edge : pollEdge.to.edges) {
                            edgePriorityQueue.add(edge);
                        }
                    }
                }
            }
            break;
        }
        return ans;
    }
}
