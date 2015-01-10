package com.jdreamer.algo.tree;

/**
 *
 */
public class BinaryTreeProblems {

    public static void main(String[] args) {
        Node root = new Node(1, new Node(2), new Node(3));
        root.getLeft().setLeft(new Node(4));
        root.getLeft().setRight(new Node(5));
        root.getLeft().getLeft().setLeft(new Node(7));
        root.getRight().setLeft(new Node(6));

        System.out.print("Pre-order (recursive): ");
        RecursiveTreeTraversal.PRE_ORDER.traverse(root);
        System.out.println();

        System.out.print("Pre-order (non-recursive): ");
        NonRecursiveTreeTraversal.PRE_ORDER.traverse(root);
        System.out.println();

        System.out.print("In-order (recursive): ");
        RecursiveTreeTraversal.IN_ORDER.traverse(root);
        System.out.println();

        System.out.print("In-order (non-recursive): ");
        NonRecursiveTreeTraversal.IN_ORDER.traverse(root);
        System.out.println();

        System.out.print("Post-order (recursive): ");
        RecursiveTreeTraversal.POST_ORDER.traverse(root);
        System.out.println();

        System.out.print("Post-order (non-recursive): ");
        NonRecursiveTreeTraversal.POST_ORDER.traverse(root);
        System.out.println();
    }
}
