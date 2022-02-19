package com.jmc.algorithm.jjb.class16;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jmc
 * @version 1.0
 * @date 2020/12/25 15:55
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public List<Node> nexts;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
