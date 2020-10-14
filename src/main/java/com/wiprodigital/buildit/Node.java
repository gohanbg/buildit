package com.wiprodigital.buildit;

import java.util.Set;

public abstract class Node<T> {

    T value;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    abstract Set<? extends Node<T>> neighbours();

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Node && ((Node<?>) obj).getValue().equals(getValue());
    }
}
