
/* Syed Ali Haider
 * Project 4 - Binary Search Tree
 * 11/28/16
 */

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {

	Vector<E> vector;

	public Iterator<E> iterator() {
		vector = new Vector<E>();
		traverse(root);
		return vector.iterator();
	}

	private void traverse(Node<E> curr) {
		if (curr != null) {
			traverse(curr.left);
			vector.add(curr.data);
			traverse(curr.right);
		}
	}

	public void insert(E data) {
		Node<E> temp = new Node<E>(data);

		if (root == null) {
			root = temp;
			return;
		}

		insert(root, temp);
	}

	private void insert(Node<E> curr, Node<E> temp) {

		if (temp.data.compareTo(curr.data) >= 0 && curr.right == null) {
			curr.right = temp;
			return;
		}
		if (temp.data.compareTo(curr.data) < 0 && curr.left == null) {
			curr.left = temp;
			return;
		}

		if (temp.data.compareTo(curr.data) >= 0) {
			insert(curr.right, temp);
		}
		if (temp.data.compareTo(curr.data) < 0) {
			insert(curr.left, temp);
		}

	}

	private Node<E> findSucessor(Node<E> curr) {

		for (curr = curr.right; curr.left != null; curr = curr.left)
			;
		return curr;
	}

	public void remove(E data) {

		if (root == null) {
			return;
		}
		if (root.data.compareTo(data) == 0 && root.left == null && root.right == null) {
			root = null;
			return;
		}
		remove(data, root, root);
	}

	private void remove(E data, Node<E> curr, Node<E> parent) {

		if (curr == null)
			return;

		else if (parent.data.compareTo(curr.data) <= 0 && curr.data.compareTo(data) == 0) {

			if (curr.left == null && curr.right == null) {
				parent.right = null;
			} else if (curr.left == null) {
				if (root.data.compareTo(data) == 0 && parent.equals(curr)) {
					root = root.right;
					return;
				}
				parent.right = curr.right;
			} else if (curr.right == null) {
				if (root.data.compareTo(data) == 0 && parent.equals(curr)) {
					root = root.left;
					return;
				}
				parent.right = curr.left;
			} else {
				Node<E> sucessor = findSucessor(curr);
				curr.data = sucessor.data;
				remove(sucessor.data, curr.right, curr);
			}
			return;
		} else if (parent.data.compareTo(curr.data) > 0 && curr.data.compareTo(data) == 0) {

			if (curr.left == null && curr.right == null) {
				parent.left = null;
			} else if (curr.right == null) {
				parent.left = curr.left;
			} else if (curr.left == null) {
				parent.left = curr.right;
			} else {
				Node<E> sucessor = findSucessor(curr);
				curr.data = sucessor.data;
				remove(sucessor.data, curr.right, curr);
			}
			return;
		}
		if (data.compareTo(curr.data) < 0) {
			remove(data, curr.left, curr);
		} else if (data.compareTo(curr.data) >= 0) {
			remove(data, curr.right, curr);
		}

	}

	public boolean search(E data) {

		return search(root, data);
	}

	private boolean search(Node<E> curr, E data) {

		if (curr == null)
			return false;

		if (curr.data.compareTo(data) == 0)
			return true;

		else if (data.compareTo(curr.data) >= 0) {
			return search(curr.right, data);
		} else if (data.compareTo(curr.data) < 0) {
			return search(curr.left, data);
		}
		return false;
	}

	public static void main(String[] args) {

		BinaryTree<Integer> tree = new BinarySearchTree<Integer>();
		int rando = 2;
		Random rand = new Random(rando);
		int num = 20;
		System.out.println("INSERTION PROCESS");
		for (int i = 0; i < num; i++) {
			tree.insert(rand.nextInt(num));
			for (Integer j : tree) {
				System.out.print(j + "  ");
			}
			System.out.println();
		}

		System.out.println("REMOVAL PROCESS");
		Random rand2 = new Random(2);
		for (int i = 0; i < num; i++) {
			int x = rand2.nextInt(num);
			System.out.print(x + ": ");
			for (Integer j : tree) {
				System.out.print(j + "  ");
			}
			tree.remove(x);
			System.out.println();
		}

		System.out.println(tree.root == null);
	}

}