package com.ctci.objects;

import java.util.ArrayList;
import java.util.List;

public class BinaryNode<T> {

    public T value;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode() {

    }

    public BinaryNode(T value) {
        this.value = value;
    }

    public T[] toInOrderArray(T[] array) {
        ArrayList<T> items = new ArrayList<>();
        addInOder(items);
        return items.toArray(array);
    }

    private void addInOder(List<T> items) {
        if (left != null)
            left.addInOder(items);

        items.add(this.value);

        if (right != null)
            right.addInOder(items);
    }
}
