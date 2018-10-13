package com.ctci.objects;

import java.util.ArrayList;

public class SinglyLinkedListNode<T> {

    public static <T> SinglyLinkedListNode<T> fromArray(T[] items) {
        SinglyLinkedListNode<T> first = null;

        if (items.length > 0)
            first = new SinglyLinkedListNode<>(items[0]);

        SinglyLinkedListNode<T> node = first;
        for (int i = 1; i < items.length; i++) {
            node.next = new SinglyLinkedListNode<>(items[i]);
            node = node.next;
        }

        return first;
    }

    public SinglyLinkedListNode<T> next;
    public T value;

    public SinglyLinkedListNode(T value) {
        this.value = value;
    }


    public T[] toArray(T[] a) {
        SinglyLinkedListNode<T> node = this;
        ArrayList<T> list = new ArrayList<>();
        while (node != null) {
            list.add(node.value);
            node = node.next;
        }
        return list.toArray(a);
    }
}
