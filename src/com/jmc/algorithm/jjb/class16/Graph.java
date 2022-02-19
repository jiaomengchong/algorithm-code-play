package com.jmc.algorithm.jjb.class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 16:03
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
