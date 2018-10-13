package com.ctci.objects;

import java.util.ArrayList;

public class LinkedListNode<T> {

    public static <T> LinkedListNode<T> fromArray(T[] items) {
        LinkedListNode<T> first = null;

        if (items.length > 0)
            first = new LinkedListNode<>(items[0]);

        LinkedListNode<T> node = first;
        for (int i = 1; i < items.length; i++) {
            node.next = new LinkedListNode<>(items[i]);
            node.next.prev = node;
            node = node.next;
        }

        return first;
    }

    public LinkedListNode<T> next;
    public LinkedListNode<T> prev;
    public T value;

    public LinkedListNode(T value) {
        this.value = value;
    }


    public T[] toArray(T[] a) {
        LinkedListNode<T> node = this;
        ArrayList<T> list = new ArrayList<>();
        while (node != null) {
            list.add(node.value);
            node = node.next;
        }
        return list.toArray(a);
    }
}
