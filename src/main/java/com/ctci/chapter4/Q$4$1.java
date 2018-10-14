package com.ctci.chapter4;

import com.ctci.objects.Graph;
import com.ctci.objects.GraphNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Question 4.1: Route Between Nodes.
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 */
public class Q$4$1 {

    /**
     * Finds a path using Breadth-First traversing.
     * Performance:
     * - runtime: O(N)
     * - memory: O(N)
     */
    static <T> boolean hasPathBreadthFirst(GraphNode<T> A, GraphNode<T> B) {
        Set tracker = new HashSet<>();
        Queue<GraphNode<T>> queue = new ArrayDeque<>();

        // start from A
        queue.add(A);

        // iterate breadth-first
        while (queue.size() > 0)
            if (hasAdjacentBF(queue.remove(), B, queue, tracker))
                return true;

        return false;
    }

    @SuppressWarnings("unchecked")
    static <T> boolean hasAdjacentBF(GraphNode<T> root, GraphNode<T> search, Queue queue, Set tracker) {
        for (GraphNode<T> node : root.adjacent) {
            if (node == search)
                return true;

            if (tracker.add(node))
                queue.add(node);
        }

        return false;
    }

    /**
     * Finds a path using Depth-First traversing.
     * Performance:
     * - runtime: O(N)
     * - memory: O(N)
     */
    static <T> boolean hasPathDepthFirst(GraphNode<T> A, GraphNode<T> B) {
        Set tracker = new HashSet<>();
        return hasAdjacentDF(A, B, tracker);
    }

    @SuppressWarnings("unchecked")
    static <T> boolean hasAdjacentDF(GraphNode<T> root, GraphNode<T> search, Set tracker) {
        for (GraphNode<T> node : root.adjacent) {
            if (!tracker.add(node))
                continue;

            if (node == search)
                return true;

            if (hasAdjacentDF(node, search, tracker))
                return true;
        }
        return false;
    }

    @ParameterizedTest
    @MethodSource("checkHasPathData")
    void checkHasPath(String from, String to) {
        Graph<String> graph = createGraph();
        GraphNode<String> A = graph.find(from);
        GraphNode<String> B = graph.find(to);

        String msg = String.format("'%s' has path to '%s'", from, to);
        assertTrue(hasPathBreadthFirst(A, B), msg);
    }

    @ParameterizedTest
    @MethodSource("checkNoPathData")
    void checkNoPath(String from, String to) {
        Graph<String> graph = createGraph();
        GraphNode<String> A = graph.find(from);
        GraphNode<String> B = graph.find(to);

        String msg = String.format("'%s' doesn't have path to '%s'", from, to);
        assertFalse(hasPathBreadthFirst(A, B), msg);
        assertFalse(hasPathDepthFirst(A, B), msg);
    }

    @SuppressWarnings("unchecked")
    static Graph<String> createGraph() {
        // create nodes
        GraphNode<String> A = new GraphNode<>("A");
        GraphNode<String> B = new GraphNode<>("B");
        GraphNode<String> C = new GraphNode<>("C");
        GraphNode<String> D = new GraphNode<>("D");
        GraphNode<String> E = new GraphNode<>("E");

        // connect edges
        // A   ←   B
        //  ↘    ↗
        //     C
        //   ↙
        // D   ↔   E
        A.setAdjacent(C);
        B.setAdjacent(A);
        C.setAdjacent(B, D);
        D.setAdjacent(E);
        E.setAdjacent(D);

        // create graph
        GraphNode[] nodes = {A, B, C, D, E};
        return new Graph<>(nodes);
    }

    static String[][] checkHasPathData() {
        return new String[][]{
                new String[]{"A", "B"},
                new String[]{"B", "C"},
                new String[]{"C", "A"},
                new String[]{"C", "E"},
                new String[]{"E", "D"},
        };
    }

    static String[][] checkNoPathData() {
        return new String[][]{
                new String[]{"D", "C"},
                new String[]{"E", "C"},
        };
    }
}
