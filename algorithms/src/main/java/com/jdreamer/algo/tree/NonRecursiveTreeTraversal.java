package com.jdreamer.algo.tree;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 *
 */
public class NonRecursiveTreeTraversal {

    public static TreeTraversal PRE_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            Stack<Node> stack = new Stack<Node>();
            while (true) {
                while (root != null) {
                    System.out.print(root.getValue() + " ");

                    stack.push(root);
                    root = root.getLeft();
                }

                if (stack.isEmpty())
                    break;

                root = stack.pop();
                root = root.getRight();
            }
        }
    };

    public static TreeTraversal IN_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            Stack<Node> stack = new Stack<Node>();
            while (true) {
                while (root != null) {
                    stack.push(root);
                    root = root.getLeft();
                }

                if (stack.isEmpty())
                    break;

                root = stack.pop();

                System.out.print(root.getValue() + " ");
                root = root.getRight();
            }
        }
    };

    public static TreeTraversal POST_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            Stack<Node> stack = new Stack<Node>();
            stack.push(root);

            while (!stack.isEmpty()) {
                Node next = stack.peek();

                if (next.getRight() == root || next.getLeft() == root ||
                        (next.getRight() == null && next.getLeft() == null)) {
                    root = stack.pop();

                    System.out.print(root.getValue() + " ");
                } else {
                    if (next.getRight() != null) {
                        stack.push(next.getRight());
                    }

                    if (next.getLeft() != null) {
                        stack.push(next.getLeft());
                    }
                }
            }
        }
    };

    public static TreeTraversal BFS_ORDER = new TreeTraversal() {
        @Override
        public void traverse(Node root) {
            if (root == null)
                return;

            Queue<Node> queue = new Queue<Node>();
            queue.enqueue(root);

            while (!queue.isEmpty()) {
                Node n = queue.dequeue();

                // Print current node data
                System.out.print(n.getValue() + " ");

                if (n.getLeft() != null) {
                    queue.enqueue(n.getLeft());
                }

                if (n.getRight() != null) {
                    queue.enqueue(n.getRight());
                }
            }
        }
    };
}
