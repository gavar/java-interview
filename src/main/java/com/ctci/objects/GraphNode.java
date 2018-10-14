package com.ctci.objects;

import java.lang.reflect.Array;
import java.util.List;

public class GraphNode<T> {
    public T value;
    public GraphNode<T>[] adjacent;

    public GraphNode() {

    }

    public GraphNode(T value) {
        this.value = value;
        this.adjacent = (GraphNode<T>[]) Array.newInstance(GraphNode.class, 0);
    }

    public GraphNode(T value, int length) {
        this.value = value;
        this.adjacent = (GraphNode<T>[]) Array.newInstance(GraphNode.class, length);
    }

    public GraphNode(T value, GraphNode<T>[] adjacents) {
        this.value = value;
        this.adjacent = adjacents;
    }

    @SuppressWarnings("unchecked")
    public void setAdjacent(GraphNode<T>... adjacent) {
        this.adjacent = adjacent;
    }

    public void setAdjacent(List<T> adjacent) {
        this.adjacent = adjacent.toArray(this.adjacent);
    }
}
