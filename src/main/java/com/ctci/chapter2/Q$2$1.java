package com.ctci.chapter2;


import com.ctci.objects.SinglyLinkedListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Question 2.1: Remove Duplicates.
 * Write code to remove duplicates from an unsorted linked list.
 */
class Q$2$1 {

    /**
     * Performance:
     * - runtime: O(N * logN)
     * - memory: O(N)
     */
    static <T> void distinctUsingHashSet(SinglyLinkedListNode<T> node) {
        HashSet<T> set = new HashSet<>();
        set.add(node.value);

        // move through all elements
        while (node != null && node.next != null) {
            // delete node if duplicate
            if (!set.add(node.next.value))
                node.next = node.next.next;
            // move to a next node
            node = node.next;
        }
    }

    /**
     * Performance:
     * - runtime: O(N^2)
     * - memory: O(1)
     */
    static <T> void distinctWithoutBuffer(SinglyLinkedListNode<T> root) {
        while (root != null) {
            // run through node starting from root
            SinglyLinkedListNode<T> node = root;
            while (node != null && node.next != null) {
                // delete node if duplicate
                if (node.value == node.next.value)
                    node.next = node.next.next;
                // move to a next node
                node = node.next;
            }
            // advance to next element to check for duplicates
            root = root.next;
        }
    }

    @ParameterizedTest
    @MethodSource("data")
    void checkDistinct(int[] values) {
        Integer[] integers = Arrays.stream(values).boxed().toArray(Integer[]::new);
        Integer[] expect = Arrays.stream(integers).distinct().toArray(Integer[]::new);
        assertArrayEquals(expect, distinctUsing(integers, Q$2$1::distinctUsingHashSet, new Integer[0]));
        assertArrayEquals(expect, distinctUsing(integers, Q$2$1::distinctWithoutBuffer, new Integer[0]));
    }

    static <T> T[] distinctUsing(T[] items, Consumer<SinglyLinkedListNode> consumer, T[] array) {
        SinglyLinkedListNode<T> root = SinglyLinkedListNode.fromArray(items);
        consumer.accept(root);
        return root.toArray(array);
    }

    private static int[][] data() {
        return new int[][]{
                {1, 2, 3, 4},
                {1, 2, 3, 4, 4},
                {1, 1, 2, 3, 4},
                {1, 1, 2, 3, 4, 4},
                {1, 2, 3, 3, 4},
        };
    }
}
