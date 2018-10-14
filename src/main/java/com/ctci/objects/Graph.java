package com.ctci.objects;

import java.lang.reflect.Array;

public class Graph<T> {
    public GraphNode<T>[] nodes;

    public Graph() {

    }

    public Graph(GraphNode[] nodes) {
        this.nodes = nodes;
    }

    public Graph(int length) {
        this.nodes = (GraphNode[]) Array.newInstance(GraphNode.class, length);
    }

    public GraphNode<T> find(T value) {
        if (nodes == null)
            return null;

        for (GraphNode<T> node : nodes)
            if (node.value == value)
                return node;

        return null;
    }
}
