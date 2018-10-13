package com.ctci.chapter2;

import com.ctci.objects.SinglyLinkedListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Question 2.2: Return Kth to Last.
 * Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class Q$2$2 {

    /**
     * Compute total size of the elements in a singly linked list.
     */
    static <T> int sizeOf(SinglyLinkedListNode<T> node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    /**
     * Hint #8:
     * What if you knew the linked list size?
     * What is the difference between finding the Kth-to­last element and finding the Xth element?
     * <p>
     * Performance:
     * - runtime: 2N -> O(N)
     * - memory: O(1)
     */
    static <T> T findByComputingCount(SinglyLinkedListNode<T> node, int k) {
        // calculate total number of elements in a list
        int size = sizeOf(node);

        // out of bounds?
        if (k >= size)
            return null;

        // find (size - k) element
        for (int count = size - k - 1; count > 0; count--)
            node = node.next;

        return node.value;
    }

    /**
     * Hint #41:
     * Try implementing it recursively.
     * If you could find the (K-l)th to last element, can you find the Kth element?
     * Performance:
     * - runtime: O(N)
     * - memory: O(N)
     */
    static <T> T findRecursive(SinglyLinkedListNode<T> node, int k) {
        SinglyLinkedListNode<T> ref = new SinglyLinkedListNode<>(null);
        findRecursiveRef(node, k, 0, ref);
        return ref.value;
    }

    static <T> int findRecursiveRef(SinglyLinkedListNode<T> node, int k, int index, SinglyLinkedListNode<T> ref) {
        int size;

        // use index + 1 as size if end of list
        if (node.next == null) size = index + 1;
        else size = findRecursiveRef(node.next, k, index + 1, ref);

        // index of K-th to last element?
        if (size - k - 1 == index)
            ref.value = node.value;

        return size;
    }


    /**
     * Hint #126: Can you do it iteratively? Imagine if you had two pointers pointing to adjacent nodes
     * and they were moving at the same speed through the linked list. When one hits the end
     * of the linked list, where will the other be?
     * <p>
     * Performance:
     * - runtime: O(N)
     * - memory: O(1)
     */
    static <T> T findByAdjacentPointers(SinglyLinkedListNode<T> root, int k) {
        // skip first k elements
        SinglyLinkedListNode<T> node = root;
        while (k-- > 0) {
            node = node.next;
            // out ouf bounds?
            if (node == null)
                return null;
        }

        // move 2 pointers together
        SinglyLinkedListNode<T> prior = root;
        while (node.next != null) {
            node = node.next;
            prior = prior.next;
        }

        return prior.value;
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void checkLastElementByOffset(int k) {
        int[] values = {1, 2, 3, 4, 5};
        Integer expect = k < values.length ? values[values.length - 1 - k] : null;
        Integer[] integers = Arrays.stream(values).boxed().toArray(Integer[]::new);
        SinglyLinkedListNode<Integer> node = SinglyLinkedListNode.fromArray(integers);

        assertEquals(expect, findByComputingCount(node, k));
        assertEquals(expect, findRecursive(node, k));
        assertEquals(expect, findByAdjacentPointers(node, k));
    }
}
