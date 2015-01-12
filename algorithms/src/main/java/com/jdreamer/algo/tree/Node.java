package com.jdreamer.algo.tree;

/**
 *
 */
public class Node<V extends Comparable<V>> {
    private V val;
    private Node left;
    private Node right;

    public Node(V val) {
        this.val = val;
    }

    public V getValue() {
        return val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String toString() {
        return "Node (" + val + ")";
    }
}
