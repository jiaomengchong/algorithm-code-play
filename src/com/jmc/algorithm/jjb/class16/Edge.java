package com.jmc.algorithm.jjb.class16;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 15:58
 */
public class Edge {
    public Node from;
    public Node to;
    public int weight;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
