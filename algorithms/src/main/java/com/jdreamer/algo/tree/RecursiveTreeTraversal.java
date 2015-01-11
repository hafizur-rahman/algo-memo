package com.jdreamer.algo.tree;

/**
 *
 */
public class RecursiveTreeTraversal {

    public static TreeTraversal PRE_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            // Print current node data
            System.out.print(root.getValue() + " ");

            // Traverse left tree
            traverse(root.getLeft());

            // Traverse right tree
            traverse(root.getRight());
        }
    };

    public static TreeTraversal IN_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            // Traverse left tree
            traverse(root.getLeft());

            // Print current node data
            System.out.print(root.getValue() + " ");

            // Traverse right tree
            traverse(root.getRight());
        }
    };

    public static TreeTraversal POST_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            // Traverse left tree
            traverse(root.getLeft());

            // Traverse right tree
            traverse(root.getRight());

            // Print current node data
            System.out.print(root.getValue() + " ");
        }
    };

}
