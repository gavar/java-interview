package com.ctci.chapter4;

import com.ctci.objects.BinaryNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Question 4.2: Minimal Tree.
 * Given a sorted (increasing order) array with unique integer elements,
 * write an algorithm to create a binary search tree with minimal height.
 */
public class Q$4$2 {

    /**
     * Performance:
     * - runtime: O(N)
     * - memory: logN
     */
    static <T> BinaryNode<T> createBST(T[] values) {
        return createBST(values, 0, values.length - 1);
    }

    static <T> BinaryNode<T> createBST(T[] values, int min, int max) {
        if (min > max) return null;
        int median = (max + min) / 2;
        BinaryNode<T> node = new BinaryNode<>(values[median]);
        node.left = createBST(values, min, median - 1);
        node.right = createBST(values, median + 1, max);
        return node;
    }

    @ParameterizedTest
    @MethodSource("data")
    void checkBST(int[] values) {
        Integer[] expect = Arrays.stream(values).boxed().toArray(Integer[]::new);
        BinaryNode<Integer> node = createBST(expect);
        Integer[] actual = node.toInOrderArray(new Integer[0]);
        assertArrayEquals(expect, actual);
    }

    static int[][] data() {
        return new int[][]{
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5, 6},
        };
    }
}
