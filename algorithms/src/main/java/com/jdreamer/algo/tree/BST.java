package com.jdreamer.algo.tree;

/**
 * http://algs4.cs.princeton.edu/32bst/BST.java.html
 */
public class BST<K extends Comparable<K>, V> {
    private Node root; // Root of BST

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int N;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }

        return x.N;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * Insert key-value pair into BST
     * If key already exists, update with new value
     */
    public void put(K key, V val) {
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public K min() {
        if (isEmpty()) return null;

        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) {
            return x;
        }

        return min(x.left);
    }


    public Node max(Node x) {
        if (x.right == null) {
            return x;
        }

        return max(x.right);
    }

    public Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(K key) {
        delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }

            Node t = x;
            x = min(t.right); // find successor x of t
            x.right = deleteMin(t.right); // delete the min in it's right subtree
            x.left = t.left; // put x in t's spot
        }

        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

}
