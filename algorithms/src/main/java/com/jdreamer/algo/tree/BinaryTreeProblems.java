package com.jdreamer.algo.tree;

/**
 *
 */
public class BinaryTreeProblems {

    public static void main(String[] args) {
        Node root = new Node<Integer>(1);
        root.setLeft(new Node<Integer>(2));
        root.setRight(new Node<Integer>(3));
        root.getLeft().setLeft(new Node<Integer>(4));
        root.getLeft().setRight(new Node<Integer>(5));
        root.getLeft().getLeft().setLeft(new Node<Integer>(7));
        root.getRight().setLeft(new Node<Integer>(6));

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

        System.out.print("BFS: ");
        NonRecursiveTreeTraversal.BFS_ORDER.traverse(root);
        System.out.println();
    }
}
