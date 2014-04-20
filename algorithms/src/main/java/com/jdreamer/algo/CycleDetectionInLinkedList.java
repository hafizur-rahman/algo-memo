package com.jdreamer.algo;

public class CycleDetectionInLinkedList {
	static class Node<T> {
		T item;
		Node next;

		public Node(T val) {
			this.item = val;
		}
	}

	static class LinkedList<T> {
		Node first;

		public void add(T val) {
			Node n = new Node(val);
			n.next = first;
			first = n;
		}

		public Node search(T val) {
			Node current = first;
			while (current != null) {
				if (current.item.equals(val)) {
					return current;
				}

				current = current.next;
			}

			return null;
		}

		public Node findLoop() {
			Node tortoise = first;
			Node hare = first;

			while (hare.next != null) {
				tortoise = tortoise.next;
				hare = hare.next.next;

				if (hare != null && hare.item.equals(tortoise.item)) {
					break;
				}
			}

			if (hare.next == null) {
				return null;
			}

			tortoise = first;
			while (!tortoise.item.equals(hare.item)) {
				tortoise = tortoise.next;
				hare = hare.next;
			}

			return hare;
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		list.add(50);
		list.add(60);

		Node tempObj1 = list.search(40);
		Node tempObj2 = list.search(10);

		tempObj2.next = tempObj1;

		Node loop = list.findLoop();
		if (loop != null) {
			System.out.println("Loop exists and starts at : " + loop.item);
		} else {
			System.out.println("there is no loop");
		}
	}
}
